package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.user.Aptitud;

public class AptitudController implements IAptitudController{

	@Override
	public int add(Aptitud oApt) {
		String sql = "INSERT INTO aptitud VALUES (\"" + oApt.getsNombreApt() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Aptitud oApt) {
		String sql = "DELETE FROM aptitud WHERE nombre_apt = (\"" + oApt.getsNombreApt() + "\")";
		return ConexionDB.executeUpdate(sql);
	}
	
	@Override
	public String mostrarAptitud() {
		String sResultado = "Mostrando listado de aptitudes:\n";
		String sql = " SELECT * FROM aptitud";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sApt = rs.getString(1);
				sResultado += sApt + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}
	
	@Override
	public Aptitud search(Aptitud oApt) {
		Aptitud lApt = null;
		String sql = "SELECT * FROM aptitud where nombre_apt = \"" + oApt.getsNombreApt() + "\"";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sNombre = rs.getString(1);
				
				lApt = new Aptitud(sNombre);
			}
		} catch (SQLException ex) {
			lApt = null;
		}
		return lApt;
	}

	@Override
	public int ExisteAptitud(Aptitud oApt) {
		String sql = "SELECT COUNT(*) FROM aptitud WHERE nombre_apt = (\"" + oApt.getsNombreApt() + "\")";
		return ConexionDB.executeCount(sql);
	}
}
