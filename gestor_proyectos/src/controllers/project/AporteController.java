package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.project.Aporte;

public class AporteController implements IAporteController{

	@Override
	public int add(Aporte oAporte) {
		int iRes = 0;
		String sql = "INSERT INTO aporte VALUES (";
		sql	+= "\"" + oAporte.getoUs().getsDniCif() + "\","; 
		sql	+= "\"" + oAporte.getoFin().getsCuenta() + "\",";
		sql	+= oAporte.getiImporte() + ")"; 
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public int remove(Aporte oAporte) {
		int iRes = 0;
		String sql = "DELETE FROM aporte WHERE dni_cif = \"" + oAporte.getoUs().getsDniCif() + "\" AND "
				+ "cuenta = \"" + oAporte.getoFin().getsCuenta() + "\"";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public int update(Aporte oAporte) {
		String sql = "UPDATE aporte SET ";
		sql += "importe = " + oAporte.getiImporte() + " WHERE ";
		sql += "dni_cif = \"" + oAporte.getoUs().getsDniCif() + "\" AND ";  
		sql += "cuenta = \"" + oAporte.getoFin().getsCuenta() + "\"";  
		
		return ConexionDB.executeUpdate(sql);
	}

	@Override 
	public String mostrarAporte() {
		String sResultado = "Mostrando listado de aportes:\n";
		String sql = "SELECT * FROM aporte";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sDniCif = rs.getString(1);
				String sCuenta = rs.getString(2);
				int iImporte = rs.getInt(3);
				sResultado += sDniCif + " " + sCuenta + " " + iImporte;
			}
			
		}catch(SQLException ex) {
			
		}
		return sResultado;
	}
	
	@Override
	public int existeAporte(Aporte oAporte) {
		String sql = "SELECT COUNT (*) FROM aporte WHERE dni_cif = \"" + oAporte.getoUs().getsDniCif() + "\" AND "
				+ "cuenta = \"" + oAporte.getoFin().getsCuenta() + "\"";
		return ConexionDB.executeCount(sql);
	}
	
	
}
