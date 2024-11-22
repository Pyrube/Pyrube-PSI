package com.pyrube.txn.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pyrube.dict.services.Spu;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.persistence.Dao;
import com.pyrube.one.lang.Strings;

@Repository
public class ProductDao extends Dao<Product, String> {
	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(ProductDao.class.getName());

	public SearchCriteria<Product> search(SearchCriteria<Product> searchCriteria) {
		Product criteria = searchCriteria.getCriteria();
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuffer script = new StringBuffer("from Product p, Spu s where p.spuId = s.spuId");
		String prodNo = criteria.getProdNo();
		if (!Strings.isEmpty(prodNo)) {
			script.append(" and p.prodNo like :prodNo");
			params.put("prodNo", "%" + prodNo + "%");
		}
		String prodName = criteria.getProdName();
		if (!Strings.isEmpty(prodName)) {
			script.append(" and p.prodName like :prodName");
			params.put("prodName", "%" + prodName + "%");
		}
		String spuName = criteria.getSpuName();
		if (!Strings.isEmpty(prodName)) {
			script.append(" and s.spuName like :spuName");
			params.put("spuName", "%" + spuName + "%");
		}
		BigDecimal costPriceFrom = criteria.getCostPriceFrom();
		if (costPriceFrom != null) {
			script.append(" and p.costPrice >= :costPriceFrom");
			params.put("costPriceFrom", costPriceFrom);
		}
		BigDecimal costPriceTo = criteria.getCostPriceTo();
		if (costPriceTo != null) {
			script.append(" and p.costPrice <= :costPriceTo");
			params.put("costPriceTo", costPriceTo);
		}
		BigDecimal retailPriceFrom = criteria.getRetailPriceFrom();
		if (retailPriceFrom != null) {
			script.append(" and p.retailPrice >= :retailPriceFrom");
			params.put("retailPriceFrom", retailPriceFrom);
		}
		BigDecimal retailPriceTo = criteria.getRetailPriceTo();
		if (retailPriceTo != null) {
			script.append(" and p.retailPrice <= :retailPriceTo");
			params.put("retailPriceTo", retailPriceTo);
		}
		searchCriteria.setAlias("p");
		searchCriteria.setAltSortBy("prodNo");
		List<Product> products = null;
		try {
			searchCriteria = this.query(script.toString(), searchCriteria, params);
			List<?> list = searchCriteria.getResults();
			if (list != null) {
				products = new ArrayList<Product>();
				for (int i = 0; i < list.size(); i++) {
					Product product = (Product) ((Object[])list.get(i))[0];
					Spu spu = (Spu) ((Object[])list.get(i))[1];
					product.setSpuName(spu.getSpuName());
					products.add(product);
				}
			}
			searchCriteria.setResults(products);
			return(searchCriteria);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw Apps.an.exception.due("message.error.dao-exception", e);
		}
	}

	public Product findId(String prodNo) {
		String script = "from Product where prodNo = ?";
		try {
			return this.find(script, prodNo);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw Apps.an.exception.due("message.error.dao-exception").param(e.getMessage());
		}
	}

}
