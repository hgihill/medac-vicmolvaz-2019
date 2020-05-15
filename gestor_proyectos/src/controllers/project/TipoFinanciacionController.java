package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.project.TipoFinanciacion;;

public class TipoFinanciacionController {
	
	public TipoFinanciacion search(TipoFinanciacion oTipo) {
		TipoFinanciacion lTipo = null;
		String sql = "SELECT * FROM tipo_financiacion WHERE tipo_financ = " + oTipo.getbTipoFinanciacion();
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				byte bTipo = rs.getByte(1);
				lTipo = new TipoFinanciacion(bTipo);
			}
		} catch (SQLException ex) {
			lTipo = null;
		}
		return lTipo;
	}

	public int ExisteTipoF(TipoFinanciacion oTipo) {
		String sql = "SELECT COUNT(*) FROM tipo_financiacion WHERE tipo_financ = " + oTipo.getbTipoFinanciacion();
		return ConexionDB.executeCount(sql);
	}

}
