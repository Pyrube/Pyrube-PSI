package com.pyrube.dict.services;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pyrube.one.app.i18n.format.annotations.Converting;
import com.pyrube.one.app.i18n.format.annotations.FormatName;
import com.pyrube.one.app.persistence.Data;

@Entity
@Table(name = "dat_channel")
public class Channel extends Data<Long> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 2410564452887895025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "channel_id")
	private Long channelId;
	@Column(name = "channel_code")
	private String channelCode;
	@Column(name = "channel_name")
	private String channelName;
	@Column(name = "create_by", updatable = false)
	private String createBy;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "create_time", updatable = false)
	private Date createTime;
	/**
	 * returns the ID of this Channel
	 */
	@Override
	public Long getId() {
		return(channelId);
	}
	/**
	 * @return the channelId
	 */
	public Long getChannelId() {
		return channelId;
	}
	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	/**
	 * @return the channelCode
	 */
	public String getChannelCode() {
		return channelCode;
	}
	/**
	 * @param channelCode the channelCode to set
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}
	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", channelCode=" + channelCode + "]";
	}

}
