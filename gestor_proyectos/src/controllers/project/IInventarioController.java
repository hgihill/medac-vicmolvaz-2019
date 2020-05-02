package controllers.project;

import model.project.Inventario;

public interface IInventarioController {

	// Crud
	public int add(Inventario oInventario);

	public int remove(Inventario oInventario);

	public int update(Inventario oInventario);

	// Queries
	public int existeInventario(Inventario oInventario);
}
