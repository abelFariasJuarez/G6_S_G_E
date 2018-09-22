package sge.repositorios;

import javax.persistence.EntityManager;

import sge.modelo.Persistible;

public abstract class Repositorio {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cerrar() {
		entityManager.close();
	}

	public void persistir(Persistible unPersistible) {
		entityManager.getTransaction().begin();
		entityManager.persist(unPersistible);
		entityManager.getTransaction().commit();
	}

}
