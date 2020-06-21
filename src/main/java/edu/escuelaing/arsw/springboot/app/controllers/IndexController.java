package edu.escuelaing.arsw.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping({"/index","","/","play"})
	public String enJuego(Model model) {
		model.addAttribute("titulo", "Partida en juego.");
		return "partida";
	}
}
