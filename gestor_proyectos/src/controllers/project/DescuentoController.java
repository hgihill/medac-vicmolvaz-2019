package controllers.project;

import controllers.ConexionDB;
import model.project.Descuento;

public class DescuentoController implements IDescuentoController{

	@Override
	public int add(Descuento oDescuento) {
		String sql = "INSERT INTO descuento VALUES (\"" + oDescuento.getoUs() + "\"," 
				+ "\"" + oDescuento.getoInv() + "\","
				+ "\"" + oDescuento.getfPorcentaje() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Descuento oDescuento) {
		String sql = "DELETE FROM descuento WHERE dni_cif = (\"" + oDescuento.getoUs() + "\" and "
				+ "id_inv = (\"" + oDescuento.getoInv() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Descuento oDescuento) {
		String sql = "UPDATE descuento SET (\"" + oDescuento.getoUs() + "\","  
				+ "\"" + oDescuento.getoInv() + "\"," 
				+ "\"" + oDescuento.getfPorcentaje() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeDescuento(Descuento oDescuento) {
		String sql = "SELECT COUNT (*) FROM descuento WHERE dni_cif = (\"" + oDescuento.getoUs() + "\" and "
				+ "id_inv = (\"" + oDescuento.getoInv() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}
