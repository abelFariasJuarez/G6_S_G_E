package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import sge.modelo.IPersistible;
import sge.modelo.regla.Sensor;

public class Repositorio {

	private static final String PERSISTENCE_UNIT_NAME = "db";
	protected EntityManager entityManager;
	EntityManagerFactory emFactory;

	private Sensores sensores;
	private Comparaciones comparaciones;
	private Condiciones condiciones;
	private Reglas reglas;
	private Clientes clientes;
	private Dispositivos dispositivos;
	private Transformadores transformadores;
	private RestriccionesHorasFamilia restriccionesHorasFamilia;
	private Zonas zonas;

	public Repositorio() {
	}

	public Repositorio(EntityManager em) {
		this.entityManager = em;
	}

	public Sensores sensores() {
		if (sensores == null) {
			sensores = new Sensores(entityManager);
		}
		return sensores;
	}

	public Comparaciones comparaciones() {
		if (comparaciones == null) {
			comparaciones = new Comparaciones(entityManager);
		}
		return comparaciones;
	}

	public Condiciones condiciones() {
		if (condiciones == null) {
			condiciones = new Condiciones(entityManager);
		}
		return condiciones;
	}

	public Reglas reglas() {
		if (reglas == null) {
			reglas = new Reglas(entityManager);
		}
		return reglas;
	}

	public Clientes clientes() {
		if (clientes == null) {
			clientes = new Clientes(entityManager);
		}
		return clientes;
	}

	public Dispositivos dispositivos() {
		if (dispositivos == null) {
			dispositivos = new Dispositivos(entityManager);
		}
		return dispositivos;
	}

	public Transformadores transformadores() {
		if (transformadores == null) {
			transformadores = new Transformadores(entityManager);
		}
		return transformadores;
	}

	public RestriccionesHorasFamilia restriccionesHorasFamilia() {
		if (restriccionesHorasFamilia == null) {
			restriccionesHorasFamilia = new RestriccionesHorasFamilia(entityManager);
			restriccionesHorasFamilia.crearRetriccionesSiNoExisten();
		}

		return restriccionesHorasFamilia;
	}

	public Zonas zonas() {
		if (zonas == null) {
			zonas = new Zonas(entityManager);
		}
		return zonas;
	}

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

	public void persistir(IPersistible unPersistible) {
		entityManager.getTransaction().begin();
		entityManager.persist(unPersistible);
		entityManager.getTransaction().commit();
	}

	public void borrar(IPersistible unPersistible) {
		entityManager.getTransaction().begin();
		entityManager.remove(unPersistible);
		entityManager.getTransaction().commit();
	}

	public void flush() {
		entityManager.flush();
	}

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	protected Object findBy(Class<?> clazz, String campo, Object valor) {
		Object objReturn;
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq(campo, valor));
		objReturn = criteria.uniqueResult();
		return objReturn;
	}
	
	public void consumo_hogar_periodo() {

		String q_consumo_hogar_periodo = 
				"SELECT s.oid"						
						+ ",i.oid as ioid" 
						+ ", ed.factor *" 
						+" d.consumoPorHora *"  
						+" TIMESTAMPDIFF(SECOND, i.inicio, IFNULL(i.fin, now()) ) / (3600) as p"
						+ ",i.inicio" 
						+ ",IFNULL(i.fin, now()) as fin"
						+ ",ed.factor" 
						+ ",d.consumoPorHora" 
						+ " FROM inteligente as s"
				+ " inner join dispositivo as d on s.oid = d.oid"
				+ " inner join inteligente_intervalo as si on s.oid = si.inteligente_oid"
				+ " inner join intervalo as i on si.intervalos_oid = i.oid"
				+ " inner join prueba.estadodispositivo ed on i.estado_oid = ed.oid";
		// Prep work
		Session session = this.getSession();

		// Get All Employees
		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(q_consumo_hogar_periodo);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() 
					+ ";" + row[1].toString() 
					+ ";" + row[2].toString() 
					+ ";" + row[3].toString() 
					//+ ";" + row[4].toString() 
					//+ ";" + row[5].toString()
					);
		}
	}
	
	

}
