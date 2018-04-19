
package Repositorios;

import sge.Cliente;
import utils.ImportadorDeJSON;

public class prueba {

	public static void main(String[] args) {

		ImportadorDeJSON json = new ImportadorDeJSON();
		RepositorioDeClientes repo = RepositorioDeClientes.getinstance();

		json.run();

		for (Cliente client : repo.Clientes()) {
			System.out
					.println("Tipo Doc:"+ client.Dni()+"   " + "Nro Doc:" + client.nroDoc() +"   "+ "telefono:" + client.telefono());
		}

	}

}
