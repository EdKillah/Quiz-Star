package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;

import edu.escuelaing.arsw.springboot.app.models.dao.IUserDao;

import edu.escuelaing.arsw.springboot.app.models.entities.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDao daoUsuario;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Entra en el userDetails: "+username);
		Usuario usuario = daoUsuario.findByUsername(username); // Esto puede cambiar por el que yo cree
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if (usuario == null) {
			System.out.println("El usuario no existe");
			throw new UsernameNotFoundException("El usuario: " + username + " no existe");
		}
		
		return new User(username, usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
	}

}
