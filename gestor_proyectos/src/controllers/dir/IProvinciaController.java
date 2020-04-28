package controllers.dir;

import java.util.List;

import model.dir.Pais;
import model.dir.Provincia;

public interface IProvinciaController {

	// Crud
	public int add(Provincia oProvincia, PaisController paisCtrl);

	public int remove(Provincia oProvincia);

	// Queries
	public int existeProvincia(Provincia oProvincia);

	public List<Provincia> searchProvinciasPorPais(Pais oPais);
}
