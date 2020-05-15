package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.user.TipoUsuario;

public class TipoUsuarioController {
	public TipoUsuario search(TipoUsuario oTipo) {
		TipoUsuario lTipo = null;
		String sql = "SELECT * FROM tipo_usuario WHERE tipo_usuario = " + oTipo.getbTipoUsuario();
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				byte bTipo = rs.getByte(1);
				lTipo = new TipoUsuario(bTipo);
			}
		} catch (SQLException ex) {
			lTipo = null;
		}
		return lTipo;
	}

	public int existeTipoU(TipoUsuario oTipo) {
		String sql = "SELECT COUNT(*) FROM tipo_usuario WHERE tipo_usuario = " + oTipo.getbTipoUsuario();
		return ConexionDB.executeCount(sql);
	}

}
