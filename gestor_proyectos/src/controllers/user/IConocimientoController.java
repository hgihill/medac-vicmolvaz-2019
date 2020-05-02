package controllers.user;

import model.user.Conocimiento;

public interface IConocimientoController {

	//Crud
	public int add(Conocimiento oCon);
	
	public int remove(Conocimiento oCon);

	//Queries
	public int ExisteConocimiento(Conocimiento oCon);
}
