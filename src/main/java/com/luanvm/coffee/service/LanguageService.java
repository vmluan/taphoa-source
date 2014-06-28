package com.luanvm.coffee.service;

import java.util.List;

import com.luanvm.coffee.domain.Language;

public interface LanguageService {

	List<Language> findAll();
	
	Language findById(Integer id);
	
	Language findByCode(String code);
	
}
