package com.pyrube.common.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pyrube.admin.services.NoteService;
import com.pyrube.admin.services.UserService;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.context.AppContextManager;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.memo.Note;
import com.pyrube.one.app.security.SecurityManager;
import com.pyrube.one.app.user.SecurityStatus;
import com.pyrube.one.app.user.User;
import com.pyrube.one.app.user.UserExt;
import com.pyrube.one.util.Dates;

public class DaoSecurityManager implements SecurityManager {
	
	/**
	 * logger
	 */
	private static Logger logger = Logger.getInstance(DaoSecurityManager.class.getName());
	
	public DaoSecurityManager() { }

	@Override
	public void init(Map<String, ?> params) throws AppException { }

	@Override
	public User findUser(String userKey) throws AppException {
		com.pyrube.admin.services.User admUser = ((UserService) AppContextManager.findBean("userService")).findUserKey(userKey);
		if (admUser == null) return(null);
		User user = new User(String.valueOf(admUser.getUserId()), admUser.getLoginName(), admUser.getUserName());
		user.setCredentials(admUser.getPassword());
		user.setMobile(admUser.getMobile());
		user.setEmail(admUser.getEmail());
		user.setIdNum(admUser.getIdNum());
		user.setCountry(admUser.getExt().getCountryCode());
		user.setLocale(Apps.a.locale.of(admUser.getLocaleCode()).value());
		user.setTimezone(TimeZone.getTimeZone(admUser.getTimezoneId()));
		user.setLastLogonTime(admUser.getExt().getLastLoginTime());
		user.setAttemptTimes(admUser.getExt().getAttemptTimes());
		user.setLastAttemptTime(admUser.getExt().getLastAttemptTime());
		user.setAttribute(Apps.the.user.attr.name.FISCAL_YEAR, Apps.the.fiscal.year(admUser.getTimezoneId()));
		user.setAttribute(Apps.the.user.attr.name.FISCAL_QUARTER, Apps.the.fiscal.quarter(admUser.getTimezoneId()));
		if (com.pyrube.admin.services.User.STAT_ENABLED.equals(admUser.getUserStat())) {
			user.setStatus(SecurityStatus.ENABLED);
		} else if (com.pyrube.admin.services.User.STAT_PWD_INITED.equals(admUser.getUserStat())) {
			user.setStatus(SecurityStatus.PWD_INITIALIZED);
		} else if (com.pyrube.admin.services.User.STAT_PWD_EXPIRED.equals(admUser.getUserStat())) {
			user.setStatus(SecurityStatus.PWD_EXPIRED);
		} else if (com.pyrube.admin.services.User.STAT_LOCKED.equals(admUser.getUserStat())) {
			user.setStatus(SecurityStatus.LOCKED);
		} else if (com.pyrube.admin.services.User.STAT_EXPIRED.equals(admUser.getUserStat())) {
			user.setStatus(SecurityStatus.EXPIRED);
		} else {
			user.setStatus(SecurityStatus.DISABLED);
		}
		UserExt ext = new UserExt();
		ext.setNick(admUser.getExt().getNickName());
		ext.setGender(admUser.getExt().getGender() == null ? null : admUser.getExt().getGender().toString());
		ext.setCountry(admUser.getExt().getCountryCode());
		ext.setBirthdate(admUser.getExt().getBirthdate());
		user.setExt(ext);
		return(user);
	}
	
	@Override
	public HashSet<String> findUserRights(String userKey) throws AppException {
		List<String> userRightCodes = ((UserService) AppContextManager.findBean("userService")).findUserRightCodes(userKey);
		HashSet<String> rights = new HashSet<String>();
		if (userRightCodes != null && userRightCodes.size() > 0) {
			for (String rightCode : userRightCodes) rights.add(rightCode);
		}
		return rights;
	}

	@Override
	public List<Date> findHolidays(String countryCode, int year) throws AppException {
		List<Date> dates = new ArrayList<Date>();
		Date date = Dates.getFirstDayOfYear(year);
		while (Dates.getYear(date) == year) {
			if (Dates.isWeekend(date)) dates.add(date);
			date = Dates.addDays(date, 1);
		}
		return dates;
	}

