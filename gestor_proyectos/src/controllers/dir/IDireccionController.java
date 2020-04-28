package controllers.dir;

import java.util.List;

import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Provincia;

public interface IDireccionController {

	// Crud
	public int add(Direccion oAddress, LocalidadController localidadCtrl, ProvinciaController provinciaCtrl,
			PaisController paisCtrl);

	public int remove(Direccion oAddress);

	// Queries
	public int existeDireccion(Direccion oDireccion);

	public List<Direccion> searchAddressesPorLocalidad(Localidad oLocalidad);

	public List<Direccion> searchAddressesPorProvincia(Provincia oProvincia);
}