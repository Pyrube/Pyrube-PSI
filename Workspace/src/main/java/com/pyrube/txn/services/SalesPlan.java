package com.pyrube.txn.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pyrube.dict.services.Channel;
import com.pyrube.one.app.i18n.format.annotations.Converting;
import com.pyrube.one.app.i18n.format.annotations.FormatName;
import com.pyrube.one.app.persistence.Data;

@Entity
@Table(name = "psi_sales_plan")
public class SalesPlan extends Data<Long> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1136197920292347843L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_plan_id")
	private Long salesPlanId;
	@Column(name = "prod_no")
	private String prodNo;
	@Transient
	private Product prod;
	@Column(name = "fiscal_year")
	private String fiscalYear;
	@Column(name = "fiscal_quar")
	private Integer fiscalQuar;
	@Column(name = "channel_id")
	private Long channelId;
	@Transient
	private Long[] channelIds;
	@Transient
	private Channel chan;
	@Column(name = "si_ca")
	private BigDecimal siCa;
	@Column(name = "si_rev")
	private BigDecimal siRev;
	@Column(name = "si_gp")
	private BigDecimal siGp;
	@Column(name = "so_ca")
	private BigDecimal soCa;
	@Column(name = "so_rev")
	private BigDecimal soRev;
	@Column(name = "so_gp")
	private BigDecimal soGp;
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
	private List<PlanDetails> details;
	
	/**
	 * @return the salesPlanId
	 */
	public Long getSalesPlanId() {
		return salesPlanId;
	}
	/**
	 * @param salesPlanId the salesPlanId to set
	 */
	public void setSalesPlanId(Long salesPlanId) {
		this.salesPlanId = salesPlanId;
	}
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
	 * @return the prod
	 */
	public Product getProd() {
		return prod;
	}
	/**
	 * @param prod the prod to set
	 */
	public void setProd(Product prod) {
		this.prod = prod;
	}
	/**
	 * @return the fiscalYear
	 */
	public String getFiscalYear() {
		return fiscalYear;
	}
	/**
	 * @param fiscalYear the fiscalYear to set
	 */
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	/**
	 * @return the fiscalQuar
	 */
	public Integer getFiscalQuar() {
		return fiscalQuar;
	}
	/**
	 * @param fiscalQuar the fiscalQuar to set
	 */
	public void setFiscalQuar(Integer fiscalQuar) {
		this.fiscalQuar = fiscalQuar;
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
	 * @return the channelIds
	 */
	public Long[] getChannelIds() {
		return channelIds;
	}
	/**
	 * @param channelIds the channelIds to set
	 */
	public void setChannelIds(Long[] channelIds) {
		this.channelIds = channelIds;
	}
	/**
	 * @return the chan
	 */
	public Channel getChan() {
		return chan;
	}
	/**
	 * @param chan the chan to set
	 */
	public void setChan(Channel chan) {
		this.chan = chan;
	}
	/**
	 * @return the siCa
	 */
	public BigDecimal getSiCa() {
		return siCa;
	}
	/**
	 * @param siCa the siCa to set
	 */
	public void setSiCa(BigDecimal siCa) {
		this.siCa = siCa;
	}
	/**
	 * @return the siRev
	 */
	public BigDecimal getSiRev() {
		return siRev;
	}
	/**
	 * @param siRev the siRev to set
	 */
	public void setSiRev(BigDecimal siRev) {
		this.siRev = siRev;
	}
	/**
	 * @return the siGp
	 */
	public BigDecimal getSiGp() {
		return siGp;
	}
	/**
	 * @param siGp the siGp to set
	 */
	public void setSiGp(BigDecimal siGp) {
		this.siGp = siGp;
	}
	/**
	 * @return the soCa
	 */
	public BigDecimal getSoCa() {
		return soCa;
	}
	/**
	 * @param soCa the soCa to set
	 */
	public void setSoCa(BigDecimal soCa) {
		this.soCa = soCa;
	}
	/**
	 * @return the soRev
	 */
	public BigDecimal getSoRev() {
		return soRev;
	}
	/**
	 * @param soRev the soRev to set
	 */
	public void setSoRev(BigDecimal soRev) {
		this.soRev = soRev;
	}
	/**
	 * @return the soGp
	 */
	public BigDecimal getSoGp() {
		return soGp;
	}
	/**
	 * @param soGp the soGp to set
	 */
	public void setSoGp(BigDecimal soGp) {
		this.soGp = soGp;
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
	 * @return the details
	 */
	public List<PlanDetails> getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(List<PlanDetails> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "SalesPlan [prodNo=" + prodNo + ", channelId=" + channelId + 
				", fiscalYear=" + fiscalYear + ", quarter=" + fiscalQuar + "]";
	}
}
