package net.devmanuals.web.security;

import net.devmanuals.dao.UserDao;
import net.devmanuals.model.Users;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	private static Log log = LogFactory.getLog(AuthenticationProvider.class);

	@Autowired
	private UserDao userDao;

	/**
	 * Since we have a customize password approach in our database we will
	 * perform the password check in this method which the super class will
	 * call. The super class does not do the password verification therefore we
	 * can handle that in this method instead.
	 * 
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (userDetails instanceof SpringUserDetails) {
			SpringUserDetails tmUserDetails = (SpringUserDetails) userDetails;
			if (!tmUserDetails.verifyPassword((String) authentication.getCredentials())) {
				log.warn("****** Invalid password for user: " + userDetails.getUsername());
				throw new BadCredentialsException("Invalid password");
			}
		} else {
			throw new RuntimeException(
					"Expecting instance of TMUserDetails but got something else: "
							+ userDetails.getClass().getCanonicalName());
		}
	}

	/**
	 * Overrode method to retrieve the user as this get us from writing yet
	 * another class (UserDetailsService) to retrieve the user from the
	 * database. Else throws an AuthenticationException.
	 */
	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		Users user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found for : " + username);
		} else {
			return new SpringUserDetails(user);
		}
	}
}
