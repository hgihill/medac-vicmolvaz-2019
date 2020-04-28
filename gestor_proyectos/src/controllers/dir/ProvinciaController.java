package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
import controllers.ConexionDB;
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
					+ oProvincia.getoPais().getsNombrePais() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Provincia oProvincia) {
		int iRes = 0;
		String sql = "DELETE FROM provincia WHERE nombre LIKE \"" + oProvincia.getsNombreProv() + "\"";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	// ##########
	// # QUERYS #
	// ##########
	@Override
	public int existeProvincia(Provincia oProvincia) {
		int iRes = 0;
		if (oProvincia.checkProvincia()) {
			String sql = "SELECT COUNT(*) FROM provincia WHERE nombre LIKE \"" + oProvincia.getsNombreProv() + "\"";
			iRes = ConexionDB.executeCount(sql);
		}
		return iRes;
	}

	@Override
	public List<Provincia> searchProvinciasPorPais(Pais oPais) {
		List<Provincia> lProvincias = new ArrayList<Provincia>();

		String sql = "SELECT * FROM provincia WHERE nombre_pais LIKE \"" + oPais.getsNombrePais() + "\"";
		Statement stm = null;

		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				lProvincias.add(new Provincia(rs.getString(1), oPais));
			}
			stm.close();
		} catch (SQLException e) {
			lProvincias = null;
		}
		return lProvincias;
	}
}
