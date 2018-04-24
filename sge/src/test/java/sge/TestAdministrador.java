package sge;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import org.junit.Test;

public class TestAdministrador {


	
	@Test
	public void MesesAdministrando() {
		
		Administrador admin = new Administrador("pedro","saraska","lavalle 148",LocalDate.of(2015, Month.APRIL, 19),"pepe","pasti");

		
		
		
		assertEquals(36,admin.cantMesesAdministrando());	
	
	}	
	
// opcion alternativa	
//	public void MesesAdministrando() {
//		Administrador admin=new Administrador();
//		admin.setFechaingreso(new GregorianCalendar(2016,Calendar.MARCH,11));
//		assertEquals(25,admin.cantMesesAdministrando());	
//	}

}

