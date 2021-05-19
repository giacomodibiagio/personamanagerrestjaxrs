package it.prova.personamanager.dao;


import it.prova.personamanager.model.Persona;

import java.util.List;

public interface PersonaDAO extends IBaseDAO<Persona>{
	public List<Persona> findByExample(Persona example) throws Exception;
}
