package net.devmanuals.web.security;

import java.util.Collection;

import net.devmanuals.model.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringUserDetails implements UserDetails {

	private static final long serialVersionUID = -6258905107308258364L;
	private Users user;

	public static Users getUserFromSecurityContext() {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SpringUserDetails) {
			SpringUserDetails tmUserDetails = (SpringUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			return tmUserDetails.getUser();
		} else {
			return null;
		}
	}

	public SpringUserDetails(Users user) {
		this.user = user;
	}

	public Users getUser() {
		return this.user;
	}

	public boolean verifyPassword(String plainPassword) {
		return this.user.verifyPassword(plainPassword);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
