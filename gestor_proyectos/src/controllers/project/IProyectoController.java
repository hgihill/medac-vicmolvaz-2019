package controllers.project;


import controllers.GeneralController;
import model.project.Proyecto;

public interface IProyectoController {

	// Crud
	public int add(Proyecto oProyecto);

	public int remove(Proyecto oProyecto);

	public int update(Proyecto oProyecto);

	public String mostrarProyecto();

	// Queries
	public Proyecto searchProyecto(Proyecto oObjeto, GeneralController controller);
	
	public int existeProyecto(Proyecto oProyecto);

	

	
}
