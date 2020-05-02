package controllers.user;

import controllers.ConexionDB;
import model.user.Tipo_Usuario;

public class TipoUsuarioController implements ITipoUsuarioController{

	@Override
	public int add(Tipo_Usuario oTipoUs) {
		String sql = "INSERT INTO tipo_usuario VALUES (\"" + oTipoUs.getbTipoUsuario() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Tipo_Usuario oTipoUs) {
		String sql = "DELETE FROM tipo_usuario WHERE tipo_usuario = (\"" + oTipoUs.getbTipoUsuario() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int ExisteTipoUsuario(Tipo_Usuario oTipoUs) {
		String sql = "SELECT COUNT(*) FROM tipo_usuario WHERE tipo_usuario = (\"" + +oTipoUs.getbTipoUsuario() + "\")";
		return ConexionDB.executeCount(sql);
	}
}
