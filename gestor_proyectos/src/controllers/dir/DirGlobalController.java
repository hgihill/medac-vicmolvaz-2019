package controllers.dir;

import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public class DirGlobalController {
	private PaisController paisCtrl;
	private ProvinciaController provinciaCtrl;
	private LocalidadController localidadCtrl;
	private DireccionController addressCtrl;

	public DirGlobalController() {
	paisCtrl = new PaisController();
	provinciaCtrl = new ProvinciaController();
	localidadCtrl = new LocalidadController();
	addressCtrl = new DireccionController();
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

	public DireccionController getAddressCtrl() {
		return addressCtrl;
	}

	// ADDRESS
	// ##############
	public int addAddress(Direccion oDir) {
		return addressCtrl.add(oDir, localidadCtrl, provinciaCtrl, paisCtrl);
	}

	// LOCALIDAD
	// ##############
	public int addLocalidad(Localidad oLoc) {
		return localidadCtrl.add(oLoc, provinciaCtrl, paisCtrl);
	}

	public int updateLocalidad(Localidad oLocalidad) {
		return localidadCtrl.update(oLocalidad, provinciaCtrl, paisCtrl);
	}

	// PROVINCIA
	// ##############
	public int addProvincia(Provincia oProv) {
		return provinciaCtrl.add(oProv, paisCtrl);
	}

	// PAIS
	// ##############
	public int addPais(Pais oPais) {
		return paisCtrl.add(oPais);
	}

}