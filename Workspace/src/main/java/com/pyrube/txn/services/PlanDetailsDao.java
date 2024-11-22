package com.pyrube.txn.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class PlanDetailsDao extends Dao<PlanDetails, Long> {
	
	public List<PlanDetails> findList(Long salesPlanId) {
		String script = "from PlanDetails where salesPlanId = ?";
		try {
			return this.query(script, salesPlanId);
		} catch(Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
