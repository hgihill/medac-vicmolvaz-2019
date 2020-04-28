package controllers.dir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public class LocalidadController implements ILocalidadController {

	// #########
	// # CRUDS #
	// #########
	@Override
	public int add(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl) {
		int iRes = 0;
		if (oLocalidad.checkLocalidad()) {

			// 1) Anadir la provincia
			provinciaCtrl.add(oLocalidad.getoProv(), paisCtrl);

			// 2) Anado la localidad
			String sql = "INSERT INTO localidad VALUES (" + oLocalidad.getsCP() + ",\"" + oLocalidad.getsNombreLoc()
					+ "\",\"" + oLocalidad.getoProv().getsNombreProv() + "\")";
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int update(Localidad oLocalidad, ProvinciaController provinciaCtrl, PaisController paisCtrl) {
		int iRes = 0;
		if (oLocalidad.checkLocalidad()) {

			// 1) Anadir la provincia y pais si no existen
			provinciaCtrl.add(oLocalidad.getoProv(), paisCtrl);

			// 2) Modifico la localidad
			String sql = "UPDATE localidad ";
			sql += "SET nombre = \"" + oLocalidad.getsNombreLoc() + "\", ";
			sql += "provincia = \"" + oLocalidad.getoProv().getsNombreProv() + "\" ";
			sql += "WHERE codigoPostal=" + oLocalidad.getsCP();
			iRes = ConexionDB.executeUpdate(sql);
		}
		return iRes;
	}

	@Override
	public int remove(Localidad oLocalidad) {
		int iRes = 0;
		String sql = "DELETE FROM localidad WHERE codigoPostal=" + oLocalidad.getsCP();
		System.out.println(sql);
		iRes = ConexionDB.executeUpdate(sql);
		return iRes;
	}

	// ##########
	// # QUERYS #
	// ##########
	@Override
	public int existeLocalidad(Localidad oLocalidad) {
		int iRes = 0;
		if (oLocalidad.checkLocalidad()) {
			String sql = "SELECT COUNT(*) FROM localidad WHERE codigoPostal=" + oLocalidad.getsCP();
			iRes = ConexionDB.executeCount(sql);
		}
		return iRes;
	}

	@Override
	public List<Localidad> searchLocalidadesPorProvincia(Provincia oProvincia) {
		List<Localidad> lLocalidades = new ArrayList<Localidad>();

		String sql = "SELECT * FROM localidad WHERE provincia LIKE \"" + oProvincia.getsNombreProv() + "\"";
		Statement stm = null;

		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				lLocalidades.add(new Localidad(Integer.toString(rs.getInt(1)), rs.getString(2), oProvincia));
			}
			stm.close();
		} catch (SQLException e) {
			lLocalidades = null;
		}
		return lLocalidades;
	}

	@Override
	public List<Localidad> searchLocalidadesPorPais(Pais oPais) {
		List<Localidad> lLocalidades = new ArrayList<Localidad>();

		String sql = "SELECT * FROM localidad, provincia, pais WHERE localidad.provincia=provincia.nombre AND provincia.nombre_pais=pais.nombre AND pais.nombre LIKE \""
				+ oPais.getsNombrePais() + "\"";
		Statement stm = null;

		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				lLocalidades.add(new Localidad(Integer.toString(rs.getInt(1)), rs.getString(2),
						new Provincia(rs.getString(3), oPais)));
			}
			stm.close();
		} catch (SQLException e) {
			lLocalidades = null;
		}
		return lLocalidades;
	}

	@Override
	public Localidad searchLocalidadByPk(String sCodigoPostal) {
		Localidad oLocalidad = null;

		String sql = "SELECT localidad.codigoPostal, localidad.nombre, provincia.nombre, pais.nombre ";
		sql += "FROM localidad, provincia, pais ";
		sql += "WHERE localidad.provincia=provincia.nombre AND provincia.nombre_pais=pais.nombre AND localidad.codigoPostal="
				+ sCodigoPostal;

		Statement stm = null;

		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				String sLocCodigoPostal = Integer.toString(rs.getInt(1));
				String sLocNombre = rs.getString(2);
				Provincia oLocProvincia = new Provincia(rs.getString(3), new Pais(rs.getString(4)));
				oLocalidad = new Localidad(sLocCodigoPostal, sLocNombre, oLocProvincia);
			}
			stm.close();
		} catch (SQLException e) {
			oLocalidad = null;
		}
		return oLocalidad;
	}
}
