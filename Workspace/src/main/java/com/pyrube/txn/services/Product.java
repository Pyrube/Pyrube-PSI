package com.pyrube.txn.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pyrube.one.app.i18n.format.annotations.Converting;
import com.pyrube.one.app.i18n.format.annotations.FormatName;
import com.pyrube.one.app.persistence.Data;

@Entity
@Table(name = "psi_product")
public class Product extends Data<String> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 2417237190296754260L;

	@Id
	@Column(name = "prod_no")
	private String prodNo;
	@Column(name = "prod_name")
	private String prodName;
	@Column(name = "spu_id")
	private Long spuId;
	@Transient
	private String spuName;
	@Column(name = "prod_desc")
	private String prodDesc;
	@Converting(format = FormatName.DATE)
	@Column(name = "market_date")
	private Date marketDate;
	@Transient
	@Converting(format = FormatName.DATE)
	private Date marketDateFrom;
	@Transient
	@Converting(format = FormatName.DATE)
	private Date marketDateTo;
	@Column(name = "cost_price")
	private BigDecimal costPrice;
	@Transient
	private BigDecimal costPriceFrom;
	@Transient
	private BigDecimal costPriceTo;
	@Column(name = "retail_price")
	private BigDecimal retailPrice;
	@Transient
	private BigDecimal retailPriceFrom;
	@Transient
	private BigDecimal retailPriceTo;
	@Column(name = "promo_price")
	private BigDecimal promoPrice;
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
	 * @return the prodNo
	 */
	public String getProdNo() {
		return prodNo;
	}
	/**
	 * @param prodNo the prodNo to set
	 */
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	/**
	 * @return the prodName
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * @param prodName the prodName to set
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
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
	 * @return the prodDesc
	 */
	public String getProdDesc() {
		return prodDesc;
	}
	/**
	 * @param prodDesc the prodDesc to set
	 */
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	/**
	 * @return the marketDate
	 */
	public Date getMarketDate() {
		return marketDate;
	}
	/**
	 * @param marketDate the marketDate to set
	 */
	public void setMarketDate(Date marketDate) {
		this.marketDate = marketDate;
	}
	/**
	 * @return the marketDateFrom
	 */
	public Date getMarketDateFrom() {
		return marketDateFrom;
	}
	/**
	 * @param marketDateFrom the marketDateFrom to set
	 */
	public void setMarketDateFrom(Date marketDateFrom) {
		this.marketDateFrom = marketDateFrom;
	}
	/**
	 * @return the marketDateTo
	 */
	public Date getMarketDateTo() {
		return marketDateTo;
	}
	/**
	 * @param marketDateTo the marketDateTo to set
	 */
	public void setMarketDateTo(Date marketDateTo) {
		this.marketDateTo = marketDateTo;
	}
	/**
	 * @return the costPrice
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * @return the costPriceFrom
	 */
	public BigDecimal getCostPriceFrom() {
		return costPriceFrom;
	}
	/**
	 * @param costPriceFrom the costPriceFrom to set
	 */
	public void setCostPriceFrom(BigDecimal costPriceFrom) {
		this.costPriceFrom = costPriceFrom;
	}
	/**
	 * @return the costPriceTo
	 */
	public BigDecimal getCostPriceTo() {
		return costPriceTo;
	}
	/**
	 * @param costPriceTo the costPriceTo to set
	 */
	public void setCostPriceTo(BigDecimal costPriceTo) {
		this.costPriceTo = costPriceTo;
	}
	/**
	 * @return the retailPrice
	 */
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	/**
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * @return the retailPriceFrom
	 */
	public BigDecimal getRetailPriceFrom() {
		return retailPriceFrom;
	}
	/**
	 * @param retailPriceFrom the retailPriceFrom to set
	 */
	public void setRetailPriceFrom(BigDecimal retailPriceFrom) {
		this.retailPriceFrom = retailPriceFrom;
	}
	/**
	 * @return the retailPriceTo
	 */
	public BigDecimal getRetailPriceTo() {
		return retailPriceTo;
	}
	/**
	 * @param retailPriceTo the retailPriceTo to set
	 */
	public void setRetailPriceTo(BigDecimal retailPriceTo) {
		this.retailPriceTo = retailPriceTo;
	}
	/**
	 * @return the promoPrice
	 */
	public BigDecimal getPromoPrice() {
		return promoPrice;
	}
	/**
	 * @param promoPrice the promoPrice to set
	 */
	public void setPromoPrice(BigDecimal promoPrice) {
		this.promoPrice = promoPrice;
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
	@Override
	public String toString() {
		return "Product [prodNo=" + prodNo + ", prodName=" + prodName + "]";
	}
}
