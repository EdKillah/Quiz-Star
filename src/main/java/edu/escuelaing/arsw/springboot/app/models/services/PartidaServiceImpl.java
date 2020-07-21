package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.springboot.app.models.dao.IPartidaDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Partida;


@Service
public class PartidaServiceImpl implements IPartidaService {

	
	@Autowired
	private IPartidaDao partidaDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Partida> buscarPartidas() {
		return partidaDao.buscarPartidas();
	}

	@Override
	@Transactional(readOnly = true)
	public Partida buscarPartida() {
		return partidaDao.buscarPartida();
	}

	@Override
	@Transactional
	public void guardarPartida(Partida partida) {
		partidaDao.guardarPartida(partida);
	}

}
