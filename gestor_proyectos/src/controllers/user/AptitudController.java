package controllers.user;

import controllers.ConexionDB;
import model.user.Aptitud;

public class AptitudController implements IAptitudController{

	@Override
	public int add(Aptitud oApt) {
		String sql = "INSERT INTO aptitud VALUES (\"" + oApt.getsNombreApt() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Aptitud oApt) {
		String sql = "DELETE FROM aptitud WHERE nombre_apt = (\"" + oApt.getsNombreApt() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int ExisteUsuario(Aptitud oApt) {
		String sql = "SELECT COUNT(*) FROM aptitud WHERE nombre_apt = (\"" + oApt.getsNombreApt() + "\")";
		return ConexionDB.executeCount(sql);
	}
}
