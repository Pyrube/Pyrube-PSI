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

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.AppMessage;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.persistence.Dataset;
import com.pyrube.txn.services.Product;
import com.pyrube.txn.services.ProductService;
import com.pyrube.wea.WeaConstants;

@Controller
@RequestMapping(value = "txn/product")
public class ProductController {

	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(ProductController.class.getName());

	private static final String MODEL_NAME = "product";

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "")
	public String initProductGrid(HttpServletRequest request, HttpServletResponse response) {
		return "txn.product.grid";
	}

	@RequestMapping(value = "lookup")
	public String initProductLookup(HttpServletRequest request, HttpServletResponse response) {
		return "txn.product.lookup";
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public SearchCriteria<Product> searchProducts(@RequestBody SearchCriteria<Product> searchCriteria) throws AppException {
		searchCriteria = productService.search(searchCriteria);
		return searchCriteria;
	}

	@RequestMapping(value = "search")
	public String initProductSearch(HttpServletRequest request, HttpServletResponse response) {
		return "txn.product.search";
	}

	@RequestMapping(value = "view")
	public String initProductView(@RequestParam String prodNo, @RequestParam String prodName, Model model) throws AppException {
		Product product = productService.findId(prodNo);
		if (product == null) throw Apps.an.exception.due("product.error.prodName.not-found").param(prodName);
		model.addAttribute(MODEL_NAME, product);
		return "txn.product.view";
	}

	@RequestMapping(value = "create")
	public String initProductCreate(Model model) throws AppException {
		Product product = Apps.a.data(Product.class);
		model.addAttribute(MODEL_NAME, product);
		return "txn.product.create";
	}

	@ResponseBody
	@RequestMapping(value = "create/save")
	public Product createSaveProduct(@ModelAttribute Product model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Create-saving the product: " + model.getProdNo());
		model = productService.createSave(model);
		return model;
	}

	@RequestMapping(value = "import")
	public String initProductImport(Model model) throws AppException {
		AppMessage message = Apps.a.message.with.info("product.info.product-import");
		model.addAttribute(WeaConstants.REQUEST_ATTRNAME_MESSAGES, Apps.some.messages(message));
		return "txn.product.import";
	}

	@ResponseBody
	@RequestMapping(value = "import/save")
	public List<Product> importSaveProducts(@RequestBody Dataset<Product> dataset) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Import-saving the products...");
		return(productService.importSave(dataset.getResults()));
	}

	@RequestMapping(value = "modify")
	public String initProductModify(@ModelAttribute Product product, Model model) {
		model.addAttribute(MODEL_NAME, product);
		return "txn.product.modify";
	}

	@ResponseBody
	@RequestMapping(value = "modify/okay")
	public Product modifyOkayProduct(@ModelAttribute Product model) throws AppException {
		return model;
	}

	@RequestMapping(value = "update")
	public String initProductUpdate(@RequestParam String prodNo, Model model) throws AppException {
		Product product = productService.findId(prodNo);
		if (product == null) throw Apps.an.exception.due("product.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, product);
		return "txn.product.update";
	}

	@ResponseBody
	@RequestMapping(value = "update/save")
	public Product updateSaveProduct(@ModelAttribute Product model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Update-saving the product: " + model.getProdNo());
		model = productService.updateSave(model);
		return model;
	}

	@RequestMapping(value = "delete")
	public String initProductDelete(@RequestParam String prodNo, Model model) throws AppException {
		Product product = productService.findId(prodNo);
		if (product == null) throw Apps.an.exception.due("product.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, product);
		return "txn.product.delete";
	}

	@ResponseBody
	@RequestMapping(value = "delete/confirm")
	public boolean deleteProduct(@ModelAttribute Product model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Deleting the product: " + model.getProdNo());
		return productService.delete(model.getProdNo());
	}

	@RequestMapping(value = "referred")
	public String initProductRefereed(@RequestParam String prodNo, Model model) throws AppException {
		Product product = productService.findId(prodNo);
		if (product == null) throw Apps.an.exception.due("product.error.prodNo.not-found").param(prodNo);
		model.addAttribute(MODEL_NAME, product);
		return "txn.product.referred";
	}

}