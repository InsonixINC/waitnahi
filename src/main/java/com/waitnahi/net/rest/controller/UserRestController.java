package com.waitnahi.net.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waitnahi.net.dto.UserDto;
import com.waitnahi.net.jwttoken.security.JwtUser;
import com.waitnahi.net.model.User;
import com.waitnahi.net.service.UserService;
import com.waitnahi.net.utils.JwtTokenUtil;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class UserRestController.
 */
/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
@RestController
@RequestMapping(value = "/user")
public class UserRestController {

    private String tokenHeader = "Authorization";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
	private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
    /**
	 * Gets the all user.
	 * 
	 * @return the all user
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public List<UserDto> getAllUser() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/addUser/{user}", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
	public User addUser(@PathVariable User user) {
		return userService.save(user);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteById(id);
	}


}
