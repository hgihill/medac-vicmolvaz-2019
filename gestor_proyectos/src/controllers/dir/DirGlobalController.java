package controllers.dir;

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

}