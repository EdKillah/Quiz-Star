package edu.escuelaing.arsw.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.escuelaing.arsw.springboot.app.models.dao.IUsuarioDao;
import edu.escuelaing.arsw.springboot.app.models.services.IPreguntaService;

@Controller
public class PartidaController {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IPreguntaService preguntaDao;
	
	@GetMapping({"play/{id}"})
	public String enJuego(@PathVariable(value="id")Integer id,Model model) {
		model.addAttribute("titulo", "Partida en juego.");
		
		if (preguntaDao.buscarPregunta(id) != null) {
			System.out.println("Si lo encontro pero esta trabado");
			//model.addAttribute("preguntas", preguntaDao.preguntasRandom());
			model.addAttribute("preguntas",preguntaDao.buscarPreguntasTema(id));
		} else {
			System.out.println("Pregunta no encontrada");

		}
		return "partida";
	}
	
}
