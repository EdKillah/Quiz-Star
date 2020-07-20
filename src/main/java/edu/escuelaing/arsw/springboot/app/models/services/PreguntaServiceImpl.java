package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.springboot.app.models.dao.IPreguntaDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;
import edu.escuelaing.arsw.springboot.app.models.entities.Tema;

@Service
public class PreguntaServiceImpl implements IPreguntaService {
	
	@Autowired
	private IPreguntaDao preguntaDao;
	
	@Override
	public ArrayList<Pregunta> preguntasRandom(){
		ArrayList<Pregunta> lista = (ArrayList<Pregunta>) buscarPreguntas();
		int size = lista.size();
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		Random r = new Random();
		int numAleatorio,i=0;
		while(i<3) {
			numAleatorio = r.nextInt(size)+1;
			if(!numeros.contains(numAleatorio)) {
				System.out.println("Enunciado: "+lista.get(numAleatorio-1).getEnunciado());
				numeros.add(numAleatorio);
				i++;
			}
			
		}
		System.out.println("Esos son los numeros: "+numeros);
		
	
		
		
		return lista;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pregunta> buscarPreguntas() {		
		return preguntaDao.buscarPreguntas();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Pregunta> buscarPreguntasTema(Integer tema) {
		
		return preguntaDao.buscarPreguntasTema(tema);
	}

	@Override
	@Transactional
	public void guardarPregunta(Pregunta pregunta) {
		preguntaDao.guardarPregunta(pregunta);
		
	}

	@Override
	@Transactional
	public Pregunta buscarPregunta(Integer id) {
		return preguntaDao.buscarPregunta(id);
		
	}
	


	@Override
	@Transactional
	public void eliminarPregunta(Integer id) {
		preguntaDao.eliminarPregunta(id);
		
	}

}
