package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;

@Repository
public class PreguntaDaoImpl implements IPreguntaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> buscarPreguntas() {

		return em.createQuery("from Pregunta").getResultList();
	}

	@Override
	@Transactional
	public void guardarPregunta(Pregunta pregunta) {
		em.persist(pregunta);

	}

	@Override
	@Transactional
	public Pregunta buscarPregunta(Integer id) {

		return em.find(Pregunta.class, id);
	}

	@Override
	@Transactional
	public void eliminarPregunta(Integer id) {
		em.remove(buscarPregunta(id));

	}

}
