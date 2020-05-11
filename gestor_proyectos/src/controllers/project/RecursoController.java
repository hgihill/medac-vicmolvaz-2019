package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import controllers.GeneralController;
import model.project.Recurso;

public class RecursoController implements IRecursoController {

	@Override
	public int add(Recurso oRecurso) {
		int iRes = 0;
		if (oRecurso.checkRecurso()) {
			String sql = "INSERT INTO recurso VALUES (" + oRecurso.getsNombre() + ",\"" + oRecurso.getiCant() + ",\""
					+ oRecurso.getoTipoRec() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Recurso oRecurso) {
		int iRes = 0;
		String sql = "DELETE FROM recurso WHERE nombre_rec =" + oRecurso.getsNombre();
		System.out.println(sql);
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public int update(Recurso oRecurso) {
	int iRes = 0;
	if (oRecurso.checkRecurso()) {
		String sql = "UPDATE recurso ";
		sql += "SET cantidad = \"" + oRecurso.getiCant() + "\",";
		sql += "WHERE nombre = \"" + oRecurso.getsNombre() + "\",";
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
		String sql = "SELECT COUNT (*) FROM recurso WHERE nombre_rec = (\"" + oRecurso.getsNombre() + "\"))";
		return ConexionDB.executeCount(sql);
	}

	@Override
	public Recurso searchRecurso(Recurso oObjeto, GeneralController c) {
		Recurso oRecurso = null;
		System.out.println(existeRecurso(oRecurso));
		if (existeRecurso(oObjeto) > 0) {
			String sql = "SELECT * FROM recurso WHERE nombre_rec = '" + oObjeto.getsNombre() + "'";
			Statement stm = null;
			System.out.println(ConexionDB.executeCount(sql));
			try {
				stm = ConexionDB.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					String sNombre = rs.getString(1);
					int iCant = rs.getInt(2);

					oRecurso = new Recurso(sNombre, iCant);
				}
				stm.close();
			} catch (SQLException ex) {
				oRecurso = null;
			}
		}
		return oRecurso;
	}
}