package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.project.TipoRecurso;

public class TipoRecursoController {
	public TipoRecurso search(TipoRecurso oTipo) {
		TipoRecurso lTipo = null;
		String sql = "SELECT * FROM tipo_recurso WHERE tipo_recurso = "+oTipo.getbTipoRecurso();
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				byte bTipo = rs.getByte(1);
				lTipo = new TipoRecurso(bTipo);
			}
		}catch(SQLException ex) {
			lTipo = null;
		}
		return lTipo;
	}
	
	public int ExisteTipoR(TipoRecurso oTipo) {
		String sql = "SELECT COUNT(*) FROM tipo_recurso WHERE tipo_recurso = "+ oTipo.getbTipoRecurso();
		return ConexionDB.executeCount(sql);
	}

}
