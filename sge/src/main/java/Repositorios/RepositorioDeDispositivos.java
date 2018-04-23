package Repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sge.Dispositivo;

import utils.ImportadorDeJsonDispositivo;

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

	
	public void guardarDispositivo(Dispositivo dispositivo) {
		Dispositivos.add(dispositivo);		
	}

	public  List<Dispositivo> Dispositivos() {
		return Dispositivos;
	}

	
	public void cargarDispositivos() {
		ImportadorDeJsonDispositivo json = new ImportadorDeJsonDispositivo();

		try {
			this.Dispositivos.addAll(json.getDispositivos());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