	@Override
	public List<Note> findNotes(SearchCriteria<Note> searchCriteria) throws AppException {
		SearchCriteria<com.pyrube.admin.services.Note> admSearchCriteria = new SearchCriteria<com.pyrube.admin.services.Note>();
		admSearchCriteria.setSortBy(searchCriteria.getSortBy());
		admSearchCriteria.setSortDir(searchCriteria.getSortDir());
		com.pyrube.admin.services.Note admCriteria = new com.pyrube.admin.services.Note();
		admCriteria.setDataType(searchCriteria.getCriteria().getDataType());
		admCriteria.setDataId(searchCriteria.getCriteria().getDataId());
		admSearchCriteria.setCriteria(admCriteria);
		
		admSearchCriteria = ((NoteService) AppContextManager.findBean("noteService")).search(admSearchCriteria);
		List<com.pyrube.admin.services.Note> admNotes = admSearchCriteria.getResults();
		List<Note> notes = new ArrayList<Note>();
		for (com.pyrube.admin.services.Note admNote : admNotes) {
			Note note = new Note();
			note.setNoteId(String.valueOf(admNote.getNoteId()));
			note.setDataType(admNote.getDataType());
			note.setDataId(admNote.getDataId());
			note.setDataStatus(admNote.getDataStatus());
			note.setEventCode(admNote.getEventCode());
			note.setNoteFrom(admNote.getNoteFrom());
			note.setNoteTo(admNote.getNoteTo());
			note.setContent(admNote.getContent());
			note.setNoteStatus(admNote.getNoteStatus());
			note.setNoteTime(admNote.getNoteTime());
			notes.add(note);
		}
		return notes;
	}

	@Override
	public Note leaveNote(Note note) throws AppException {
		com.pyrube.admin.services.Note admNote = new com.pyrube.admin.services.Note();
		admNote.setDataType(note.getDataType());
		admNote.setDataId(note.getDataId());
		admNote.setDataStatus(note.getDataStatus());
		admNote.setEventCode(note.getEventCode());
		admNote.setContent(note.getContent());
		admNote = ((NoteService) AppContextManager.findBean("noteService")).leave(admNote);

		note.setNoteId(String.valueOf(admNote.getNoteId()));
		note.setNoteFrom(admNote.getNoteFrom());
		note.setNoteTo(admNote.getNoteTo());
		note.setNoteStatus(admNote.getNoteStatus());
		note.setNoteTime(admNote.getNoteTime());
		return note;
	}
	
	@Override
	public void afterSignon(User user, Map<String, Object> moreProps) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("After user - " + user.loginame() + " signs on.");
		((UserService) AppContextManager.findBean("userService")).logSignonSuccessEvent(user.loginame());
	}
	
	@Override
	public void failedSignon(String userKey, SecurityStatus failedStatus) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("After user - " + userKey + " failed signing on...");
		((UserService) AppContextManager.findBean("userService")).logSignonFailureEvent(userKey, failedStatus);
	}

	@Override
	public User updateUserDetails(User details) throws AppException {
		com.pyrube.admin.services.User admUser = ((UserService) AppContextManager.findBean("userService")).findUserKey(details.loginame());
		if (admUser == null)
			throw new UsernameNotFoundException("User is not found.");
		admUser.setLocaleCode(details.locale().toString());
		com.pyrube.admin.services.UserExt admUserExt = admUser.getExt();
		admUserExt.setNickName(details.getExt().getNick());
		admUserExt.setGender((details.getExt().getGender() == null) ? null
				: Integer.valueOf(Integer.parseInt(details.getExt().getGender())));
		admUserExt.setBirthdate(details.getExt().getBirthdate());
		admUserExt.setCountryCode(details.getExt().getCountry());
		((UserService) AppContextManager.findBean("userService")).updateUserDetails(details.loginame(), admUser);
		return details;
	}

	@Override
	public User changePassword(User user, String password) throws AppException {
		String encPassword = ((UserService) AppContextManager.findBean("userService")).updatePassword(user.loginame(), password);
		user.setCredentials(encPassword);
		return user;
	}

	@Override
	public User changeMobile(User user, String mobile) throws AppException {
		((UserService) AppContextManager.findBean("userService")).updateMobile(user.loginame(), mobile);
		user.setMobile(mobile);
		return user;
	}

	@Override
	public User changeEmail(User user, String email) throws AppException {
		((UserService) AppContextManager.findBean("userService")).updateEmail(user.loginame(), email);
		user.setEmail(email);
		return user;
	}

}
