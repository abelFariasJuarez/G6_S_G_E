package sge.repositorios;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

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
		TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );				
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		LocalDateTime desde = LocalDateTime.of(2018,10,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,10,28,20,00,00,15);
		
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		
		String q_consumo_hogar_periodo = 
"    SELECT r.oid, r.username, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"+
" FROM " + 
"    ( " + 
"    SELECT " + 
"        c.oid, " + 
"        u.username, " + 
"        ed.factor * d.consumoPorHora as consumoPorHora, " + 
"        IF(i.inicio < :desde, :desde, i.inicio) as inicio, " + 
"        IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin " + 
"    FROM cliente c" + 
"    inner join usuariosge u on c.oid = u.oid " +
"    inner join cliente_dispositivo cd on c.oid = cd.cliente_oid " +
"    inner join inteligente as s on cd.dispositivos_oid = s.oid " + 
"    inner join dispositivo as d on s.oid = d.oid " + 
"    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid " + 
"    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde " + 
"    inner join " + 
"        prueba.estadodispositivo ed " + 
"            on i.estado_oid = ed.oid ) as r"+
"    union "+	
"    SELECT c.oid, u.username, d.consumoPorHora * de.horasEncendidoPorDia * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo" + 
"    FROM cliente c" + 
"    inner join usuariosge u on c.oid = u.oid " +
"    inner join cliente_dispositivo cd on c.oid = cd.cliente_oid " +
"    inner join dispositivoestandar de on cd.dispositivos_oid = de.oid " + 
"    inner join dispositivo as d on de.oid = d.oid "; 		

		// Prep work
		Session session = this.getSession();

		// Get All Employees
		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(q_consumo_hogar_periodo);
		
		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);
		
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() 
					+ ";" + row[1].toString() 
					+ ";" + row[2].toString() 
					//+ ";" + row[3].toString() 
					//+ ";" + row[4].toString() 
					/*+ ";" + row[5].toString()
					+ ";" + row[6].toString()
					+ ";" + row[7].toString()*/
					);
		}
	}
	
	

}
