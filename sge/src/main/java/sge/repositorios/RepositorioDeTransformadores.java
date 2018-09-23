package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Transformador;
import utils.ImportadorDeJsonTransformador;


public class RepositorioDeTransformadores extends Repositorio {
	private static RepositorioDeTransformadores  repoTransformadores;
	private List<Transformador> transformadores= new ArrayList<Transformador>();

	private RepositorioDeTransformadores() {
	};

	public static RepositorioDeTransformadores getinstance() {
		if (repoTransformadores == null) {
			repoTransformadores = new RepositorioDeTransformadores();
		}
		return repoTransformadores;
	}

	public void guardarZona(Transformador transfo) {
		transformadores.add(transfo);
	}

	public List<Transformador> transformadores() {
		return transformadores;
	}

	public void cargarTransformadores() {
		transformadores.clear();
		ImportadorDeJsonTransformador json = new ImportadorDeJsonTransformador();

		try {
			this.transformadores.addAll(json.getTransformadores());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
