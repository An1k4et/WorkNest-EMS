package com.worknest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.worknest.seviceImpl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUserDetailsService customUserDetailService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> {
			csrf.disable();
		});
		
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/login","/forget-password","/images/**").permitAll();
			auth.requestMatchers("/add-employee","/edit-employee").hasAnyAuthority("ADMIN","SUPERADMIN","HR");
			auth.anyRequest().authenticated();
		});
		
		http.formLogin(form -> {
			form.loginPage("/login");
			form.loginProcessingUrl("/submit-login");
			form.successForwardUrl("/dashboard");
			form.defaultSuccessUrl("/dashboard");
			form.failureUrl("/login?error=true");
		});
		
		http.logout(logout -> {
			logout.logoutSuccessUrl("/login?logout=true");
			logout.permitAll();
		});
		
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
