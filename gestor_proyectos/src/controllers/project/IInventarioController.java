package controllers.project;

import java.util.List;

import model.project.Inventario;

public interface IInventarioController {

	// Crud
	public int add(Inventario oInventario);

	public int remove(Inventario oInventario);

	public int update(Inventario oInventario);

	// Queries
	public List<Inventario> searchInventario(Inventario oInventario);
	
	public int existeInventario(Inventario oInventario);
}
