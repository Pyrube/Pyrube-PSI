package com.pyrube.dict.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyrube.one.app.AppException;

@Service
@Transactional
public class ChannelService {

	@Autowired
	private ChannelDao channelDao;

	public List<Channel> findList() throws AppException {
		return channelDao.findList();
	}

}
