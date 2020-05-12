package controllers.dir;

import model.dir.Pais;

public interface IPaisController {

	// Crud
	public int add(Pais oObject);

	public int remove(Pais oPais);

	// Queries
	public int existePais(Pais oPais);
	
	public Pais searchPais(Pais oPais);

	public String mostrarPais();
}