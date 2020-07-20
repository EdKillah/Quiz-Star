package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.springboot.app.models.entities.Usuario;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> buscarUsuarios() {
		
		return em.createQuery("from Usuario").getResultList();
	}

	@Override
	@Transactional
	public void guardarUsuario(Usuario usuario) {
		if(usuario!=null) { //Modificar esto para que quede un metodo de actualizar
			em.merge(usuario);
		}
		else {
			em.persist(usuario);
		}
		
	}

	
	@Override
	@Transactional
	public Usuario buscarUsuario(String username) {
		return em.find(Usuario.class, username);
		
	}

	@Override
	@Transactional
	public void eliminarUsuario(String username) {
		em.remove(buscarUsuario(username));
	}



}
