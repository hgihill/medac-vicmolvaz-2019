package controllers.project;

import controllers.ConexionDB;
import model.project.TipoRecurso;

public class TipoRecursoController implements ITipoRecursoController{

	@Override
	public int add(TipoRecurso oTipoRecurso) {
		String sql = "INSERT INTO tipo_recurso VALUES (\"" + oTipoRecurso.getbTipoRecurso() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(TipoRecurso oTipoRecurso) {
		String sql = "DELETE FROM tipo_recurso WHERE tipo_recurso = (\"" + oTipoRecurso.getbTipoRecurso() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeTipoRecurso(TipoRecurso oTipoRecurso) {
		String sql = "SELECT COUNT (*) FROM tipo_recurso WHERE tipo_recurso = (\"" + oTipoRecurso.getbTipoRecurso() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}