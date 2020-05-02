package controllers.user;

import controllers.ConexionDB;
import model.user.Rol;

public class RolController implements IRolController{

	@Override
	public int add(Rol oRol) {
		String sql = "INSERT INTO rol VALUES (\"" + oRol.getoUs() + "\"," 
				+ "\"" + oRol.getoProyecto() + "\"," 
				+ "\"" + oRol.getbRol() + "\")";
		return ConexionDB.executeUpdate(sql);

	}

	@Override
	public int remove(Rol oRol) {
		String sql = "DELETE FROM rol WHERE dni_cif = (\"" + oRol.getoUs() + "\" and " 
				+ " id_proyecto = (\"" + oRol.getoProyecto() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Rol oRol) {
		String sql = "UPDATE rol SET (\"" + oRol.getoUs() + "\"," 
				+ "\"" + oRol.getoProyecto() + "\"," 
				+ "\"" + oRol.getbRol() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeRol(Rol oRol) {
		String sql = "SLECT COUN (*) FROM rol WHERE dni_cif = (\"" + oRol.getoUs() + "\" and " + " id_proyecto = (\""
				+ oRol.getoProyecto() + "\")";
		return ConexionDB.executeCount(sql);
	}
}
