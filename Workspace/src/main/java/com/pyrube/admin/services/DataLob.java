package com.pyrube.admin.services;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.pyrube.one.app.i18n.format.annotations.Converting;
import com.pyrube.one.app.i18n.format.annotations.FormatName;
import com.pyrube.one.app.persistence.Data;

@Entity
@Table(name = "sys_data_lob")
public class DataLob extends Data<Long> {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1881885393448173358L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_lob_id")
	private Long dataLobId;
	@Column(name = "data_type")
	private String dataType;
	@Column(name = "data_id")
	private String dataId;
	@Lob
	@Column(name = "data_clob")
	private String dataClob;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "data_blob")
	private byte[] dataBlob;
	@Column(name = "comments")
	private String comments;
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
	/**
	 * @return the dataLobId
	 */
	public Long getDataLobId() {
		return dataLobId;
	}
	/**
	 * @param dataLobId the dataLobId to set
	 */
	public void setDataLobId(Long dataLobId) {
		this.dataLobId = dataLobId;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}
	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	/**
	 * @return the dataClob
	 */
	public String getDataClob() {
		return dataClob;
	}
	/**
	 * @param dataClob the dataClob to set
	 */
	public void setDataClob(String dataClob) {
		this.dataClob = dataClob;
	}
	/**
	 * @return the dataBlob
	 */
	public byte[] getDataBlob() {
		return dataBlob;
	}
	/**
	 * @param dataBlob the dataBlob to set
	 */
	public void setDataBlob(byte[] dataBlob) {
		this.dataBlob = dataBlob;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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

}
