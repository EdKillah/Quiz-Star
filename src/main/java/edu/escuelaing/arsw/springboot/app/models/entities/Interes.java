package edu.escuelaing.arsw.springboot.app.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intereses")
public class Interes {
	
	@Id
	private Integer id;
	
	private String usuario;
	
	private Integer tema;
	
	private Integer siguiendo;
	
	private Integer voto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getTema() {
		return tema;
	}

	public void setTema(Integer tema) {
		this.tema = tema;
	}

	public Integer getSiguiendo() {
		return siguiendo;
	}

	public void setSiguiendo(Integer seguidor) {
		this.siguiendo = seguidor;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}
	
	
	
}
