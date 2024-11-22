package com.pyrube.txn.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.dict.services.Channel;
import com.pyrube.dict.services.ChannelDao;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Service
@Transactional
public class SalesPlanService {

	@Autowired
	private SalesPlanDao salesPlanDao;
	@Autowired
	private PlanDetailsDao planDetailsDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ChannelDao channelDao;

	public SearchCriteria<SalesPlan> search(SearchCriteria<SalesPlan> searchCriteria) throws AppException {
		searchCriteria = salesPlanDao.search(searchCriteria);
		return searchCriteria;
	}

	public SalesPlan findId(Long salesPlanId) throws AppException {
		SalesPlan salesPlan = salesPlanDao.findId(salesPlanId);
		if (salesPlan != null) {
			Product prod = productDao.find(salesPlan.getProdNo());
			if (prod != null) salesPlan.setProd(prod);
			Channel chan = channelDao.find(salesPlan.getChannelId());
			if (chan != null) salesPlan.setChan(chan);
			List<PlanDetails> details = planDetailsDao.findList(salesPlanId);
			salesPlan.setDetails(details);
		}
		return salesPlan;
	}

	public List<PlanDetails> makePlanDetails() throws AppException {
		List<PlanDetails> planDetails = new ArrayList<PlanDetails>();
		Integer fiscalQuar = (Integer) Apps.the.user.current(Apps.the.user.attr.name.FISCAL_QUARTER);
		Integer[] fiscalMonths = Apps.the.fiscal.months.in.quarter(fiscalQuar);
		for (Integer fiscalMonth : fiscalMonths) {
			PlanDetails planDetail = Apps.a.data(PlanDetails.class);
			planDetail.setPlanMonth(fiscalMonth);
			planDetail.setDataStatus(Apps.constants.stat.ADDED);
			planDetails.add(planDetail);
		}
		return(planDetails);
	}

	public SalesPlan createSave(SalesPlan salesPlan) throws AppException {
		salesPlan.setCreateBy(Apps.the.user().loginame());
		salesPlan.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
		salesPlanDao.save(salesPlan);
		// sub-data
		this.createSubdata(salesPlan);
		return salesPlan;
	}

	public SalesPlan duplicate(SalesPlan source) throws AppException {
		List<SalesPlan> targets = salesPlanDao.findList(source.getFiscalYear(), source.getFiscalQuar(), source.getChannelIds(), source.getProdNo());
		for (SalesPlan target : targets) {
			Long salesPlanId = target.getSalesPlanId();
			target.setSalesPlanId(null);
			String fiscalYear = source.getFiscalYear();
			Integer fiscalQuar = source.getFiscalQuar();
			if (fiscalQuar < 4) {
				target.setFiscalYear(fiscalYear);
				target.setFiscalQuar(fiscalQuar + 1);
			} else {
				Date next = Apps.a.date.format.of(Apps.i18n.format.name.FISCALYEAR).parses(fiscalYear).adds.years(1).value();
				target.setFiscalYear(Apps.a.date(next).to.format(Apps.i18n.format.name.FISCALYEAR));
				target.setFiscalQuar(1);
			}
			target.setCreateBy(Apps.the.user().loginame());
			target.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
			target.setUpdateBy(null);
			target.setUpdateTime(null);
			salesPlanDao.save(target);
			List<PlanDetails> details = planDetailsDao.findList(salesPlanId);
			Integer[] fiscalMonths = Apps.the.fiscal.months.in.quarter(target.getFiscalQuar());
			int i = 0;
			for (PlanDetails detail : details) {
				detail.setPlanDetailsId(null);
				detail.setPlanMonth(fiscalMonths[i++]);
				detail.setUpdateBy(null);
				detail.setUpdateTime(null);
			}
			// sub-data
			this.createSubdata(target);
		}
		return source;
	}

	public SalesPlan updateSave(SalesPlan salesPlan) throws AppException {
		salesPlan.setUpdateBy(Apps.the.user().loginame());
		salesPlan.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		salesPlanDao.saveOrUpdate(salesPlan);
		// sub-data
		this.updateSubdata(salesPlan);
		return salesPlan;
	}

	public boolean delete(Long salesPlanId) throws AppException {
		return salesPlanDao.deleteById(salesPlanId);
	}
	
	private void createSubdata(SalesPlan salesPlan) throws AppException {
		List<PlanDetails> details = salesPlan.getDetails();
		for (PlanDetails detail : details) {
			detail.setPlanDetailsId(salesPlan.getSalesPlanId());
			detail.setCreateBy(Apps.the.user.loginame());
			detail.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
			planDetailsDao.save(detail);
		}
	}
	
	private void updateSubdata(SalesPlan salesPlan) throws AppException {
		List<PlanDetails> details = salesPlan.getDetails();
		for (PlanDetails detail : details) {
			if (Apps.constants.stat.MODIFIED.equals(detail.getDataStatus())) {
				detail.setUpdateBy(Apps.the.user.loginame());
				detail.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
				planDetailsDao.saveOrUpdate(detail);
			}
		}
	}

}
