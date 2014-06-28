package com.luanvm.coffee.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/admin/system")
@Controller
public class SystemController {

	final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	MessageSource messageSource;


	@RequestMapping(method = RequestMethod.GET)
	public String show(Model model, HttpServletRequest httpServletRequest) {

		model.addAttribute("realpath", httpServletRequest.getRealPath("/"));
		return "system/systeminfo";
	}
}
