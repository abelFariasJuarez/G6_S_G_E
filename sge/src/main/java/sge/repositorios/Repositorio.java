package sge.repositorios;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import sge.modelo.IPersistible;
import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoEstandar;
import sge.modelo.dispositivo.DispositivoFactoryMethod;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.driver.DriverBasico;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.Sensor;
import sge.modelo.usuarios.Administrador;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.GestorCliente;
import sge.modelo.usuarios.UsuarioSGE;

public class Repositorio {

	private static final String PERSISTENCE_UNIT_NAME = "db";
	@PersistenceContext
	protected EntityManager entityManager;
	EntityManagerFactory emFactory;

	private Sensores sensores;
	private Comparaciones comparaciones;
	private Condiciones condiciones;
	private Reglas reglas;
	private Clientes clientes;
	private Dispositivos dispositivos;
	private DispositivosDisponibles dispositivosDisponibles;
	private Transformadores transformadores;
	private RestriccionesHorasFamilia restriccionesHorasFamilia;
	private Zonas zonas;
	private Ubicaciones ubicaciones;
    private static Repositorio instance;
    
    private Repositorio(){}
    
    public static Repositorio getInstance(){
        if(instance == null){
            instance = new Repositorio();
        }
        return instance;
    }	

	/*public Repositorio() {
	}*/

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

	public DispositivosDisponibles dispositivosDisponibles() {
		if (dispositivosDisponibles == null) {
			dispositivosDisponibles = new DispositivosDisponibles(entityManager);
		}
		return dispositivosDisponibles;
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
		}

