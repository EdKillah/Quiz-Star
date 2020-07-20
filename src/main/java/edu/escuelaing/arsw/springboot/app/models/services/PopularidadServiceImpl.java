package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.springboot.app.models.dao.IPopularidadDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Tema;
import edu.escuelaing.arsw.springboot.app.models.entities.Interes;

@Service
public class PopularidadServiceImpl implements IPopularidadService {

	@Autowired
	private IPopularidadDao daoPopularidad;

	@Override
	@Transactional(readOnly = true)
	public List<Interes> buscarVotos() {
		return daoPopularidad.buscarVotos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Interes> buscarVotosTema(Integer tema) {

		return daoPopularidad.buscarVotosTema(tema);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Interes> buscarVotosUsuario(String usuario) {
		return daoPopularidad.buscarInteresesUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Interes buscarVoto(Integer id) {
		return daoPopularidad.buscarVoto(id);
	}

	@Override
	@Transactional
	public void guardarVoto(Integer tema, String usuario, boolean isVoto) {		
		List<Interes> auxiliar = daoPopularidad.buscarInteresUsuarioTema(usuario, tema);
		System.out.println("Este es el auxiliar: " + auxiliar);
		if (auxiliar.size() > 0) {
			System.out.println("Esta eliminando el voto de: " + usuario + " en el tema: " + tema);
			if(auxiliar.get(0).getVoto()==0) {
				daoPopularidad.agregarVoto(tema,usuario);	
			}else {
				daoPopularidad.eliminarVoto(tema,usuario);
			}
			//eliminarVoto(tema, usuario);
			
		} else {
			System.out.println("El voto no existe y esta creando el voto");
			Interes v = new Interes();
			v.setTema(tema);
			v.setUsuario(usuario);
			if(isVoto) {
				v.setSiguiendo(0);
				v.setVoto(1);
			}else {
				v.setSiguiendo(1);
				v.setVoto(0);
			}
			v.setId(buscarVotos().size()+1);
			daoPopularidad.guardarInteres(v);
		}
	}

	@Override
	@Transactional
	public void eliminarVoto(Integer tema, String usuario) {
		daoPopularidad.eliminarVoto(tema, usuario);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Tema> buscarTemasPor(String orden) {
		return daoPopularidad.buscarTemasPor(orden);
	}

	@Override
	@Transactional
	public void agregarSeguidor(Integer tema, String usuario) {
		List<Interes> voto = daoPopularidad.buscarInteresUsuarioTema(usuario, tema);
		if (voto.size() == 0) {
			guardarVoto(tema,usuario,false);
		} else {
			System.out.println("Votos para agregar seguidor: " + voto);
			if (voto.get(0).getSiguiendo() == 1) {
				System.out.println("Lo va a eliminar");
				eliminarSeguidor(tema, usuario);
			} else {
				
				daoPopularidad.agregarSeguidor(tema, usuario);
			}
		}

	}

	@Override
	@Transactional
	public void eliminarSeguidor(Integer tema, String usuario) {
		daoPopularidad.eliminarSeguidor(tema, usuario);

	}

}