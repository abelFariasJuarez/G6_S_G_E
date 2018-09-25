package sge.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import sge.modelo.Persistible;

public abstract class Repositorio {

	private static final String PERSISTENCE_UNIT_NAME = "db";
	protected EntityManager entityManager;
	EntityManagerFactory emFactory;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void abrir() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.setEntityManager(emFactory.createEntityManager());
	}

	public void cerrar() {
		entityManager.close();
		emFactory.close();
	}

	public void persistir(Persistible unPersistible) {
		entityManager.getTransaction().begin();
		entityManager.persist(unPersistible);
		entityManager.getTransaction().commit();
	}

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	protected Object findBy(Class<?> clazz, String campo, String valor) {
		Object objReturn;
		this.abrir();
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq(campo, valor));
		objReturn = criteria.uniqueResult();
		this.cerrar();
		return objReturn;	
	}
}
