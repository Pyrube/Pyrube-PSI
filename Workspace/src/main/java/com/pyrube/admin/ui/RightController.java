package com.pyrube.admin.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pyrube.admin.services.Right;
import com.pyrube.admin.services.RightService;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Controller
@RequestMapping(value = "admin/right")
public class RightController {

	private static final String MODEL_NAME = "right";
	
	@Autowired
	private RightService rightService;
	
	@RequestMapping(value = "lookup")
	public String initRightLookup(@ModelAttribute Right right, Model model) {
		model.addAttribute(MODEL_NAME, right);
		return "admin.right.lookup";
	}
	
	@ResponseBody
	@RequestMapping(value = "list")
	public SearchCriteria<Right> searchRights(@RequestBody SearchCriteria<Right> searchCriteria) throws AppException {
		searchCriteria = rightService.search(searchCriteria);
		return searchCriteria;
	}
	
	@RequestMapping(value = "search")
	public String initRightSearch() {
		return "admin.right.search";
	}
	
}
