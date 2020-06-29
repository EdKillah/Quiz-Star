package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;

public interface IPreguntaService {
	
	public List<Pregunta> buscarPreguntas();

	public void guardarPregunta(Pregunta pregunta);

	public Pregunta buscarPregunta(Integer id);

	public void eliminarPregunta(Integer id);
	
	public ArrayList<Pregunta> preguntasRandom();
	
	public List<Pregunta> buscarPreguntasTema(Integer tema);

}
