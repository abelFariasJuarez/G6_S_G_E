package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoDisponibleVO;
import sge.modelo.valueobjects.DispositivoEstandarVO;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.ZonaVO;

public abstract class ImportadorJson {
	private String archivoFuente;

	public String getArchivoFuente() {
		return archivoFuente;
	}

	public void setArchivoFuente(String archivo) {
		this.archivoFuente = archivo;
	}

	public String getStringJson() throws Exception {
		String txt = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos(this.getArchivoFuente());
		while (!lectorDeArchivos.lecturaFinalizada()) {
			txt = txt + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();
		return txt;
	}
	
	public List<?> myFromJson(Type typeToken) throws Exception {
		String stringJson = this.getStringJson();
		Gson gson = new Gson();
		return gson.fromJson(stringJson, typeToken);
	}

	public List<ClienteVO> getClientes() throws Exception {
		this.setArchivoFuente(System.getProperty("user.dir") + "/src/test/pruebacliente.json");
		Type typeToken = new TypeToken<List<ClienteVO>>() {
		}.getType();
		List<ClienteVO> clienteVOs = (List<ClienteVO>) myFromJson(typeToken);
		return clienteVOs;
	}
	
	public List<DispositivoDisponibleVO> getDispositivoDisponible(String archivo) throws Exception {
		this.setArchivoFuente(archivo);
		Type typeToken = new TypeToken<List<DispositivoDisponibleVO>>() {
		}.getType();
		List<DispositivoDisponibleVO> DispositivoDisponibles = (List<DispositivoDisponibleVO>) this.myFromJson(typeToken);

		return DispositivoDisponibles;
	}
	
	public List<DispositivoVO> agregarDispositivos(String archivo) throws Exception {
		this.setArchivoFuente(archivo);
		Type tipoListaDispositivos = new TypeToken<List<DispositivoInteligenteVO>>() {
		}.getType();

		return (List<DispositivoVO>) this.myFromJson(tipoListaDispositivos);
	}

	public List<DispositivoVO> getDispositivos(String string) throws Exception {
		List<DispositivoVO> dispositivos = new ArrayList<DispositivoVO>();

		if (string == "inteligente" || string == "todos") {
			dispositivos.addAll(this.agregarDispositivos(System.getProperty("user.dir") + "/src/test/pruebaDispositivoInteligente.json"));
		}

		if (string == "estandar" || string == "todos") {
			dispositivos.addAll(this.agregarDispositivos(System.getProperty("user.dir") + "/src/test/pruebaDispositivoEstandar.json"));
		}

		return dispositivos;
	}
	
	public List<TransformadorVO> getTransformadores() throws Exception {
		
		this.setArchivoFuente(System.getProperty("user.dir") + "/src/test/pruebatransformadores.json");
		Type tipoListaTransformador = new TypeToken<List<TransformadorVO>>() {
		}.getType();
		
		return (List<TransformadorVO>) this.myFromJson(tipoListaTransformador);
	}

	public List<ZonaVO> getZona() throws Exception {

		this.setArchivoFuente(System.getProperty("user.dir") + "/src/test/pruebazonas.json");

		Type tipoListaZona = new TypeToken<List<ZonaVO>>() {
		}.getType();
		return (List<ZonaVO>) this.myFromJson(tipoListaZona);

	}
	
	public List<DispositivoVO> getDispositivos() throws IOException {
		String dispositivosInteligentes = "";
		String dispositivosEstandar = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebaDispositivoInteligente.json");
		lectorDeArchivos lectorDeArchivos2 = new lectorDeArchivos("pruebaDispositivoEstandar.json");

		while (!lectorDeArchivos.lecturaFinalizada()) {
			dispositivosInteligentes = dispositivosInteligentes + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Type tipoListaDispositivos = new TypeToken<List<DispositivoInteligenteVO>>() {
		}.getType();

		Gson gson = new Gson();

		List<DispositivoVO> dispositivos = gson.fromJson(dispositivosInteligentes, tipoListaDispositivos);

		while (!lectorDeArchivos2.lecturaFinalizada()) {
			dispositivosEstandar = dispositivosEstandar + lectorDeArchivos2.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Type tipoListaDispositivos2 = new TypeToken<List<DispositivoEstandarVO>>() {
		}.getType();

		Gson gson2 = new Gson();

		dispositivos.addAll(gson2.fromJson(dispositivosEstandar, tipoListaDispositivos2));

		return dispositivos;
	}	
	
}
