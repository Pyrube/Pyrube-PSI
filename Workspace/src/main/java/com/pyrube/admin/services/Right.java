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
@Table(name = "sys_right")
public class Right extends Data<Long> {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 7959339399387110961L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "right_id")
	private Long rightId;
	@Column(name = "right_code")
	private String rightCode;
	@Column(name = "right_name")
	private String rightName;
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
	@Transient
	private Long roleId;
	@Transient
	private String roleCode;
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
	 * @return the rightName
	 */
	public String getRightName() {
		return rightName;
	}
	/**
	 * @param rightName the rightName to set
	 */
	public void setRightName(String rightName) {
		this.rightName = rightName;
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
}
