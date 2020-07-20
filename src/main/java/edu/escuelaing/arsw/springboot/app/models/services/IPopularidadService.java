package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Tema;
import edu.escuelaing.arsw.springboot.app.models.entities.Interes;

public interface IPopularidadService {

	public List<Interes> buscarVotos();
	
	public List<Interes> buscarVotosTema(Integer tema);
	
	public List<Tema> buscarTemasPor(String orden);
	
	public List<Interes> buscarVotosUsuario(String usuario);
	
	public Interes buscarVoto(Integer id);
	
	//public void guardarVoto(Interes pregunta);
	
	public void guardarVoto(Integer tema, String usuario, boolean isVoto);

	public void eliminarVoto(Integer tema, String usuario); //Esto se puede reemplazar por el voto
	
	public void agregarSeguidor(Integer tema, String usuario); 
	
	public void eliminarSeguidor(Integer tema, String usuario); 
	
}
