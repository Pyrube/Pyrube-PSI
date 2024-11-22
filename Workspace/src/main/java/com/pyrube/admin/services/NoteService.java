package com.pyrube.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteDao noteDao;

	public SearchCriteria<Note> search(SearchCriteria<Note> searchCriteria) throws AppException {
		return noteDao.search(searchCriteria);
	}
	
	public Note leave(Note note) throws AppException {
		note.setUserFrom(Long.parseLong(Apps.the.user.uuk()));
		note.setNoteFrom(Apps.the.user.name());
		note.setNoteTime(Apps.a.l_timestamp.in.GMT().value());
		noteDao.save(note);
		return note;
	}

}
