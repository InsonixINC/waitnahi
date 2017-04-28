package com.waitnahi.net.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.waitnahi.net.authentication.RestAuthenticationEntryPoint;
import com.waitnahi.net.filter.AuthenticationTokenFilter;

/**
 * The Class RestSecurityConfig.
 */
/**
 * @author pramod
 * @since 15-Apr-2017
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new AuthenticationTokenFilter();
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests().antMatchers("/auth/**").permitAll().anyRequest().authenticated();

		// Custom JWT based security filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().cacheControl();

	}

}
