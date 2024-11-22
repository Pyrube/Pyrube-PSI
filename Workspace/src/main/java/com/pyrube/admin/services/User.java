package com.pyrube.admin.services;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pyrube.one.app.i18n.format.annotations.Converting;
import com.pyrube.one.app.i18n.format.annotations.FormatName;
import com.pyrube.one.app.persistence.Data;

@Entity
@Table(name = "adm_user")
public class User extends Data<Long> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * constants for user
	 */
	public static final String STAT_ENABLED     = "E";
	public static final String STAT_LOCKED      = "L";
	public static final String STAT_PWD_INITED  = "I";
	public static final String STAT_PWD_EXPIRED = "P";
	public static final String STAT_EXPIRED     = "X";
	public static final String STAT_DISABLED    = "D";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "login_name", updatable = false)
	private String loginName;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "password", updatable = false)
	private String password;
	@Converting(format = FormatName.DATE)
	@Column(name = "pwd_expiry_date", updatable = false)
	private Date pwdExpiryDate;
	@Transient
	private String password1;
	@Transient
	private String password2;
	@Column(name = "mobile", updatable = false)
	private String mobile;
	@Column(name = "email", updatable = false)
	private String email;
	@Column(name = "id_num")
	private String idNum;
	@Column(name = "locale_code")
	private String localeCode;
	@Column(name = "timezone_id", updatable = false)
	private String timezoneId;
	@Column(name = "user_type", updatable = false)
	private Integer userType;
	@Column(name = "user_stat", updatable = false)
	private String userStat;
	@Column(name = "logo_url", updatable = false)
	private String logoUrl;
	@Column(name = "head_url", updatable = false)
	private String headUrl;
	@Column(name = "create_by", updatable = false)
	private String createBy;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "create_time", updatable = false)
	private Date createTime;
	@Column(name = "update_by")
	private String updateBy;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "verify_by")
	private String verifyBy;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "verify_time")
	private Date verifyTime;
	@Column(name = "data_status")
	private String dataStatus;
	@Transient
	private UserExt ext;
	@Transient
	private List<UserRole> roles;
	
	/**
	 * returns the ID of this User
	 */
	@Override
	public Long getId() {
		return userId;
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
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the pwdExpiryDate
	 */
	public Date getPwdExpiryDate() {
		return pwdExpiryDate;
	}

	/**
	 * @param pwdExpiryDate the pwdExpiryDate to set
	 */
	public void setPwdExpiryDate(Date pwdExpiryDate) {
		this.pwdExpiryDate = pwdExpiryDate;
	}

	/**
	 * @return the password1
	 */
	public String getPassword1() {
		return password1;
	}

	/**
	 * @param password1 the password1 to set
	 */
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	/**
	 * @return the password2
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 the password2 to set
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the idNum
	 */
	public String getIdNum() {
		return idNum;
	}

	/**
	 * @param idNum the idNum to set
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * @return the localeCode
	 */
	public String getLocaleCode() {
		return localeCode;
	}

	/**
	 * @param localeCode the localeCode to set
	 */
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	/**
	 * @return the timezoneId
	 */
	public String getTimezoneId() {
		return timezoneId;
	}

	/**
	 * @param timezoneId the timezoneId to set
	 */
	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}

	/**
	 * @return the userType
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return the userStat
	 */
	public String getUserStat() {
		return userStat;
	}

	/**
	 * @param userStat the userStat to set
	 */
	public void setUserStat(String userStat) {
		this.userStat = userStat;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the headUrl
	 */
	public String getHeadUrl() {
		return headUrl;
	}

	/**
	 * @param headUrl the headUrl to set
	 */
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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

	/**
	 * @return the verifyBy
	 */
	public String getVerifyBy() {
		return verifyBy;
	}

	/**
	 * @param verifyBy the verifyBy to set
	 */
	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}

	/**
	 * @return the verifyTime
	 */
	public Date getVerifyTime() {
		return verifyTime;
	}

	/**
	 * @param verifyTime the verifyTime to set
	 */
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	/**
	 * @return the dataStatus
	 */
	public String getDataStatus() {
		return dataStatus;
	}

	/**
	 * @param dataStatus the dataStatus to set
	 */
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	/**
	 * @return the ext
	 */
	public UserExt getExt() {
		return ext;
	}

	/**
	 * @param ext the ext to set
	 */
	public void setExt(UserExt ext) {
		this.ext = ext;
	}

	/**
	 * @return the roles
	 */
	public List<UserRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginName=" + loginName 
				+ ", userName=" + userName + ", email=" + email 
				+ ", mobile=" + mobile + ", idNum=" + idNum
				+ ", dataStatus=" + dataStatus+ "]";
	}

}