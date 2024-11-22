package com.pyrube.dict.caches;

import java.util.ArrayList;
import java.util.List;

import com.pyrube.dict.services.Channel;
import com.pyrube.dict.services.ChannelService;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.cache.Cacheable;
import com.pyrube.one.app.context.AppContextManager;
import com.pyrube.one.app.logging.Logger;

public class ListChannelsCacheable extends Cacheable<ArrayList<Channel>> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1156888613029026034L;

	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(ListChannelsCacheable.class.getName());

	private ChannelService channelService = (ChannelService) AppContextManager.findBean("channelService");;

	/**
	 * @see com.pyrube.one.app.cache.Cacheable#refresh()
	 */
	protected void refresh() throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Refreshing channel list ...");
		ArrayList<Channel> al = null;
		if (object != null) {
			al = (ArrayList<Channel>) object;
			al.clear();
		} else {
			al = new ArrayList<Channel>();
			object = al;
		}
		List<Channel> channels = channelService.findList();
		if (channels != null && channels.size() != 0) {
			for (Channel channel : channels) {
				al.add(channel);
			}
		}
	}

}
