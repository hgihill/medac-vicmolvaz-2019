package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Provincia;

public class DireccionController implements IDireccionController {

	// #########
	// # CRUDS #
	// #########
	@Override
	public int add(Direccion oDireccion, LocalidadController localidadCtrl, ProvinciaController provinciaCtrl,
			PaisController paisCtrl) {
		int iRes = 0;

		if (oDireccion.checkDireccion()) {
			// 1) Compruebo si existe la localidad, provincia y pais
			if (localidadCtrl.existeLocalidad(oDireccion.getoLoc()) != 0
					&& provinciaCtrl.existeProvincia(oDireccion.getoLoc().getoProv()) != 0
					&& paisCtrl.existePais(oDireccion.getoLoc().getoProv().getoPais()) != 0) {

				// 2) Anado la direccion
				String sql = "INSERT INTO direccion VALUES (";
				sql += "\"" + oDireccion.getsCalle() + "\",";
				sql += "\"" + oDireccion.getbBloque() + "\",";
				sql += oDireccion.getbNum() + ",";
				sql += oDireccion.getbBloque() + ",";
				sql += "'" + oDireccion.getbPortal() + "',";
				sql += oDireccion.getoLoc().getsCP();
				sql += ")";
				iRes = ConexionDB.executeUpdate(sql);
			}
		}
		return iRes;
	}

	@Override
	public int remove(Direccion oDireccion) {
		int iRes = 0;
		if (oDireccion.checkDireccion()) {
			String sql = "DELETE FROM direccion WHERE calle LIKE \"" + oDireccion.getsCalle() + "\" AND numero="
					+ oDireccion.getbNum();
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	// ##########
	// # QUERYS #
	// ##########
	@Override
	public int existeDireccion(Direccion oDireccion) {
		int iRes = 0;
		if (oDireccion.checkDireccion()) {
			String sql = "SELECT COUNT(*) FROM direccion WHERE calle=\"" + oDireccion.getsCalle() + "\" AND numero="
					+ oDireccion.getbNum();
			iRes = ConexionDB.executeCount(sql);
		}
		return iRes;
	}

	@Override
	public List<Direccion> searchAddressesPorLocalidad(Localidad oLoc) {

		List<Direccion> lAddresses = new ArrayList<Direccion>();
		String sql = "SELECT * FROM direccion WHERE localidad=" + oLoc.getsCP();
		Statement stm = null;

		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCalle = rs.getString(1);
				byte bNum = (byte) rs.getInt(2);
				byte bBloque = (byte) rs.getInt(3);
				byte bPortal = (byte) rs.getInt(4);
				lAddresses.add(new Direccion(sCalle, bNum, bBloque, bPortal, oLoc));
			}
			stm.close();
		} catch (SQLException e) {
			lAddresses = null;
		}
		return lAddresses;
	}

	@Override
	public List<Direccion> searchAddressesPorProvincia(Provincia oProvincia) {

		List<Direccion> lAddresses = new ArrayList<Direccion>();
		String sql = "SELECT * ";
		sql += "FROM direccion, localidad, provincia ";
		sql += "WHERE direccion.localidad=localidad.codigoPostal AND localidad.provincia=provincia.nombre AND ";
		sql += "provincia.nombre LIKE \"" + oProvincia.getsNombreProv() + "\"";

		Statement stm = null;

		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCalle = rs.getString(1);
				byte bNum = (byte) rs.getInt(2);
				byte bBloque = (byte) rs.getInt(3);
				byte bPortal = (byte) rs.getInt(4);
				Localidad oLoc = new Localidad(rs.getString(5));
				lAddresses.add(new Direccion(sCalle, bNum, bBloque, bPortal, oLoc));
			}
			stm.close();
		} catch (SQLException e) {
			lAddresses = null;
		}
		return lAddresses;
	}
}
