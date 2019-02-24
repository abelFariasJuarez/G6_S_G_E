package sge.modelo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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

import sge.dao.*;
import sge.modelo.valueobjects.*;

public class Repositorio {

	private static final String PERSISTENCE_UNIT_NAME = "db";
	protected EntityManager entityManager;
	EntityManagerFactory emFactory;

	public Repositorio() {
	}

	public Repositorio(EntityManager em) {
		this.entityManager = em;
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

	public List<SensorVO> allSensores()
	{
		return (List<SensorVO>) this.allOf(SensorVO.class);
	}
	
	public void persistir(SensorVO c) {
		this.persistir(c);
	}
	
	public Object findBy(Class<?> clazz, String campo1, String campo2, Object valor1, Object valor2) {
		Object objReturn;
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq(campo1, valor1))
				.add(Restrictions.eq(campo2, valor2));
		objReturn = criteria.uniqueResult();
		return objReturn;
	}

	protected List<?> allOf(Class<?> clazz) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz);
		List<?> objects = criteria.list();
		return objects;
	}

	protected List<?> allDistinctOf(Class<?> clazz) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<?> objects = criteria.list();
		return objects;
	}

	public void persistir(ClienteVO c2) {
		/*this.persistir(c2.getUbicacion());
		this.persistir(c2);*/
	}

	private ClienteVO getPersistenteBy(String campo, Object valor) {
		ClienteVO transDAO = this.findClienteBy(campo, valor);
		if (transDAO == null) {
			transDAO = new ClienteVO();
		}
		return transDAO;
	}

	public List<ClienteVO> allClientes() {
		return (List<ClienteVO>) this.allDistinctOf(ClienteVO.class);
	}

	public ClienteVO findClienteBy(String campo, Object valor) {
		ClienteVO rhf = (ClienteVO) this.findBy(ClienteVO.class, campo, valor);
		return rhf;
	}

	public ComparadorVO findBy(String campo, Long valor) {
		ComparadorVO rhf = (ComparadorVO) this.findBy(ComparadorVO.class, campo, valor);
		return rhf;
	}

	public List<ComparadorVO> allComparadores() {
		return (List<ComparadorVO>) this.allOf(ComparadorVO.class);
	}

	public void persistir(ComparadorVO c) {
		this.persistir(c);
	}

	public List<DispositivoDisponibleVO> allDisponibles() {
		return (List<DispositivoDisponibleVO>) this.allOf(DispositivoDisponibleVO.class);
	}

	public DispositivoDisponibleVO findDisponibleBy(String campo, String valor) {
		DispositivoDisponibleVO rhf = (DispositivoDisponibleVO) this.findBy(DispositivoDisponibleVO.class, campo, valor);
		return rhf;
	}

	public void guardarDispositivosDisponibles(List<DispositivoDisponibleVO> dispositivosDisponibles) {
		for (DispositivoDisponibleVO dispdis : dispositivosDisponibles) {
			DispositivoDisponibleVO transDAO = this.findDisponibleBy("codigo", dispdis.getCodigo());
			if (transDAO == null) {
				transDAO = new DispositivoDisponibleVO();
			}
			this.llenarAtributos(dispdis, transDAO);
			this.persistir(transDAO);
		}
	}

	private void llenarAtributos(DispositivoDisponibleVO dis, DispositivoDisponibleVO transDAO) {
		transDAO.setCodigo(dis.getCodigo());
		transDAO.setIsInteligente(dis.getIsInteligente());
		transDAO.setNombre(dis.getNombre());
		transDAO.setIsBajoConsumo(dis.getIsBajoConsumo());
		transDAO.setCodigoRestriccionHoras(dis.getCodigoRestriccionHoras());
		transDAO.setConsumoPorHora(dis.getConsumoPorHora());

	}

	public void persistir(DispositivoDisponibleVO c) {
		this.persistir(c);
	}

	public ZonaVO findZonaBy(String campo, Object valor) {
		ZonaVO rhf = (ZonaVO) this.findBy(ZonaVO.class, campo, valor);
		return rhf;
	}

	public void bajaTransformadores(List<ZonaVO> zonas) {
		for (ZonaVO zona : zonas) {
			zona.desactivarTransformadores();
		}
	}

	public List<ZonaVO> allZonas() {
		return (List<ZonaVO>) this.allOf(ZonaVO.class);
	}

	public Ubicacion findByUbicacion(Double longitud, Double latitud) {
		Ubicacion rhf = (Ubicacion) this.findBy(Ubicacion.class, "longitud", "latitud", longitud, latitud);
		if (rhf == null) {
			rhf = new Ubicacion();
			rhf.setLatitud(latitud);
			rhf.setLongitud(longitud);
		}
		return rhf;
	}

	public UbicacionVO findByOid(Object valor) {
		UbicacionVO rhf = (UbicacionVO) this.findBy(UbicacionVO.class, "oid", valor);
		return rhf;
	}

	public void persistir(List<IPersistible> persistibles) {
		persistibles.forEach(z -> this.persistir(z));
	}

	public void persistir(TransformadorVO t) {
		this.persistir(t);
	}

	private TransformadorVO getPersistenteTransformadorBy(String campo, Object valor) {
		TransformadorVO transDAO = this.findTransformadorBy(campo, valor);
		if (transDAO == null) {
			transDAO = new TransformadorVO();
		}
		return transDAO;
	}

	public TransformadorVO findTransformadorBy(String campo, Object valor) {
		TransformadorVO rhf = (TransformadorVO) this.findBy(TransformadorVO.class, campo, valor);
		return rhf;
	}

	private void llenarAtributos(TransformadorVO transfo, TransformadorVO transDAO) {

		transDAO.setClientes(transfo.getClientes());
		transDAO.setId(transfo.getId());
		transDAO.setIdZona(transfo.getIdZona());
		transDAO.setUbicacion(transfo.getUbicacion());

	}

	public List<TransformadorVO> buscarTodos() {
		List<TransformadorVO> ts = null;
		ts = entityManager.createNamedQuery("buscarTodosTransformadores").getResultList();
		return ts;
	}

	public List<TransformadorVO> allTransformadores() {
		return (List<TransformadorVO>) this.allOf(TransformadorVO.class);
	}

	public void persistir(ZonaVO zona) {
		/*this.persistir(zona.getCentro());
		this.persistir(zona);*/
	}

	private ZonaVO getPersistentZonaeBy(String campo, Object valor) {
		ZonaVO transDAO = this.findZonaBy(campo, valor);
		if (transDAO == null) {
			transDAO = new ZonaVO();
		}
		return transDAO;
	}

	public void consumo_hogar_periodo(LocalDateTime desde, LocalDateTime hasta) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);

		String consulta = "    SELECT oid, username, sum(IFNULL(consumo,0)) from ("
				+ "            SELECT r.oid, r.username, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"
				+ "            FROM " + "                (SELECT " + "                    c.oid, "
				+ "                    u.username, "
				+ "                    ed.factor * d.consumoPorHora as consumoPorHora, "
				+ "                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, "
				+ "                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin "
				+ "                FROM cliente c" + "                    inner join usuariosge u on c.oid = u.oid "
				+ "                    inner join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                    inner join inteligente as s on cd.dispositivos_oid = s.oid "
				+ "                    inner join dispositivo as d on s.oid = d.oid "
				+ "                    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid "
				+ "                    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde "
				+ "                    inner join estadodispositivo ed on i.estado_oid = ed.oid ) as r"
				+ "            union "
				+ "            SELECT c.oid, u.username, IFNULL(d.consumoPorHora,0) * IFNULL(de.horasEncendidoPorDia,0) * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo"
				+ "            FROM cliente c" + "                inner join usuariosge u on c.oid = u.oid "
				+ "                inner join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                inner join dispositivoestandar de on cd.dispositivos_oid = de.oid "
				+ "                inner join dispositivo as d on de.oid = d.oid " + "    ) as h "
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
	}

	public void consumo_promedio_tipo_dispositivo_periodo(LocalDateTime desde, LocalDateTime hasta) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);

		String consulta = "    SELECT deviceType, AVG(IFNULL(consumo,0)) from ("
				+ "            SELECT r.deviceType, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"
				+ "            FROM " + "                (SELECT " + "                    d.deviceType, "
				+ "                    ed.factor * d.consumoPorHora as consumoPorHora, "
				+ "                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, "
				+ "                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin "
				+ "                FROM inteligente as s "
				+ "                    inner join dispositivo as d on s.oid = d.oid "
				+ "                    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid "
				+ "                    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde "
				+ "                    inner join estadodispositivo ed on i.estado_oid = ed.oid ) as r"
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

		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() + ";" + row[1].toString());
		}
	}

	public void consumo_transformador_periodo(LocalDateTime desde, LocalDateTime hasta) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTimeConverter converter = new LocalDateTimeConverter();
		Timestamp p_desde = converter.convertToDatabaseColumn(desde);
		Timestamp p_hasta = converter.convertToDatabaseColumn(hasta);

		String consulta = "    SELECT oid, sum(IFNULL(consumo,0)) from ("
				+ "            SELECT r.oid, r.consumoPorHora * TIMESTAMPDIFF(SECOND, r.inicio, r.fin) / (3600) as consumo"
				+ "            FROM " + "                (SELECT " + "                    t.oid, "
				+ "                    ed.factor * d.consumoPorHora as consumoPorHora, "
				+ "                    IF(i.inicio < :desde, :desde, i.inicio) as inicio, "
				+ "                    IF(IFNULL(i.fin,now()) < :hasta,IFNULL(i.fin,now()), :hasta) as fin "
				+ "                FROM transformador t"
				+ "                    inner join transformador_cliente tc on t.oid = tc.transformador_oid "
				+ "                    inner join cliente c on tc.clientes_oid = c.oid "
				+ "                    inner join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                    inner join inteligente as s on cd.dispositivos_oid = s.oid "
				+ "                    inner join dispositivo as d on s.oid = d.oid "
				+ "                    inner join inteligente_intervalo as si on s.oid = si.inteligente_oid "
				+ "                    inner join intervalo as i on si.intervalos_oid = i.oid and i.inicio <= :hasta and i.fin > :desde "
				+ "                    inner join estadodispositivo ed on i.estado_oid = ed.oid ) as r"
				+ "            union "
				+ "            SELECT c.oid, IFNULL(d.consumoPorHora,0) * IFNULL(de.horasEncendidoPorDia,0) * TIMESTAMPDIFF(SECOND, :desde, :hasta) / (3600) as consumo"
				+ "            FROM transformador t"
				+ "                inner join transformador_cliente tc on t.oid = tc.transformador_oid "
				+ "                inner join cliente c on tc.clientes_oid = c.oid "
				+ "                inner join cliente_dispositivo cd on c.oid = cd.cliente_oid "
				+ "                inner join dispositivoestandar de on cd.dispositivos_oid = de.oid "
				+ "                inner join dispositivo as d on de.oid = d.oid " + "    ) as h " + "    GROUP BY oid";

		Session session = this.getSession();

		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(consulta);

		query.setTimestamp("desde", p_desde);
		query.setTimestamp("hasta", p_hasta);

		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			System.out.println(row[0].toString() + ";" + row[1].toString());
		}
	}

	public DispositivoVO findDispoBy(String campo, String valor) {
		DispositivoVO rhf = (DispositivoVO) this.findBy(DispositivoVO.class, campo, valor);
		return rhf;
	}

	public List<DispositivoVO> allDispos() {
		return (List<DispositivoVO>) this.allOf(DispositivoVO.class);
	}

	public void persistir(DispositivoVO c1) {
		this.persistir(c1);
	}

	public ReglaVO findReglaBy(String campo, String valor) {		
		ReglaVO rhf = (ReglaVO) this.findBy(ReglaVO.class,campo,valor);
		return rhf;
	}	

	public List<ReglaVO> allReglas()
	{
		return (List<ReglaVO>) this.allOf(ReglaVO.class);
	}
	
	public void persistir(ReglaVO c) {
		persistir(c);
	}
	
	public CondicionVO findCondicionBy(String campo, Long valor) {		
		CondicionVO rhf = (CondicionVO) this.findBy(CondicionVO.class,campo,valor);
		return rhf;
	}

	public List<CondicionVO> allCondiciones()
	{
		return (List<CondicionVO>) this.allOf(CondicionVO.class);
	}
	
	public void persistir(CondicionVO c) {
		persistir(c);
	}
	
	public void cargaDeDatosIniciales() {
		crearRetriccionesSiNoExisten();
		DispositivoFactoryMethod.cargaBasica();
		GestorCliente gestor = new GestorCliente();
		gestor.setRepositorio(this);
		gestor.cargarClientesZonasTransformadores();
		gestor.transformadoresAsignacionZona();
		gestor.asignarClientesATransformadores();

		AdministradorVO admin = (AdministradorVO) this.findBy(AdministradorVO.class, "username", "admin");
		if (admin == null) {
			admin = new AdministradorVO("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19), "admin",
					"admin");
		}
		admin.setUsername("admin");
		admin.setPassword("1234");
		this.persistirUsuario(admin);

		// Inicializamos las variables
		ClienteVO cliente1 = new ClienteVO();
		ClienteVO cliente2 = new ClienteVO();
		ClienteVO cliente3 = new ClienteVO();

		String nombreDispo = "";
		DispositivoInteligenteVO inteligente = new DispositivoInteligenteVO();
		DispositivoEstandarVO estandar = new DispositivoEstandarVO();

		// Cargamos los usuarios clienteVOs.
		// ClienteVO 1
		cliente1 = this.findClienteBy("username", "fperez");
		if (cliente1 == null) {
			cliente1 = new ClienteVO("Felipe", "Perez", "Medrano 123", LocalDate.of(1995, 4, 7), "fperez", "menToL2017",
					"Dni", 11222333, 1543312311);
			cliente1.setAhorroAutomatico(true);
			cliente1.setUbicacion(new UbicacionVO(10.0, 90.0));
			this.persistir(cliente1);
		}

		// ClienteVO 2
		cliente2 = this.findClienteBy("username", "mlopez");
		if (cliente2 == null) {
			cliente2 = new ClienteVO("Mariano", "Lopez", "Mozart 2300", LocalDate.of(1995, 01, 10), "mlopez", "ml1234",
					"Dni", 38888383, 1533333333);
			cliente2.setAhorroAutomatico(false);
			cliente2.setUbicacion(new UbicacionVO(20.0, 95.0));
			this.persistir(cliente2);
		}

		// ClienteVO 3
		cliente3 = this.findClienteBy("username", "afarias");
		if (cliente3 == null) {
			cliente3 = new ClienteVO("Abel", "Farias", "Mozart 2300", LocalDate.of(1988, 01, 10), "afarias", "af1234",
					"Dni", 38888384, 1533333334);
			cliente3.setAhorroAutomatico(false);
			cliente3.setUbicacion(new UbicacionVO(20.0, 95.0));
			this.persistir(cliente3);
		}

		DispositivoVO air = DispositivoFactoryMethod.getDispositivoByCode("Aire2200");
		air.setConsumoPorHora(10.0);

		// Agrego los dispositivos con sus respectivos dueños
		// DISPOSITIVO 1 (Inteligente)
		nombreDispo = "Aire acondicionado de 3500 frigorías";
		inteligente = (DispositivoInteligenteVO) this.findDispoBy("nombre", nombreDispo);
		if (inteligente == null) {
			inteligente = new DispositivoInteligenteVO(nombreDispo, 1.613, "mlopez", false, false, new DriverBasico());
			inteligente.prender();

			RestriccionHorasFamiliaVO rest_air = findRestriccionHorasFamiliaVOBy("codigo", "AIRCONDITIONER");
			inteligente.setRestriccionHoras(rest_air);
			cliente2.addDispositivo(inteligente);
			this.persistir(cliente2);

		}

		// DISPOSITIVO 2 (Estandar)
		nombreDispo = "Televisor color tubo fluorecente de 21";
		estandar = (DispositivoEstandarVO) this.findDispoBy("nombre", nombreDispo);
		if (estandar == null) {
			estandar = new DispositivoEstandarVO(nombreDispo, 0.075, "mlopez", false, 18.0);
			RestriccionHorasFamiliaVO rest_tv = findRestriccionHorasFamiliaVOBy("codigo", "TV");
			estandar.setRestriccionHoras(rest_tv);
			cliente2.addDispositivo(estandar);
			this.persistir(cliente2);
		}

		// DISPOSITIVO 3 (Inteligente)
		nombreDispo = "Televisor Led 40";
		DispositivoInteligenteVO inteligente1 = (DispositivoInteligenteVO) this.findDispoBy("nombre", nombreDispo);
		if (inteligente1 == null) {
			inteligente1 = new DispositivoInteligenteVO(nombreDispo, 0.08, "fperez", true, true, new DriverBasico());
			inteligente1.prender();
			RestriccionHorasFamiliaVO rest_tv2 = findRestriccionHorasFamiliaVOBy("codigo", "TV");
			inteligente1.setRestriccionHoras(rest_tv2);
			cliente1.addDispositivo(inteligente1);
			this.persistir(cliente1);
		}

		// DISPOSITIVO 4 (Estandar)
		nombreDispo = "Ventilador de pie";
		DispositivoEstandarVO estandar2 = (DispositivoEstandarVO) this.findDispoBy("nombre", nombreDispo);
		if (estandar2 == null) {
			estandar2 = new DispositivoEstandarVO(nombreDispo, 0.09, "fperez", true, 6.0);
			cliente1.addDispositivo(estandar2);
			RestriccionHorasFamiliaVO rest_fan = findRestriccionHorasFamiliaVOBy("codigo", "FAN");
			estandar2.setRestriccionHoras(rest_fan);
			cliente1.agrega_modulo_a_estandar(estandar2);
			this.persistir(cliente1);
		}

		// DISPOSITIVO 5 (Inteligente)
		nombreDispo = "PC de escritorio";
		DispositivoInteligenteVO inteligente3 = (DispositivoInteligenteVO) this.findDispoBy("nombre", nombreDispo);
		if (inteligente3 == null) {
			inteligente3 = new DispositivoInteligenteVO(nombreDispo, 0.4, "fperez", true, false, new DriverBasico());
			inteligente3.prender();
			RestriccionHorasFamiliaVO rest_PC = findRestriccionHorasFamiliaVOBy("codigo", "COMPUTER");
			inteligente3.setRestriccionHoras(rest_PC);
			cliente1.addDispositivo(inteligente3);
			this.persistir(cliente1);
		}

		DispositivoVO d1 = (DispositivoVO) this.findDispoBy("nombre", "Convencional");
		if (d1 == null) {
			d1 = DispositivoFactoryMethod.getDispositivoByCode("Microondas");
			cliente1.addDispositivo(d1);
			d1.setConsumoPorHora(10.0);
			this.persistir(cliente1);
		}

		DispositivoInteligenteVO d2 = (DispositivoInteligenteVO) this.findDispoBy("nombre", "Con freezer");
		if (d2 == null) {
			d2 = (DispositivoInteligenteVO) DispositivoFactoryMethod.getDispositivoByCode("RefriConFreezer");
			cliente1.addDispositivo(d2);
			d2.setConsumoPorHora(10.0);
			d2.prender();
			this.persistir(cliente1);
		}

	}

	private RestriccionHorasFamiliaVO findRestriccionHorasFamiliaVOBy(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void persistirUsuario(UsuarioVO u) {
		/*this.persistir(u);*/
	}

	public RestriccionHorasFamilia findRestriccionHorasFamiliaBy(String campo, String valor) {
		RestriccionHorasFamilia rhf = (RestriccionHorasFamilia) this.findBy(RestriccionHorasFamilia.class, campo,
				valor);
		return rhf;
	}

	public List<RestriccionHorasFamiliaVO> allRestriccionesHorasFamilia() {
		return (List<RestriccionHorasFamiliaVO>) this.allOf(RestriccionHorasFamiliaVO.class);
	}

	public void crearRetriccionesSiNoExisten() {
		RestriccionHorasFamiliaVO rhf = null;

		/*rhf = this.findRestriccionHorasFamiliaBy("codigo", "AIRCONDITIONER");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("AIRCONDITIONER", 90.0, 360.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "LAMP");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("LAMP", 90.0, 360.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "TV");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("TV", 90.0, 360.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "WASHINGMACHINE");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("WASHINGMACHINE", 6.0, 30.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "COMPUTER");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("COMPUTER", 60.0, 360.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "MICROWAVE");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("MICROWAVE", 3.0, 15.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "GRIDDLE");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("GRIDDLE", 3.0, 30.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "FAN");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("FAN", 120.0, 360.0));
		}
		rhf = null;

		rhf = this.findRestriccionHorasFamiliaBy("codigo", "REFRIGERATOR");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamiliaVO("REFRIGERATOR", 0.0, Double.MAX_VALUE));
		}
		rhf = null;*/
	}

}
