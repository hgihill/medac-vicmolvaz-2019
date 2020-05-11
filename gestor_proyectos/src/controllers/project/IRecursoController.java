package controllers.project;

import controllers.GeneralController;
import model.project.Recurso;

public interface IRecursoController {

	// Crud
	public int add(Recurso oRecurso);

	public int remove(Recurso oRecurso);

	int update(Recurso oRecurso);
	
	String mostrarRecurso();

	// Queries
	public int existeRecurso(Recurso oRecurso);

	Recurso searchRecurso(Recurso oObjeto, GeneralController c);

}
