package com.waitnahi.net.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waitnahi.net.dto.UserDto;
import com.waitnahi.net.model.User;
import com.waitnahi.net.repository.UserRepository;
import com.waitnahi.net.service.UserService;

/**
 * @author Pramod Maurya
 * @since : Apr 14, 2017
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	private UserRepository userRepository;

	@Autowired
	public void setUserDao(UserRepository userRepository) {
		this.userRepository = userRepository;
		setGenericRepository(userRepository);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAllUsers();
	}

}
