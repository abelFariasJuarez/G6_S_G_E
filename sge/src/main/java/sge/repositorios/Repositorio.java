package sge.repositorios;

import java.sql.Timestamp;
import java.time.LocalDate;
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
import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoEstandar;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.driver.DriverBasico;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.Sensor;
import sge.modelo.usuarios.Cliente;

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
	
	public void consumo_hogar_periodo(LocalDateTime desde,LocalDateTime hasta) {
		
		TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );				
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		
		String consulta = 
"    SELECT oid, username, sum(IFNULL(consumo,0)) from ("+				
"            SELECT r.oid, r.username, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"+
"            FROM " + 
"                (SELECT " + 
"                    c.oid, " + 
"                    u.username, " + 
"                    ed.factor * d.consumoPorHora as consumoPorHora, " + 
"                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, " + 
"                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin " + 
"                FROM cliente c" + 
"                    inner join usuariosge u on c.oid = u.oid " +
"                    inner join cliente_dispositivo cd on c.oid = cd.cliente_oid " +
"                    inner join inteligente as s on cd.dispositivos_oid = s.oid " + 
"                    inner join dispositivo as d on s.oid = d.oid " + 
"                    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid " + 
"                    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde " + 
"                    inner join estadodispositivo ed on i.estado_oid = ed.oid ) as r"+
"            union "+	
"            SELECT c.oid, u.username, IFNULL(d.consumoPorHora,0) * IFNULL(de.horasEncendidoPorDia,0) * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo" + 
"            FROM cliente c" + 
"                inner join usuariosge u on c.oid = u.oid " +
"                inner join cliente_dispositivo cd on c.oid = cd.cliente_oid " +
"                inner join dispositivoestandar de on cd.dispositivos_oid = de.oid " + 
"                inner join dispositivo as d on de.oid = d.oid "+
"    ) as h "+
"    GROUP BY oid, username"; 		

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);
		
		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);
		
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() 
					+ ";" + row[1].toString() 
					+ ";" + row[2].toString() 
					);
		}
	}
	
	public void consumo_promedio_tipo_dispositivo_periodo(LocalDateTime desde,LocalDateTime hasta) {
		
		TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );				
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		
		String consulta = 
"    SELECT deviceType, AVG(IFNULL(consumo,0)) from ("+				
"            SELECT r.deviceType, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"+
"            FROM " + 
"                (SELECT " + 
"                    d.deviceType, " +
"                    ed.factor * d.consumoPorHora as consumoPorHora, " + 
"                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, " + 
"                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin " + 
"                FROM inteligente as s " + 
"                    inner join dispositivo as d on s.oid = d.oid " + 
"                    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid " + 
"                    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde " + 
"                    inner join estadodispositivo ed on i.estado_oid = ed.oid ) as r"+
"            union "+	
"            SELECT d.deviceType, d.consumoPorHora * de.horasEncendidoPorDia * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo" + 
"            FROM dispositivoestandar de " + 
"                inner join dispositivo as d on de.oid = d.oid "+
"    ) as h "+
"    GROUP BY deviceType "; 		

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);
		
		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);
		
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(
							row[0].toString() 
					+ ";" + row[1].toString()  
					);
		}
	}	

	public void consumo_transformador_periodo(LocalDateTime desde,LocalDateTime hasta) {
		
		TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );				
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		
		String consulta = 
