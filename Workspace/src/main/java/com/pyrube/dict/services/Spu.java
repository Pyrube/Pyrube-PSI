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
@Table(name = "dat_spu")
public class Spu extends Data<Long> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 5529800914515758767L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spu_id")
	private Long spuId;
	@Column(name = "spu_name")
	private String spuName;
	@Column(name = "create_by", updatable = false)
	private String createBy;
	@Converting(format = FormatName.LONGTIMESTAMPZ, local = true)
	@Column(name = "create_time", updatable = false)
	private Date createTime;
	/**
	 * returns the ID of this Spu
	 */
	@Override
	public Long getId() {
		return(spuId);
	}
	/**
	 * @return the spuId
	 */
	public Long getSpuId() {
		return spuId;
	}
	/**
	 * @param spuId the spuId to set
	 */
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	/**
	 * @return the spuName
	 */
	public String getSpuName() {
		return spuName;
	}
	/**
	 * @param spuName the spuName to set
	 */
	public void setSpuName(String spuName) {
		this.spuName = spuName;
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
		return "Spu [spuId=" + spuId + ", spuName=" + spuName + "]";
	}

}
