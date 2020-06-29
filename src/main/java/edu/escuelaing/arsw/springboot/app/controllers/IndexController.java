package edu.escuelaing.arsw.springboot.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.escuelaing.arsw.springboot.app.models.dao.IUsuarioDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;
import edu.escuelaing.arsw.springboot.app.models.entities.Tema;
import edu.escuelaing.arsw.springboot.app.models.services.IPreguntaService;
import edu.escuelaing.arsw.springboot.app.models.services.ITemaService;

@Controller
public class IndexController {

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IPreguntaService preguntaDao;
	@Autowired
	private ITemaService temaService;

	@GetMapping({ "/index", "", "/", "play/{id}" })
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
		} else {
			System.out.println("El numero de preguntas actuales es 0");
		}
		return "preguntas";
	}

	@GetMapping("/temas")
	public String temas(Model model) {
		model.addAttribute("titulo", "Detalle de los temas");
		model.addAttribute("temas", temaService.buscarTemas());
		model.addAttribute("tema", temaService.buscarTema(2));
		return "temas";
	}

	@RequestMapping(value = "/crear_tema", method = RequestMethod.GET)
	public String crearCliente(Model model) {
		Tema tema = new Tema();
		model.addAttribute("titutlo", "Formulario de tema");
		model.addAttribute("tema", tema);
		return "crear_tema";

	}

	@RequestMapping(value = "/crear_tema", method = RequestMethod.POST)
	public String guardar(Tema tema, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash,
			SessionStatus status) {

		if (!foto.isEmpty()) {

			String rootPath = "C://Temp//quizz_star";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Ha subido correctamente " + foto.getOriginalFilename());
				tema.setId(temaService.buscarTemas().size() + 1);
				tema.setFoto(foto.getOriginalFilename());

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		temaService.guardarTema(tema);
		flash.addFlashAttribute("success", "Tema creado con exito.");
		status.setComplete();
		return "redirect:temas";
	}

	@RequestMapping(value = "/crear_preguntas", method = RequestMethod.GET)
	public String crearPregunta(Model model) {
		Pregunta pregunta = new Pregunta();
		model.addAttribute("titutlo", "Formulario de tema");
		model.addAttribute("pregunta", pregunta);
		return "crear_preguntas";

	}

	@RequestMapping(value = "/crear_preguntas", method = RequestMethod.POST)
	public String guardarPregunta(Pregunta pregunta, Model model, @RequestParam("file") MultipartFile foto,
			RedirectAttributes flash, SessionStatus status) {

		if (!foto.isEmpty()) {

			String rootPath = "C://Temp//quizz_star";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Ha subido correctamente " + foto.getOriginalFilename());

				pregunta.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
		pregunta.setId(preguntaDao.buscarPreguntas().size() + 1);
		preguntaDao.guardarPregunta(pregunta);
		flash.addFlashAttribute("success", "Pregunta creada con exito.");
		status.setComplete();
		return "redirect:preguntas";
	}

}
