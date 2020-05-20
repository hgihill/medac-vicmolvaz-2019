package controllers.project;

import model.user.Con_X_User;

public interface ICon_X_Us_Controller {

	public int add(Con_X_User oConXUs);

	public int remove(Con_X_User oConXUs);

	public String mostrarConocimientoDeUsuario();

	public int ExisteConocimientoDeUsuario(Con_X_User oConXUs);
}
