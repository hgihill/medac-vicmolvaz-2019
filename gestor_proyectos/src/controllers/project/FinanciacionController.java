package controllers.project;

import controllers.ConexionDB;
import model.project.Financiacion;

public class FinanciacionController implements IFinanciacionController{

	@Override
	public int add(Financiacion oFinanciacion) {
		String sql = "INSERT INTO financiacion VALUES (\"" + oFinanciacion.getsCuenta() + "\"," 
				+ "\"" + oFinanciacion.getsEntidad() + "\","
				+ "\"" + oFinanciacion.getoTipoFin() + "\","
				+ "\"" + oFinanciacion.getoProyecto() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Financiacion oFinanciacion) {
		String sql = "DELETE FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Financiacion oFinanciacion) {
		String sql = "UPDATE financiacion SET (\"" + oFinanciacion.getsCuenta() + "\","  
				+ "\"" + oFinanciacion.getsEntidad() + "\"," 
				+ "\"" + oFinanciacion.getoTipoFin() + "\","
				+ "\"" + oFinanciacion.getoProyecto() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeDescuento(Financiacion oFinanciacion) {
		String sql = "SELECT COUNT (*) FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}