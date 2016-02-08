package br.com.boleto.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("boletoPU");
	}

	@Produces
	@RequestScoped
	public EntityManager create() {
		return factory.createEntityManager();
	}

	public void close(@Disposes @Any EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}
	
}
