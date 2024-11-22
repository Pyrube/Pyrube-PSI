package com.pyrube.dict.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.persistence.Dao;

@Repository
public class ChannelDao extends Dao<Channel, Long> {
	
	public List<Channel> findList() {
		String script = "from Channel order by channelId asc";
		try {
			List<Channel> list = this.query(script);
			List<Channel> channels = new ArrayList<Channel>();
			for (Channel channel : list) {
				channels.add(channel);
			}
			return(channels);
		} catch (Exception e) {
			throw AppException.due("message.error.dao-exception").param(e.getMessage());
		}
	}
}
