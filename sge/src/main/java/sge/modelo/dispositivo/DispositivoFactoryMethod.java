package sge.modelo.dispositivo;

import javax.persistence.Column;

import sge.modelo.driver.DriverBasico;
import sge.repositorios.Repositorio;

public class DispositivoFactoryMethod {

	public DispositivoFactoryMethod() {
	}

	public static void cargaBasica() {
		Repositorio repositorio = Repositorio.getInstance();
		//repositorio.abrir();

		DispositivoDisponible check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Aire3500");
		if (check == null) {
			check = new DispositivoDisponible("Aire3500", Boolean.TRUE, "De 3500 frigorías", Boolean.FALSE, 1.613,
					"AIRCONDITIONER");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Aire2200");
		if (check == null) {
			check = new DispositivoDisponible("Aire2200", Boolean.TRUE, "De 2200 frigorías", Boolean.TRUE,
					1.013, "AIRCONDITIONER");
			repositorio.dispositivosDisponibles().persistir(check);
		}	

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "TvTubo21");
		if (check == null) {
			check = new DispositivoDisponible("TvTubo21", Boolean.FALSE, "Color de tubo fluorescente 21", 
					Boolean.FALSE, 0.075, "TV");
			repositorio.dispositivosDisponibles().persistir(check);
		}		

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "TvTubo29a34");
		if (check == null) {
			check = new DispositivoDisponible("TvTubo29a34", Boolean.FALSE, "Color de tubo fluorescente 21", 
					Boolean.FALSE, 0.175, "TV");
			repositorio.dispositivosDisponibles().persistir(check);
		}	

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "TvLCD40");
		if (check == null) {
			check = new DispositivoDisponible("TvLCD40", Boolean.FALSE, "LCD de 40", 
					Boolean.FALSE, 0.18, "TV");
			repositorio.dispositivosDisponibles().persistir(check);
		}	

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "TvLED24");
		if (check == null) {
			check = new DispositivoDisponible("TvLED24", Boolean.TRUE, "LED 24", 
					Boolean.TRUE, 0.04, "TV");
			repositorio.dispositivosDisponibles().persistir(check);
		}	

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "TvLED32");
		if (check == null) {
			check = new DispositivoDisponible("TvLED32", Boolean.TRUE, "LED 32", 
					Boolean.TRUE, 0.055, "TV");
			repositorio.dispositivosDisponibles().persistir(check);
		}		

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "TvLED40");
		if (check == null) {
			check = new DispositivoDisponible("TvLED40", Boolean.TRUE, "LED 40", 
					Boolean.TRUE, 0.08, "TV");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "RefriConFreezer");
		if (check == null) {
			check = new DispositivoDisponible("RefriConFreezer", Boolean.TRUE, "Con freezer", 
					Boolean.TRUE, 0.09, "REFRIGERATOR");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "RefriSinFreezer");
		if (check == null) {
			check = new DispositivoDisponible("RefriSinFreezer", Boolean.TRUE, "Sin freezer", 
					Boolean.TRUE, 0.075, "REFRIGERATOR");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "LavaAutoHot");
		if (check == null) {
			check = new DispositivoDisponible("LavaAutoHot", Boolean.FALSE, "Automático de 5 kg con calentamiento de agua", 
					Boolean.FALSE, 0.875, "WASHINGMACHINE");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "LavaAuto");
		if (check == null) {
			check = new DispositivoDisponible("LavaAuto", Boolean.TRUE, "Automático de 5 kg", 
					Boolean.TRUE, 0.175, "WASHINGMACHINE");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "LavaSemiAuto");
		if (check == null) {
			check = new DispositivoDisponible("LavaSemiAuto", Boolean.TRUE, "Semi-automático de 5 kg", 
					Boolean.TRUE, 0.1275, "WASHINGMACHINE");
			repositorio.dispositivosDisponibles().persistir(check);
		}		

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "VentiladorPie");
		if (check == null) {
			check = new DispositivoDisponible("VentiladorPie", Boolean.FALSE, "De pie", 
					Boolean.TRUE, 0.09, "FAN");
			repositorio.dispositivosDisponibles().persistir(check);
		}		

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "VentiladorTecho");
		if (check == null) {
			check = new DispositivoDisponible("VentiladorTecho", Boolean.TRUE, "De techo", 
					Boolean.TRUE, 0.06, "FAN");
			repositorio.dispositivosDisponibles().persistir(check);
		}
		
		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Lampara40");
		if (check == null) {
			check = new DispositivoDisponible("Lampara40", Boolean.TRUE, "Halógenas de 40 W", 
					Boolean.FALSE, 0.04, "LAMP");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Lampara60");
		if (check == null) {
			check = new DispositivoDisponible("Lampara60", Boolean.TRUE, "Halógenas de 60 W", 
					Boolean.FALSE, 0.06, "LAMP");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Lampara100");
		if (check == null) {
			check = new DispositivoDisponible("Lampara100", Boolean.TRUE, "Halógenas de 100 W", 
					Boolean.FALSE, 0.1, "LAMP");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Lampara11");
		if (check == null) {
			check = new DispositivoDisponible("Lampara11", Boolean.TRUE, "de 11 W", 
					Boolean.TRUE, 0.011, "LAMP");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Lampara15");
		if (check == null) {
			check = new DispositivoDisponible("Lampara15", Boolean.TRUE, "de 15 W", 
					Boolean.TRUE, 0.015, "LAMP");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Lampara20");
		if (check == null) {
			check = new DispositivoDisponible("Lampara20", Boolean.TRUE, "de 20 W", 
					Boolean.TRUE, 0.020, "LAMP");
			repositorio.dispositivosDisponibles().persistir(check);
		}
		
		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "PC");
		if (check == null) {
			check = new DispositivoDisponible("PC", Boolean.TRUE, "De escritorio", 
					Boolean.TRUE, 0.04, "COMPUTER");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Microondas");
		if (check == null) {
			check = new DispositivoDisponible("Microondas", Boolean.FALSE, "Convencional", 
					Boolean.TRUE, 0.64, "MICROWAVE");
			repositorio.dispositivosDisponibles().persistir(check);
		}

		check = null;
		check = repositorio.dispositivosDisponibles().findBy("codigo", "Plancha");
		if (check == null) {
			check = new DispositivoDisponible("Plancha", Boolean.FALSE, "A vapor", 
					Boolean.TRUE, 0.75, "GRIDDLE");
			repositorio.dispositivosDisponibles().persistir(check);
		}		

	}

	public static Dispositivo getDispositivoByCode(String code) {
		Dispositivo objeto = null;
		Repositorio repositorio = Repositorio.getInstance();
		//repositorio.abrir();

		DispositivoDisponible template = repositorio.dispositivosDisponibles().findBy("codigo", code);

		if (template.getIsInteligente()) {
			objeto = new DispositivoInteligente(template.getNombre(), template.getConsumoPorHora(),
					template.getIsBajoConsumo(), new DriverBasico());
		} else {
			objeto = new DispositivoEstandar(template.getNombre(), template.getConsumoPorHora(),
					template.getIsBajoConsumo());
		}

		RestriccionHorasFamilia restriccion = repositorio.restriccionesHorasFamilia().findBy("codigo",
				template.getCodigoRestriccionHoras());
		objeto.setRestriccionHoras(restriccion);

		//repositorio.cerrar();
		return objeto;
	}

}
