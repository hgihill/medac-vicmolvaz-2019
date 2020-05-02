package controllers.project;

import controllers.ConexionDB;
import model.project.Inventario;

public class InventarioController implements IInventarioController{

	@Override
	public int add(Inventario oInventario) {
		String sql = "INSERT INTO aporte VALUES (\"" + oInventario.getiIdInv() + "\"," 
				+ "\"" + oInventario.getiCant() + "\","
				+ "\"" + oInventario.getoRec() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Inventario oInventario) {
		String sql = "DELETE FROM inventario WHERE usuario = (\"" + oInventario.getiIdInv() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Inventario oInventario) {
		String sql = "UPDATE inventario SET (\"" + oInventario.getiIdInv() + "\","  
				+ "\"" + oInventario.getiCant() + "\"," 
				+ "\"" + oInventario.getoRec() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeInventario(Inventario oInventario) {
		String sql = "SELECT COUNT (*) FROM inventario WHERE id_inventario = (\"" + oInventario.getiIdInv() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}