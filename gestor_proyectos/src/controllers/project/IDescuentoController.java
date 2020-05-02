package controllers.project;

import model.project.Descuento;

public interface IDescuentoController {

	// Crud
	public int add(Descuento oDescuento);

	public int remove(Descuento oDescuento);

	public int update(Descuento oDescuento);

	// Queires
	public int existeDescuento(Descuento oDescuento);
}
