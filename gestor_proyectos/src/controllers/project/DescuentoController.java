package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.project.Descuento;

public class DescuentoController implements IDescuentoController{

	@Override
	public int add(Descuento oDescuento) {
		String sql = "INSERT INTO descuento VALUES (";
		sql += "" + oDescuento.getoInv().getiIdInv() + ",";
		sql += "\"" + oDescuento.getoUs().getsDniCif() + "\","; 
		sql += "" + oDescuento.getfPorcentaje() + ")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Descuento oDescuento) {
		String sql = "DELETE FROM descuento WHERE ";
		sql += "dni_cif = \"" + oDescuento.getoUs().getsDniCif() + "\" AND ";
		sql += "id_inv = " + oDescuento.getoInv().getiIdInv() + "";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Descuento oDescuento) {
		String sql = "UPDATE descuento SET ";
		sql += "porcentaje = " + oDescuento.getfPorcentaje() + " WHERE ";
		sql += "dni_cif = \"" + oDescuento.getoUs().getsDniCif() + "\" AND ";
		sql += "d_inv = " + oDescuento.getoInv().getiIdInv() + "";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public String mostrarDescuento() {
		String sResultado = "Mostrando listado de descuentos:\n";
		String sql = " SELECT * FROM descuento";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int iIdInv = rs.getInt(1);
				String sDniCif = rs.getString(2);
				float fPorcentaje = rs.getFloat(3);
				sResultado += iIdInv + " " + sDniCif + " " + fPorcentaje + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}
	
	@Override
	public int existeDescuento(Descuento oDescuento) {
		String sql = "SELECT COUNT (*) FROM descuento WHERE dni_cif = \"" + oDescuento.getoUs().getsDniCif() + "\" AND "
				+ "id_inv = " + oDescuento.getoInv().getiIdInv() + "";
		return ConexionDB.executeCount(sql);
	}
}
