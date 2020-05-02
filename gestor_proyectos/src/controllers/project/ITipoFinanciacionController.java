package controllers.project;

import model.project.TipoFinanciacion;

public interface ITipoFinanciacionController {

	// Crud
	public int add(TipoFinanciacion oTipoFinanciacion);

	public int remove(TipoFinanciacion oTipoFinanciacion);

	// Queries
	public int existeTipoFinanciacion(TipoFinanciacion oTipoFinanciacion);
}
