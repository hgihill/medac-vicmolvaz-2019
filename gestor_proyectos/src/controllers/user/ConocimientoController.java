package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.user.Conocimiento;

public class ConocimientoController implements IConocimientoController {

	@Override
	public int add(Conocimiento oCon) {
		String sql = "INSERT INTO conocimiento VALUES ('" + oCon.getsNombre() + "')";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Conocimiento oCon) {
		String sql = "DELETE FROM conocimiento WHERE nombre_con = \"" + oCon.getsNombre() + "\"";
		return ConexionDB.executeUpdate(sql);
	}
	
	@Override
	public String mostrarConocimiento() {
		String sResultado = "Mostrando listado de conocimientos:\n";
		String sql = " SELECT * FROM conocimiento";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCon = rs.getString(1);
				sResultado += sCon + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}
	
	@Override
	public Conocimiento search(Conocimiento oCon) {
		Conocimiento lCon = null;
		String sql = "SELECT * FROM conocimiento where nombre_con = \"" + oCon.getsNombre() + "\"";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sNombre = rs.getString(1);
				
				lCon = new Conocimiento(sNombre);
			}
		} catch (SQLException ex) {
			lCon = null;
		}
		return lCon;
	}

	@Override
	public int existeConocimiento(Conocimiento oCon) {
		String sql = "SELECT COUNT(*) FROM conocimiento WHERE nombre_con = \"" + oCon.getsNombre() + "\"";
		return ConexionDB.executeCount(sql);
	}
}
