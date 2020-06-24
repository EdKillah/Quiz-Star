package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import edu.escuelaing.arsw.springboot.app.models.entities.Tema;


@Repository
public class TemaDaoImpl implements ITemaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tema> buscarTemas() {
		return em.createQuery("from Tema").getResultList();
	}

	@Override
	public void guardarTema(Tema tema) {
		em.persist(tema);

	}

	@Override
	public Tema buscarTema(Integer id) {
		
		return em.find(Tema.class, id);
	}

	@Override
	public void eliminarTema(Integer id) {
		em.remove(id);

	}

}
