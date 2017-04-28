package com.waitnahi.net.serviceImpl;

import java.io.Serializable;


/**
 * The Class JwtAuthenticationResponse.
 */
/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
