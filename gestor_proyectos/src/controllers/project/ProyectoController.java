package controllers.project;

import controllers.ConexionDB;
import model.project.Proyecto;

public class ProyectoController implements IProyectoController{

	@Override
	public int add(Proyecto oProyecto) {
		String sql = "INSERT INTO proyecto VALUES (\"" + oProyecto.getiId_Proyecto() + "\"," 
				+ "\"" + oProyecto.getsNombre() + "\","
				+ "\"" + oProyecto.getsDescripcion() + "\","
				+ "\"" + oProyecto.getoUs() + "\","
				+ "\"" + oProyecto.getoInv() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Proyecto oProyecto) {
		String sql = "DELETE FROM proyecto WHERE id_proyecto = (\"" + oProyecto.getiId_Proyecto() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Proyecto oProyecto) {
		String sql = "UPDATE proyecto SET (\"" + oProyecto.getiId_Proyecto() + "\","  
				+ "\"" + oProyecto.getsNombre() + "\","
				+ "\"" + oProyecto.getsDescripcion() + "\","
				+ "\"" + oProyecto.getoUs() + "\","
				+ "\"" + oProyecto.getoInv() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeProyecto(Proyecto oProyecto) {
		String sql = "SELECT COUNT (*) FROM proyecto WHERE id_proyecto = (\"" + oProyecto.getiId_Proyecto() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}