package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Partida;

public interface IPartidaService {

	
	public List<Partida> buscarPartidas();
	
	public Partida buscarPartida();
	
	public void guardarPartida(Partida partida);
	
	
}
