package com.pyrube.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Service
@Transactional
public class RightService {

	@Autowired
	private RightDao rightDao;

	public SearchCriteria<Right> search(SearchCriteria<Right> searchCriteria) throws AppException {
		return rightDao.search(searchCriteria);
	}

}
