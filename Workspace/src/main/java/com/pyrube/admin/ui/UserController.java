package com.pyrube.admin.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pyrube.admin.services.User;
import com.pyrube.admin.services.UserService;
import com.pyrube.one.app.AppException;
import com.pyrube.one.app.Apps;
import com.pyrube.one.app.inquiry.SearchCriteria;
import com.pyrube.one.app.logging.Logger;

@Controller
@RequestMapping(value = "admin/user")
public class UserController {
	
	/**
	 * logger
	 */
	private static Logger logger = Apps.a.logger.named(UserController.class.getName());

	private static final String MODEL_NAME = "user";
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "unique/loginName/{loginName}")
	public boolean ifLoginNameUnique(@PathVariable String loginName, Model model)
		throws AppException {
		return userService.ifLoginNameUnique(loginName);
	}
	
	@ResponseBody
	@RequestMapping(value = "unique/mobile/{mobile}")
	public boolean ifMobileUnique(@PathVariable String mobile, Model model)
		throws AppException {
		return userService.ifMobileUnique(mobile);
	}
	
	@ResponseBody
	@RequestMapping(value = "unique/email/{email:.+}")
	public boolean ifEmailUnique(@PathVariable String email, Model model)
		throws AppException {
		return userService.ifEmailUnique(email);
	}
	
	@RequestMapping(value = "")
	public String initUserGrid(HttpServletRequest request, HttpServletResponse response) {
		return "admin.user.grid";
	}
	
	@ResponseBody
	@RequestMapping(value = "list")
	public SearchCriteria<User> searchUsers(@RequestBody SearchCriteria<User> searchCriteria) throws AppException {
		searchCriteria = userService.search(searchCriteria);
		return searchCriteria;
	}
	
	@RequestMapping(value = "search")
	public String initUserSearch(HttpServletRequest request, HttpServletResponse response) {
		return "admin.user.search";
	}
	
	@RequestMapping(value = "view")
	public String initUserView(@RequestParam Long userId, @RequestParam String loginName, Model model) throws AppException {
		User user = userService.findId(userId);
		if (user == null) throw AppException.due("user.error.loginName.not-found").param(loginName);
		model.addAttribute(MODEL_NAME, user);
		return "admin.user.view";
	}
	
	@RequestMapping(value = "create")
	public String initUserCreate(Model model) throws AppException {
		User user = new User();
		model.addAttribute(MODEL_NAME, user);
		return "admin.user.create";
	}

	@ResponseBody
	@RequestMapping(value = "create/abort")
	public User createAbortUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Abort the created user: " + model.getLoginName());
		model = userService.createAbort(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "create/submit")
	public User createSubmitUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Submit the created user: " + model.getLoginName());
		model = userService.createSubmit(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "create/reject")
	public User createRejectUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Reject the created user: " + model.getLoginName());
		model = userService.createReject(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "create/approve")
	public User createApproveUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Approve the created user: " + model.getLoginName());
		model = userService.createApprove(model);
		return model;
	}
	
	@RequestMapping(value = "update")
	public String initUserUpdate(@RequestParam Long userId, @RequestParam String loginName, Model model) throws AppException {
		User user = userService.findId(userId);
		if (user == null) throw AppException.due("user.error.loginName.not-found").param(loginName);
		model.addAttribute(MODEL_NAME, user);
		return "admin.user.update";
	}

	@ResponseBody
	@RequestMapping(value = "update/abort")
	public User updateAbortUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Abort the updated user: " + model.getLoginName());
		model = userService.updateAbort(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "update/submit")
	public User updateSubmitUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Submit the updating user: " + model.getLoginName());
		model = userService.updateSubmit(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "update/reject")
	public User updateRejectUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Reject the updated user: " + model.getLoginName());
		model = userService.updateReject(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "update/approve")
	public User updateApproveUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Approve the updated user: " + model.getLoginName());
		model = userService.updateApprove(model);
		return model;
	}
	
	@RequestMapping(value = "delete")
	public String initUserDelete(@RequestParam Long userId, @RequestParam String loginName, Model model) throws AppException {
		User user = userService.findId(userId);
		if (user == null) throw AppException.due("user.error.loginName.not-found").param(loginName);
		model.addAttribute(MODEL_NAME, user);
		return "admin.user.delete";
	}

	@ResponseBody
	@RequestMapping(value = "delete/abort")
	public User deleteAbortUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Abort the deleted user: " + model.getLoginName());
		model = userService.deleteAbort(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "delete/submit")
	public User deleteSubmitUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Submit the deleting user: " + model.getLoginName());
		model = userService.deleteSubmit(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "delete/reject")
	public User deleteRejectUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Reject the deleted user: " + model.getLoginName());
		model = userService.deleteReject(model);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "delete/approve")
	public User deleteApproveUser(@ModelAttribute User model) throws AppException {
		if (logger.isDebugEnabled()) logger.debug("Approve the deleted user: " + model.getLoginName());
		model = userService.deleteApprove(model);
		return model;
	}
	
	@RequestMapping(value = "continue")
	public String initUserContinue(@RequestParam Long userId, @RequestParam String loginName, Model model) throws AppException {
		User user = userService.findRejectedId(userId);
		if (user == null) throw AppException.due("message.error.setup.lob-finalized").param(loginName);
		model.addAttribute(MODEL_NAME, user);
		return "admin.user.continue." + user.getDataStatus();
	}
	
	@RequestMapping(value = "verify")
	public String initUserVerify(@RequestParam Long userId, @RequestParam String loginName, Model model) throws AppException {
		User user = userService.findPendingId(userId);
		if (user == null) throw AppException.due("message.error.setup.lob-finalized").param(loginName);
		model.addAttribute(MODEL_NAME, user);
		return "admin.user.verify." + user.getDataStatus();
	}
}
