package com.luanvm.coffee.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.luanvm.coffee.domain.Language;
import com.luanvm.coffee.repository.LanguageRepository;
import com.luanvm.coffee.service.LanguageService;

@Service("languageService")
@Repository
@Transactional
public class DefaultLanguageService implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	@Transactional(readOnly = true)
	public List<Language> findAll() {
		return Lists.newArrayList(languageRepository.findAll());
	}
	
	
	@Transactional(readOnly = true)
	public Language findById(Integer id) {
		return languageRepository.findOne(id);
	}
	

	@Transactional(readOnly = true)
	public Language findByCode(String code) {
		return languageRepository.findByCode(code);
	}
	
}
