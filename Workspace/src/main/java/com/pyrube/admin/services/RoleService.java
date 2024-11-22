package com.pyrube.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public SearchCriteria<Role> search(SearchCriteria<Role> searchCriteria) throws AppException {
		return roleDao.search(searchCriteria);
	}

}
