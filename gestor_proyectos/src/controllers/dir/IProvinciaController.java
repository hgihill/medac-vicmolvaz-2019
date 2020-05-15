package controllers.dir;

import controllers.GeneralController;
import model.dir.Provincia;

public interface IProvinciaController {

	// Crud
	public int add(Provincia oProvincia);

	public int remove(Provincia oProvincia);
	
	public String mostrarProvincia();

	// Queries
	public int existeProvincia(Provincia oProvincia);

	public Provincia searchProvincia(Provincia oProvincia, GeneralController c);

}
