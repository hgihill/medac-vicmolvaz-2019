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
			String sql = "INSERT INTO inventario VALUES (";
			sql +=  oInventario.getiIdInv() + ",";
			sql += "\"" + oInventario.getoRec() + "\",";
			sql +=  oInventario.getiCant() + ")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Inventario oInventario) {
		int iRes = 0;
		String sql = "DELETE FROM inventario WHERE id_inventario = (\"" + oInventario.getiIdInv() + "\"))";
		iRes =  ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public int update(Inventario oInventario) {
		int iRes = 0;
		
		if (oInventario.checkInventario()) {
		String sql = "UPDATE inventario ";
		sql += "SET id_inv = \"" + oInventario.getiIdInv() + "\","; 
		sql += "cantidad = \"" + oInventario.getiCant() + "\",";
		sql += "recurso = \"" + oInventario.getoRec() + "\" ";
		sql += "WHERE id_inv = \"" + oInventario.getiIdInv() + "\""; 
		iRes =  ConexionDB.executeUpdate(sql);
		}
		return iRes;
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
				lInventario.add(new Inventario(iIdInv, oRec, iCant));
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
		String sql = "SELECT COUNT (*) FROM inventario WHERE id_inventario = (\"" + oInventario.getiIdInv() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}