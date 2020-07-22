package edu.escuelaing.arsw.springboot.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.escuelaing.arsw.springboot.app.endpoints.QSEndPoint;
import edu.escuelaing.arsw.springboot.app.models.dao.IUserDao;
import edu.escuelaing.arsw.springboot.app.models.dao.IUsuarioDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Partida;
import edu.escuelaing.arsw.springboot.app.models.entities.Pregunta;
import edu.escuelaing.arsw.springboot.app.models.entities.Usuario;
import edu.escuelaing.arsw.springboot.app.models.services.IPartidaService;
import edu.escuelaing.arsw.springboot.app.models.services.IPreguntaService;

@Controller
public class PartidaController {
	
	static Queue<Usuario> queues = new ConcurrentLinkedQueue<>();
	static HashMap<Usuario,Integer> lista = new HashMap<>(); //Aca el Integer puede ser reemplzado x la clase Tema
	static HashMap<Partida,Integer> partidas = new HashMap<>();
	
	private Usuario usuario;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IPartidaService partidaService;
	
	@Autowired
	private IUserDao userDao; //Esto debe ser con el service no con el dao
	
	@Autowired
	private IPreguntaService preguntaDao;
	
	@GetMapping({"play/{id}"})
	public String enJuego(@PathVariable(value="id")Integer id,Model model) {
		if(id==0) {
			return "redirect:/temas_populares";
		}	
		model.addAttribute("titulo", "Partida en juego.");
		
		if (preguntaDao.buscarPregunta(id) != null) {
			List<Pregunta> preguntas = init(id);
			if(preguntas!=null) {
				model.addAttribute("preguntas",init(id));
				
			}
			else {
				return "redirect:/emparejando";
			}
			
		} else {
			System.out.println("Pregunta no encontrada");
		}
		//init(id);
		return "partida";
	}
	
	
	public List<Pregunta> init(Integer tema) {
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    System.out.println("Este es el user: "+userDetail);
	    System.out.println("Este es el userNAME: "+userDetail.getUsername());
	    queues.add(userDao.findByUsername(userDetail.getUsername()));	    
	    usuario = userDao.findByUsername(userDetail.getUsername());
	    lista.put(usuario,tema);
	    System.out.println("Queses propio: "+queues);
	    
	    for (HashMap.Entry<Usuario, Integer> entry : lista.entrySet()) {	    	
	        System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
	        for (HashMap.Entry<Usuario, Integer> rival : lista.entrySet()) {
	        	if(!rival.getKey().equals(entry.getKey())) {
	        		if(rival.getValue()==entry.getValue()) {
	        			System.out.println("Ambos jugadores tienen el tema: "+rival.getValue()); //comentario
	        			crearPartida(rival.getKey(),entry.getKey(),entry.getValue());
	        			List<Pregunta> preguntas = preguntaDao.buscarPreguntasTema(tema);
	        			return preguntas;
	        			
	        		}
	        	}
	        }
	    }
	    return null;
	    
	 
	}
	
	public void crearPartida(Usuario u1, Usuario u2, Integer tema) {
		System.out.println("!!!!!!! El usuario1: "+u1.getUsername());
		System.out.println("******* El usuario2: "+u2.getUsername());
		Partida partida = new Partida();
		partida.setPlayer1(u1.getUsername());
		partida.setPlayer2(u2.getUsername());
		partidas.put(partida, tema);
		System.out.println("Estos son los queues actuales: "+ QSEndPoint.queue);
		System.out.println("Creo la partida: "+partidas);
	}
	
	@GetMapping("/emparejando")
	public String emparejando(Model model) {		
		return "emparejando";
	}
	
}
