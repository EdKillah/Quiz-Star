package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Tema;
import edu.escuelaing.arsw.springboot.app.models.entities.Interes;

public interface IPopularidadDao {
	
	public List<Interes> buscarVotos();
	
	public List<Interes> buscarVotosTema(Integer tema);
	
	public List<Tema> buscarTemasPor(String orden);
	
	public List<Interes> buscarInteresesUsuario(String usuario);
	
	public  List<Interes> buscarInteresUsuarioTema(String usuario,Integer tema);
	
	public Interes buscarVoto(Integer id);
	
	public void guardarInteres(Interes pregunta);

	public void eliminarVoto(Integer tema, String usuario); //Esto se puede reemplazar por el voto
	
	public void agregarVoto(Integer tema, String usuario); //Esto se puede reemplazar por el voto
	
	public void agregarSeguidor(Integer tema, String usuario);
	
	public void eliminarSeguidor(Integer tema, String usuario); 
	

}

