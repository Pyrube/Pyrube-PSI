package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.persistence.Dao;
import com.pyrube.one.lang.Strings;

@Repository
public class RightDao extends Dao<Right, Long> {

	public SearchCriteria<Right> search(SearchCriteria<Right> searchCriteria) {
		Right criteria = searchCriteria.getCriteria();
		List<Object> params = new ArrayList<Object>();

		StringBuffer script = new StringBuffer("from Right r, RoleRight rr where r.rightId = rr.rightId and rr.roleId = ?");
		params.add(criteria.getRoleId());
		
		String rightCode = criteria.getRightCode();
		if (!Strings.isEmpty(rightCode)) {
			script.append(" and r.rightCode like ?");
			params.add("%" + rightCode + "%");
		}
		String rightName = criteria.getRightName();
		if (!Strings.isEmpty(rightName)) {
			script.append(" and r.rightName like ?");
			params.add("%" + rightName + "%");
		}
		searchCriteria.setAlias("r");
		searchCriteria.setAltSortBy("rightCode");
		List<Right> rights = null;
		try {
			searchCriteria = this.query(script.toString(), searchCriteria, params);
			List<?> list = searchCriteria.getResults();
			if (list != null) {
				rights = new ArrayList<Right>();
				for (int i = 0; i < list.size(); i++) {
					Right right = (Right) ((Object[])list.get(i))[0];
					rights.add(right);
				}
			}
			searchCriteria.setResults(rights);
			return(searchCriteria);
		} catch (Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
