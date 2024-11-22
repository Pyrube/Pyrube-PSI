package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class RoleRightDao extends Dao<RoleRight, Long> {
	
	public List<RoleRight> query(Long roleId) {
		String script = "from RoleRight rr, Right r where rr.rightId = r.rightId and rr.roleId = ?";
		List<RoleRight> roleRights = null;
		try {
			List<?> list =  this.query(script, roleId);
			if (list != null) {
				roleRights = new ArrayList<RoleRight>();
				for (int i = 0; i < list.size(); i++) {
					RoleRight roleRight = (RoleRight) ((Object[])list.get(i))[0];
					Right right = (Right) ((Object[])list.get(i))[1];
					roleRight.setRightCode(right.getRightCode());
					roleRights.add(roleRight);
				}
			}
			return roleRights;
		} catch(Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
