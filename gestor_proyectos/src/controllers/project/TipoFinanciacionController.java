package controllers.project;

import controllers.ConexionDB;
import model.project.TipoFinanciacion;

public class TipoFinanciacionController implements ITipoFinanciacionController{

	@Override
	public int add(TipoFinanciacion oTipoFinanciacion) {
		String sql = "INSERT INTO tipo_financiacion VALUES (\"" + oTipoFinanciacion.getbTipoFinanciacion() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(TipoFinanciacion oTipoFinanciacion) {
		String sql = "DELETE FROM tipo_financiacion WHERE tipo_financ = (\"" + oTipoFinanciacion.getbTipoFinanciacion() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeTipoFinanciacion(TipoFinanciacion oTipoFinanciacion) {
		String sql = "SELECT COUNT (*) FROM tipo_financiacion WHERE tipo_financ = (\"" + oTipoFinanciacion.getbTipoFinanciacion() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}
