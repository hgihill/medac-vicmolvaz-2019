package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
import model.project.Financiacion;
import model.project.Proyecto;
import model.project.TipoFinanciacion;

public class FinanciacionController implements IFinanciacionController {

	@Override
	public int add(Financiacion oFinanciacion) {
		String sql = "INSERT INTO financiacion VALUES (\"" + oFinanciacion.getsCuenta() + "\"," + "\""
				+ oFinanciacion.getsEntidad() + "\"," + "\"" + oFinanciacion.getoTipoFin() + "\"," + "\""
				+ oFinanciacion.getoProyecto() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Financiacion oFinanciacion) {
		String sql = "DELETE FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Financiacion oFinanciacion) {
		String sql = "UPDATE financiacion SET (\"" + oFinanciacion.getsCuenta() + "\"," + "\""
				+ oFinanciacion.getsEntidad() + "\"," + "\"" + oFinanciacion.getoTipoFin() + "\"," + "\""
				+ oFinanciacion.getoProyecto() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public List<Financiacion> searchFinanciacion(Financiacion oFinanciacion) {
		List<Financiacion> lFinanciacion = new ArrayList<Financiacion>();
		String sql = " SELECT * FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\")";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCuenta = rs.getString(1);
				byte bTFin = rs.getByte(2);
				int iIdPro = rs.getInt(3);
				String sEntidad = rs.getString(4);
				TipoFinanciacion oTFin = new TipoFinanciacion(bTFin);
				Proyecto oPro = new Proyecto(iIdPro);
				lFinanciacion.add(new Financiacion(sCuenta, sEntidad, oTFin, oPro));
			}
			stm.close();
		} catch (SQLException ex) {
			lFinanciacion = null;
		}
		return lFinanciacion;
	}

	@Override
	public int existeFinanciacion(Financiacion oFinanciacion) {
		String sql = "SELECT COUNT (*) FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}