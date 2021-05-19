package it.prova.personamanager.service;


import it.prova.personamanager.dao.PersonaDAO;
import it.prova.personamanager.model.Persona;
import it.prova.personamanager.web.listener.LocalEntityManagerFactoryListener;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonaServiceImpl implements PerosnaService {
	private PersonaDAO personaDao;

	@Override
	public List<Persona> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			personaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return personaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Persona caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			personaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return personaDao.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Persona personaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			personaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			personaDao.update(personaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Persona personaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			personaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			personaDao.insert(personaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Persona personaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			personaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			personaDao.delete(personaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void setPerosnaDAO(PersonaDAO personaDAO) {
		this.personaDao = personaDAO;

	}

	@Override
	public List<Persona> findByExample(Persona persona) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			personaDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return personaDao.findByExample(persona);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
