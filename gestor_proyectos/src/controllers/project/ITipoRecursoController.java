package controllers.project;

import model.project.TipoRecurso;

public interface ITipoRecursoController {

	// Crud
	public int add(TipoRecurso oTipoRecurso);

	public int remove(TipoRecurso oTipoRecurso);

	// Qeuries
	public int existeTipoRecurso(TipoRecurso oTipoRecurso);

}
