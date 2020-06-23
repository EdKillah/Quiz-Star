package edu.escuelaing.arsw.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.escuelaing.arsw.springboot.app.models.dao.IPreguntaDao;
import edu.escuelaing.arsw.springboot.app.models.dao.IUsuarioDao;

@Controller
public class IndexController {

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IPreguntaDao preguntaDao;

	@GetMapping({ "/index", "", "/", "play" })
	public String enJuego(Model model) {
		model.addAttribute("titulo", "Partida en juego.");
		if(preguntaDao.buscarPregunta(1) != null) {
			System.out.println("Si lo encontro pero esta trabado");
			model.addAttribute("pregunta", preguntaDao.buscarPregunta(1));
		}
		else {
			System.out.println("Pregunta no encontrada");
			
		}
		return "partida";
	}

	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("titulo", "Detalle de los usuarios");
		model.addAttribute("usuarios", usuarioDao.buscarUsuarios());
		return "usuarios";
	}

	@GetMapping("/preguntas")
	public String preguntas(Model model) {
		model.addAttribute("titulo", "Preguntas de Quiz-Star");
		if (preguntaDao.buscarPreguntas() != null) {
			model.addAttribute("preguntas", preguntaDao.buscarPreguntas());
		}else {
			System.out.println("El numero de preguntas actuales es 0");
		}
		return "preguntas";
	}
}
