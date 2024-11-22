package com.pyrube.admin.services;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.pyrube.one.app.Apps;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class UserExtDao extends Dao<UserExt, Long> {
	
	public UserExt findId(Long userId) {
		try {
			return this.find(userId);
		} catch(Exception e) {
			throw Apps.an.exception.due("message.error.dao-exception").param(e.getMessage());
		}
	}
	
	public void updateLoginSuccessInfo(String userKey) {
		Query<?> query = this.getSession().createQuery("update UserExt set lastLoginTime = ?1, loginTimes = loginTimes + 1, "
				+ "lastAttemptTime = ?2, attemptTimes = 0, updateTime = ?3 where userId = (select userId from User where loginName = ?4)");
		int index = 1;
		query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
		query.setParameter(index++, null);
		query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
		query.setParameter(index++, userKey);
		query.executeUpdate();
	}
	
	public void increaseLoginAttempt(String userKey) {
		Query<?> query = this.getSession().createQuery("update UserExt set lastAttemptTime = ?1, attemptTimes = attemptTimes + 1, updateTime = ?2 where userId = (select userId from User where loginName = ?3)");
		int index = 1;
		query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
		query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
		query.setParameter(index++, userKey);
		query.executeUpdate();
	}
	
	public void clearLoginAttempts(String userKey) {
		Query<?> query = this.getSession().createQuery("update UserExt set lastAttemptTime = ?1, attemptTimes = 0, updateTime = ?2 where userId = (select userId from User where loginName = ?3)");
		int index = 1;
		query.setParameter(index++, Apps.a.date.NULVAL.value());
		query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
		query.setParameter(index++, userKey);
		query.executeUpdate();
	}

	public void updateUserExt(String userKey, UserExt ext) {
		Query<?> query = getSession().createQuery("update UserExt set nickName = ?1, gender = ?2, birthdate = ?3, countryCode = ?4, updateTime = ?5 where userId = (select userId from User where loginName = ?6)");
		int index = 1;
		query.setParameter(index++, ext.getNickName());
		query.setParameter(index++, ext.getGender());
		query.setParameter(index++, ext.getBirthdate());
		query.setParameter(index++, ext.getCountryCode());
		query.setParameter(index++, Apps.a.l_timestamp.in.GMT().value());
		query.setParameter(index++, userKey);
		query.executeUpdate();
	}
}
