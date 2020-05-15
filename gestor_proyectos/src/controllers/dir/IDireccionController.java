package controllers.dir;

import java.util.List;

import controllers.GeneralController;
import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Provincia;

public interface IDireccionController {

	// Crud
	public int add(Direccion oAddress);

	public int remove(Direccion oAddress);
	
	public int update(Direccion oDireccion);
	
	public String mostrarDireccion();

	// Queries
	public int existeDireccion(Direccion oDireccion);

	public List<Direccion> searchAddressesPorLocalidad(Localidad oLocalidad);

	public List<Direccion> searchAddressesPorProvincia(Provincia oProvincia);

	public Direccion search(Direccion oObjeto, GeneralController c);

	
}