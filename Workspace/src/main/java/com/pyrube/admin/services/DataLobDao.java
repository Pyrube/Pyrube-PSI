package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class DataLobDao extends Dao<DataLob, Long> {
	
	/**
	 * logger
	 */
	private static Logger logger = Logger.getInstance(DataLobDao.class.getName());
	
	public DataLob findTypeAndId(String dataType, Long dataId) {
		String script = "from DataLob where dataType = ? and dataId = ?";
		try {
			return this.find(script, dataType, dataId);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public DataLob findTypeAndId(String dataType, String dataId) {
		String script = "from DataLob where dataType = ? and dataId = ?";
		try {
			return this.find(script, dataType, dataId);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw AppException.due("message.error.dao-exception", e);
		}
	}
	
	public List<DataLob> findTypeAndIds(String dataType, List<?> dataIds) {
		List<Object> params = new ArrayList<Object>();

		StringBuffer script = new StringBuffer("from DataLob where dataType = ?");
		params.add(dataType);
		if (dataIds != null && dataIds.size() > 0) {
			script.append(" and dataId in (");
			for (int i = 0; i < dataIds.size(); i++) {
				script.append("?");
				params.add(String.valueOf(dataIds.get(i)));
				if (i < dataIds.size() - 1) script.append(", ");
			}
			script.append(")");
		}
		try {
			return this.query(script.toString(), params.toArray());
		} catch (Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
	
}
