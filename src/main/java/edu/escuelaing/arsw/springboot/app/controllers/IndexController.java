package edu.escuelaing.arsw.springboot.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import edu.escuelaing.arsw.springboot.app.models.entities.Interes;
import edu.escuelaing.arsw.springboot.app.models.services.IPopularidadService;
import edu.escuelaing.arsw.springboot.app.models.services.IPreguntaService;
import edu.escuelaing.arsw.springboot.app.models.services.ITemaService;

@Controller
public class IndexController {

	private List<Tema> temasOrdenados;

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IPreguntaService preguntaDao;
	@Autowired
	private ITemaService temaService;
	@Autowired
	private IPopularidadService popularidadService;

	/*
	 * @GetMapping({"play/{id}"}) public String
	 * enJuego(@PathVariable(value="id")Integer id,Model model) {
	 * model.addAttribute("titulo", "Partida en juego.");
	 * 
	 * if (preguntaDao.buscarPregunta(id) != null) {
	 * System.out.println("Si lo encontro pero esta trabado");
	 * //model.addAttribute("preguntas", preguntaDao.preguntasRandom());
	 * model.addAttribute("preguntas",preguntaDao.buscarPreguntasTema(id)); } else {
	 * System.out.println("Pregunta no encontrada");
	 * 
	 * } return "partida"; }
	 */

	@GetMapping({ "/", "" })
	public String alIniciar() {
		return "redirect:/temas";
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
		List<Interes> vox = popularidadService.buscarVotos();
		System.out.println("Vox: " + vox);
		List<Interes> votos = popularidadService.buscarVotosTema(3);
		System.out.println(votos + " Longitud: " + vox.size());
		model.addAttribute("votos_totales", vox.size()); // ESTO OBVIAMENTE DEBE MEJORAR
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

	@GetMapping("/votar/{id}")
	public String votar(@PathVariable(value = "id") Integer id, Model model) {
		System.out.println("Entrando en votar OK " + id);
		String usuario = "eduard21";
		popularidadService.guardarVoto(id,usuario,true);
		/*
		Interes interes = new Interes();
		interes.setTema(id);
		interes.setSiguiendo(0);
		interes.setUsuario("eduard21");
		interes.setVoto(1);
		List<Interes> votos = popularidadService.buscarVotos();
		//int longitud = votos.get(votos.size() - 1).getId() + 1;
		interes.setId(votos.size()+1);
		System.out.println("Creo bien el voto a parecer");
		*/
		//popularidadService.guardarVoto(interes);
		//popularidadService.guardarVoto(id,usuario);
		return "redirect:/temas";
	}

	@GetMapping("/temas_populares")
	public String populares(Model model) {
		
		//List<Voto> votos = popularidadService.buscarVotos();
		//for(Voto voto: votos) {
			
		//}
		
		if (temasOrdenados != null) {
			System.out.println("Es diferente de nulo");
			model.addAttribute("temas", temasOrdenados);
		} else {
			model.addAttribute("temas", temaService.buscarTemas());
			model.addAttribute("tema", temaService.buscarTema(2));
		}

		return "temas_populares";

	}

	@GetMapping("/populares/{orden}")
	public String ordenar(@PathVariable(value = "orden") String orden, Model model) {
		System.out.println("Este es el orden escogido: " + orden);
		model.addAttribute("temas", temaService.buscarTemas());
		model.addAttribute("tema", temaService.buscarTema(2));
		temasOrdenados = popularidadService.buscarTemasPor(orden);
		return "redirect:/temas_populares";
	}
	
	@GetMapping("/seguir/{id}")
	public String seguir(@PathVariable(value="id")Integer id, Model model) {
		popularidadService.agregarSeguidor(id, "eduard21");
		return "redirect:/temas_populares";
	}

	@GetMapping("/perfil")
	public String perfil(Model model) {
		model.addAttribute("nombre", "Diana");
		return "perfil";
	}

}
