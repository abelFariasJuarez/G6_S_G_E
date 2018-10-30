package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.dispositivo.*;
import sge.modelo.usuarios.Cliente;
import utils.ImportadorDeJsonDispositivo;

public class Dispositivos extends Repositorio {

	public List<Dispositivo> Dispositivos = new ArrayList<Dispositivo>();

	public Dispositivos() {
	};

	public Dispositivos(EntityManager entityManager) {
		super(entityManager);
	}

	public void guardarDispositivo(Dispositivo dispositivo) {
		Dispositivos.add(dispositivo);
	}

	public List<Dispositivo> getDispositivos() {
		return Dispositivos;
	}

	public void cargarDispositivos(String disp) {
		ImportadorDeJsonDispositivo json = new ImportadorDeJsonDispositivo();

		try {
			this.Dispositivos.addAll(json.getDispositivos(disp));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Dispositivo findBy(String campo, String valor) {
		Dispositivo rhf = (Dispositivo) this.findBy(Dispositivo.class, campo, valor);
		return rhf;
	}
}