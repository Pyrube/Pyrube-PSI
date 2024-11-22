package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.task.RunMessage;
import com.pyrube.one.app.task.RunResult;
import com.pyrube.one.app.user.SecurityStatus;
import com.pyrube.one.lang.Strings;
import com.pyrube.one.util.crypto.PwdEncryptor;

@Service
@Transactional
public class UserService {
	
	private static final String DATA_TYPE = "USER";

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserExtDao userExtDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RoleRightDao roleRightDao;
	@Autowired
	private DataLobService dataLobService;

	public boolean ifLoginNameUnique(String loginName) throws AppException {
		User user = userDao.findLoginName(loginName);
		return (user == null);
	}

	public boolean ifMobileUnique(String mobile) throws AppException {
		User user = userDao.findMobile(mobile);
		return (user == null);
	}

	public boolean ifEmailUnique(String email) throws AppException {
		User user = userDao.findEmail(email);
		return (user == null);
	}

	public User findUserKey(String userKey) throws AppException {
		// for now, just only login name supported
		User user = userDao.findLoginName(userKey);
		if (user != null) {
			UserExt ext = userExtDao.findId(user.getUserId());
			user.setExt(ext);
		}
		return user;
	}

	public List<String> findUserRightCodes(String userKey) throws AppException {
		User user = findUserKey(userKey);
		List<UserRole> userRoles = userRoleDao.query(user.getUserId());
		List<String> userRightCodes = new ArrayList<String>();
		if (userRoles != null && userRoles.size() > 0) {
			for (UserRole userRole : userRoles) {
				List<RoleRight> roleRights = roleRightDao.query(userRole.getRoleId());
				if (roleRights != null && roleRights.size() > 0) {
					for (RoleRight roleRight : roleRights)
						userRightCodes.add(roleRight.getRightCode());
				}
			}
		}
		return userRightCodes;
	}

	public void logSignonSuccessEvent(String userKey) throws AppException {
		User user = findUserKey(userKey);
		if (User.STAT_LOCKED.equals(user.getUserStat())) {
			// when policy supports auto-unlocking
			userDao.updateUserStat(userKey, User.STAT_ENABLED);
		}
		userExtDao.updateLoginSuccessInfo(userKey);
	}

	public void logSignonFailureEvent(String userKey, SecurityStatus failedStatus) throws AppException {
		if (SecurityStatus.INVALID_CREDENTIAL.equals(failedStatus)) {
			userExtDao.increaseLoginAttempt(userKey);
		} else if (SecurityStatus.TOO_MANY_TRIES.equals(failedStatus)) {
			User user = findUserKey(userKey);
			if (User.STAT_ENABLED.equals(user.getUserStat())) {
				// lock the user if it is enabled
				userDao.updateUserStat(userKey, User.STAT_LOCKED);
			}
			userExtDao.increaseLoginAttempt(userKey);
		}
	}

	public User updateUserDetails(String userKey, User details) throws AppException {
		this.userDao.updateUser(userKey, details);
		this.userExtDao.updateUserExt(userKey, details.getExt());
		return details;
	}

	public String updatePassword(String userKey, String password) throws AppException {
		String encPassword = PwdEncryptor.encrypt(password);
		User user = findUserKey(userKey);
		Date pwdExpiryDate = Apps.the.sys_default.pass_policy().decides.exp_date(user.getTimezoneId()).value();
		userDao.updatePassword(userKey, encPassword, pwdExpiryDate);
		return encPassword;
	}

	public void updateMobile(String userKey, String mobile) throws AppException {
		userDao.updateMobile(userKey, mobile);
	}

	public void updateEmail(String userKey, String email) throws AppException {
		userDao.updateEmail(userKey, email);
	}
	
