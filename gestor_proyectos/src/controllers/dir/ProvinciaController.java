package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controllers.ConexionDB;
import controllers.GeneralController;
import model.dir.Pais;
import model.dir.Provincia;

public class ProvinciaController implements IProvinciaController {
	// #########
	// # CRUDS #
	// #########
	@Override
	public int add(Provincia oProvincia) {
		int iRes = 0;
		if (oProvincia.checkProvincia() && existeProvincia(oProvincia) == 0) {
			String sql = "INSERT INTO provincia VALUES ( '" + oProvincia.getsNombreProv() + "', '" + oProvincia.getoPais().getsNombre() + "')";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Provincia oProvincia) {
		int iRes = 0;
		String sql = "DELETE FROM provincia WHERE nombre_prov LIKE \"" + oProvincia.getsNombreProv() + "\"";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public String mostrarProvincia() {
		String sResultado = "Mostrando listado de provincia:\n";
		String sql = " SELECT * FROM provincia";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sNombre = rs.getString(1);
				sResultado += sNombre + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	// ##########
	// # QUERYS #
	// ##########
	
	@Override
	public Provincia searchProvincia(Provincia oProvincia, GeneralController c) {
		Provincia Provincia = null;
		String sql = "SELECT * FROM provincia WHERE nombre_prov = \"" + oProvincia.getsNombreProv() + "\"";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sNombreProvincia = rs.getString(1);
				String sNombrePais = rs.getString(2);
				Pais p = c.getDireccionCtrl().getPaisCtrl().searchPais(new Pais(sNombrePais));
				Provincia = new Provincia(sNombreProvincia, p);
			}
			stm.close();
		} catch (SQLException ex) {
			Provincia = null;
		}

		return Provincia;
	}
	
	@Override
	public int existeProvincia(Provincia oProvincia) {
		int iRes = 0;
		String sql = "SELECT COUNT(*) FROM provincia WHERE nombre_prov LIKE \"" + oProvincia.getsNombreProv() + "\"";
		iRes = ConexionDB.executeCount(sql);
		return iRes;
	}


}
