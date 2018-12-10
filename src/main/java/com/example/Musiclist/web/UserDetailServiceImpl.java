package com.example.Musiclist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Musiclist.domain.User;
import com.example.Musiclist.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository userepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userepository = userRepository;
	}
	
	//tarkistetaan käyttäjä, salasana ja rooli
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currUser = userepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(currUser.getRole()));
		return user;
	}

}
