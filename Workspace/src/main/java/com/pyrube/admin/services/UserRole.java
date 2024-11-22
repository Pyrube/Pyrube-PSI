package com.pyrube.admin.services;

import java.util.Date;

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
@Table(name = "adm_user_role")
public class UserRole extends Data<Long> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private Long userRoleId;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "role_id")
	private Long roleId;
	@Transient
	private String roleCode;
	@Transient
	private String roleName;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "create_time")
	private Date createTime;
	@Transient
	private String dataStatus;
	/**
	 * @return the userRoleId
	 */
	public Long getUserRoleId() {
		return userRoleId;
	}
	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

}
