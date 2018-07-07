package Repositorios;

import sge.dispositivo.Dispositivo;
import sge.dispositivo.familia.AireAcondicionadoInteligente;
import sge.dispositivo.familia.ComputadoraInteligente;
import sge.dispositivo.familia.Heladera;
import sge.dispositivo.familia.LamparaInteligente;
import sge.dispositivo.familia.Lavarropas;
import sge.dispositivo.familia.LavarropasInteligente;
import sge.dispositivo.familia.Microondas;
import sge.dispositivo.familia.Plancha;
import sge.dispositivo.familia.Television;
import sge.dispositivo.familia.TelevisionInteligente;
import sge.dispositivo.familia.Ventilador;
import sge.dispositivo.familia.VentiladorInteligente;

public class FactoryDispositivo {
	

public static Dispositivo	getDispositivo(int valor) {
Dispositivo dispo =RepositorioDispositivoValido.Dispositivos().get(valor);
	
switch(valor) {

	case 0:
		
	dispo=new AireAcondicionadoInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
	case 1:
		dispo= new AireAcondicionadoInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
		break;
	case 2:
		dispo= new Television(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
		break;

	case 3:
		dispo= new Television(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
		break;

  case 4:
	  dispo= new Television(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;

case 5:
	dispo= new TelevisionInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 6:
	dispo= new TelevisionInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 7:
	dispo= new TelevisionInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;

case 8:
	dispo= new Heladera(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 9:
	dispo= new Heladera(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
	
case 10:
	dispo= new Lavarropas(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 11:
	dispo= new LavarropasInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 12:
	dispo= new Lavarropas(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 13:
	dispo= new Ventilador(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 14:
	dispo= new VentiladorInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;

case 15:
	dispo= new LamparaInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 16:
	dispo= new LamparaInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 17:
	dispo= new LamparaInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 18:
	dispo= new LamparaInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 19:
	dispo= new LamparaInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 20:
	dispo= new LamparaInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 21:
	dispo= new ComputadoraInteligente(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 22:
	dispo= new Microondas(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
case 23:
	dispo= new Plancha(dispo.getNombre(),dispo.getConsumoPorHora(),dispo.getBajoconsumo());
	break;
default: dispo= null;
break;
}
return dispo;
}
}