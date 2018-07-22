package Repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sge.posicionamiento.ZonaGeografica;
import utils.ImportadorDeJsonZona;

public class RepositorioDeZonas {
	private static RepositorioDeZonas repoZonas;
	private List<ZonaGeografica> zonas= new ArrayList<ZonaGeografica>();

	private RepositorioDeZonas() {
	};

	public static RepositorioDeZonas getinstance() {
		if (repoZonas == null) {
			repoZonas = new RepositorioDeZonas();
		}
		return repoZonas;
	}

	public void guardarZona(ZonaGeografica zona) {
		zonas.add(zona);

	}

	public List<ZonaGeografica> zonas() {
		return zonas;
	}

	public void cargarZonas() {
		zonas.clear();
		ImportadorDeJsonZona json = new ImportadorDeJsonZona();
		try {
			this.zonas.addAll(json.getZona());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void bajaTransformadores() {
		for (ZonaGeografica zona: zonas) {
			zona.desactivarTransformadores();
		}
	}
}
