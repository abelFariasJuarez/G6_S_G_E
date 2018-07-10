package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.Dispositivo;
import sge.dispositivo.DispositivoInteligente;
import sge.dispositivo.familia.LamparaInteligente;
public class TestImportadorDispositivos {

	RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
	List<Dispositivo> Dispositivos;
	
	@Before
	public void setUp() {
	repo2.cargarDispositivos();
	Dispositivos = repo2.Dispositivos();
	 
	for (Dispositivo disp : Dispositivos) {
		disp.presentate();
		System.out.println(disp.getClass());
	}
	}
	@Test
	public void hayLamparaInteligente() {
		
		assertEquals(sge.dispositivo.familia.LamparaInteligente.class, Dispositivos.get(0).getClass()); 
		//assertEquals(sge.dispositivo.DispositivoInteligente.class, Dispositivos.get(0).getClass());
	}
	
	@Test
	public void hayMicroondas() {
		assertEquals(true, Dispositivos.stream().anyMatch(disp -> disp.getClass()==sge.dispositivo.familia.Microondas.class));
	}
	
}
