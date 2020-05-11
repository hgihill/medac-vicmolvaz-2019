package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import model.dir.Pais;

public class PaisController implements IPaisController {

	// #########
	// # CRUDS #
	// #########
	@Override
	public int add(Pais oObject) {
		int iRes = 0;
		if (oObject.checkPais()) {
			String sql = "INSERT INTO pais VALUES (\"" + oObject.getsNombre() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Pais oPais) {
		int iRes = 0;
		String sql = "DELETE FROM pais WHERE nombre_pais LIKE \"" + oPais.getsNombre() + "\"";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public String mostrarPais() {
		String sResultado = "Mostrando listado de paises:\n";
		String sql = " SELECT * FROM pais";
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

	@Override
	public int Update(Pais oPais, Pais oOtro) {
		int iRes = 0;

		if (oPais.checkPais()) {
			remove(oPais);
			iRes = add(oOtro);
		}
		return iRes;
	}

	// ###########
	// # QUERIES #
	// ###########

	@Override
	public Pais searchPais(Pais oPais) {
		Pais Pais = null;
		if (existePais(oPais) > 0) {
			String sql = " SELECT * FROM pais WHERE nombre_pais = (\"" + oPais.getsNombre() + "\")";
			Statement stm = null;
			try {
				stm = ConexionDB.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					String sNombre = rs.getString(1);
					Pais = new Pais(sNombre);
				}
				stm.close();
			} catch (SQLException ex) {
				Pais = null;
			}
		}
		return Pais;
	}

	@Override
	public int existePais(Pais oPais) {
		int iRes = 0;
		if (oPais.checkPais()) {
			String sql = "SELECT COUNT(*) FROM pais WHERE nombre_pais LIKE \"" + oPais.getsNombre() + "\"";
			iRes = ConexionDB.executeCount(sql);
		}
		return iRes;
	}

}