package it.prova.personamanager.dao;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface IBaseDAO<T> {

	public List<T> list() throws Exception;

	public Optional<T> findOne(Long id) throws Exception;

	public void update(T input) throws Exception;

	public void insert(T input) throws Exception;

	public void delete(T input) throws Exception;

	// questo mi serve per l'injection
	public void setEntityManager(EntityManager entityManager);

}
