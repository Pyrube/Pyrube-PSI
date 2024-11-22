package com.pyrube.admin.tasks;

import com.pyrube.admin.services.UserService;
import com.pyrube.one.app.context.AppContextManager;
import com.pyrube.one.app.logging.Logger;
import com.pyrube.one.app.task.Job;
import com.pyrube.one.app.task.Jobable;
import com.pyrube.one.app.task.RunResult;

public class PasswordExpiredJobable extends Jobable {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 7012510960464734174L;

	/**
	 * logger
	 */
	private static Logger logger = Logger.getInstance(PasswordExpiredJobable.class.getName());
	
	private UserService userService = (UserService) AppContextManager.findBean("userService");

	public PasswordExpiredJobable() { }

	public PasswordExpiredJobable(Job job) {
		super(job);
	}

	@Override
	public RunResult run() {
		if (logger.isDebugEnabled()) logger.debug("Run the PasswordExpired job.");
		RunResult rr = userService.expirePasswords(this.getJob().getTimezoneId());
		if (logger.isDebugEnabled()) logger.debug(rr.getMessage().getItemsFinished() + " user passwords have expired.");
		return rr;
	}

}
