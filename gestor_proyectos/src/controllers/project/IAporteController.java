package controllers.project;

import model.project.Aporte;

public interface IAporteController {

	// Crud
	public int add(Aporte oAporte);

	public int remove(Aporte oAporte);

	public int update(Aporte oAporte);
	
	public String mostrarAporte();

	// Queries
	public int existeAporte(Aporte oAporte);

	
}
