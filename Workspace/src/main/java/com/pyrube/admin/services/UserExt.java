package com.pyrube.admin.services;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pyrube.one.app.i18n.format.annotations.Converting;
import com.pyrube.one.app.i18n.format.annotations.FormatName;
import com.pyrube.one.app.persistence.Data;

@Entity  
@Table(name = "adm_user_ext")
public class UserExt extends Data<Long> {
	
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 279687235880540115L;
	@Id  
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "nick_name")
	private String nickName;
	@Column(name = "name_spell")
	private String nameSpell;
	@Column(name = "gender")
	private Integer gender;
	@Column(name = "birthday")
	private String birthday;
	@Converting(format = FormatName.DATE)
	@Column(name = "birthdate")
	private Date birthdate;
	@Column(name = "country_code")
	private String countryCode = "CN";
	@Column(name = "msg_accepted")
	private Integer msgAccepted = 1;
	@Column(name = "regulation_accepted")
	private Integer regulationAccepted = 1;
	@Column(name = "register_src", updatable = false)
	private String registerSrc = "WEB";
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "register_time", updatable = false)
	private Date registerTime;
	@Column(name = "user_points", updatable = false)
	private Integer userPoints = 0;
	@Column(name = "visited_times", updatable = false)
	private Integer visitedTimes = 0;
	@Column(name = "user_level", updatable = false)
	private Integer userLevel = 10;
	@Column(name = "login_times", updatable = false)
	private Integer loginTimes = 0;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "last_login_time", updatable = false)
	private Date lastLoginTime;
	@Column(name = "attempt_times", updatable = false)
	private Integer attemptTimes = 0;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "last_attempt_time", updatable = false)
	private Date lastAttemptTime;
	@Column(name = "binding_flag")
	private Integer bindingFlag = 0;
	@Column(name = "trusted_ips")
	private String trustedIps;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "update_time")
	private Date updateTime;
	
	public UserExt() {
		super();
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the nameSpell
	 */
	public String getNameSpell() {
		return nameSpell;
	}

	/**
	 * @param nameSpell the nameSpell to set
	 */
	public void setNameSpell(String nameSpell) {
		this.nameSpell = nameSpell;
	}

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the msgAccepted
	 */
	public Integer getMsgAccepted() {
		return msgAccepted;
	}

	/**
	 * @param msgAccepted the msgAccepted to set
	 */
	public void setMsgAccepted(Integer msgAccepted) {
		this.msgAccepted = msgAccepted;
	}

	/**
	 * @return the regulationAccepted
	 */
	public Integer getRegulationAccepted() {
		return regulationAccepted;
	}

	/**
	 * @param regulationAccepted the regulationAccepted to set
	 */
	public void setRegulationAccepted(Integer regulationAccepted) {
		this.regulationAccepted = regulationAccepted;
	}

	/**
	 * @return the registerSrc
	 */
	public String getRegisterSrc() {
		return registerSrc;
	}

	/**
	 * @param registerSrc the registerSrc to set
	 */
	public void setRegisterSrc(String registerSrc) {
		this.registerSrc = registerSrc;
	}

	/**
	 * @return the registerTime
	 */
	public Date getRegisterTime() {
		return registerTime;
	}

	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * @return the userPoints
	 */
	public Integer getUserPoints() {
		return userPoints;
	}

	/**
	 * @param userPoints the userPoints to set
	 */
	public void setUserPoints(Integer userPoints) {
		this.userPoints = userPoints;
	}

	/**
	 * @return the visitedTimes
	 */
	public Integer getVisitedTimes() {
		return visitedTimes;
	}

	/**
	 * @param visitedTimes the visitedTimes to set
	 */
	public void setVisitedTimes(Integer visitedTimes) {
		this.visitedTimes = visitedTimes;
	}

	/**
	 * @return the userLevel
	 */
	public Integer getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * @return the loginTimes
	 */
	public Integer getLoginTimes() {
		return loginTimes;
	}

	/**
	 * @param loginTimes the loginTimes to set
	 */
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the attemptTimes
	 */
	public Integer getAttemptTimes() {
		return attemptTimes;
	}

	/**
	 * @param attemptTimes the attemptTimes to set
	 */
	public void setAttemptTimes(Integer attemptTimes) {
		this.attemptTimes = attemptTimes;
	}

	/**
	 * @return the lastAttemptTime
	 */
	public Date getLastAttemptTime() {
		return lastAttemptTime;
	}

	/**
	 * @param lastAttemptTime the lastAttemptTime to set
	 */
	public void setLastAttemptTime(Date lastAttemptTime) {
		this.lastAttemptTime = lastAttemptTime;
	}

	/**
	 * @return the bindingFlag
	 */
	public Integer getBindingFlag() {
		return bindingFlag;
	}

	/**
	 * @param bindingFlag the bindingFlag to set
	 */
	public void setBindingFlag(Integer bindingFlag) {
		this.bindingFlag = bindingFlag;
	}

	/**
	 * @return the trustedIps
	 */
	public String getTrustedIps() {
		return trustedIps;
	}

	/**
	 * @param trustedIps the trustedIps to set
	 */
	public void setTrustedIps(String trustedIps) {
		this.trustedIps = trustedIps;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserExt [userId=" + userId + ", nickName=" + nickName 
				+ ", nameSpell=" + nameSpell + ", gender=" + gender 
				+ ", updateTime=" + updateTime + "]";
	}

}