	public RunResult expirePasswords(String timezoneId) throws AppException {
		RunResult rr = null;
		List<User> users = null;
		try {
			users = userDao.searchPasswordExpirying(timezoneId);
		} catch (Exception e) {
			rr = new RunResult(RunResult.STATUS_ERROR, RunResult.NEXTSTEP_CONTINUE);
			rr.setMessage(new RunMessage("user.error.users_inquiry").withErrors());
			return rr;
		}
		if (users == null || users.size() == 0) {
			rr = new RunResult(RunResult.STATUS_FINISHED, RunResult.NEXTSTEP_CONTINUE);
			rr.setMessage(new RunMessage("user.info.no-user-pass-expire"));
			return rr;
		};
		long totalItems = users.size();
		long finishedItems = 0;
		long errorItems = 0;
		for (User user : users) {
			try {
				userDao.updateUserStat(user.getLoginName(), User.STAT_PWD_EXPIRED);
				finishedItems++;
			} catch (Exception e) {
				errorItems++;
			}
		}
		if (errorItems > 0) {
			rr = new RunResult(RunResult.STATUS_ERROR, RunResult.NEXTSTEP_CONTINUE);
		} else {
			rr = new RunResult(RunResult.STATUS_FINISHED, RunResult.NEXTSTEP_CONTINUE);
		}
		rr.setMessage(new RunMessage("user.info.users-pass-expire", totalItems, finishedItems, errorItems));
		return rr;
	}
	
	public RunResult clearAttempts(String timezoneId) throws AppException {
		RunResult rr = null;
		List<User> users = null;
		try {
			users = userDao.searchAttemptsClearing(timezoneId);
		} catch (Exception e) {
			rr = new RunResult(RunResult.STATUS_ERROR, RunResult.NEXTSTEP_CONTINUE);
			rr.setMessage(new RunMessage("user.error.users_inquiry").withErrors());
			return rr;
		}
		if (users == null || users.size() == 0) {
			rr = new RunResult(RunResult.STATUS_FINISHED, RunResult.NEXTSTEP_CONTINUE);
			rr.setMessage(new RunMessage("user.info.no-user-attempts-clear"));
			return rr;
		};
		long totalItems = users.size();
		long finishedItems = 0;
		long errorItems = 0;
		for (User user : users) {
			try {
				userExtDao.clearLoginAttempts(user.getLoginName());
				finishedItems++;
			} catch (Exception e) {
				errorItems++;
			}
		}
		if (errorItems > 0) {
			rr = new RunResult(RunResult.STATUS_ERROR, RunResult.NEXTSTEP_CONTINUE);
		} else {
			rr = new RunResult(RunResult.STATUS_FINISHED, RunResult.NEXTSTEP_CONTINUE);
		}
		rr.setMessage(new RunMessage("user.info.users-attempts-clear", totalItems, finishedItems, errorItems));
		return rr;
	}
	
	@SuppressWarnings("unchecked")
	public SearchCriteria<User> search(SearchCriteria<User> searchCriteria) throws AppException {
		searchCriteria = userDao.search(searchCriteria);
		searchCriteria = (SearchCriteria<User>) dataLobService.commentsOn(DATA_TYPE, searchCriteria);
		return searchCriteria;
	}

	public User findId(Long userId) throws AppException {
		User user = userDao.findId(userId);
		if (user == null) return null;
		UserExt ext = userExtDao.findId(userId);
		user.setExt(ext);
		List<UserRole> roles = userRoleDao.query(userId);
		user.setRoles(roles);
		return user;
	}
	
	public User findRejectedId(Long userId) throws AppException {
		return (User) dataLobService.objectize(DATA_TYPE, userId);
	}

	public User findPendingId(Long userId) throws AppException {
		return (User) dataLobService.objectize(DATA_TYPE, userId);
	}

	public User createAbort(User user) throws AppException {
		user = (User) dataLobService.objectize(DATA_TYPE, user.getUserId());
		userDao.deleteById(user.getUserId());
		dataLobService.finalize(DATA_TYPE, user.getUserId());
		return user;
	}

