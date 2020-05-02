package controllers.user;

import model.user.Tipo_Usuario;

public interface ITipoUsuarioController {

	//Crud
	public int add(Tipo_Usuario oTipoUs);

	public int remove(Tipo_Usuario oTipoUs);

	//Queries
	public int ExisteTipoUsuario(Tipo_Usuario oTipoUs);
}
