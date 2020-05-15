package controllers.project;


import controllers.GeneralController;
import model.project.Financiacion;

public interface IFinanciacionController {

	// Crud
	public int add(Financiacion oFinanciacion);

	public int remove(Financiacion oFinanciacion);

	public int update(Financiacion oFinanciacion);
	
	public String mostrarFinanciacion();

	// Queries
	
	public int existeFinanciacion(Financiacion oFinanciacion);

	Financiacion search(Financiacion oFinanciacion, GeneralController c);

	Financiacion searchByPK(Financiacion oFinanciacion);

	

	
}
