package Repositorios;

import java.util.ArrayList;
import java.util.List;

import sge.Dispositivo;

public class RepositorioDeDispositivos {

	private static RepositorioDeDispositivos repoDispositivos;
	
	public  List<Dispositivo> Dispositivos = new ArrayList<Dispositivo>();

	private RepositorioDeDispositivos() {
	};

	public static RepositorioDeDispositivos getinstance() {
		if (repoDispositivos == null) {
			repoDispositivos = new RepositorioDeDispositivos();
		}
		return repoDispositivos;
	}

	public void guardarCliente(Dispositivo dispositivo) {
		Dispositivos.add(dispositivo);

	}

	public  List<Dispositivo> Dispositivos() {
		return Dispositivos;
	}
	
}
