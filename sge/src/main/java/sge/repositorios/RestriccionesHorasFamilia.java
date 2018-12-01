package sge.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.usuarios.Cliente;

public class RestriccionesHorasFamilia extends Repositorio {
	private List<RestriccionesHorasFamilia> restriccionesHorasFamilia = new ArrayList<RestriccionesHorasFamilia>();

	public RestriccionesHorasFamilia() {
		this.crearRetriccionesSiNoExisten();
	};

	public RestriccionesHorasFamilia(EntityManager entityManager) {
		super(entityManager);
		// this.crearRetriccionesSiNoExisten();
	}

	public RestriccionHorasFamilia findBy(String campo, String valor) {
		RestriccionHorasFamilia rhf = (RestriccionHorasFamilia) this.findBy(RestriccionHorasFamilia.class, campo,
				valor);
		return rhf;
	}

	public List<RestriccionHorasFamilia> all()
	{
		return (List<RestriccionHorasFamilia>) this.allOf(RestriccionHorasFamilia.class);
	}
	
	public void crearRetriccionesSiNoExisten() {
		RestriccionHorasFamilia rhf = null;

		rhf = this.findBy("codigo", "AIRCONDITIONER");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("AIRCONDITIONER", 90.0, 360.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "LAMP");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("LAMP", 90.0, 360.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "TV");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("TV", 90.0, 360.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "WASHINGMACHINE");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("WASHINGMACHINE", 6.0, 30.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "COMPUTER");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("COMPUTER", 60.0, 360.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "MICROWAVE");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("MICROWAVE", 3.0, 15.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "GRIDDLE");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("GRIDDLE", 3.0, 30.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "FAN");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("FAN", 120.0, 360.0));
		}
		rhf = null;

		rhf = this.findBy("codigo", "REFRIGERATOR");
		if (rhf == null) {
			this.persistir(new RestriccionHorasFamilia("REFRIGERATOR", 0.0, Double.MAX_VALUE));
		}
		rhf = null;
	}

}
