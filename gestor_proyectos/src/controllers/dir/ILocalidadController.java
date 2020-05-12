package controllers.dir;

import controllers.GeneralController;
import model.dir.Localidad;

public interface ILocalidadController {

	// CRUD
	public int add(Localidad oLocalidad, ProvinciaController oProvinciaCtrl, PaisController paisCtrl);

	public int remove(Localidad oLocalidad);
	
	public int update(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl);

	public String mostrarLocalidad();

	// QUERYS
	public int existeLocalidad(Localidad oLocalidad);

	public Localidad searchLocalidadByPk(Localidad oLoc, GeneralController c);

	



}