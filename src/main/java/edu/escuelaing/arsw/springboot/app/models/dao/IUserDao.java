package edu.escuelaing.arsw.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.arsw.springboot.app.models.entities.Usuario;



public interface IUserDao extends CrudRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
}
