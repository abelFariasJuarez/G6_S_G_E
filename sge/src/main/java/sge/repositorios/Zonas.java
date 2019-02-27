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

	public Zonas(EntityManager entityManager) {
		super(entityManager);
	}

	public void agregarZona(ZonaGeografica zona) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargarGuardar() {
		this.cargarZonas();
		this.zonas = this.persistir(this.zonas);
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

	public List<ZonaGeografica> persistir(List<ZonaGeografica> zonas) {
		List<ZonaGeografica> zonasPer = new ArrayList<ZonaGeografica>(); 
		for (ZonaGeografica z : zonas) {
			ZonaGeografica z1 = this.getPersistenteBy("id",z.getId());
			z1.llenarAtributos(z);
			this.persistir(z1);			
			zonasPer.add(z1);
		}	
		return zonasPer; 	
	}

	public void persistir(ZonaGeografica zona) {
		/*Double latitud = zona.getCentro().getLatitud();
		Double longitud = zona.getCentro().getLongitud();
		this.detach(zona.getCentro());
		
		Ubicacion ubi = this.ubicaciones().getPersistente(longitud,latitud);
		
		ZonaGeografica zona1 = this.getPersistenteBy("id",zona.getId());		
		zona1.llenarAtributos(zona);
		zona1.setCentro(ubi);
		super.clientes().persistir(zona1.getClientes());
		super.transformadores().persistir(zona1.getTransformadores());
		super.persistir(zona1);
		super.persistir(zona.getCentro());*/
		super.persistir(zona);
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
