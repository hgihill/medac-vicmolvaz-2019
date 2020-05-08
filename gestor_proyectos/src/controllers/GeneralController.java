package controllers;

import controllers.dir.DirGlobalController;
import controllers.project.ProjectGlobalController;
import controllers.user.UserGlobalController;

public class GeneralController {
	public DirGlobalController direccionCtrl;
	public ProjectGlobalController proyectoCtrl;
	public UserGlobalController usuarioCtrl;

	public GeneralController(String sDatabase) {
		direccionCtrl = new DirGlobalController();
		proyectoCtrl = new ProjectGlobalController();
		usuarioCtrl = new UserGlobalController();
		new ConexionDB(sDatabase);
	}

	public DirGlobalController getDireccionCtrl() {
		return direccionCtrl;
	}

	public ProjectGlobalController getProyectoCtrl() {
		return proyectoCtrl;
	}

	public UserGlobalController getUsuarioCtrl() {
		return usuarioCtrl;
	}

}