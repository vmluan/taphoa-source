package com.luanvm.coffee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.luanvm.coffee.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	User findByUsername(String username);
}