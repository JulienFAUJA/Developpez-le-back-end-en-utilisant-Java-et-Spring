package com.openclassroom.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.openclassroom.models.DBUser;
import com.openclassroom.repositories.DBUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private DBUserRepository DBUserRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		DBUser user = DBUserRepository.findByName(name);
		
		return new User(user.getName(), user.getPassword(), getGrantedAuthorities(user.getRole()));
	}

	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}