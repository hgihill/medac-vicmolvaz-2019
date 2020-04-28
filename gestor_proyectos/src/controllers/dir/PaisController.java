package controllers.dir;

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
			String sql = "INSERT INTO pais VALUES (\"" + oObject.getsNombrePais() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Pais oPais) {
		int iRes = 0;
		String sql = "DELETE FROM pais WHERE nombre LIKE \"" + oPais.getsNombrePais() + "\"";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	// ###########
	// # QUERIES #
	// ###########
	@Override
	public int existePais(Pais oPais) {
		int iRes = 0;
		if (oPais.checkPais()) {
			String sql = "SELECT COUNT(*) FROM pais WHERE nombre LIKE \"" + oPais.getsNombrePais() + "\"";
			iRes = ConexionDB.executeCount(sql);
		}
		return iRes;
	}
}