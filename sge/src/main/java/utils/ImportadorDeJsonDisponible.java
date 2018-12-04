package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import sge.modelo.dispositivo.DispositivoDisponible;


public class ImportadorDeJsonDisponible {
	
		public List<DispositivoDisponible> getDispositivoDisponible(String Archivo) throws IOException {

			String DispositivoDisponibleJSON = "";

			lectorDeArchivos lectorDeArchivos = new lectorDeArchivos(Archivo);
			while (!lectorDeArchivos.lecturaFinalizada()) {
				DispositivoDisponibleJSON= DispositivoDisponibleJSON + lectorDeArchivos.leerSiguiente();
			}
			lectorDeArchivos.cerrar();

			Gson gson = new  Gson();/*Builder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();*/
			Type tipoListaDispositivoDisponibles = new TypeToken<List<DispositivoDisponible>>() {
			}.getType();
			List<DispositivoDisponible> DispositivoDisponibles = gson.fromJson(DispositivoDisponibleJSON, tipoListaDispositivoDisponibles );

			return DispositivoDisponibles;
		}

		

	}

