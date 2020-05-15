package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.project.Inventario;
import model.project.Recurso;

public class InventarioController implements IInventarioController {

	@Override
	public int add(Inventario oInventario) {
		int iRes = 0;
		if (oInventario.checkInventario()) {
			String sql = "INSERT INTO inventario VALUES (" + oInventario.getiIdInv() + ", '"
					+ oInventario.getoRec().getsNombre() + "', " + oInventario.getiCant() + ")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Inventario oInventario) {
		int iRes = 0;
		String sql = "DELETE FROM inventario WHERE id_inv = (" + oInventario.getiIdInv() + ")";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public int update(Inventario oInventario) {
		int iRes = 0;

		if (oInventario.checkInventario()) {
			String sql = "UPDATE inventario SET cantidad = " + oInventario.getiCant() + ", recurso = '"
					+ oInventario.getoRec().getsNombre() + "' WHERE id_inv = " + oInventario.getiIdInv() + "";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public Inventario searchInventario(Inventario oInventario) {
		Inventario lInventario = null;
		String sql = " SELECT * FROM inventario WHERE id_inv = (\"" + oInventario.getiIdInv() + "\")";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int iIdInv = rs.getInt(1);
				String sRec = rs.getString(2);
				int iCant = rs.getInt(3);
				Recurso oRec = new Recurso(sRec);
				lInventario = new Inventario(iIdInv, oRec, iCant);
			}
			stm.close();
		} catch (SQLException ex) {
			lInventario = null;
		}
		return lInventario;
	}

	@Override
	public String mostrarInventario() {
		String sResultado = "Mostrando listado de inventarios:\n";
		String sql = " SELECT * FROM inventario";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int iIdInv = rs.getInt(1);
				String sRecurso = rs.getString(2);
				int iCant = rs.getInt(3);
				sResultado += iIdInv + " " + sRecurso + " " + iCant + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public int existeInventario(Inventario oInventario) {
		String sql = "SELECT COUNT (*) FROM inventario WHERE id_inv = (\"" + oInventario.getiIdInv() + "\")";
		return ConexionDB.executeCount(sql);
	}
}