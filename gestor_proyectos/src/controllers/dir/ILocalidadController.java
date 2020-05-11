package controllers.dir;

import controllers.GeneralController;
import model.dir.Localidad;

public interface ILocalidadController {

	// CRUD
	int add(Localidad oLocalidad, ProvinciaController oProvinciaCtrl, PaisController paisCtrl);

	public int remove(Localidad oLocalidad);

	int update(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl);

	public String mostrarLocalidad();

	// QUERYS
	public int existeLocalidad(Localidad oLocalidad);

	//List<Localidad> searchLocalidad(Provincia oProvincia);

	//public Localidad searchLocalidadByPk(String sCodigoPostal);

	Localidad searchLocalidadByPk(Localidad oLoc, GeneralController c);

}