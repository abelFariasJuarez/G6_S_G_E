package Repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sge.dispositivo.Dispositivo;
import utils.ImportadorDispositivoValido;

public class RepositorioDispositivoValido {
	private static RepositorioDispositivoValido repoDispositivos;

	public static List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

	private RepositorioDispositivoValido() {
	};

	public static RepositorioDispositivoValido getinstance() {
		if (repoDispositivos == null) {
			repoDispositivos = new RepositorioDispositivoValido();
		}
		return repoDispositivos;
	}

	public void guardarDispositivo(Dispositivo dispositivo) {
		dispositivos.add(dispositivo);
	}

	public static List<Dispositivo> Dispositivos() {
		return dispositivos;
	}

	public void cargarDispositivos() {
		ImportadorDispositivoValido json = new ImportadorDispositivoValido();

		try {
			RepositorioDispositivoValido.dispositivos.addAll(json.getDispositivos());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

}
