package com.pyrube.dict.caches;

import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.cache.Cacheable;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.lang.Strings;

public class FiscalYearsCacheable extends Cacheable<String[]> {
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 2988528129629026478L;

	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(FiscalYearsCacheable.class.getName());

	/**
	 * @see com.pyrube.one.app.cache.Cacheable#refresh()
	 */
	protected void refresh() throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Refreshing fiscal years ...");
		String param = (String) objectInfo.getParam("range");
		if (!Strings.isEmpty(param)) {
			String[] range = param.split(",");
			Integer[] iRange = new Integer[range.length];
			for (int i = 0; i < range.length; i++) {
				iRange[i] = Integer.valueOf(range[i]);
			}
			int start = iRange[0];
			int end   = iRange[1];
			String[] fiscalYears = new String[(end - start) + 1];
			for (int y = start, i = 0; y <= end; y++, i++) {
				fiscalYears[i] = Apps.a.date().adds.years(y).to.format(Apps.the.sys_default.locale(), Apps.i18n.format.name.FISCALYEAR);
			}
			object = fiscalYears;
		} else {
			object = new String[0];
		}
	}

}
