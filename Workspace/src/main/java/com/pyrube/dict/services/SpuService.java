package com.pyrube.dict.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Service
@Transactional
public class SpuService {

	@Autowired
	private SpuDao spuDao;

	public SearchCriteria<Spu> search(SearchCriteria<Spu> searchCriteria) throws AppException {
		return spuDao.search(searchCriteria);
	}

}
