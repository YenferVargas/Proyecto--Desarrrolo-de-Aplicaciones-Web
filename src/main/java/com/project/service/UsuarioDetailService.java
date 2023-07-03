package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.model.Usuario;

@Service
public class UsuarioDetailService implements UserDetailsService{

	@Autowired
	private IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario usuario = userService.findByCorreo(correo);
		UserBuilder userbuilder = null;
		if (usuario != null) {
			userbuilder = User.withUsername(correo);
			userbuilder.disabled(false);
			userbuilder.password(usuario.getPassword());
			userbuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return userbuilder.build();
	}

}
