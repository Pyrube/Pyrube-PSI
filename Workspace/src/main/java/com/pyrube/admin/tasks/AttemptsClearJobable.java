package com.pyrube.admin.tasks;

import com.pyrube.admin.services.UserService;
import com.pyrube.one.app.context.AppContextManager;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.task.Job;
import com.pyrube.one.app.task.Jobable;
import com.pyrube.one.app.task.RunResult;

public class AttemptsClearJobable extends Jobable {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 3851771600022172285L;

	/**
	 * logger
	 */
	private static Logger logger = Logger.getInstance(AttemptsClearJobable.class.getName());
	
	private UserService userService = (UserService) AppContextManager.findBean("userService");

	public AttemptsClearJobable() { }

	public AttemptsClearJobable(Job job) {
		super(job);
	}

	@Override
	public RunResult run() {
		if (logger.isDebugEnabled()) logger.debug("Run the AttemptsClear job.");
		RunResult rr = userService.clearAttempts(this.getJob().getTimezoneId());
		if (logger.isDebugEnabled()) logger.debug(rr.getMessage().getItemsFinished() + " users attempts have been cleared.");
		return rr;
	}

}
