package com.pyrube.admin.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pyrube.admin.services.Right;
import com.pyrube.admin.services.RightService;
import com.pyrube.admin.services.Role;
import com.pyrube.admin.services.RoleService;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Controller
@RequestMapping(value = "admin/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RightService rightService;
	
	@RequestMapping(value = "lookup")
	public String initRoleLookup(HttpServletRequest request, HttpServletResponse response) {
		return "admin.role.lookup";
	}
	
	@ResponseBody
	@RequestMapping(value = "list")
	public SearchCriteria<Role> searchRoles(@RequestBody SearchCriteria<Role> searchCriteria) throws AppException {
		searchCriteria = roleService.search(searchCriteria);
		return searchCriteria;
	}
	
	@RequestMapping(value = "search")
	public String initRoleSearch(HttpServletRequest request, HttpServletResponse response) {
		return "admin.role.search";
	}
	
	@ResponseBody
	@RequestMapping(value = "rights")
	public SearchCriteria<Right> searchRoleRights(@RequestBody SearchCriteria<Right> searchCriteria) throws AppException {
		searchCriteria = rightService.search(searchCriteria);
		return searchCriteria;
	}
}
