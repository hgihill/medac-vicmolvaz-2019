package controllers.user;

import model.user.TipoUsuario;

public interface ITipoUsuarioController {

	//Crud
	public int add(TipoUsuario oTipoUs);

	public int remove(TipoUsuario oTipoUs);

	//Queries
	public int existeTipoUsuario(TipoUsuario oTipoUs);
}
