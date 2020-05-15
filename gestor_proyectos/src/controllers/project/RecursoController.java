package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.project.Recurso;
import model.project.TipoRecurso;

public class RecursoController implements IRecursoController {

	@Override
	public int add(Recurso oRecurso) {
		int iRes = 0;
		if (oRecurso.checkRecurso()) {
			String sql = "INSERT INTO recurso VALUES (";
			sql += "\"" + oRecurso.getsNombre() + "\",";
			sql += "" + oRecurso.getiCant() + ",";
			sql += "" + oRecurso.getoTipoRec().getbTipoRecurso() + ")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Recurso oRecurso) {
		int iRes = 0;
		String sql = "DELETE FROM recurso WHERE nombre_rec LIKE \"" + oRecurso.getsNombre() + "\"";
		System.out.println(sql);
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public int update(Recurso oRecurso) {
		int iRes = 0;
		if (oRecurso.checkRecurso()) {
			String sql = "UPDATE recurso SET ";
			sql += "cantidad_rec = " + oRecurso.getiCant() + ",";
			sql += "tipo_rec = " + oRecurso.getoTipoRec().getbTipoRecurso() + " WHERE ";
			sql += "nombre_rec = \"" + oRecurso.getsNombre() + "\"";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public String mostrarRecurso() {
		String sResultado = "Mostrando listado de recuso:\n";
		String sql = " SELECT * FROM recurso";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sNombre = rs.getString(1);
				int iCant = rs.getInt(2);
				byte bTRec = rs.getByte(3);
				sResultado += sNombre + " " + iCant + " " + bTRec + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public int existeRecurso(Recurso oRecurso) {
		String sql = "SELECT COUNT (*) FROM recurso WHERE nombre_rec = \"" + oRecurso.getsNombre() + "\"";
		return ConexionDB.executeCount(sql);
	}

	@Override
	public Recurso search(Recurso oObjeto) {
		Recurso oRecurso = null;
		if (existeRecurso(oObjeto) > 0) {
			String sql = "SELECT * FROM recurso WHERE nombre_rec = '" + oObjeto.getsNombre() + "'";
			Statement stm = null;
			try {
				stm = ConexionDB.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					String sNombre = rs.getString(1);
					int iCant = rs.getInt(2);
					byte bTipoRec = rs.getByte(3);
					TipoRecurso oTRec = new TipoRecurso(bTipoRec);
					oRecurso = new Recurso(sNombre, iCant, oTRec);
				}
				stm.close();
			} catch (SQLException ex) {
				oRecurso = null;
			}
		}
		return oRecurso;
	}
}