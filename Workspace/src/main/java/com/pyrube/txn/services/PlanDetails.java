package com.pyrube.txn.services;

import java.math.BigDecimal;
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
@Table(name = "psi_plan_details")
public class PlanDetails extends Data<Long> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 7531047219532089470L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_details_id")
	private Long planDetailsId;
	@Column(name = "sales_plan_id")
	private Long salesPlanId;
	@Column(name = "plan_month")
	private Integer planMonth;
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
	private String dataStatus;
	/**
	 * @return the planDetailsId
	 */
	public Long getPlanDetailsId() {
		return planDetailsId;
	}
	/**
	 * @param planDetailsId the planDetailsId to set
	 */
	public void setPlanDetailsId(Long planDetailsId) {
		this.planDetailsId = planDetailsId;
	}
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
	 * @return the planMonth
	 */
	public Integer getPlanMonth() {
		return planMonth;
	}
	/**
	 * @param planMonth the planMonth to set
	 */
	public void setPlanMonth(Integer planMonth) {
		this.planMonth = planMonth;
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
	@Override
	public String toString() {
		return "PlanDetails [salesPlanId=" + salesPlanId + ", planMonth=" + planMonth + "]";
	}
}
