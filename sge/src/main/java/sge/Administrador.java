package sge;

import java.time.LocalDate;
import java.time.Period;

//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;

public class Administrador extends UsuarioSGE {
	
	public Administrador(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
	
	}

	Integer nroAdminID;

	public int cantMesesAdministrando() {
		 LocalDate today = LocalDate.now();
		 Period age = Period.between(fechaIngreso, today);
		 int months = age.getMonths()+(age.getYears()*12);
		 return months;
	}
	
	
	
	
	
//OPCION ALTERNATIVA	
//	public long cantMesesAdministrando() {
//		Calendar today = new GregorianCalendar();
//		today.setTime(new Date());
//		int yearsInBetween = today.get(Calendar.YEAR) - fechaingreso.get(Calendar.YEAR);
//		int monthsDiff = today.get(Calendar.MONTH) - fechaingreso.get(Calendar.MONTH);
//		long ageInMonths = yearsInBetween * 12 + monthsDiff;
//		return ageInMonths;
//	}
}
