package sge;

import java.util.ArrayList;
import java.util.List;
import sge.Dispositivo;

public class Cliente extends UsuarioSGE {
	String tipodoc;
	Integer nrodoc;
	Integer telefono;
	Categoria categoria;
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

	public Cliente(String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos) {
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos = _dispositivos;

	}

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
		System.out.println("Tipo Doc:" + this.tipodoc + "   " + "Nro Doc:" + this.nrodoc + "   " + "telefono:"
				+ this.telefono + "Dispositivos:");
	}

}
