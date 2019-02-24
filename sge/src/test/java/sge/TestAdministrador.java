package sge;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import org.junit.Test;

import sge.modelo.valueobjects.AdministradorVO;

public class TestAdministrador {

	
	
	AdministradorVO admin = new AdministradorVO("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19),
			"pepe", "pasti");
	@Test
	public void MesesAdministrando() {		

		assertEquals(45, admin.cantMesesAdministrando());

	}
	
}

