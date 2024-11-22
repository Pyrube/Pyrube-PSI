package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.persistence.Dao;
import com.pyrube.one.lang.Strings;

@Repository
public class RoleDao extends Dao<Role, Long> {
	
	public SearchCriteria<Role> search(SearchCriteria<Role> searchCriteria) {
		Role criteria = searchCriteria.getCriteria();
		List<Object> params = new ArrayList<Object>();

		StringBuffer script = new StringBuffer("from Role r where 1 = 1");
		
		String roleCode = criteria.getRoleCode();
		if (!Strings.isEmpty(roleCode)) {
			script.append(" and r.roleCode like ?");
			params.add("%" + roleCode + "%");
		}
		String roleName = criteria.getRoleName();
		if (!Strings.isEmpty(roleName)) {
			script.append(" and r.roleName like ?");
			params.add("%" + roleName + "%");
		}
		Long[] excludeds = criteria.getExcludeds();
		if (excludeds != null && excludeds.length > 0) {
			script.append(" and roleId not in (");
			for (int i = 0; i < excludeds.length; i++) {
				script.append("?");
				params.add(excludeds[i]);
				if (i < excludeds.length - 1) script.append(", ");
			}
			script.append(")");
		}
		searchCriteria.setAlias("r");
		searchCriteria.setAltSortBy("roleCode");
		try {
			return this.query(script.toString(), searchCriteria, params);
		} catch (Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
