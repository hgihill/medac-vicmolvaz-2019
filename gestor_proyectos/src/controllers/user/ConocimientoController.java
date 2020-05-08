package controllers.user;

import controllers.ConexionDB;
import model.user.Conocimiento;

public class ConocimientoController implements IConocimientoController {

	@Override
	public int add(Conocimiento oCon) {
		String sql = "INSERT INTO conocimiento VALUES (\" " + oCon.getsNombre() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Conocimiento oCon) {
		String sql = "DELETE FROM conocimiento WHERE nombre_con = (\"" + oCon.getsNombre() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeConocimiento(Conocimiento oCon) {
		String sql = "SELECT COUNT(*) FROM conocimiento WHERE nombre_con = (\"" + oCon.getsNombre() + "\")";
		return ConexionDB.executeCount(sql);
	}
}
