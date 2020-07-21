package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Partida;

public interface IPartidaDao {

	public List<Partida> buscarPartidas();
	
	public Partida buscarPartida();
	
	public void guardarPartida(Partida partida);
	
	
}
