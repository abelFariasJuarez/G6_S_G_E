package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
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
		this.persistir(this.zonas);

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

	public void persistir(List<ZonaGeografica> zonas2) {
		zonas2.forEach(z -> this.persistir(z));		
	}

	public void persistir(ZonaGeografica zona) {
		ZonaGeografica zona1 = this.getPersistenteBy("id",zona.getId());
		Ubicacion ubi = this.ubicaciones().getPersistente(zona.getCentro().getLongitud(),zona.getCentro().getLatitud());
		zona.setCentro(ubi);
		zona1.llenarAtributos(zona);
		super.persistir(zona1);
	}

	private ZonaGeografica getPersistenteBy(String campo, Object valor) {
		ZonaGeografica transDAO = this.findBy(campo, valor);
		if(transDAO == null)
		{
			transDAO = new ZonaGeografica();
		}
		return transDAO;
	}

	
}
