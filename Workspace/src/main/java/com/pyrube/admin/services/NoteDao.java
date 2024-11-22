package com.pyrube.admin.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class NoteDao extends Dao<Note, Long> {
	
	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(NoteDao.class.getName());
	
	public SearchCriteria<Note> search(SearchCriteria<Note> searchCriteria) {
		Note criteria = searchCriteria.getCriteria();
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuffer script = new StringBuffer(
				"from Note n left join User u1 on (n.userFrom = u1.userId) left join User u2 on (n.userTo = u2.userId) where n.dataType = :dataType and  n.dataId = :dataId");
		params.put("dataType", criteria.getDataType());
		params.put("dataId", criteria.getDataId());
		searchCriteria.setAlias("n");

		List<Note> notes = null;
		try {
			searchCriteria = this.query(script.toString(), searchCriteria, params);
			List<?> list = searchCriteria.getResults();
			if (list != null) {
				notes = new ArrayList<Note>();
				for (int i = 0; i < list.size(); i++) {
					Note note = (Note) ((Object[]) list.get(i))[0];
					User user1 = (User) ((Object[]) list.get(i))[1];
					User user2 = (User) ((Object[]) list.get(i))[2];
					if (user1 != null) note.setNoteFrom(user1.getUserName());
					if (user2 != null) note.setNoteTo(user2.getUserName());
					notes.add(note);
				}
			}
			searchCriteria.setResults(notes);
			return (searchCriteria);
		} catch (Exception e) {
			logger.error("SQL error (" + e.getMessage() + ") occurs.", e);
			throw Apps.an.exception.due("message.error.dao-exception", e);
		}
	}
	
}
