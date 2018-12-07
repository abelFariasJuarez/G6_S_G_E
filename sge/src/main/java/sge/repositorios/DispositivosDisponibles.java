package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.usuarios.Cliente;
import utils.ImportadorDeJsonDisponible;
import utils.ImportadorDeJsonDispositivo;

public class DispositivosDisponibles extends Repositorio {

	public List<DispositivoDisponible> DispositivosDisponibles = new ArrayList<DispositivoDisponible>();
	public DispositivosDisponibles(EntityManager entityManager) {
		super(entityManager);
	}

	public List<DispositivoDisponible> all()
	{
		return (List<DispositivoDisponible>) this.allOf(DispositivoDisponible.class);
	}
	
	public DispositivoDisponible findBy(String campo, String valor) {		
		DispositivoDisponible rhf = (DispositivoDisponible) this.findBy(DispositivoDisponible.class,campo,valor);
		return rhf;
	}
	
	public void guardarDispositivosDisponible(DispositivoDisponible dispositivo) {
		DispositivosDisponibles.add(dispositivo);
	}
	public void cargarDispositivos(String archivo) {
		ImportadorDeJsonDisponible json = new ImportadorDeJsonDisponible();

		try {
			this.DispositivosDisponibles.addAll(json.getDispositivoDisponible(archivo));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarDispositivosDisponibles() {
		for (DispositivoDisponible dispdis : this.DispositivosDisponibles) {
			DispositivoDisponible transDAO = this.findBy("codigo", dispdis.getCodigo());
			if(transDAO == null)
			{
				transDAO = new DispositivoDisponible();
			}
			this.llenarAtributos(dispdis,transDAO);
			this.persistir(transDAO);
		}		
	}

	private void llenarAtributos(DispositivoDisponible dis, DispositivoDisponible transDAO) {
	    transDAO.setCodigo(dis.getCodigo());
	    transDAO.setIsInteligente(dis.getIsInteligente());
	    transDAO.setNombre(dis.getNombre());
	    transDAO.setIsBajoConsumo(dis.getIsBajoConsumo());
	    transDAO.setCodigoRestriccionHoras(dis.getCodigoRestriccionHoras());
		transDAO.setConsumoPorHora(dis.getConsumoPorHora());
		

	}
}
