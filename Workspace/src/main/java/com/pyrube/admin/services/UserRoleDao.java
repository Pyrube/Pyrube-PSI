package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class UserRoleDao extends Dao<UserRole, Long> {
	
	public List<UserRole> query(Long userId) {
		String script = "from UserRole ur, Role r where ur.roleId = r.roleId and ur.userId = ?";
		List<UserRole> userRoles = null;
		try {
			List<?> list = this.query(script, userId);
			if (list != null) {
				userRoles = new ArrayList<UserRole>();
				for (int i = 0; i < list.size(); i++) {
					UserRole userRole = (UserRole) ((Object[])list.get(i))[0];
					Role role = (Role) ((Object[])list.get(i))[1];
					userRole.setRoleCode(role.getRoleCode());
					userRole.setRoleName(role.getRoleName());
					userRoles.add(userRole);
				}
			}
			return userRoles;
		} catch(Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
