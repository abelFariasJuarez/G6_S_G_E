package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.ZonaGeografica;
import utils.ImportadorDeJsonZona;

public class Zonas extends Repositorio {

	private List<ZonaGeografica> zonas= new ArrayList<ZonaGeografica>();

	public Zonas() {
	};

	public Zonas(EntityManager entityManager) {
		super(entityManager);
	}

	public void guardarZona(ZonaGeografica zona) {
		zonas.add(zona);
	}

	public List<ZonaGeografica> getZonas() {
		return zonas;
	}

	public void cargarZonas() {
		zonas.clear();
		ImportadorDeJsonZona json = new ImportadorDeJsonZona();
		try {
			this.zonas.addAll(json.getZona());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void bajaTransformadores() {
		for (ZonaGeografica zona: zonas) {
			zona.desactivarTransformadores();
		}
	}
}
