package controllers.project;

import java.util.List;

import model.project.Financiacion;

public interface IFinanciacionController {

	// Crud
	public int add(Financiacion oFinanciacion);

	public int remove(Financiacion oFinanciacion);

	public int update(Financiacion oFinanciacion);

	// Queries
	public List<Financiacion> searchFinanciacion(Financiacion oFinanciacion);
	
	public int existeFinanciacion(Financiacion oFinanciacion);
}
