package controllers;

import controllers.dir.DirGlobalController;

public class GeneralController {
	private DirGlobalController direccionCtrl;
    //private RrhhPackageController rrhhCtrl;
    
    public GeneralController(String sDatabase) {
	direccionCtrl = new DirGlobalController();
	//rrhhCtrl = new RrhhPackageController();
	new ConexionDB(sDatabase);	
    }    

    public DirGlobalController getDireccionCtrl () {
	return direccionCtrl;
    }

//    public RrhhPackageController getRrhhCtrl () {
//	return rrhhCtrl;
//    }
//    
//    // RRHH (Cliente) <-> ADDRESS (Address - Localidad - Provincia - Pais)
//    public int addCliente (Cliente oCliente) {
//	return rrhhCtrl.getClienteCtrl().add(oCliente, rrhhCtrl.getUserCtrl(), addressCtrl.getAddressCtrl(),
//		addressCtrl.getLocalidadCtrl(), addressCtrl.getProvinciaCtrl(), addressCtrl.getPaisCtrl());
//    }
//
//    // RRHH (Empleado) <-> ADDRESS (Address - Localidad - Provincia - Pais)
//    public int addEmpleado (Empleado oEmpleado) {
//	return rrhhCtrl.getEmpleadoCtrl().add(oEmpleado, rrhhCtrl.getUserCtrl(), addressCtrl.getAddressCtrl(),
//		addressCtrl.getLocalidadCtrl(), addressCtrl.getProvinciaCtrl(), addressCtrl.getPaisCtrl());
//    }
}