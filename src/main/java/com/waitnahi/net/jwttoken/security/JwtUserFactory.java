package com.waitnahi.net.jwttoken.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.waitnahi.net.model.User;
import com.waitnahi.net.model.UserAuthority;


/**
 * A factory for creating JwtUser objects.
 */
/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    /**
     * Creates the.
     *
     * @param user the user
     * @return the jwt user
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getUserAuthority()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    /**
     * Map to granted authorities.
     *
     * @param authorities the authorities
     * @return the list
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<UserAuthority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName().name()))
                .collect(Collectors.toList());
    }
}
