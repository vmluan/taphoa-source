package com.luanvm.coffee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luanvm.coffee.domain.User;

public interface UserService {

	List<User> findAll();
	
	User findById(Integer id);
	
	User save(User user);
	
	Page<User> findAllByPage(Pageable pageable);	
	
}
