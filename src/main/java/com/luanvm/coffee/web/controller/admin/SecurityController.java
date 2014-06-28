package com.luanvm.coffee.web.controller.admin;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luanvm.coffee.web.form.Message;

@Controller
public class SecurityController {

	final Logger logger = LoggerFactory.getLogger(SecurityController.class);	
	
	@Autowired
	private MessageSource messageSource;	
	
	@RequestMapping("/security/loginfail")
	public String loginFail(Model uiModel, Locale locale) {
		logger.info("Login failed detected");
		uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_login_fail", new Object[]{}, locale))); 
		return "login";
	}
	
	@RequestMapping(value = "admin/login")
	public String getLoginPage() {
		logger.debug("Calling Login page.");
		return "login";
	}
	
	@RequestMapping(value = "admin")
	public String getAdminPage()
	{
		return "users/list";
	}
}
