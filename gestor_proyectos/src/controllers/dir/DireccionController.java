package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
import controllers.GeneralController;
import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Provincia;

public class DireccionController implements IDireccionController {

	// #########
	// # CRUDS #
	// #########
	@Override
	public int add(Direccion oDireccion) {
		int iRes = 0;

		if (oDireccion.checkDireccion()) {

			String sql = "INSERT INTO direccion VALUES ('" + oDireccion.getsCalle() + "', " + oDireccion.getbNum()
					+ ", " + oDireccion.getbBloque() + ", " + oDireccion.getbPortal() + ", '"
					+ oDireccion.getoLoc().getsCP() + "')";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Direccion oDireccion) {
		int iRes = 0;

		String sql = "DELETE FROM direccion WHERE calle = '" + oDireccion.getsCalle() + "' AND numero ="
				+ oDireccion.getbNum() + "";
		iRes = ConexionDB.executeUpdate(sql);

		return iRes;
	}

	@Override
	public int update(Direccion oDireccion) {
		int iRes = 0;
		if (oDireccion.checkDireccion()) {
			String sql = "UPDATE direccion SET ";
			sql += "calle = \"" + oDireccion.getsCalle() + "\",";
			sql += "numero = " + oDireccion.getbNum() + ",";
			sql += "portal = " + oDireccion.getbPortal() + ",";
			sql += "bloque = " + oDireccion.getbBloque() + ",";
			sql += "cp = \"" + oDireccion.getoLoc().getsCP() + "\" WHERE ";
			sql += "calle = \"" + oDireccion.getsCalle() + "\" AND ";
			sql += "numero = " + oDireccion.getbNum() + "";
			iRes = ConexionDB.executeUpdate(sql);
		}

		return iRes;
	}

	@Override
	public String mostrarDireccion() {
		String sResultado = "Mostrando listado de direcciones:\n";
		String sql = " SELECT * FROM direccion";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCalle = rs.getString(1);
				int iNum = rs.getInt(2);
				int iPortal = rs.getInt(3);
				int iBloque = rs.getInt(4);
				sResultado += sCalle + " " + iNum + " " + iPortal + " " + iBloque + "\n";
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

	@Override
	public Direccion search(Direccion oObjeto, GeneralController c) {
		Direccion oDir = null;
		if (existeDireccion(oObjeto) > 0) {
			String sql = "SELECT * FROM direccion WHERE calle = \"" + oObjeto.getsCalle() + "\" ";
			sql += "AND numero=" + oObjeto.getbNum();
			Statement stm = null;
			try {
				stm = ConexionDB.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					String sCalle = rs.getString(1);
					byte bNum = rs.getByte(2);
					byte bPortal = rs.getByte(3);
					byte bBloque = rs.getByte(4);
					String sCp = rs.getString(5);
					Localidad oLoc = c.getDireccionCtrl().getLocalidadCtrl().searchLocalidadByPk(new Localidad(sCp), c);
					oDir = new Direccion(sCalle, bNum, bBloque, bPortal, oLoc);
				}
				stm.close();
			} catch (SQLException ex) {
				oDir = null;
			}
		}
		return oDir;
	}

	@Override
	public int existeDireccion(Direccion oDireccion) {
		int iRes = 0;

		String sql = "SELECT COUNT(*) FROM direccion WHERE calle=\"" + oDireccion.getsCalle() + "\" AND numero="
				+ oDireccion.getbNum();
		iRes = ConexionDB.executeCount(sql);

		return iRes;
	}
}
