package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.springboot.app.models.entities.Usuario;

public interface IUsuarioDao  {
	
	//public Usuario findByUsername(String username);

	public List<Usuario> buscarUsuarios();

	public void guardarUsuario(Usuario usuario);

	public Usuario buscarUsuario(String username);

	public void eliminarUsuario(String username);

}
