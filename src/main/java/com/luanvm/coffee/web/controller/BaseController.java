/**
 * Copyright (c) 2013 THKAssociates,Inc. All rights reserved.
 * Filename   : BaseController.java
 * Description: 
 * @author    : Khanh.Pham
 * Created    : Sep 3, 2013
 */

package com.luanvm.coffee.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.luanvm.coffee.domain.Language;
import com.luanvm.coffee.helper.Constants;
import com.luanvm.coffee.service.LanguageService;

public class BaseController {
	final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private LanguageService languageService;

	public String getLanguage() {
		Locale locale = LocaleContextHolder.getLocale();
		if (locale.getLanguage().equalsIgnoreCase(Constants.VIETNAMESE)) {
			return locale.getLanguage();
		} else {
			return Constants.ENGLISH_AMERICAN;
		}
	}

	protected Integer getLanguageId(String code) {
		Language language = languageService.findByCode(code);
		return language.getLanguageid();
	}
}
