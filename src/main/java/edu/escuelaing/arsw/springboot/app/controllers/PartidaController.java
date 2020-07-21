package edu.escuelaing.arsw.springboot.app.controllers;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.escuelaing.arsw.springboot.app.models.dao.IUserDao;
import edu.escuelaing.arsw.springboot.app.models.dao.IUsuarioDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Usuario;
import edu.escuelaing.arsw.springboot.app.models.services.IPreguntaService;

@Controller
public class PartidaController {
	
	static Queue<Usuario> queue = new ConcurrentLinkedQueue<>();
	static HashMap<Usuario,Integer> lista = new HashMap<>(); //Aca el Integer puede ser reemplzado x la clase Tema

	private Usuario usuario;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IPreguntaService preguntaDao;
	
	@GetMapping({"play/{id}"})
	public String enJuego(@PathVariable(value="id")Integer id,Model model) {
		if(id==0) {
			return "redirect:/temas_populares";
		}
		model.addAttribute("titulo", "Partida en juego.");
		
		if (preguntaDao.buscarPregunta(id) != null) {
			model.addAttribute("preguntas",preguntaDao.buscarPreguntasTema(id));
		} else {
			System.out.println("Pregunta no encontrada");

		}
		init(id);
		return "partida";
	}
	
	
	public void init(Integer tema) {
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    System.out.println("Este es el user: "+userDetail);
	    System.out.println("Este es el userNAME: "+userDetail.getUsername());
	    queue.add(userDao.findByUsername(userDetail.getUsername()));	    
	    usuario = userDao.findByUsername(userDetail.getUsername());
	    lista.put(usuario,tema);
	    System.out.println(queue);
	    Usuario inicial;
	    for (HashMap.Entry<Usuario, Integer> entry : lista.entrySet()) {	    	
	        System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
	        for (HashMap.Entry<Usuario, Integer> rival : lista.entrySet()) {
	        	if(!rival.getKey().equals(entry.getKey())) {
	        		if(rival.getValue()==entry.getValue()) {
	        			System.out.println("Ambos jugadores tienen el tema: "+rival.getValue());
	        		}
	        	}
	        }
	    }
	    
	 
	}
	
}