	public User createSubmit(User user) throws AppException {
		boolean setupDualcontrol = Apps.setup.is.dualcontrol();
		String userName = isFFLL(Apps.the.user().timezone().getID()) 
				? combineName(user.getFirstName(), user.getLastName())
						: combineName(user.getLastName(), user.getFirstName());
		user.setUserName(userName);
		user.setPassword(Apps.the.sys_default.pass_policy().generates());
		user.setPwdExpiryDate(Apps.the.sys_default.pass_policy().decides.exp_date().value());
		user.setUserStat(User.STAT_PWD_INITED);
		user.setTimezoneId(Apps.the.user().timezone().getID());
		user.setCreateBy(Apps.the.user.loginame());
		user.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		if (setupDualcontrol) user.setDataStatus(Apps.setup.stat.CREATED_PENDING);
		else user.setDataStatus(Apps.setup.stat.VERIFIED);
		userDao.saveOrUpdate(user);
		if (setupDualcontrol) {
			dataLobService.serialize(DATA_TYPE, user.getUserId(), user);
		} else {
			this.createSubdata(user);
		}
		return user;
	}

	public User createReject(User user) throws AppException {
		String comments = user.getComments();
		user = (User) dataLobService.objectize(DATA_TYPE, user.getUserId());
		user.setComments(comments);
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setVerifyBy(Apps.the.user.loginame());
		user.setVerifyTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.CREATED_REJECTED);
		userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
		dataLobService.serialize(DATA_TYPE, user.getUserId(), user);
		Apps.a.note(comments)
			.of(DATA_TYPE, user.getUserId(), user.getDataStatus())
			.in.event(Apps.event.setup.CREATED_REJECTED)
			.to.leave();
		return user;
	}

	public User createApprove(User user) throws AppException {
		user = (User) dataLobService.objectize(DATA_TYPE, user.getUserId());
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setVerifyBy(Apps.the.user.loginame());
		user.setVerifyTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.VERIFIED);
		userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
		this.createSubdata(user);
		dataLobService.finalize(DATA_TYPE, user.getUserId());
		return user;
	}

	public User updateAbort(User user) throws AppException {
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.VERIFIED);
		userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
		dataLobService.finalize(DATA_TYPE, user.getUserId());
		return user;
	}

	public User updateSubmit(User user) throws AppException {
		boolean setupDualcontrol = Apps.setup.is.dualcontrol();
		String userName = isFFLL(Apps.the.user().timezone().getID()) 
				? combineName(user.getFirstName(), user.getLastName())
						: combineName(user.getLastName(), user.getFirstName());
		user.setUserName(userName);
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		if (setupDualcontrol) user.setDataStatus(Apps.setup.stat.UPDATED_PENDING);
		else user.setDataStatus(Apps.setup.stat.VERIFIED);
		if (setupDualcontrol) {
			userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
			dataLobService.serialize(DATA_TYPE, user.getUserId(), user);
		} else {
			userDao.saveOrUpdate(user);
			this.updateSubdata(user);
		}
		return user;
	}

	public User updateReject(User user) throws AppException {
		String comments = user.getComments();
		user = (User) dataLobService.objectize(DATA_TYPE, user.getUserId());
		user.setComments(comments);
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setVerifyBy(Apps.the.user.loginame());
		user.setVerifyTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.UPDATED_REJECTED);
		userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
		dataLobService.serialize(DATA_TYPE, user.getUserId(), user);
		Apps.a.note(comments)
			.of(DATA_TYPE, user.getUserId(), user.getDataStatus())
			.in.event(Apps.event.setup.UPDATED_REJECTED)
			.to.leave();
		return user;
	}

	public User updateApprove(User user) throws AppException {
		user = (User) dataLobService.objectize(DATA_TYPE, user.getUserId());
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setVerifyBy(Apps.the.user.loginame());
		user.setVerifyTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.VERIFIED);
		userDao.saveOrUpdate(user);
		this.updateSubdata(user);
		dataLobService.finalize(DATA_TYPE, user.getUserId());
		return user;
	}

	public User deleteAbort(User user) throws AppException {
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.VERIFIED);
		userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
		dataLobService.finalize(DATA_TYPE, user.getUserId());
		return user;
	}

	public User deleteSubmit(User user) throws AppException {
		boolean setupDualcontrol = Apps.setup.is.dualcontrol();
		user = this.findId(user.getUserId());
		if (setupDualcontrol) {
			user.setUpdateBy(Apps.the.user.loginame());
			user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
			user.setDataStatus(Apps.setup.stat.DELETED_PENDING);
			userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
			dataLobService.serialize(DATA_TYPE, user.getUserId(), user);
		} else {
			this.deleteSubdata(user);
			userDao.deleteById(user.getUserId());
		}
		return user;
	}

	public User deleteReject(User user) throws AppException {
		String comments = user.getComments();
		user = (User) dataLobService.objectize(DATA_TYPE, user.getUserId());
		user.setComments(comments);
		user.setUpdateBy(Apps.the.user.loginame());
		user.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		user.setVerifyBy(Apps.the.user.loginame());
		user.setVerifyTime(Apps.a.l_timestamp.in.GMT().value());
		user.setDataStatus(Apps.setup.stat.DELETED_REJECTED);
		userDao.updateDataStatus(user.getUserId(), user.getUpdateTime(), user.getDataStatus());
		dataLobService.serialize(DATA_TYPE, user.getUserId(), user);
		Apps.a.note(comments)
			.of(DATA_TYPE, user.getUserId(), user.getDataStatus())
			.in.event(Apps.event.setup.DELETED_REJECTED)
			.to.leave();
		return user;
	}

	public User deleteApprove(User user) throws AppException {
		this.deleteSubdata(user);
		userDao.deleteById(user.getUserId());
		dataLobService.finalize(DATA_TYPE, user.getUserId());
		return user;
	}
	
	private void createSubdata(User user) throws AppException {
		UserExt userExt = user.getExt();
		userExt.setUserId(user.getUserId());
		userExt.setRegisterTime(Apps.a.l_timestamp.in.GMT().value());
		userExt.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		userExtDao.save(userExt);
		List<UserRole> userRoles = user.getRoles();
		for (UserRole userRole : userRoles) {
			userRole.setUserId(user.getUserId());
			userRole.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
			userRoleDao.save(userRole);
		}
	}
	
	private void updateSubdata(User user) throws AppException {
		UserExt userExt = user.getExt();
		userExt.setUserId(user.getUserId());
		userExt.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		userExtDao.saveOrUpdate(userExt);
		List<UserRole> userRoles = user.getRoles();
		for (UserRole userRole : userRoles) {
			if (Apps.constants.stat.ADDED.equals(userRole.getDataStatus())) {
				userRole.setUserId(user.getUserId());
				userRole.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
				userRoleDao.save(userRole);
			} else if (Apps.constants.stat.REMOVED.equals(userRole.getDataStatus())) {
				userRoleDao.deleteById(userRole.getUserRoleId());
			}
		}
	}
	
	private void deleteSubdata(User user) {
		userExtDao.deleteById(user.getUserId());
		List<UserRole> userRoles = userRoleDao.query(user.getUserId());
		userRoleDao.deleteAll(userRoles);
	}
	
	private String combineName(String firstName, String lastName) {
		String userName = null;
		if (!Strings.isEmpty(firstName)) userName = firstName;
		if (!Strings.isEmpty(lastName)) userName = (userName == null ? Strings.EMPTY : userName + " ") + lastName;
		return userName;
	}
	
	private boolean isFFLL(String timezoneId) {
		return !("Asia/Shanghai".equals(timezoneId));
	}
}
