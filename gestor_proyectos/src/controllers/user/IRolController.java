package controllers.user;

import model.user.Rol;

public interface IRolController {

	//Crud
	public int add(Rol oRol);

	public int remove(Rol oRol);

	public int update(Rol oRol);

	//Queries
	public int existeRol(Rol oRol);
}
