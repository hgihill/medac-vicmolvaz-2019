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
	public int add(Provincia oProvincia, PaisController paisCtrl) {
		int iRes = 0;
		if (oProvincia.checkProvincia()) {

			// 1) Anadir el pais
			paisCtrl.add(oProvincia.getoPais());

			// 2) Anado la provincia
			String sql = "INSERT INTO provincia VALUES (\"" + oProvincia.getsNombreProv() + "\",\""
					+ oProvincia.getoPais().getsNombre() + "\")";
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
	public int existeProvincia(Provincia oProvincia) {
		int iRes = 0;
		String sql = "SELECT COUNT(*) FROM provincia WHERE nombre_prov LIKE '" + oProvincia.getsNombreProv() + "'";
		iRes = ConexionDB.executeCount(sql);
		return iRes;
	}

	public Provincia searchProvincia(Provincia oProvincia, GeneralController c) {
		Provincia Provincia = null;
		System.out.println(existeProvincia(oProvincia));
		if (existeProvincia(oProvincia) > 0) {
			String sql = "SELECT * FROM PROVINCIA WHERE NOMBRE_PROV = '" + oProvincia.getsNombreProv() + "'";
			Statement stm = null;
			System.out.println(ConexionDB.executeCount(sql));
			try {
				stm = ConexionDB.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					String sNombreProvincia = rs.getString(1);
					String sNombrePais = rs.getString(2);
					Pais p = c.direccionCtrl.getPaisCtrl().searchPais(new Pais(sNombrePais));
					Provincia = new Provincia(sNombreProvincia, p);
				}
				stm.close();
			} catch (SQLException ex) {
				Provincia = null;
			}
		}
		return Provincia;
	}

}
