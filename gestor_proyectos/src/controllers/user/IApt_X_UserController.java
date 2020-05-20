package controllers.user;

import model.user.Apt_X_User;

public interface IApt_X_UserController {

	public int add(Apt_X_User oAptXUs);

	public int remove(Apt_X_User oAptXUs);

	public String mostrarAptitudDeUsuario();

	public int ExisteAptitudDeUsuario(Apt_X_User oAptXUs);

}
