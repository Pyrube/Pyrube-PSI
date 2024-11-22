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
@Table(name = "sys_role_right")
public class RoleRight extends Data<Long> {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1470984750344042395L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_right_id")
	private Long roleRightId;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "right_id")
	private Long rightId;
	@Transient
	private String rightCode;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "create_time", updatable = false)
	private Date createTime;
	/**
	 * @return the roleRightId
	 */
	public Long getRoleRightId() {
		return roleRightId;
	}
	/**
	 * @param roleRightId the roleRightId to set
	 */
	public void setRoleRightId(Long roleRightId) {
		this.roleRightId = roleRightId;
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
	 * @return the rightId
	 */
	public Long getRightId() {
		return rightId;
	}
	/**
	 * @param rightId the rightId to set
	 */
	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}
	/**
	 * @return the rightCode
	 */
	public String getRightCode() {
		return rightCode;
	}
	/**
	 * @param rightCode the rightCode to set
	 */
	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
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
}
