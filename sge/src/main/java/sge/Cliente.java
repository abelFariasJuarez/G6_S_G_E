package sge;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sge.Dispositivo;

public class Cliente extends UsuarioSGE {
	String tipodoc;
	Integer nrodoc;
	Integer telefono;
	Categoria categoria;
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	LocalDate fechaAlta;
	
	public Cliente(String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos/*, String _fechaAlta*/) {
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos = _dispositivos;
		//LocalDate _fecha = obtenerFecha(_fechaAlta);
		//fechaAlta = _fecha;
	}

/*	private LocalDate obtenerFecha(String _fechaAlta) {
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    LocalDate dateTime = LocalDate.parse(_fechaAlta, formatter);
		return dateTime;
	}
*/
	public String getTipoDoc() {
		return tipodoc;
	}

	public int getNroDoc() {
		return nrodoc;
	}

	public int getTelefono() {
		return telefono;
	}

	public Categoria getCategoria() {

		return categoria;
	}

	public void addDispositivo(Dispositivo dispositivo) {
		dispositivos.add(dispositivo);

	}

	public void removeDispositivo(Dispositivo dispositivo) {
		dispositivos.remove(dispositivo);

	}

	public boolean tengoAlgunDispositivoON() {
		return dispositivos.stream().anyMatch(dis -> dis.estoyON());

	}

	public Integer cantDispositivosON() {
		return (int) dispositivos.stream().filter(dis -> dis.estoyON() == true).count();
	}

	Integer cantDispositivosOFF() {
		return (int) dispositivos.stream().filter(dis -> dis.estoyON() == false).count();
	}

	Integer cantDispositivos() {
		return dispositivos.size();
	}

	public void presentate() {
		System.out.println("Tipo Doc:" + this.tipodoc + "\n" + "Nro Doc:" + this.nrodoc + "\n" + "telefono:" + this.telefono +"\n"+"Fecha de alta: "+this.fechaAlta +"\n"+ "Dispositivos:");
	}

		
		
	
	
}