"    SELECT oid, sum(IFNULL(consumo,0)) from ("+				
"            SELECT r.oid, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"+
"            FROM " + 
"                (SELECT " + 
"                    t.oid, " +                     
"                    ed.factor * d.consumoPorHora as consumoPorHora, " + 
"                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, " + 
"                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin " + 
"                FROM transformador t" +
"                    inner join transformador_cliente tc on t.oid = tc.transformador_oid " +
"                    inner join cliente c on tc.clientes_oid = c.oid " +
"                    inner join cliente_dispositivo cd on c.oid = cd.cliente_oid " +
"                    inner join inteligente as s on cd.dispositivos_oid = s.oid " + 
"                    inner join dispositivo as d on s.oid = d.oid " + 
"                    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid " + 
"                    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde " + 
"                    inner join estadodispositivo ed on i.estado_oid = ed.oid ) as r"+
"            union "+	
"            SELECT c.oid, IFNULL(d.consumoPorHora,0) * IFNULL(de.horasEncendidoPorDia,0) * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo" + 
"            FROM transformador t" +
"                inner join transformador_cliente tc on t.oid = tc.transformador_oid " +
"                inner join cliente c on tc.clientes_oid = c.oid " +
"                inner join cliente_dispositivo cd on c.oid = cd.cliente_oid " +
"                inner join dispositivoestandar de on cd.dispositivos_oid = de.oid " + 
"                inner join dispositivo as d on de.oid = d.oid "+
"    ) as h "+
"    GROUP BY oid";		

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);
		
		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);
		
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(
							row[0].toString() 
					+ ";" + row[1].toString()  
					);
		}
	}	
	
	public void cargaDeDatosIniciales() {
		//Inicializamos las variables
		Cliente cliente = new Cliente();
		String nombreDispo = "";
		DispositivoInteligente inteligente = new DispositivoInteligente();
		DispositivoEstandar estandar = new DispositivoEstandar();
	
		try {
			//Cargamos los usuarios clientes.
			//Cliente 1
			cliente = this.clientes().findBy("username", "fperez");
			if (cliente == null) {
				cliente = new Cliente("Felipe", "Perez", "Medrano 123", LocalDate.of(1995, 4, 7), "fperez",
						"menToL2017", "Dni", 11222333, 1543312311);
				cliente.setAhorroAutomatico(false);
				cliente.setUbicacion(new Ubicacion(10.0, 90.0));
				this.persistir(cliente);
			}
			
			//Cliente 2
			cliente = this.clientes().findBy("username", "mlopez");
			if (cliente == null) {
				cliente = new Cliente("Mariano", "Lopez", "Mozart 2300", LocalDate.of(1995, 01, 10), "mlopez",
						"ml1234", "Dni", 38888383, 1533333333);
				cliente.setAhorroAutomatico(false);
				cliente.setUbicacion(new Ubicacion(20.0, 95.0));
				this.persistir(cliente); 
			}
			
			//Agrego los dispositivos con sus respectivos due�os
			//DISPOSITIVO 1 (Inteligente)
			nombreDispo = "Aire acondicionado de 3500 frigor�as";
			inteligente = (DispositivoInteligente) this.dispositivos().findBy("nombre",
					nombreDispo);
			if (inteligente == null) {
				inteligente = new DispositivoInteligente(nombreDispo, 1.613,"mlopez", false,false, new DriverBasico());
				inteligente.prender();
				this.persistir(inteligente);	
			}
			
			//DISPOSITIVO 2 (Estandar)
			nombreDispo = "Televisor color tubo fluorecente de 21";
			estandar = (DispositivoEstandar) this.dispositivos().findBy("nombre",
					nombreDispo);
			if (estandar == null) {
				estandar = new DispositivoEstandar(nombreDispo, 0.075, "mlopez",false,18.0);
				
				this.persistir(estandar);	
			}
			
			//DISPOSITIVO 3 (Inteligente)
			nombreDispo = "Televisor Led 40";
			inteligente = (DispositivoInteligente) this.dispositivos().findBy("nombre",
					nombreDispo);
			if (inteligente == null) {
				inteligente = new DispositivoInteligente(nombreDispo, 0.08,"fperez", true,true, new DriverBasico());
				inteligente.prender();
				this.persistir(inteligente);	
			}
			
			//DISPOSITIVO 4 (Estandar)
			nombreDispo = "Ventilador de pie";
			estandar = (DispositivoEstandar) this.dispositivos().findBy("nombre",
					nombreDispo);
			if (estandar == null) {
				estandar = new DispositivoEstandar(nombreDispo, 0.09, "fperez",true, 6.0);
				this.persistir(estandar);	
			}
			
			//DISPOSITIVO 5 (Inteligente)
			nombreDispo = "PC de escritorio";
			inteligente = (DispositivoInteligente) this.dispositivos().findBy("nombre",
					nombreDispo);
			if (inteligente == null) {
				inteligente = new DispositivoInteligente(nombreDispo, 0.4,"fperez", true,false, new DriverBasico());
				inteligente.prender();
				this.persistir(inteligente);	
			}
			
//			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
