package com.worknest.seviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.worknest.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	public EmployeeRepository empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return empRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

}
