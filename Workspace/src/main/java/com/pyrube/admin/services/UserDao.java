package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.persistence.Dao;
import com.pyrube.one.lang.Strings;
import com.pyrube.one.util.Dates;

@Repository
public class UserDao extends Dao<User, Long> {
	
	/**
	 * logger
	 */
	private static Logger logger = Logger.getInstance(UserDao.class.getName());

	public List<User> searchPasswordExpirying(String timezoneId) {
		String script = "from User where timezoneId = :timezoneId and userStat = :userStat and pwdExpiryDate <= :pwdExpiryDate";
		SearchCriteria<User> searchCriteria = new SearchCriteria<User>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timezoneId", timezoneId);
		params.put("userStat", User.STAT_ENABLED);
		params.put("pwdExpiryDate", Apps.a.date.of.today(timezoneId));
		searchCriteria.setSortBy("loginName");
		try {
			searchCriteria = this.query(script, searchCriteria, params);
			return searchCriteria.getResults();
		} catch (Exception e) {
			throw Apps.an.exception.due("message.error.dao-exception").param(e.getMessage());
		}
	}

	public List<User> searchAttemptsClearing(String timezoneId) {
		String script = "from User u left join UserExt ext on (u.userId = ext.userId) where u.timezoneId = :timezoneId and u.userStat = :userStat and ext.attemptTimes > :attemptTimes";
		SearchCriteria<User> searchCriteria = new SearchCriteria<User>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timezoneId", timezoneId);
		params.put("userStat", User.STAT_ENABLED);
		params.put("attemptTimes", 0);
		searchCriteria.setAlias("u");
		searchCriteria.setSortBy("loginName");
		
		List<User> users = null;
		try {
			searchCriteria = this.query(script, searchCriteria, params);
			List<?> list = searchCriteria.getResults();
			if (list != null) {
				users = new ArrayList<User>();
				for (int i = 0; i < list.size(); i++) {
					User user = (User) ((Object[])list.get(i))[0];
					users.add(user);
				}
			}
			return users;
		} catch (Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
	
	public SearchCriteria<User> search(SearchCriteria<User> searchCriteria) {
		User criteria = searchCriteria.getCriteria();
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuffer script = new StringBuffer("from User u left join UserExt ext on (u.userId = ext.userId) where u.userId > 0 and u.timezoneId = :timezoneId");
		params.put("timezoneId", Apps.the.user.timezone().getID());
		String loginName = criteria.getLoginName();
		if (!Strings.isEmpty(loginName)) {
			script.append(" and u.loginName like :loginName");
			params.put("loginName", "%" + loginName + "%");
		}
		String userName = criteria.getUserName();
		if (!Strings.isEmpty(userName)) {
			script.append(" and u.userName like :userName");
			params.put("userName", "%" + userName + "%");
		}
		searchCriteria.setAlias("u");
		searchCriteria.setAltSortBy("loginName");
		
		List<User> users = null;
		try {
			searchCriteria = this.query(script.toString(), searchCriteria, params);
			List<?> list = searchCriteria.getResults();
			if (list != null) {
				users = new ArrayList<User>();
				for (int i = 0; i < list.size(); i++) {
					User user = (User) ((Object[])list.get(i))[0];
					UserExt ext = (UserExt) ((Object[])list.get(i))[1];
					user.setExt(ext);
					users.add(user);
				}
			}
			searchCriteria.setResults(users);
			return(searchCriteria);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public User findId(Long userId) {
		String script = "from User where userId = ? and timezoneId = ?";
		try {
			return this.find(script, userId, Apps.the.user.timezone().getID());
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
	
	public User findLoginName(String loginName) {
		String script = "from User where loginName = ?";
		try {
			return this.find(script, loginName);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public User findMobile(String mobile) {
		String script = "from User where mobile = ?";
		try {
			return this.find(script, mobile);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public User findEmail(String email) {
		String script = "from User where email = ?";
		try {
			return this.find(script, email);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public void updateDataStatus(Long userId, Date updateTime, String dataStatus) {
		try {
			Query<?> query = this.getSession().createQuery("update User u set u.updateTime = ?1, u.dataStatus = ?2 where u.userId = ?3");
			int index = 1;
			query.setParameter(index++, updateTime);
			query.setParameter(index++, dataStatus);
			query.setParameter(index++, userId);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}

	public void updateUser(String userKey, User details) {
		try {
			Query<?> query = getSession().createQuery("update User u set u.localeCode = ?1, u.updateTime = ?2 where u.loginName = ?3");
			int index = 1;
			query.setParameter(index++, details.getLocaleCode());
			query.setParameter(index++, Dates.getGmtLongTimestamp());
			query.setParameter(index++, userKey);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}

	public void updatePassword(String userKey, String encPassword, Date pwdExpiryDate) {
		try {
			Query<?> query = this.getSession().createQuery("update User u set u.password = ?1, u.pwdExpiryDate = ?2, u.userStat = ?3, u.updateTime = ?4 where u.loginName = ?5");
			int index = 1;
			query.setParameter(index++, encPassword);
			query.setParameter(index++, pwdExpiryDate);
			query.setParameter(index++, User.STAT_ENABLED);
			query.setParameter(index++, Dates.getGmtLongTimestamp());
			query.setParameter(index++, userKey);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public void updateMobile(String userKey, String mobile) {
		try {
			Query<?> query = this.getSession().createQuery("update User u set u.mobile = ?1, u.updateTime = ?2 where u.loginName = ?3");
			int index = 1;
			query.setParameter(index++, mobile);
			query.setParameter(index++, Dates.getGmtLongTimestamp());
			query.setParameter(index++, userKey);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public void updateEmail(String userKey, String email) {
		try {	
			Query<?> query = this.getSession().createQuery("update User u set u.email = ?1, u.updateTime = ?2 where u.loginName = ?3");
			int index = 1;
			query.setParameter(index++, email);
			query.setParameter(index++, Dates.getGmtLongTimestamp());
			query.setParameter(index++, userKey);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public void updateUserStat(String userKey, String userStat) {
		try {	
			Query<?> query = this.getSession().createQuery("update User u set u.userStat = ?1, u.updateTime = ?2 where u.loginName = ?3");
			int index = 1;
			query.setParameter(index++, userStat);
			query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
			query.setParameter(index++, userKey);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}

}
