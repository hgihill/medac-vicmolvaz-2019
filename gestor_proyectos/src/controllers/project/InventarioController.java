package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
import model.project.Inventario;
import model.project.Recurso;


public class InventarioController implements IInventarioController {

	@Override
	public int add(Inventario oInventario) {
		int iRes = 0;
		if(oInventario.checkInventario()) {
			String sql = "INSERT INTO aporte VALUES (\"" + oInventario.getiIdInv() + "\"," + "\"" + oInventario.getiCant()
			+ "\"," + "\"" + oInventario.getoRec() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Inventario oInventario) {
		String sql = "DELETE FROM inventario WHERE id_inventario = (\"" + oInventario.getiIdInv() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Inventario oInventario) {
		String sql = "UPDATE inventario SET (\"" + oInventario.getiIdInv() + "\"," + "\"" + oInventario.getiCant()
				+ "\"," + "\"" + oInventario.getoRec() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public List<Inventario> searchInventario(Inventario oInventario) {
		List<Inventario> lInventario = new ArrayList<Inventario>();
		String sql = " SELECT * FROM financiacion WHERE cuenta = (\"" + oInventario.getiIdInv() + "\")";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int iIdInv = rs.getInt(1);
				String sRec = rs.getString(2);
				int iCant = rs.getInt(3);
				Recurso oRec = new Recurso(sRec);
				lInventario.add(new Inventario(iIdInv, iCant, oRec));
			}
			stm.close();
		} catch (SQLException ex) {
			lInventario = null;
		}
		return lInventario;
	}

	@Override
	public int existeInventario(Inventario oInventario) {
		String sql = "SELECT COUNT (*) FROM inventario WHERE id_inventario = (\"" + oInventario.getiIdInv() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}