package it.prova.personamanager.web.listener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("personamanager");
			// questa chiamata viene fatta qui per semplicità ma in genere è meglio trovare
			// altri modi per fare init
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

}
