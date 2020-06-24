package edu.escuelaing.arsw.springboot.app.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="temas")
public class Tema {

	@Id	
	private Integer id;
	
	private String nombre;
	
	private String eslogan;
	
	//private List<Pregunta> preguntas;
	
	private String usuario;

	private String foto;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEslogan() {
		return eslogan;
	}

	public void setEslogan(String eslogan) {
		this.eslogan = eslogan;
	}

	/*
	 * public List<Pregunta> getPreguntas() { return preguntas; }
	 * 
	 * public void setPreguntas(List<Pregunta> preguntas) { this.preguntas =
	 * preguntas; }
	 */
	
	

	public String getFoto() {
		return foto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	
}
