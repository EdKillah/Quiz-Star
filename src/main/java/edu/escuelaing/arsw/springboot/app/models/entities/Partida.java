package edu.escuelaing.arsw.springboot.app.models.entities;

import java.io.Serializable;
//import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="partidas")
public class Partida implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//private ArrayList<Integer> puntajes;
	
	private Integer tema;
	
	private String player1;
	
	private String player2;
	
	private Integer puntaje1;
	
	private Integer puntaje2;
	
	private Integer ronda;
	
	private String ganador;
	
	
	@PostConstruct
	public void init() {
		puntaje1 = 0;
		puntaje2 = 0;
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
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public Integer getTema() {
		return tema;
	}

	public void setTema(Integer tema) {
		this.tema = tema;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPuntaje1() {
		return puntaje1;
	}

	public void setPuntaje1(Integer puntaje1) {
		this.puntaje1 = puntaje1;
	}

	public Integer getPuntaje2() {
		return puntaje2;
	}

	public void setPuntaje2(Integer puntaje2) {
		this.puntaje2 = puntaje2;
	}
	
	
	
	
	
}
