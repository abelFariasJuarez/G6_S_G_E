package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.Dispositivo;

public class TestImportadorDispositivos {

	RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
	List<Dispositivo> Dispositivos;
	
	@Before
	public void setUp() {
	repo2.cargarDispositivos();
	Dispositivos = repo2.Dispositivos();
	 
	for (Dispositivo disp : Dispositivos) {
		disp.presentate();
	}
	}
	@Test
	public void hayDispositivos() {
		assertEquals(false,Dispositivos.isEmpty());
		
	}
	
	
}
