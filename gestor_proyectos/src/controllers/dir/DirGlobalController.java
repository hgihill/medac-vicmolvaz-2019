package controllers.dir;

import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public class DirGlobalController {
	private PaisController paisCtrl;
	private ProvinciaController provinciaCtrl;
	private LocalidadController localidadCtrl;
	private DireccionController direccionCtrl;

	public DirGlobalController() {
	paisCtrl = new PaisController();
	provinciaCtrl = new ProvinciaController();
	localidadCtrl = new LocalidadController();
	direccionCtrl = new DireccionController();
    }

	public PaisController getPaisCtrl() {
		return paisCtrl;
	}

	public ProvinciaController getProvinciaCtrl() {
		return provinciaCtrl;
	}

	public LocalidadController getLocalidadCtrl() {
		return localidadCtrl;
	}

	public DireccionController getDireccionCtrl() {
		return direccionCtrl;
	}

	// DIRECCION
	// ##############
	public int addDireccion(Direccion oDireccion) {
		return direccionCtrl.add(oDireccion, localidadCtrl, provinciaCtrl, paisCtrl);
	}
	public int removeDireccion(Direccion oDir) {
		return direccionCtrl.remove(oDir);
	}

	public int exisateDireccion(Direccion oDireccion) {
		return direccionCtrl.existeDireccion(oDireccion);
	}

	// LOCALIDAD
	// ##############
	public int addLocalidad(Localidad oLoc) {
		return localidadCtrl.add(oLoc, provinciaCtrl, paisCtrl);
	}
	public int exisateLocalidad(Localidad oLocalidad) {
		return localidadCtrl.existeLocalidad(oLocalidad);
	}

	// PROVINCIA
	// ##############
	public int addProvincia(Provincia oProv) {
		return provinciaCtrl.add(oProv, paisCtrl);
	}
	public int existeProvincia(Provincia oProvincia) {
		return provinciaCtrl.existeProvincia(oProvincia);
	}

	// PAIS
	// ##############
	public int addPais(Pais oPais) {
		return paisCtrl.add(oPais);
	}
	public int exisatePais(Pais oPais) {
		return paisCtrl.existePais(oPais);
	}

}