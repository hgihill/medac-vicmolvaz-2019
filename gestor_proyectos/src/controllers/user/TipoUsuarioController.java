package controllers.user;

import controllers.ConexionDB;
import model.user.TipoUsuario;

public class TipoUsuarioController implements ITipoUsuarioController{

	@Override
	public int add(TipoUsuario oTipoUs) {
		String sql = "INSERT INTO tipo_usuario VALUES (\"" + oTipoUs.getbTipoUsuario() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(TipoUsuario oTipoUs) {
		String sql = "DELETE FROM tipo_usuario WHERE tipo_usuario = (\"" + oTipoUs.getbTipoUsuario() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeTipoUsuario(TipoUsuario oTipoUs) {
		String sql = "SELECT COUNT(*) FROM tipo_usuario WHERE tipo_usuario = (\"" + +oTipoUs.getbTipoUsuario() + "\")";
		return ConexionDB.executeCount(sql);
	}
}
