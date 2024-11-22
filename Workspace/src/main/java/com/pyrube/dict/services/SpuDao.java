package com.pyrube.dict.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.persistence.Dao;
import com.pyrube.one.lang.Strings;

@Repository
public class SpuDao extends Dao<Spu, Long> {
	
	public SearchCriteria<Spu> search(SearchCriteria<Spu> searchCriteria) {
		Spu criteria = searchCriteria.getCriteria();
		List<Object> params = new ArrayList<Object>();

		StringBuffer script = new StringBuffer("from Spu where 1 = 1");

		String spuName = criteria.getSpuName();
		if (!Strings.isEmpty(spuName)) {
			script.append(" and spuName like ?");
			params.add("%" + spuName + "%");
		}
		searchCriteria.setAltSortBy("spuId");
		try {
			return this.query(script.toString(), searchCriteria, params);
		} catch (Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
