package Repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import posicionamiento.Transformador;

import utils.ImportadorDeJsonTransformador;


public class RepositorioDeTransformadores {
	private static RepositorioDeTransformadores  repoTransformadores;
	public List<Transformador> transformadores= new ArrayList<Transformador>();

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
		ImportadorDeJsonTransformador json = new ImportadorDeJsonTransformador();

		try {
			this.transformadores.addAll(json.getTransformadores());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
