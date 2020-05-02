package controllers.project;

import model.project.Financiacion;

public interface IFinanciacionController {

	// Crud
	public int add(Financiacion oFinanciacion);

	public int remove(Financiacion oFinanciacion);

	public int update(Financiacion oFinanciacion);

	// Queries
	public int existeDescuento(Financiacion oFinanciacion);
}
