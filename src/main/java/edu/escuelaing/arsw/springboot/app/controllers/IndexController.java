package edu.escuelaing.arsw.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.escuelaing.arsw.springboot.app.models.dao.IUsuarioDao;

@Controller
public class IndexController {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@GetMapping({"/index","","/","play"})
	public String enJuego(Model model) {
		model.addAttribute("titulo", "Partida en juego.");
		return "partida";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("titulo", "Detalle de los usuarios");
		model.addAttribute("usuarios",usuarioDao.buscarUsuarios());
		return "usuarios";
	}
}
