package controllers.dir;

import controllers.GeneralController;
import model.dir.Localidad;

public interface ILocalidadController {

	// CRUD
	public int add(Localidad oLocalidad);

	public int remove(Localidad oLocalidad);

	public int update(Localidad oLocalidad);

	public String mostrarLocalidad();

	// QUERYS
	public int existeLocalidad(Localidad oLocalidad);

	public Localidad searchLocalidadByPk(Localidad oLoc, GeneralController c);

}