		return restriccionesHorasFamilia;
	}
	
	public Ubicaciones ubicaciones() {
		if (ubicaciones == null) {
			ubicaciones = new Ubicaciones(entityManager);
		}

		return ubicaciones;
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

	protected void persistir(IPersistible unPersistible) {
		entityManager.getTransaction().begin();
		entityManager.persist(unPersistible);
		entityManager.getTransaction().commit();
	}
	
	protected void detach(IPersistible unPersistible) {
		entityManager.detach(unPersistible);
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

	public Object findBy(Class<?> clazz, String campo, Object valor) {
		Object objReturn;
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq(campo, valor));
		objReturn = criteria.uniqueResult();
		return objReturn;
	}
	
	public Object findBy(Class<?> clazz, String campo1,  String campo2,Object valor1,Object valor2) {
		Object objReturn;
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz)
				.add(Restrictions.eq(campo1, valor1))
				.add(Restrictions.eq(campo2, valor2));
		objReturn = criteria.uniqueResult();
		return objReturn;
	}	
	
	protected List<?> allOf(Class<?> clazz) {
		Session session = this.getSession();
		Criteria criteria= session.createCriteria(clazz);
		List<?> objects = criteria.list();
		return objects;
	}
	
	protected List<?> allDistinctOf(Class<?> clazz) {
		Session session = this.getSession();
		Criteria criteria= session.createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<?> objects = criteria.list();
		return objects;
	}	
	
	
	
	public List<RowReport> consumo_hogar_periodo(LocalDateTime desde, LocalDateTime hasta) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		
		List<RowReport> rows = new ArrayList<RowReport>(); 
		
		for(Cliente c : this .clientes().all() )
		{
			RowReport row = new RowReport();
			row.setOid(c.getOid());
			row.setUsername(c.getUsername());
			row.setConsumo(c.consumoEnPeriodo(desde, hasta));
			rows.add(row);
		}
		return rows;
		/*
		String consulta = "    SELECT oid, username, sum(IFNULL(consumo,0)) as consumo from ("
				+ "            SELECT r.oid, r.username, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"
				+ "            FROM " + "                (SELECT " + "                    c.oid, "
				+ "                    u.username, "
				+ "                    ed.factor * d.consumoPorHora as consumoPorHora, "
				+ "                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, "
				+ "                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin "
				+ "                FROM cliente c" + "                    inner join usuariosge u on c.oid = u.oid "
				+ "                    left join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                    left join inteligente as s on cd.dispositivos_oid = s.oid "
				+ "                    left join dispositivo as d on s.oid = d.oid "
				+ "                    left join inteligente_intervalo as si on s.oid = si.inteligente_oid "
				+ "                    left join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde "
				+ "                    left join estadodispositivo ed on i.estado_oid = ed.oid ) as r"
				+ "            union "
				+ "            SELECT c.oid, u.username, IFNULL(d.consumoPorHora,0) * IFNULL(de.horasEncendidoPorDia,0) * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo"
				+ "            FROM cliente c" + "                inner join usuariosge u on c.oid = u.oid "
				+ "                left join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                left join dispositivoestandar de on cd.dispositivos_oid = de.oid "
				+ "                left join dispositivo as d on de.oid = d.oid " + "    ) as h "
				+ "    GROUP BY oid, username";

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);

		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);

		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() + ";" + row[1].toString() + ";" + row[2].toString());
		}
		return rows;*/
	}

	public List<RowReport> consumo_promedio_tipo_dispositivo_periodo(LocalDateTime desde, LocalDateTime hasta) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		List<RowReport> rows = new ArrayList<RowReport>(); 
		String consulta = "    SELECT deviceType, AVG(IFNULL(consumo,0)) as promedio from ("
				+ "            SELECT r.deviceType, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"
				+ "            FROM " + "                (SELECT " + "                    d.deviceType, "
				+ "                    ed.factor * d.consumoPorHora as consumoPorHora, "
				+ "                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, "
				+ "                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin "
				+ "                FROM inteligente as s "
				+ "                    inner join dispositivo as d on s.oid = d.oid "
				+ "                    left join inteligente_intervalo as si on s.oid = si.inteligente_oid "
				+ "                    left join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde "
				+ "                    left join estadodispositivo ed on i.estado_oid = ed.oid ) as r"
				+ "            union "
				+ "            SELECT d.deviceType, d.consumoPorHora * de.horasEncendidoPorDia * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo"
				+ "            FROM dispositivoestandar de "
				+ "                inner join dispositivo as d on de.oid = d.oid " + "    ) as h "
				+ "    GROUP BY deviceType ";

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);

		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);

		List<Object[]> list = query.list();
		for (Object[] row : list) {
			RowReport r = new RowReport();
			System.out.println(row[0].toString() + ";" + row[1].toString());
			r.setDeviceType(row[0].toString());
			r.setPromedio((Double) row[1]);
			rows.add(r);
		}
		return rows;
	}

	public List<RowReport> consumo_transformador_periodo(LocalDateTime desde, LocalDateTime hasta) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);
		List<RowReport> rows = new ArrayList<RowReport>(); 
		
		for(Transformador t : this.transformadores().all() )
		{
			RowReport row = new RowReport();
			row.setOid(t.getOid());			
			row.setConsumo(t.consumoEnPeriodo(desde, hasta));
			rows.add(row);
		}
		return rows;
		/*String consulta = "    SELECT oid, sum(IFNULL(consumo,0)) as consumo from ("
				+ "            SELECT r.oid, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"
				+ "            FROM " + "                (SELECT " + "                    t.oid, "
				+ "                    ed.factor * d.consumoPorHora as consumoPorHora, "
				+ "                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, "
				+ "                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin "
				+ "                FROM transformador t"
				+ "                    left join transformador_cliente tc on t.oid = tc.transformador_oid "
				+ "                    left join cliente c on tc.clientes_oid = c.oid "
				+ "                    left join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                    left join inteligente as s on cd.dispositivos_oid = s.oid "
				+ "                    left join dispositivo as d on s.oid = d.oid "
				+ "                    left join inteligente_intervalo as si on s.oid = si.inteligente_oid "
				+ "                    left join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde "
				+ "                    left join estadodispositivo ed on i.estado_oid = ed.oid ) as r"
				+ "            union "
				+ "            SELECT c.oid, IFNULL(d.consumoPorHora,0) * IFNULL(de.horasEncendidoPorDia,0) * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo"
				+ "            FROM transformador t"
				+ "                left join transformador_cliente tc on t.oid = tc.transformador_oid "
				+ "                left join cliente c on tc.clientes_oid = c.oid "
				+ "                left join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                left join dispositivoestandar de on cd.dispositivos_oid = de.oid "
				+ "                left join dispositivo as d on de.oid = d.oid " + "    ) as h " + "    GROUP BY oid";

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);

		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);

		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() + ";" + row[1].toString());
		}
		return rows;*/
	}

	
	
	public void cargaDeDatosIniciales() {
		this.restriccionesHorasFamilia().crearRetriccionesSiNoExisten();
		DispositivoFactoryMethod.cargaBasica();
		GestorCliente gestor = new GestorCliente();
		gestor.setRepositorio(this);
		gestor.cargarClientesZonasTransformadores();
		gestor.transformadoresAsignacionZona();
		gestor.asignarClientesATransformadores();
		
		Administrador admin = (Administrador) this.findBy(Administrador.class, "username", "admin");
		if (admin == null) {
			admin = new Administrador("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19),
					"admin", "admin");
		}		
		admin.setUsername("admin");
		admin.setPassword("1234");
		this.persistirUsuario(admin);
		
		// Inicializamos las variables
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		
		String nombreDispo = "";
		DispositivoInteligente inteligente = new DispositivoInteligente();
		DispositivoEstandar estandar = new DispositivoEstandar();

		// Cargamos los usuarios clientes.
		// Cliente 1
		cliente1 = this.clientes().findBy("username", "fperez");
		if (cliente1 == null) {
			cliente1 = new Cliente("Felipe", "Perez", "Medrano 123", LocalDate.of(1995, 4, 7), "fperez", "menToL2017",
					"Dni", 11222333, 1543312311);
			cliente1.setAhorroAutomatico(true);
			cliente1.setUbicacion(new Ubicacion(10.0, 90.0));
			this.clientes().persistir(cliente1);
		}

		// Cliente 2
		cliente2 = this.clientes().findBy("username", "mlopez");
		if (cliente2 == null) {
			cliente2 = new Cliente("Mariano", "Lopez", "Mozart 2300", LocalDate.of(1995, 01, 10), "mlopez", "ml1234",
					"Dni", 38888383, 1533333333);
			cliente2.setAhorroAutomatico(false);
			cliente2.setUbicacion(new Ubicacion(20.0, 95.0));
			this.clientes().persistir(cliente2);
		}

		// Cliente 3
		cliente3 = this.clientes().findBy("username", "afarias");
		if (cliente3 == null) {
			cliente3 = new Cliente("Abel", "Farias", "Mozart 2300", LocalDate.of(1988, 01, 10), "afarias", "af1234",
					"Dni", 38888384, 1533333334);
			cliente3.setAhorroAutomatico(false);
			cliente3.setUbicacion(new Ubicacion(20.0, 95.0));
			this.clientes().persistir(cliente3);
		}

		Dispositivo air = DispositivoFactoryMethod.getDispositivoByCode("Aire2200");
		air.setConsumoPorHora(10.0);
		
		// Agrego los dispositivos con sus respectivos dueños
		// DISPOSITIVO 1 (Inteligente)
		nombreDispo = "Aire acondicionado de 3500 frigorías";
		inteligente = (DispositivoInteligente) this.dispositivos().findBy("nombre", nombreDispo);
		if (inteligente == null) {
			inteligente = new DispositivoInteligente(nombreDispo, 1.613, "mlopez", false, false, new DriverBasico());
			inteligente.prender();
			
			 RestriccionHorasFamilia rest_air = this.restriccionesHorasFamilia().findBy("codigo", "AIRCONDITIONER");
			inteligente.setRestriccionHoras(rest_air);
			cliente2.addDispositivo(inteligente);
			this.clientes().persistir(cliente2);

		}

		// DISPOSITIVO 2 (Estandar)
		nombreDispo = "Televisor color tubo fluorecente de 21";
		estandar = (DispositivoEstandar) this.dispositivos().findBy("nombre", nombreDispo);
		if (estandar == null) {
			estandar = new DispositivoEstandar(nombreDispo, 0.075, "mlopez", false, 18.0);
			 RestriccionHorasFamilia rest_tv = this.restriccionesHorasFamilia().findBy("codigo", "TV");
			 estandar.setRestriccionHoras(rest_tv);
			cliente2.addDispositivo(estandar);
			this.clientes().persistir(cliente2);
		}

		// DISPOSITIVO 3 (Inteligente)
		nombreDispo = "Televisor Led 40";
		DispositivoInteligente inteligente1 = (DispositivoInteligente) this.dispositivos().findBy("nombre",
				nombreDispo);
		if (inteligente1 == null) {
			inteligente1 = new DispositivoInteligente(nombreDispo, 0.08, "fperez", true, true, new DriverBasico());
			inteligente1.prender();
			 RestriccionHorasFamilia rest_tv2 = this.restriccionesHorasFamilia().findBy("codigo", "TV");
			 inteligente1.setRestriccionHoras(rest_tv2);
			cliente1.addDispositivo(inteligente1);
			this.clientes().persistir(cliente1);
		}

		// DISPOSITIVO 4 (Estandar)
		nombreDispo = "Ventilador de pie";
		DispositivoEstandar estandar2 = (DispositivoEstandar) this.dispositivos().findBy("nombre", nombreDispo);
		if (estandar2 == null) {
			estandar2 = new DispositivoEstandar(nombreDispo, 0.09, "fperez", true, 6.0);
			cliente1.addDispositivo(estandar2);
			 RestriccionHorasFamilia rest_fan = this.restriccionesHorasFamilia().findBy("codigo", "FAN");
			 estandar2.setRestriccionHoras(rest_fan);
			cliente1.agrega_modulo_a_estandar(estandar2);
			this.clientes().persistir(cliente1);
		}

		// DISPOSITIVO 5 (Inteligente)
		nombreDispo = "PC de escritorio";
		DispositivoInteligente inteligente3 = (DispositivoInteligente) this.dispositivos().findBy("nombre",
				nombreDispo);
		if (inteligente3 == null) {
			inteligente3 = new DispositivoInteligente(nombreDispo, 0.4, "fperez", true, false, new DriverBasico());
			inteligente3.prender();
			 RestriccionHorasFamilia rest_PC = this.restriccionesHorasFamilia().findBy("codigo", "COMPUTER");
			 inteligente3.setRestriccionHoras(rest_PC);
			cliente1.addDispositivo(inteligente3);
			this.clientes().persistir(cliente1);
		}

		Dispositivo d1 = (Dispositivo) this.dispositivos().findBy("nombre",
				"Convencional");
		if (d1 == null) {
			d1 = DispositivoFactoryMethod.getDispositivoByCode("Microondas");
			cliente1.addDispositivo(d1);
			d1.setConsumoPorHora(10.0);
			this.clientes().persistir(cliente1);
		}
		
		DispositivoInteligente d2 = (DispositivoInteligente) this.dispositivos().findBy("nombre",
				"Con freezer");
		if (d2 == null) {
			d2 = (DispositivoInteligente) DispositivoFactoryMethod.getDispositivoByCode("RefriConFreezer");
			cliente1.addDispositivo(d2);
			d2.setConsumoPorHora(10.0);
			d2.prender();
			this.clientes().persistir(cliente1);
		}
	

	}

	public void persistirUsuario(UsuarioSGE u) {
		this.persistir(u);
		
	}

}
