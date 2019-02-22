package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import utils.ImportadorDeJsonTransformador;


public class Transformadores extends Repositorio {
	private List<Transformador> transformadores= new ArrayList<Transformador>();

	public Transformadores() {
	};

	public Transformadores(EntityManager entityManager) {
		super(entityManager);
	}

	public void guardarZona(Transformador transfo) {
		transformadores.add(transfo);
	}

	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public void cargarTransformadores() {
		transformadores.clear();
		ImportadorDeJsonTransformador json = new ImportadorDeJsonTransformador();

		try {
			this.transformadores.addAll(json.getTransformadores());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarGuardar() {
		this.cargarTransformadores();
		this.persistir(this.transformadores);
	}
	
	
	
	public void persistir(List<Transformador> transformadores2) {
		transformadores2.forEach(t -> this.persistir(t));		
	}

	public void persistir(Transformador t) {
		
		/*Double latitud = t.getUbicacion().getLatitud();
		Double longitud = t.getUbicacion().getLongitud();
		this.detach(t.getUbicacion());		

		Ubicacion ubi = this.ubicaciones().getPersistente(longitud,latitud);
		
		Transformador t1 = this.getPersistenteBy("id",t.getId());
		t1.llenarAtributos(t);		
		t1.setUbicacion(ubi);

		super.clientes().persistir(t1.getClientes());
		super.persistir(t1);
		return t1;*/
		super.persistir(t);
	}
	
	private Transformador getPersistenteBy(String campo, Object valor) {
		Transformador transDAO = this.findBy(campo, valor);
		if(transDAO == null)
		{
			transDAO = new Transformador();
		}
		return transDAO;
	}
	
	public Transformador findBy(String campo, Object valor) {
		Transformador rhf = (Transformador) this.findBy(Transformador.class, campo, valor);
		return rhf;
	}	

	public void guardarTransforamdores() {
		for (Transformador transfo : this.getTransformadores()) {
			Transformador transDAO = this.findBy("id", transfo.getId());
			if(transDAO == null)
			{
				transDAO = new Transformador();
			}
			this.llenarAtributos(transfo,transDAO);
			this.persistir(transDAO);
		}		
	}

	private void llenarAtributos(Transformador transfo, Transformador transDAO) {
	
		transDAO.setClientes(transfo.getClientes());
		transDAO.setId(transfo.getId());
		transDAO.setIdZona(transfo.getIdZona());
		transDAO.setUbicacion(transfo.getUbicacion());

	}
	
	public List<Transformador> buscarTodos() {
		List<Transformador> ts = null;
		ts = entityManager.createNamedQuery("buscarTodosTransformadores").getResultList();
		return ts;
	}	

	public List<Transformador> all()
	{
		return (List<Transformador>) this.allOf(Transformador.class);
	}
}
