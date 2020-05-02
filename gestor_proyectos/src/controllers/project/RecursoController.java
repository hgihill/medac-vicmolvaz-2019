package controllers.project;

import controllers.ConexionDB;
import model.project.Recurso;

public class RecursoController implements IRecursoController{
	
	@Override
	public int add(Recurso oRecurso) {
		String sql = "INSERT INTO recurso VALUES (\"" + oRecurso.getsNombre() + "\"," 
				+ "\"" + oRecurso.getiCant() + "\","
				+ "\"" + oRecurso.getoTipoRec() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Recurso oRecurso) {
		String sql = "DELETE FROM recurso WHERE nombre = (\"" + oRecurso.getsNombre() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Recurso oRecurso) {
		String sql = "UPDATE recurso SET (\"" + oRecurso.getsNombre() + "\","  
				+ "\"" + oRecurso.getiCant() + "\","
				+ "\"" + oRecurso.getoTipoRec() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeRecurso(Recurso oRecurso) {
		String sql = "SELECT COUNT (*) FROM recurso WHERE nombre = (\"" + oRecurso.getsNombre() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}