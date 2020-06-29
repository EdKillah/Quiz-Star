package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;


import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;

@Repository
public class PreguntaDaoImpl implements IPreguntaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override	
	public List<Pregunta> buscarPreguntas() {

		return em.createQuery("from Pregunta").getResultList();
	}

	@Override
	public void guardarPregunta(Pregunta pregunta) {
		em.persist(pregunta);

	}

	@Override
	public Pregunta buscarPregunta(Integer id) {

		return em.find(Pregunta.class, id);
	}

	@Override
	public void eliminarPregunta(Integer id) {
		em.remove(buscarPregunta(id));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pregunta> buscarPreguntasTema(Integer tema) {
        String queryStr = "select * from preguntas where tema = ?1";
        Query query = em.createNativeQuery(queryStr,Pregunta.class);
        query.setParameter(1, tema);
        return query.getResultList();
		


	}

}
