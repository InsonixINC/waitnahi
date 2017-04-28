package com.waitnahi.net.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.waitnahi.net.jwttoken.security.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Class JwtTokenUtil.
 * Using Claim : Claim is piece of information that describes given identity on some aspect.
 */
/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3301605591108950415L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";

	private static final String AUDIENCE_UNKNOWN = "unknown";
	private static final String AUDIENCE_WEB = "web";
	private static final String AUDIENCE_MOBILE = "mobile";
	private static final String AUDIENCE_TABLET = "tablet";

	private String secret = "mySecret";

	private Long expiration = 604800l;

	/**
	 * Gets the username from token.
	 *
	 * @param token
	 *            the token
	 * @return the username from token
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			//Claim based authentication on the bases of token.
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Gets the created date from token.
	 *
	 * @param token
	 *            the token
	 * @return the created date from token
	 */
	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			//Claim based authentication on the bases of token.
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	/**
	 * Gets the expiration date from token.
	 *
	 * @param token
	 *            the token
	 * @return the expiration date from token
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			//Claim based authentication on the bases of token.
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * Gets the audience from token.
	 *
	 * @param token
	 *            the token
	 * @return the audience from token
	 */
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			//Claim based authentication on the bases of token.
			final Claims claims = getClaimsFromToken(token);
			audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	/**
	 * Gets the claims from token.
	 *
	 * @param token
	 *            the token
	 * @return the claims from token
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			//Claim based authentication on the bases of token.
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * Generate expiration date.
	 *
	 * @return the date
	 */
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * Checks if is token expired.
	 *
	 * @param token
	 *            the token
	 * @return the boolean
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Checks if is created before last password reset.
	 *
	 * @param created
	 *            the created
	 * @param lastPasswordReset
	 *            the last password reset
	 * @return the boolean
	 */
	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	/**
	 * Generate audience.
	 *
	 * @param device
	 *            the device
	 * @return the string
	 */
	private String generateAudience(Device device) {
		String audience = AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = AUDIENCE_WEB;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		}
		return audience;
	}

	/**
	 * Ignore token expiration.
	 *
	 * @param token
	 *            the token
	 * @return the boolean
	 */
	private Boolean ignoreTokenExpiration(String token) {
		String audience = getAudienceFromToken(token);
		return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
	}

	/**
	 * Generate token.
	 *
	 * @param userDetails
	 *            the user details
	 * @param device
	 *            the device
	 * @return the string
	 */
	public String generateToken(UserDetails userDetails, Device device) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	/**
	 * Generate token.
	 *
	 * @param claims
	 *            the claims
	 * @return the string
	 */
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * Can token be refreshed.
	 *
	 * @param token
	 *            the token
	 * @param lastPasswordReset
	 *            the last password reset
	 * @return the boolean
	 */
	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getCreatedDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
				&& (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	/**
	 * Refresh token.
	 *
	 * @param token
	 *            the token
	 * @return the string
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			//Claim based authentication on the bases of token.
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * Validate token.
	 *
	 * @param token
	 *            the token
	 * @param userDetails
	 *            the user details
	 * @return the boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getCreatedDateFromToken(token);
		// final Date expiration = getExpirationDateFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token)
				&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
	}
}