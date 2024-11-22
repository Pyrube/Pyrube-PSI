package com.pyrube.txn.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pyrube.dict.services.Channel;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.txn.services.PlanDetails;
import com.pyrube.txn.services.SalesPlan;
import com.pyrube.txn.services.SalesPlanService;

@Controller
@RequestMapping(value = "txn/plan")
public class SalesPlanController {

	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(SalesPlanController.class.getName());

	private static final String MODEL_NAME = "plan";

	private static final String MODEL_NAME_DETAIL = "detail";

	@Autowired
	private SalesPlanService salesPlanService;

	@RequestMapping(value = "")
	public String initSalesPlanGrid(HttpServletRequest request, HttpServletResponse response) {
		return "txn.plan.grid";
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public SearchCriteria<SalesPlan> searchProducts(@RequestBody SearchCriteria<SalesPlan> searchCriteria) throws AppException {
		searchCriteria = salesPlanService.search(searchCriteria);
		return searchCriteria;
	}

	@RequestMapping(value = "search")
	public String initSalesPlanSearch(HttpServletRequest request, HttpServletResponse response) {
		return "txn.plan.search";
	}

	@RequestMapping(value = "view")
	public String initSalesPlanView(@RequestParam Long salesPlanId, @RequestParam String prodNo, Model model) throws AppException {
		SalesPlan salesPlan = salesPlanService.findId(salesPlanId);
		if (salesPlan == null) throw Apps.an.exception.due("salesPlan.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, salesPlan);
		return "txn.plan.view";
	}

	@RequestMapping(value = "create")
	public String initSalesPlanCreate(Model model) throws AppException {
		SalesPlan salesPlan = Apps.a.data(SalesPlan.class);
		salesPlan.setFiscalYear((String) Apps.the.user.current(Apps.the.user.attr.name.FISCAL_YEAR));
		salesPlan.setFiscalQuar((Integer) Apps.the.user.current(Apps.the.user.attr.name.FISCAL_QUARTER));
		salesPlan.setDetails(salesPlanService.makePlanDetails());
		model.addAttribute(MODEL_NAME, salesPlan);
		return "txn.plan.create";
	}

	@ResponseBody
	@RequestMapping(value = "create/save")
	public SalesPlan createSaveSalesPlan(@ModelAttribute SalesPlan model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Create-saving the sales plan: " + model.getProdNo());
		model = salesPlanService.createSave(model);
		return model;
	}

	@RequestMapping(value = "duplicate")
	public String initSalesPlanDuplicate(Model model) throws AppException {
		SalesPlan salesPlan = Apps.a.data(SalesPlan.class);
		salesPlan.setFiscalYear((String) Apps.the.user.current(Apps.the.user.attr.name.FISCAL_YEAR));
		salesPlan.setFiscalQuar((Integer) Apps.the.user.current(Apps.the.user.attr.name.FISCAL_QUARTER));
		@SuppressWarnings("unchecked")
		List<Channel> listChannels = (List<Channel>) Apps.some.objects.cached("listChannels");
		Long[] channelIds = new Long[listChannels.size()];
		for (int i = 0; i < listChannels.size(); i++) {
			channelIds[i] = listChannels.get(i).getChannelId();
		}
		salesPlan.setChannelIds(channelIds);
		model.addAttribute(MODEL_NAME, salesPlan);
		return "txn.plan.duplicate";
	}

	@ResponseBody
	@RequestMapping(value = "duplicate/okay")
	public SalesPlan duplicateSalesPlan(@ModelAttribute SalesPlan source) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Duplicating the sales plan: " + source.getProdNo());
		source = salesPlanService.duplicate(source);
		return source;
	}

	@RequestMapping(value = "update")
	public String initSalesPlanUpdate(@RequestParam Long salesPlanId, @RequestParam String prodNo, Model model) throws AppException {
		SalesPlan salesPlan = salesPlanService.findId(salesPlanId);
		if (salesPlan == null) throw Apps.an.exception.due("salesPlan.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, salesPlan);
		return "txn.plan.update";
	}

	@ResponseBody
	@RequestMapping(value = "update/save")
	public SalesPlan updateSaveSalesPlan(@ModelAttribute SalesPlan model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Update-saving the sales plan: " + model.getProdNo());
		model = salesPlanService.updateSave(model);
		return model;
	}

	@RequestMapping(value = "delete")
	public String initSalesPlanDelete(@RequestParam Long salesPlanId, @RequestParam String prodNo, Model model) throws AppException {
		SalesPlan salesPlan = salesPlanService.findId(salesPlanId);
		if (salesPlan == null) throw Apps.an.exception.due("salesPlan.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, salesPlan);
		return "txn.plan.delete";
	}

	@ResponseBody
	@RequestMapping(value = "delete/confirm")
	public boolean deleteSalesPlan(@ModelAttribute SalesPlan model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Deleting the sales plan: " + model.getProdNo());
		return salesPlanService.delete(model.getSalesPlanId());
	}

	@RequestMapping(value = "details")
	public String initSalesPlanDetails(@RequestParam Long salesPlanId, @RequestParam String prodNo, Model model) throws AppException {
		SalesPlan salesPlan = salesPlanService.findId(salesPlanId);
		if (salesPlan == null) throw Apps.an.exception.due("salesPlan.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, salesPlan);
		return "txn.plan.details";
	}

	@RequestMapping(value = "detail/modify")
	public String initPlanDetailsModify(@ModelAttribute PlanDetails detail, Model model) throws AppException {
		model.addAttribute(MODEL_NAME_DETAIL, detail);
		return "txn.plan.detail.modify";
	}

	@ResponseBody
	@RequestMapping(value = "detail/modify/okay")
	public PlanDetails initPlanDetailsModify(@ModelAttribute PlanDetails model) throws AppException {
		return model;
	}

}