package it.prova.personamanager.dao;

import it.prova.personamanager.model.Persona;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

public class PersonaDAOImpl implements PersonaDAO {
	private EntityManager entityManager;

	@Override
	public List<Persona> list() throws Exception {
		return entityManager.createQuery("from Persona", Persona.class).getResultList();
	}

	@Override
	public Optional<Persona> findOne(Long id) throws Exception {
		return Optional.ofNullable(entityManager.find(Persona.class, id));
	}

	@Override
	public void update(Persona input) throws Exception {
		input = entityManager.merge(input);

	}

	@Override
	public void insert(Persona input) throws Exception {
		entityManager.persist(input);

	}

	@Override
	public void delete(Persona input) throws Exception {
		entityManager.remove(entityManager.merge(input));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Persona> findByExample(Persona example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Regista r where r.id = r.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" r.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" r.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (example.getDataNascita() != null) {
			whereClauses.add("r.dataDiNascita >= :dataDiNascita ");
			paramaterMap.put("dataDiNascita", example.getDataNascita());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Persona> typedQuery = entityManager.createQuery(queryBuilder.toString(), Persona.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
