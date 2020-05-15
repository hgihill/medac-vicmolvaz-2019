package controllers.user;

import model.user.Conocimiento;

public interface IConocimientoController {

	//Crud
	public int add(Conocimiento oCon);
	
	public int remove(Conocimiento oCon);
	
	public String mostrarConocimiento();

	//Queries
	public int existeConocimiento(Conocimiento oCon);

	
}
