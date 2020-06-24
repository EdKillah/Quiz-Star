package edu.escuelaing.arsw.springboot.app.models.dao;

import java.util.List;

import edu.escuelaing.arsw.springboot.app.models.entities.Tema;

public interface ITemaDao {

	public List<Tema> buscarTemas();

	public void guardarTema(Tema tema);

	public Tema buscarTema(Integer id);

	public void eliminarTema(Integer id);
}
