package sge.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.usuarios.Cliente;

public class RepositorioRestriccionHorasFamilia extends Repositorio {
	private static RepositorioRestriccionHorasFamilia repositorio;
	private List<RestriccionHorasFamilia> restriccionesHorasFamilia = new ArrayList<RestriccionHorasFamilia>();

	public static RepositorioRestriccionHorasFamilia getinstance() {
		if (repositorio == null) {
			repositorio = new RepositorioRestriccionHorasFamilia();
		}
		return repositorio;
	}

	public static RepositorioRestriccionHorasFamilia getRepositorio() {
		return repositorio;
	}

	private RepositorioRestriccionHorasFamilia() {
		this.crearRetriccionesSiNoExisten();
	};

	public RestriccionHorasFamilia findBy(String campo, String valor) {		
		RestriccionHorasFamilia rhf = (RestriccionHorasFamilia) this.findBy(RestriccionHorasFamilia.class,campo,valor);
		return rhf;
	}

	private void crearRetriccionesSiNoExisten() {
		RestriccionHorasFamilia rhf = null;

		rhf = this.findBy("codigo", "AIRCONDITIONER");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("AIRCONDITIONER", 90.0, 360.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "LAMP");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("LAMP", 90.0, 360.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "TV");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("TV", 90.0, 360.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "WASHINGMACHINE");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("WASHINGMACHINE", 6.0, 30.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "COMPUTER");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("COMPUTER", 60.0, 360.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "MICROWAVE");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("MICROWAVE", 3.0, 15.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "GRIDDLE");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("GRIDDLE", 3.0, 30.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "FAN");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("FAN", 120.0, 360.0));
			this.cerrar();
		}
		rhf = null;

		rhf = this.findBy("codigo", "REFRIGERATOR");
		if (rhf == null) {
			this.abrir();
			this.persistir(new RestriccionHorasFamilia("REFRIGERATOR", 0.0, Double.MAX_VALUE));
			this.cerrar();
		}
		rhf = null;

	}

}
