package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;

import edu.escuelaing.arsw.springboot.app.models.entities.Partida;


@Repository
public class PartidaDaoImpl implements IPartidaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Partida> buscarPartidas() {
		return em.createQuery("from Partida").getResultList();
	}

	@Override
	public Partida buscarPartida() {
		return null;
	}

	@Override
	public void guardarPartida(Partida partida) {
		em.persist(partida);
	}

}
