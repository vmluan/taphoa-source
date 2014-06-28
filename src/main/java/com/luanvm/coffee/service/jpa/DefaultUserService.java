
package com.luanvm.coffee.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.luanvm.coffee.domain.User;
import com.luanvm.coffee.repository.UserRepository;
import com.luanvm.coffee.service.UserService;

@Service("userService")
@Repository
@Transactional
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly=true)
	public List<User> findAll() {
		return Lists.newArrayList(userRepository.findAll());
	}

	@Transactional(readOnly=true)
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

	public User save(User User) {
		return userRepository.save(User);
	}

	@Transactional(readOnly=true)
	public Page<User> findAllByPage(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
