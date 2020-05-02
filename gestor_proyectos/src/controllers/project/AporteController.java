package controllers.project;

import controllers.ConexionDB;
import model.project.Aporte;

public class AporteController implements IAporteController{

	@Override
	public int add(Aporte oAporte) {
		String sql = "INSERT INTO aporte VALUES (\"" + oAporte.getoUs() + "\"," 
				+ "\"" + oAporte.getoFin() + "\","
				+ "\"" + oAporte.getiImporte() + "\"," 
				+ "\"" + oAporte.getdFecha() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Aporte oAporte) {
		String sql = "DELETE FROM aporte WHERE dni_cif = (\"" + oAporte.getoUs() + "\" and "
				+ "cuenta = (\"" + oAporte.getoFin() + "\"))";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Aporte oAporte) {
		String sql = "UPDATE aporte SET (\"" + oAporte.getoUs() + "\","  
				+ "\"" + oAporte.getoFin() + "\","  
				+ "\"" + oAporte.getiImporte() + "\","  
				+ "\"" + oAporte.getdFecha() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeAporte(Aporte oAporte) {
		String sql = "SELECT COUNT (*) FROM aporte WHERE dni_cif = (\"" + oAporte.getoUs() + "\" and "
				+ "cuenta = (\"" + oAporte.getoFin() + "\"))";
		return ConexionDB.executeCount(sql);
	}
}
