package edu.escuelaing.arsw.springboot.app.models.entities;

import java.util.List;


/**
 * Clase Entity encargada de crear objetos de tipo pregunta para ser almacenados en los temas. 
 * 
 */
public class Pregunta {

	
	private Integer id;
	
	private String tema; //revisar esta linea si deberia ser un string  o Tema
	
	private String enunciado;
	
	private List<String> opciones;
	
	private String correcta;
	
	private Integer dificultad;
	
	private String foto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public Integer getDificultad() {
		return dificultad;
	}

	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	
}
