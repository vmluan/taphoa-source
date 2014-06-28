package com.luanvm.coffee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luanvm.coffee.domain.Story;

public interface StoryService {

	List<Story> findAll();
	
	Story findById(Integer id);
	
	Story save(Story story);
	
	Page<Story> findAllByPage(Pageable pageable);	
	
}
