package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.persistence.Data;
import com.pyrube.one.lang.Strings;

@Service
@Transactional
public class DataLobService {

	@Autowired
	private DataLobDao dataLobDao;

	@SuppressWarnings("unchecked")
	public SearchCriteria<?> commentsOn(String dataType, SearchCriteria<?> searchCriteria) {
		List<?> list = searchCriteria.getResults();
		if (list != null && list.size() > 0) {
			List<Object> dataIds = new ArrayList<Object>();
			boolean allVerified = true;
			for (Data<?> data : (List<Data<?>>) list) {
				if (!Apps.setup.stat.VERIFIED.equals(data.getStatus())) {
					dataIds.add(String.valueOf(data.getId()));
					allVerified = false;
				}
			}
			if (!allVerified) {
				List<DataLob> dataLobs = this.findList(dataType, dataIds);
				if (dataLobs != null && dataLobs.size() > 0) {
					Map<Object, String> map = new HashMap<Object, String>();
					for (DataLob dataLob : dataLobs) {
						if (!Strings.isEmpty(dataLob.getComments()))
							map.put(dataLob.getDataId(), dataLob.getComments());
					}
					for (Data<?> data : (List<Data<?>>) list) {
						data.setComments(map.get(String.valueOf(data.getId())));
					}
				}
			}
		}
		return searchCriteria;
	}

	public DataLob find(String dataType, Long dataId) throws AppException {
		return dataLobDao.findTypeAndId(dataType, dataId);
	}

	public DataLob find(String dataType, String dataId) throws AppException {
		return dataLobDao.findTypeAndId(dataType, dataId);
	}

	public List<DataLob> findList(String dataType, List<?> dataIds) throws AppException {
		return dataLobDao.findTypeAndIds(dataType, dataIds);
	}

	public Data<?> objectize(String dataType, Long dataId) throws AppException {
		return objectize(dataType, String.valueOf(dataId));
	}

	public Data<?> objectize(String dataType, String dataId) throws AppException {
		DataLob dataLob = this.find(dataType, dataId);
		return Data.objectize(dataLob.getDataClob());
	}

	public void serialize(String dataType, Long dataId, Data<?> data) throws AppException {
		serialize(dataType, String.valueOf(dataId), data);
	}

	public void serialize(String dataType, String dataId, Data<?> data) throws AppException {
		DataLob dataLob = this.find(dataType, dataId);
		if (dataLob == null) {
			dataLob = new DataLob();
			dataLob.setDataType(dataType);
			dataLob.setDataId(dataId);
			dataLob.setCreateBy(Apps.the.user.loginame());
			dataLob.setCreateTime(Apps.a.l_timestamp.in.GMT().value());
		} else {
			dataLob.setUpdateBy(Apps.the.user.loginame());
			dataLob.setUpdateTime(Apps.a.l_timestamp.in.GMT().value());
		}
		if (!Strings.isEmpty(data.getComments())) {
			dataLob.setComments(data.getComments());
		}
		dataLob.setDataClob(data.serialize());
		dataLobDao.saveOrUpdate(dataLob);
	}

	public void finalize(String dataType, Long dataId) throws AppException {
		finalize(dataType, String.valueOf(dataId));
	}

	public void finalize(String dataType, String dataId) throws AppException {
		DataLob dataLob = this.find(dataType, dataId);
		dataLobDao.delete(dataLob);
	}

}
