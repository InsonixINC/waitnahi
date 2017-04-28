package com.waitnahi.net.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waitnahi.net.jwttoken.security.JwtAuthenticationRequest;
import com.waitnahi.net.jwttoken.security.JwtUser;
import com.waitnahi.net.serviceImpl.JwtAuthenticationResponse;
import com.waitnahi.net.utils.JwtTokenUtil;

@RestController
public class UserAuthenticationRestController {

	private String tokenHeader = "Authorization";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * This method is call when user try to login, Ig login success then token
	 * will created and return from response header. Creates the authentication
	 * token.
	 *
	 * @param authenticationRequest
	 *            the authentication request
	 * @param device
	 *            the device
	 * @return the response entity
	 * @throws AuthenticationException
	 *             the authentication exception
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			Device device) throws AuthenticationException {

		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		// Set the current logged in username in Spring Security.
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, device);
		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	
	/**
	 * Refresh and get authentication token.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
