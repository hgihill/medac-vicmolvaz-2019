package controllers.user;

import model.user.Aptitud;

public interface IAptitudController {

	// Crud
	public int add(Aptitud oApt);

	public int remove(Aptitud oApt);

	// Queries
	public int ExisteUsuario(Aptitud oApt);
}
