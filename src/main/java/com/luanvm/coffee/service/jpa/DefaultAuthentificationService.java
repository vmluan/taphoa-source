package com.luanvm.coffee.service.jpa;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luanvm.coffee.domain.User;
import com.luanvm.coffee.repository.UserRepository;

import static com.google.common.collect.Lists.newArrayList;

@Service("authentificationService")
public class DefaultAuthentificationService implements UserDetailsService  {

	final Logger logger = LoggerFactory.getLogger(DefaultAuthentificationService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		List<String> roles = Arrays.asList("ROLE_USER", "User");

		
		Collection<GrantedAuthority> grantedAuthorities = toGrantedAuthorities(roles);
		User user = userRepository.findByUsername(userName);
		if (user != null)
		{
			String password = user.getPassword();
			
			boolean enabled = true;
			boolean userNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean userNonLocked = true;
	
			return new org.springframework.security.core.userdetails.User(userName,
					password, enabled, userNonExpired, credentialsNonExpired,
					userNonLocked, grantedAuthorities);
		}
		
		String errorMsg = "User with user name: " + userName + "could not be found";
		logger.debug(errorMsg);
		throw new UsernameNotFoundException(errorMsg);
	}

	public static Collection<GrantedAuthority> toGrantedAuthorities(List<String> roles) {
		
		List<GrantedAuthority> result = newArrayList();

		for (String role : roles) {
			result.add(new SimpleGrantedAuthority(role));
		}

		return result;
	}

}
