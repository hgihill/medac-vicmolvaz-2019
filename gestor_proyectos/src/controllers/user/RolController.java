package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.user.Rol;

public class RolController implements IRolController {

	@Override
	public int add(Rol oRol) {
		String sql = "INSERT INTO rol VALUES (";
		sql += "\"" + oRol.getoUs().getsDniCif() + "\",";
		sql += "" + oRol.getbRol() + ",";
		sql += "" + oRol.getoProyecto().getiId_Proyecto() + ")";
		return ConexionDB.executeUpdate(sql);

	}

	@Override
	public int remove(Rol oRol) {
		String sql = "DELETE FROM rol WHERE dni_cif = '" + oRol.getoUs().getsDniCif() + "' AND " + "id_proyecto = "
				+ oRol.getoProyecto().getiId_Proyecto() + "";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Rol oRol) {
		String sql = "UPDATE rol SET ";
		sql += "dni_cif = \"" + oRol.getoUs().getsDniCif() + "\",";
		sql += "rol = " + oRol.getbRol() + ",";
		sql += "id_proyecto = " + oRol.getoProyecto().getiId_Proyecto() + " WHERE ";
		sql += "dni_cif = \"" + oRol.getoUs().getsDniCif() + "\" AND ";
		sql += "id_proyecto = " + oRol.getoProyecto().getiId_Proyecto() + "";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public String mostrarRol() {
		String sResultado = "Mostrando lista de rol de usuarios en proyectos:\n";
		String sql = "SELECT * FROM rol";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sDniCif = rs.getString(1);
				byte bRol = rs.getByte(2);
				int iIdProyecto = rs.getInt(3);
				sResultado += sDniCif + " " + bRol + " " + iIdProyecto + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public int existeRol(Rol oRol) {
		String sql = "SELECT COUNT (*) FROM rol WHERE dni_cif = '" + oRol.getoUs().getsDniCif() + "' AND "
				+ " id_proyecto = " + oRol.getoProyecto().getiId_Proyecto() + "";
		return ConexionDB.executeCount(sql);
	}
}
