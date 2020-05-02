package controllers.project;

import model.project.Recurso;

public interface IRecursoController {

	// Crud
	public int add(Recurso oRecurso);

	public int remove(Recurso oRecurso);

	public int update(Recurso oRecurso);

	// Queries
	public int existeRecurso(Recurso oRecurso);
}
