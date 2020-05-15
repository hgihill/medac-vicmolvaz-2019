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
	public int add(Localidad oLocalidad) {
		int iRes = 0;
		if (oLocalidad.checkLocalidad() && existeLocalidad(oLocalidad) == 0) {

			String sql = "INSERT INTO localidad VALUES ('" + oLocalidad.getsCP() + "', '" + oLocalidad.getsNombreLoc()
					+ "', '" + oLocalidad.getoProv().getsNombreProv() + "')";
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
	public int update(Localidad oLocalidad) {
		int iRes = 0;

		if (oLocalidad.checkLocalidad()) {
			String sql = "UPDATE localidad ";
			sql += "SET cp = \"" + oLocalidad.getsCP() + "\", ";
			sql += "nombre_loc = \"" + oLocalidad.getsNombreLoc() + "\", ";
			sql += "nombre_prov = \"" + oLocalidad.getoProv().getsNombreProv() + "\" ";
			sql += "WHERE cp = \"" + oLocalidad.getsCP() + "\"";
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
		String sql = "SELECT COUNT(*) FROM localidad WHERE cp =\"" + oLocalidad.getsCP() + "\"";
		iRes = ConexionDB.executeCount(sql);
		System.out.println(sql);
		System.out.println(iRes);
		return iRes;
	}

	@Override
	public Localidad searchLocalidadByPk(Localidad oLoc, GeneralController c) {
		Localidad oLocalidad = null;
		String sql = "SELECT * FROM localidad WHERE cp = \"" + oLoc.getsCP() + "\"";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				String sLocCodigoPostal = rs.getString(1);
				String sLocNombre = rs.getString(2);
				String sNombreProv = rs.getString(3);
				Provincia oProv = c.getDireccionCtrl().getProvinciaCtrl().searchProvincia(new Provincia(sNombreProv),
						c);

				oLocalidad = new Localidad(sLocCodigoPostal, sLocNombre, oProv);
			}
			stm.close();
		} catch (SQLException e) {
			oLocalidad = null;
		}

		return oLocalidad;
	}
}
