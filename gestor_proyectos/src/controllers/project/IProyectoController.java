package controllers.project;


import model.project.Proyecto;

public interface IProyectoController {

	// Crud
	public int add(Proyecto oProyecto);

	public int remove(Proyecto oProyecto);

	public int update(Proyecto oProyecto);

	// Queries
	public int existeProyecto(Proyecto oProyecto);
}
