package com.waitnahi.net.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.waitnahi.net.utils.JwtTokenUtil;

/**
 * The Class AuthenticationTokenFilter.
 * This class verify token for every request header.
 */
/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private String tokenHeader = "Authorization";

	/**
	 * Do filter internal.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param chain
	 *            the chain
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String authToken = request.getHeader(this.tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		logger.info("checking authentication for user " + username);
		
		// current logged in username in Spring Security :
		// SecurityContextHolder.getContext().getAuthentication()
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// It is not compelling necessary to load the use details from the
			// database. You could also store the information
			// in the token and read it from it. It's up to you ;)
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			// For simple validation it is completely sufficient to just check
			// the token integrity. We don't have to call
			// the database compellingly.
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}
}