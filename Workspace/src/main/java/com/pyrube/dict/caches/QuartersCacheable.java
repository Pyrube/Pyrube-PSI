package com.pyrube.dict.caches;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.cache.Cacheable;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.lang.Strings;

public class QuartersCacheable extends Cacheable<Integer[]> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 3129658051092058595L;

	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(QuartersCacheable.class.getName());

	/**
	 * @see com.pyrube.one.app.cache.Cacheable#refresh()
	 */
	protected void refresh() throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Refreshing quarters ...");
		String param = (String) objectInfo.getParam("quarters");
		if (!Strings.isEmpty(param)) {
			String[] sQuarters = param.split(",");
			Integer[] iQuarters = new Integer[sQuarters.length];
			for (int i = 0; i < sQuarters.length; i++) {
				iQuarters[i] = Integer.valueOf(sQuarters[i]);
			}
			object = iQuarters;
		} else {
			object = new Integer[0];
		}
	}

}
