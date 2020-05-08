package controllers.dir;

import java.util.List;

import model.dir.Pais;

public interface IPaisController {

	// Crud
	public int add(Pais oObject);

	public int remove(Pais oPais);

	// Queries
	public int existePais(Pais oPais);
	
	public Pais searchPais(Pais oPais);

	public String mostrarPais();

	public int Update(Pais oPais, Pais oOtro);
}