package edu.escuelaing.arsw.springboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.arsw.springboot.app.models.dao.ITemaDao;
import edu.escuelaing.arsw.springboot.app.models.entities.Tema;

@Service
public class TemaServiceImpl implements ITemaService {

	@Autowired
	private ITemaDao temaDao;
	
	@Override
	@Transactional
	public List<Tema> buscarTemas() {
		return temaDao.buscarTemas();
		
	}

	@Override
	@Transactional
	public void guardarTema(Tema tema) {
		temaDao.guardarTema(tema);
		
	}

	@Override
	@Transactional
	public Tema buscarTema(Integer id) {
		return temaDao.buscarTema(id);
	}

	@Override
	@Transactional
	public void eliminarTema(Integer id) {
		temaDao.eliminarTema(id);
		
	}

}
