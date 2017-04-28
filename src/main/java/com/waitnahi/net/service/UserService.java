package com.waitnahi.net.service;

import java.util.List;

import com.waitnahi.net.dto.UserDto;
import com.waitnahi.net.model.User;

/**
 * @author Pramod Maurya
 * @since : Apr 14, 2017
 */
public interface UserService extends BaseService<User> {
	
	public List<UserDto> getAllUsers();
	
	
}
