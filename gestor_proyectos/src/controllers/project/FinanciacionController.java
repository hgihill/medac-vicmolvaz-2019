package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import controllers.GeneralController;
import model.project.Financiacion;
import model.project.Proyecto;
import model.project.TipoFinanciacion;

public class FinanciacionController implements IFinanciacionController {

	@Override
	public int add(Financiacion oFinanciacion) {
		String sql = "INSERT INTO financiacion VALUES ('" + oFinanciacion.getsCuenta() + "', "
				+ oFinanciacion.getoTipoFin().getbTipoFinanciacion() + ", "
				+ oFinanciacion.getoProyecto().getiId_Proyecto() + ", '" + oFinanciacion.getsEntidad() + "')";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Financiacion oFinanciacion) {
		String sql = "DELETE FROM financiacion WHERE cuenta = ('" + oFinanciacion.getsCuenta() + "')";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Financiacion oFinanciacion) {
		String sql = "UPDATE financiacion SET tipo_fin = " + oFinanciacion.getoTipoFin().getbTipoFinanciacion() + ", id_proyecto = "
				+ oFinanciacion.getoProyecto().getiId_Proyecto() + ", entidad = " + "'" + oFinanciacion.getsEntidad()
				+ "' WHERE cuenta = '" + oFinanciacion.getsCuenta() + "'";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public String mostrarFinanciacion() {
		String sResultado = "Mostrando listado de financiaciones:\n";
		String sql = " SELECT * FROM financiacion";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCuenta = rs.getString(1);
				byte bTipoFinanc = rs.getByte(2);
				int iIdProyecto = rs.getInt(3);
				String sEntidad = rs.getString(4);
				sResultado += sCuenta + " " + bTipoFinanc + " " + iIdProyecto + " " + sEntidad + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public Financiacion search(Financiacion oFinanciacion, GeneralController c) {
		Financiacion lFinanciacion = null;
		String sql = " SELECT * FROM financiacion WHERE cuenta = \"" + oFinanciacion.getsCuenta() + "\"";
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
				Proyecto oPro = c.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iIdPro), c);
				lFinanciacion = new Financiacion(sCuenta, sEntidad, oTFin, oPro);
			}
			stm.close();
		} catch (SQLException ex) {
			lFinanciacion = null;
		}
		return lFinanciacion;
	}

	@Override
	public Financiacion searchByPK(Financiacion oFinanciacion) {
		Financiacion lFinanciacion = null;
		String sql = " SELECT * FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\")";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCuenta = rs.getString(1);
				lFinanciacion = new Financiacion(sCuenta);
			}
			stm.close();
		} catch (SQLException ex) {
			lFinanciacion = null;
		}
		return lFinanciacion;
	}

	@Override
	public int existeFinanciacion(Financiacion oFinanciacion) {
		String sql = "SELECT COUNT (*) FROM financiacion WHERE cuenta = (\"" + oFinanciacion.getsCuenta() + "\")";
		return ConexionDB.executeCount(sql);
	}
}