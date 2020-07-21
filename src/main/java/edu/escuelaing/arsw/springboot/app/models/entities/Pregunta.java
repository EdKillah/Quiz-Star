package edu.escuelaing.arsw.springboot.app.models.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Clase Entity encargada de crear objetos de tipo pregunta para ser almacenados en los temas. 
 * 
 */
@Entity
@Table(name="preguntas")
public class Pregunta {

	@Id
	private Integer id;
	
	private Integer tema; //revisar esta linea si deberia ser un string  o Tema
	
	private String enunciado;
	
	//private List<String> opciones;
	
	private String opcion1;
	
	private String opcion2;
	
	private String opcion3;
	
	private String opcion4;
	
	private Integer correcta;
	
	private Integer dificultad;
	
	private String foto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTema() {
		return tema;
	}

	public void setTema(Integer tema) {
		this.tema = tema;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
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

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	public Integer getCorrecta() {
		return correcta;
	}

	public void setCorrecta(Integer correcta) {
		this.correcta = correcta;
	}
	
	
	
	
}
