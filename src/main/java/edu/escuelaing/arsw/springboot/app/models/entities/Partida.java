package edu.escuelaing.arsw.springboot.app.models.entities;

import java.util.ArrayList;

public class Partida {


	private ArrayList<Integer> puntajes;
	
	private  Integer ronda;
	
	private String ganador;
	
	private String Player1;
	
	private String Player2;
	
	private String tema;

	public ArrayList<Integer> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<Integer> puntajes) {
		this.puntajes = puntajes;
	}

	public Integer getRonda() {
		return ronda;
	}

	public void setRonda(Integer ronda) {
		this.ronda = ronda;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public String getPlayer1() {
		return Player1;
	}

	public void setPlayer1(String player1) {
		Player1 = player1;
	}

	public String getPlayer2() {
		return Player2;
	}

	public void setPlayer2(String player2) {
		Player2 = player2;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
	
	
	
}
