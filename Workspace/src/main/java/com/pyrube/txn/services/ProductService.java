package com.pyrube.txn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.dict.services.Spu;
import com.pyrube.dict.services.SpuDao;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private SpuDao spuDao;

	public SearchCriteria<Product> search(SearchCriteria<Product> searchCriteria) throws AppException {
		searchCriteria = productDao.search(searchCriteria);
		return searchCriteria;
	}

	public Product findId(String prodNo) throws AppException {
		Product product = productDao.findId(prodNo);
		if (product != null) {
			Spu spu = spuDao.find(product.getSpuId());
			if (spu != null) product.setSpuName(spu.getSpuName());
		}
		return product;
	}

	public Product createSave(Product product) throws AppException {
		product.setCreateBy(Apps.the.user().loginame());
		product.setCreateTime(Apps.a.l_timestamp.in.GMT().value());

		productDao.save(product);
		return product;
	}

	public List<Product> importSave(List<Product> products) throws AppException {
		for (Product product : products) {
			product.setCreateBy(Apps.the.user().loginame());
			product.setCreateTime(Apps.a.l_timestamp.in.GMT().value());

			productDao.save(product);
		}
		return products;
	}

	public Product updateSave(Product product) throws AppException {
		product.setUpdateBy(Apps.the.user().loginame());
		product.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());

		productDao.saveOrUpdate(product);
		return product;
	}

	public boolean delete(String prodNo) throws AppException {
		return productDao.deleteById(prodNo);
	}

}
