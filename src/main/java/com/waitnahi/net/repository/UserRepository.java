/**
 * 
 */
package com.waitnahi.net.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.waitnahi.net.dto.UserDto;
import com.waitnahi.net.model.User;

/**
 * @author pramod
 * @since 15-Apr-2017
 */
@Repository("userRepository")
public interface UserRepository extends GenericRepository<User, Long> {

	public User findUserByUsernameAndPassword(String username, String password);
	
	public User findByUsername(String username);
	
	@Query("SELECT NEW com.waitnahi.net.dto.UserDto(user.userId, user.username, user.password, user.email, user.enabled, user.lastPasswordResetDate) FROM USER AS user")
	public List<UserDto> findAllUsers();

}
