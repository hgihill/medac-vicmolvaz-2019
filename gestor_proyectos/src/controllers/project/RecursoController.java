package controllers.project;

import controllers.ConexionDB;
import model.project.Recurso;

public class RecursoController implements IRecursoController{
	private Recurso vRecurso[];
	
	@Override
	public int add(Recurso oRecurso) {
		String sql = "INSERT INTO recurso VALUES (\"" + oRecurso.getsNombre() + "\"," 
				+ "\"" + oRecurso.getiCant() + "\","
				+ "\"" + oRecurso.getoTipoRec() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Recurso oRecurso) {
		String sql = "DELETE FROM recurso WHERE nombre_rec = (\"" + oRecurso.getsNombre() + "\"))";
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
	public Recurso searchRecurso(Recurso oObjeto) {
		Recurso oRecurso= null;
		int iContador = 0;
		while (oRecurso == null && iContador < vRecurso.length) {
			if(oObjeto.equals(vRecurso[iContador])) {
				oRecurso = vRecurso[iContador];
			}
			iContador++;
		}
		return oRecurso;
	}

	@Override
	public int existeRecurso(Recurso oRecurso) {
		String sql = "SELECT COUNT (*) FROM recurso WHERE nombre_rec = (\"" + oRecurso.getsNombre() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}