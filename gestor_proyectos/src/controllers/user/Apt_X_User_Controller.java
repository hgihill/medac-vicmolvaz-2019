package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.user.Apt_X_User;

public class Apt_X_User_Controller implements IApt_X_UserController {

	@Override
	public int add(Apt_X_User oAptXUs) {
		String sql = "INSERT INTO apt_x_us VALUES ('" + oAptXUs.getoApt().getsNombreApt() + "' AND '"
				+ oAptXUs.getoUs().getsDniCif() + "')";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Apt_X_User oAptXUs) {
		String sql = "DELETE FROM apt_x_us WHERE nombre_apt = '" + oAptXUs.getoApt().getsNombreApt()
				+ "' AND dni_cif = '" + oAptXUs.getoUs().getsDniCif() + "'";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public String mostrarAptitudDeUsuario() {
		String sResultado = "Mostrando listado de aptitudes:\n";
		String sql = " SELECT * FROM apt_x_us";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sDniCif = rs.getString(1);
				String sApt = rs.getString(2);
				sResultado += sDniCif + " " + sApt + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public int ExisteAptitudDeUsuario(Apt_X_User oAptXUs) {
		String sql = "SELECT COUNT(*) FROM apt_x_us WHERE nombre_apt = '" + oAptXUs.getoApt().getsNombreApt()
				+ "' AND  dni_cif= '" + oAptXUs.getoUs().getsDniCif() + "'";
		return ConexionDB.executeCount(sql);
	}

}
