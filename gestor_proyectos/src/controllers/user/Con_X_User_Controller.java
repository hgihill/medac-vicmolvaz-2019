package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.user.Con_X_User;

public class Con_X_User_Controller implements ICon_X_UserController {

	@Override
	public int add(Con_X_User oConXUs) {
		String sql = "INSERT INTO con_x_us VALUES ('" + oConXUs.getoCon().getsNombre() + "' AND '"
				+ oConXUs.getoUs().getsDniCif() + "')";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Con_X_User oConXUs) {
		String sql = "DELETE FROM apt_x_us WHERE nombre_con = '" + oConXUs.getoCon().getsNombre()
				+ "' AND dni_cif = '" + oConXUs.getoUs().getsDniCif() + "'";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public String mostrarConocimientoDeUsuario() {
		String sResultado = "Mostrando listado de conocimientos:\n";
		String sql = " SELECT * FROM con_x_us";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sDniCif = rs.getString(1);
				String sCon = rs.getString(2);
				sResultado += sDniCif + " " + sCon + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public int existeConocimientoDeUsuario(Con_X_User oConXUs) {
		String sql = "SELECT COUNT(*) FROM con_x_us WHERE nombre_con = '" + oConXUs.getoCon().getsNombre()
				+ "' AND  dni_cif= '" + oConXUs.getoUs().getsDniCif() + "'";
		return ConexionDB.executeCount(sql);
	}

}
