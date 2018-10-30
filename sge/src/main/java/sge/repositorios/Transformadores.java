package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import sge.modelo.posicionamiento.Transformador;
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
		} catch (IOException e) {
			e.printStackTrace();
		}

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
}