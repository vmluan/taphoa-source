/**
 * Copyright (c) 2013 THKAssociates,Inc. All rights reserved.
 * Filename   : HomeController.java
 * Description: 
 * @author    : Khanh.Pham
 * Created    : Sep 3, 2013
 */


package com.luanvm.coffee.web.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController{

	final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String getHomePage(Model ciModel,@RequestParam(value="lang", required=false)String id) {
		Locale locale = LocaleContextHolder.getLocale();	
		if(StringUtils.isNotEmpty(id)){
			if(id.equalsIgnoreCase(com.luanvm.coffee.helper.Constants.VIETNAMESE))
				locale.setDefault(new Locale(id));
		}
//		return "home";
		return "taphoa";
	}
	
	
	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	@Transactional
	public String getCharts() {
		return "redirect:/charts?index";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@Transactional
	public String getCategories() {
		return "redirect:/categories?index";
	}
	
	@RequestMapping(value = "/medias", method = RequestMethod.GET)
	public String getMedias() {
		return "home/medias";
	}
	
	@RequestMapping(value = "/stories", method = RequestMethod.GET)
	public String getStories() {
		return "home/stories";
	}
	
	@RequestMapping(value = "/downloads", method = RequestMethod.GET)
	public String getDownloads() {
		return "redirect:/downloads?index";
	}
}
