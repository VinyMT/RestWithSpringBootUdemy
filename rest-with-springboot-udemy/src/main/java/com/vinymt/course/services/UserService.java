package com.vinymt.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vinymt.course.converter.DozerConverter;
import com.vinymt.course.converter.custom.PersonConverter;
import com.vinymt.course.data.model.Person;
import com.vinymt.course.data.vo.v1.PersonVO;
import com.vinymt.course.data.vo.v1.PersonVOV2;
import com.vinymt.course.exception.ResourceNotFoundException;
import com.vinymt.course.repository.PersonRepository;
import com.vinymt.course.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repo.findByUsername(username);
		if(user != null) {
			return null;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}
	
	
}
