package com.pyrube.txn.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pyrube.dict.services.Channel;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.persistence.Dao;
import com.pyrube.one.lang.Strings;

@Repository
public class SalesPlanDao extends Dao<SalesPlan, Long> {
	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(SalesPlanDao.class.getName());

	public SearchCriteria<SalesPlan> search(SearchCriteria<SalesPlan> searchCriteria) {
		SalesPlan criteria = searchCriteria.getCriteria();
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuffer script = new StringBuffer("from SalesPlan sp, Product prod, Channel chan where sp.prodNo = prod.prodNo and sp.channelId = chan.channelId");
		String prodNo = criteria.getProdNo();
		if (!Strings.isEmpty(prodNo)) {
			script.append(" and sp.prodNo like :prodNo");
			params.put("prodNo", "%" + prodNo + "%");
		}
		Product prod = criteria.getProd();
		String prodName = null;
		if (prod != null && !Strings.isEmpty(prodName = prod.getProdName())) {
			script.append(" and prod.prodName like :prodName");
			params.put("prodName", "%" + prodName + "%");
		}
		Long channelId = criteria.getChannelId();
		if (channelId != null) {
			script.append(" and sp.channelId = :channelId");
			params.put("channelId", channelId);
		}
		String fiscalYear = criteria.getFiscalYear();
		if (!Strings.isEmpty(fiscalYear)) {
			script.append(" and sp.fiscalYear = :fiscalYear");
			params.put("fiscalYear", fiscalYear);
		}
		Integer quarter = criteria.getFiscalQuar();
		if (quarter != null) {
			script.append(" and sp.quarter = :quarter");
			params.put("quarter", quarter);
		}
		searchCriteria.setAlias("sp");
		searchCriteria.setAltSortBy("salesPlanId");
		List<SalesPlan> salesPlans = null;
		try {
			searchCriteria = this.query(script.toString(), searchCriteria, params);
			List<?> list = searchCriteria.getResults();
			if (list != null) {
				salesPlans = new ArrayList<SalesPlan>();
				for (int i = 0; i < list.size(); i++) {
					SalesPlan salesPlan = (SalesPlan) ((Object[])list.get(i))[0];
					Product product     = (Product) ((Object[])list.get(i))[1];
					Channel channel     = (Channel) ((Object[])list.get(i))[2];
					salesPlan.setProd(product);
					salesPlan.setChan(channel);
					salesPlans.add(salesPlan);
				}
			}
			searchCriteria.setResults(salesPlans);
			return(searchCriteria);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw Apps.an.exception.due("message.error.dao-exception", e);
		}
	}

	public SalesPlan findId(Long salesPlanId) {
		String script = "from SalesPlan where salesPlanId = ?";
		try {
			return this.find(script, salesPlanId);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw Apps.an.exception.due("message.error.dao-exception").param(e.getMessage());
		}
	}
	
	public List<SalesPlan> findList(String fiscalYear, Integer fiscalQuar, Long[] channelIds, String prodNo) {
		try {
			StringBuffer script = new StringBuffer("from SalesPlan where fiscalYear = ?1 and fiscalQuar = ?2");
			List<Object> params = new ArrayList<Object>();
			params.add(fiscalYear);
			params.add(fiscalQuar);
			script.append(" and channelId in (");
			int i = 1;
			for (Long channelId : channelIds) {
				script.append("?" + (params.size() + 1));
				params.add(channelId);
				if (i++ < channelIds.length) script.append(",");
			}
			script.append(")");
			if (!Strings.isEmpty(prodNo)) {
				script.append(" and prodNo = ?" + (params.size() + 1));
				params.add(prodNo);
			}
			return this.query(script.toString(), params);
		} catch(Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}

}
