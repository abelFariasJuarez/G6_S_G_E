package sge;

import Repositorios.RepositorioDeClientes;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		RepositorioDeClientes repo = RepositorioDeClientes.getinstance();
		repo.cargarClientes();

		for (Cliente client : repo.Clientes()) {
			System.out.println("Tipo Doc:" + client.Dni() + "   " + "Nro Doc:" + client.nroDoc() + "   " + "telefono:"
					+ client.telefono() + "Dispositivos:");
			client.mostrarDispositivos();
		}

	}
}
