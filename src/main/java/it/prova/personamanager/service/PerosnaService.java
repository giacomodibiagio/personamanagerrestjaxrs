package it.prova.personamanager.service;


import it.prova.personamanager.dao.PersonaDAO;
import it.prova.personamanager.model.Persona;

import java.util.List;

public interface PerosnaService {
	public List<Persona> listAllElements() throws Exception;

	public Persona caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Persona personaInstance) throws Exception;

	public void inserisciNuovo(Persona personaInstance) throws Exception;

	public void rimuovi(Persona personaInstance) throws Exception;
	
	public List<Persona> findByExample (Persona persona) throws Exception;
	// per injection
	public void setPerosnaDAO(PersonaDAO personaDAO);
}
