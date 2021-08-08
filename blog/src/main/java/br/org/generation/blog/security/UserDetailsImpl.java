package br.org.generation.blog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.blog.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String userPassword;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return null;
	}
	
	public UserDetailsImpl(Usuario usuario) {
		this.userName = usuario.getNome();
		this.userPassword = usuario.getSenha();
	}
	
	public UserDetailsImpl() {
		
	}
	
	@Override
	public String getUsername() {
		return userName;
	}	
	
	@Override
	public String getPassword() {
		return userPassword;
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
