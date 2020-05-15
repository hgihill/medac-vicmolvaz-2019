package controllers.user;

import model.user.Aptitud;

public interface IAptitudController {

	// Crud
	public int add(Aptitud oApt);

	public int remove(Aptitud oApt);
	
	public String mostrarAptitud();

	// Queries
	public int ExisteAptitud(Aptitud oApt);

	
}
