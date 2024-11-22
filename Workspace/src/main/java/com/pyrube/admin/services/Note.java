package com.pyrube.admin.services;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pyrube.one.app.persistence.Data;

@Entity
@Table(name = "sys_note")
public class Note extends Data<Long> {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 4868930432197229679L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Long noteId;
	@Column(name = "data_type")
	private String dataType;
	@Column(name = "data_id")
	private String dataId;
	@Column(name = "data_status")
	private String dataStatus;
	@Column(name = "event_code")
	private String eventCode;
	@Column(name = "user_from")
	private Long userFrom;
	@Transient
	private String noteFrom;
	@Column(name = "user_to")
	private Long userTo;
	@Transient
	private String noteTo;
	@Lob
	@Column(name = "content")
	private String content;
	@Column(name = "note_status")
	private String noteStatus;
	@Column(name = "note_time")
	private Date noteTime;
	@Column(name = "update_by")
	private String updateBy;
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * @return the noteId
	 */
	public Long getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
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
	 * @return the eventCode
	 */
	public String getEventCode() {
		return eventCode;
	}
	/**
	 * @param eventCode the eventCode to set
	 */
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	/**
	 * @return the userFrom
	 */
	public Long getUserFrom() {
		return userFrom;
	}
	/**
	 * @param userFrom the userFrom to set
	 */
	public void setUserFrom(Long userFrom) {
		this.userFrom = userFrom;
	}
	/**
	 * @return the noteFrom
	 */
	public String getNoteFrom() {
		return noteFrom;
	}
	/**
	 * @param noteFrom the noteFrom to set
	 */
	public void setNoteFrom(String noteFrom) {
		this.noteFrom = noteFrom;
	}
	/**
	 * @return the userTo
	 */
	public Long getUserTo() {
		return userTo;
	}
	/**
	 * @param userTo the userTo to set
	 */
	public void setUserTo(Long userTo) {
		this.userTo = userTo;
	}
	/**
	 * @return the noteTo
	 */
	public String getNoteTo() {
		return noteTo;
	}
	/**
	 * @param noteTo the noteTo to set
	 */
	public void setNoteTo(String noteTo) {
		this.noteTo = noteTo;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the noteStatus
	 */
	public String getNoteStatus() {
		return noteStatus;
	}
	/**
	 * @param noteStatus the noteStatus to set
	 */
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	/**
	 * @return the noteTime
	 */
	public Date getNoteTime() {
		return noteTime;
	}
	/**
	 * @param noteTime the noteTime to set
	 */
	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
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
