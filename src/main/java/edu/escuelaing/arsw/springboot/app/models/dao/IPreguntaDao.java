package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;

public interface IPreguntaDao {

	public List<Pregunta> buscarPreguntas();

	public void guardarPregunta(Pregunta pregunta);

	public Pregunta buscarPregunta(Integer id);

	public void eliminarPregunta(Integer id);
}
