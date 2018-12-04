package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
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
	public void guardarZonas() {
		for (ZonaGeografica zona : this.getZonas()) {
			ZonaGeografica transDAO = this.findBy("id", zona.getId());
			if(transDAO == null)
			{
				transDAO = new ZonaGeografica();
			}
			this.llenarAtributos(zona,transDAO);
			this.persistir(transDAO);
		}		
	}

	private void llenarAtributos(ZonaGeografica zona, ZonaGeografica transDAO) {
		
		
		transDAO.setId(zona.getId());
		transDAO.setNombre(zona.getNombre());
		transDAO.setCentro(zona.getCentro());
		transDAO.setRadio(zona.getRadio());

	}
	
	public ZonaGeografica findBy(String campo, Object valor) {
		ZonaGeografica rhf = (ZonaGeografica) this.findBy(ZonaGeografica.class, campo, valor);
		return rhf;
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
	
	public List<ZonaGeografica> all()
	{
		return (List<ZonaGeografica>) this.allOf(ZonaGeografica.class);
	}
}
