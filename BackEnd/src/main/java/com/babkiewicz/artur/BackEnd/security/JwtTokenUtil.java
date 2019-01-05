package com.babkiewicz.artur.BackEnd.security;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil implements Serializable {
	
	private static final long serialVersionUID = -7668856869156415801L;
	static final String CLAM_KEY_USERNAME = "sub";
	static final String CLAM_KEY_AUDIENCE = "audience";
	static final String CLAM_KEY_CREATED = "created";
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String getUsernameFromToken(String token) {		
		String userName = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			userName = claims.getSubject();
		}catch(Exception e) {
			userName = null;
		}
		return userName;
	}
	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			claims = null;
		}
		return claims;
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	private Date getExpirationDateFromToken(String token) {
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			if(claims != null) {
				expiration = claims.getExpiration();
			} else {
				expiration = null;
			}
		}catch(Exception e) {
			expiration = null;
		}
		return expiration;
	}	
}
