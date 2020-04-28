package controllers.dir;

import java.util.List;

import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public interface ILocalidadController {

	// Crud
	public int add(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl);

	public int update(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl);

	public int remove(Localidad oLocalidad);

	// Queries
	public int existeLocalidad(Localidad oLocalidad);

	public List<Localidad> searchLocalidadesPorProvincia(Provincia oProvincia);

	public List<Localidad> searchLocalidadesPorPais(Pais oPais);

	public Localidad searchLocalidadByPk(String sCodigoPostal);
}