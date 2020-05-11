package controllers.dir;

import model.dir.Provincia;

public interface IProvinciaController {

	// Crud
	public int add(Provincia oProvincia, PaisController paisCtrl);

	public int remove(Provincia oProvincia);

	// Queries
	public int existeProvincia(Provincia oProvincia);
	
	public String mostrarProvincia();
	
	public int Update(Provincia oProvincia, Provincia oOtra, PaisController paisCtrl);
}
