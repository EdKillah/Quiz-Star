package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import edu.escuelaing.arsw.springboot.app.models.entities.Interes;
import edu.escuelaing.arsw.springboot.app.models.entities.Tema;

@Repository
public class PopularidadDaoImpl implements IPopularidadDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Interes> buscarVotos() {
		return em.createQuery("from Interes").getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interes> buscarVotosTema(Integer tema) {
		String queryStr = "select * from intereses where tema = ?1";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, tema);
        return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tema> buscarTemasPor(String orden) {
		String queryStr = "select * from temas ORDER BY id "+orden;
        Query query = em.createNativeQuery(queryStr,Tema.class);
        //query.setParameter(1, orden);
        return query.getResultList();

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Interes> buscarInteresesUsuario(String usuario) {
		String queryStr = "select * from intereses where usuario = ?1";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, usuario);
        return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Interes> buscarInteresUsuarioTema(String usuario,Integer tema) {
		String queryStr = "select * from intereses where usuario = ?1 AND tema=?2";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, usuario);
        query.setParameter(2, tema);
        return query.getResultList();
		
	}
	
	

	@Override
	public Interes buscarVoto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarInteres(Interes interes) {
		System.out.println("Esta entrando bien en agregar interes");
		em.persist(interes);
	}

	@Override
	public void eliminarVoto(Integer tema, String usuario) {
		//em.remove(voto); //Se supone que así quedaría mas pro pero vamos por lo seguro buscando parametros		
		//String queryStr = "delete from intereses where usuario = ?1 AND tema=?2";
		String queryStr = "UPDATE intereses SET voto =?1 WHERE usuario = ?2 AND tema=?3";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, 0);
        query.setParameter(2, usuario);
        query.setParameter(3, tema);
        query.executeUpdate();
        System.out.println("ELIMINO CORRECTAMENTE EL VOTO");

	}
	
	@Override
	public void agregarVoto(Integer tema, String usuario) {
		//em.remove(voto); //Se supone que así quedaría mas pro pero vamos por lo seguro buscando parametros		
		//String queryStr = "delete from intereses where usuario = ?1 AND tema=?2";
		System.out.println("Por aca esta entenadoo?");
		String queryStr = "UPDATE intereses SET voto =?1 WHERE usuario = ?2 AND tema=?3";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, 1);
        query.setParameter(2, usuario);
        query.setParameter(3, tema);
        query.executeUpdate();
        System.out.println("AGREGO CORRECTAMENTE EL VOTO A UN USUARIO REGISTRADO EN LA BD");

	}
	
	@Override
	public void agregarSeguidor(Integer tema, String usuario) {
		System.out.println("Esta entrando agregar seguidor");
		String queryStr = "update intereses set siguiendo =?1  where usuario = ?2 AND tema=?3";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, 1);
        query.setParameter(2, usuario);
        query.setParameter(3, tema);
        query.executeUpdate();
		System.out.println("Agrego el seguidor correctamente.");

	}
	
	@Override
	public void eliminarSeguidor(Integer tema, String usuario) {
		System.out.println("Esta entrando ELIMINAR el seguidor");
		String queryStr = "update intereses set siguiendo =?1  where usuario = ?2 AND tema=?3";
        Query query = em.createNativeQuery(queryStr,Interes.class);
        query.setParameter(1, 0);
        query.setParameter(2, usuario);
        query.setParameter(3, tema);
        query.executeUpdate();
		System.out.println("Elimino al seguidor correctamente.");
	}

}
