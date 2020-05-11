package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.ConexionDB;
import controllers.GeneralController;
import model.dir.Localidad;
import model.dir.Provincia;

public class LocalidadController implements ILocalidadController {

	// #########
	// # CRUDS #
	// #########
	@Override
	public int add(Localidad oLocalidad, ProvinciaController oProvinciaCtrl, PaisController paisCtrl) {
		int iRes = 0;
		if (oLocalidad.checkLocalidad()) {

			// 1) Anadir la provincia
			oProvinciaCtrl.add(oLocalidad.getoProv(), paisCtrl);

			// 2) Anado la localidad
			String sql = "INSERT INTO localidad VALUES (" + oLocalidad.getsCP() + ",\"" + oLocalidad.getsNombreLoc()
					+ "\",\"" + oLocalidad.getoProv().getsNombreProv() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Localidad oLocalidad) {
		int iRes = 0;
		String sql = "DELETE FROM localidad WHERE cp LIKE \"" + oLocalidad.getsCP() + "\"";
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	@Override
	public Localidad searchLocalidadByPk(Localidad oLoc, GeneralController c) {
		Localidad oLocalidad = null;
		if (existeLocalidad(oLoc) > 0) {
			String sql = "SELECT localidad.cp, localidad.nombre_loc, localidad.nombre_prov FROM localidad, provincia, pais  WHERE localidad.nombre_prov=provincia.nombre_prov AND provincia.nombre_pais=pais.nombre_pais AND localidad.cp='"
					+ oLoc.getsCP() + "'";

			Statement stm = null;
			try {
				stm = ConexionDB.getConnection().createStatement();
				ResultSet rs = stm.executeQuery(sql);

				while (rs.next()) {
					String sLocCodigoPostal = rs.getString(1);
					String sLocNombre = rs.getString(2);
					String sNombreProv = rs.getString(3);
					System.out.println(sNombreProv);
					Provincia oProv = c.getDireccionCtrl().getProvinciaCtrl()
							.searchProvincia(new Provincia(sNombreProv), c);
					System.out.println(oProv);
					oLocalidad = new Localidad(sLocCodigoPostal, sLocNombre, oProv);

				}
				stm.close();
			} catch (SQLException e) {
				oLocalidad = null;
			}
		}
		return oLocalidad;
	}

	@Override
	public int update(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl) {
		int iRes = 0;

		provinciaCtrl.add(oLocalidad.getoProv(), paisCtrl);

		if (oLocalidad.checkLocalidad()) {
			String sql = "UPDATE localidad ";
			sql += "SET nombre_loc = \"" + oLocalidad.getsNombreLoc() + "\", ";
			sql += "provincia = \"" + oLocalidad.getoProv().getsNombreProv() + "\" ";
			sql += "WHERE cp = \"" + oLocalidad.getsCP();
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public String mostrarLocalidad() {
		String sResultado = "Mostrando listado de localidades:\n";
		String sql = " SELECT * FROM localidad";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCp = rs.getString(1);
				String sNombre = rs.getString(2);
				sResultado += sCp + " " + sNombre + "\n";
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
	public int existeLocalidad(Localidad oLocalidad) {
		int iRes = 0;
		String sql = "SELECT COUNT(*) FROM localidad WHERE cp =" + oLocalidad.getsCP();
		iRes = ConexionDB.executeCount(sql);
		return iRes;
	}

//	@Override
//	public List<Localidad> searchLocalidad(Provincia oProvincia) {
//		List<Localidad> lLocalidad = new ArrayList<Localidad>();
//
//		String sql = "SELECT * FROM localidad WHERE cp = (\"" + oProvincia.getsNombreProv() + "\")";
//		Statement stm = null;
//
//		try {
//			stm = ConexionDB.getConnection().createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				String sCp = Integer.toString(rs.getInt(1));
//				String sNombre = rs.getString(2);
//				Provincia oLocProvincia = new Provincia(rs.getString(3));
//				lLocalidad.add(new Localidad(sCp, sNombre, oLocProvincia));
//			}
//			stm.close();
//		} catch (SQLException e) {
//			lLocalidad = null;
//		}
//		return lLocalidad;

}
