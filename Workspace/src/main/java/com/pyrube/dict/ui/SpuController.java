package com.pyrube.dict.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pyrube.dict.services.Spu;
import com.pyrube.dict.services.SpuService;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Controller
@RequestMapping(value = "dict/spu")
public class SpuController {

	@Autowired
	private SpuService spuService;

	@RequestMapping(value = "lookup")
	public String initSpuLookup(HttpServletRequest request, HttpServletResponse response) {
		return "dict.spu.lookup";
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public SearchCriteria<Spu> searchSpus(@RequestBody SearchCriteria<Spu> searchCriteria) throws AppException {
		searchCriteria = spuService.search(searchCriteria);
		return searchCriteria;
	}

	@RequestMapping(value = "search")
	public String initRoleSearch(HttpServletRequest request, HttpServletResponse response) {
		return "dict.spu.search";
	}

}
