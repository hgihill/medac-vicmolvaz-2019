package controllers.user;

import model.user.Con_X_User;

public interface ICon_X_UserController {

	public int add(Con_X_User oConXUs);
	
	public int remove(Con_X_User oConXUs);
	
	public String mostrarConocimientoDeUsuario();

	public int existeConocimientoDeUsuario(Con_X_User oConXUs);
}
