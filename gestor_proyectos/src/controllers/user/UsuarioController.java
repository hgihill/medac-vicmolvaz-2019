package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
import model.dir.Direccion;
import model.user.TipoUsuario;
import model.user.Usuario;

public class UsuarioController implements IUsuarioController {

	@Override
	public int add(Usuario oUsuario) {
		String sql = "INSERT INTO usuario VALUES ('" + oUsuario.getsDniCif() + "', "
				+ oUsuario.getoTipoUs().getbTipoUsuario() + ", '" + oUsuario.getoDir().getsCalle() + "', "
				+ oUsuario.getoDir().getbNum() + ", '" + oUsuario.getsNombre() + "', '" + oUsuario.getsContrasena()
				+ "', '" + oUsuario.getsMail() + "', '" + oUsuario.getsTelefono() + "')";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Usuario oUsuario) {
		String sql = "DELETE FROM usuario WHERE dni_cif = '" + oUsuario.getsDniCif() + "'";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Usuario oUsuario) {
		String sql = "UPDATE usuario SET nombre_us = '" + oUsuario.getsNombre() + "', contraseña = '"
				+ oUsuario.getsContrasena() + "', mail = '" + oUsuario.getsMail() + "', telefono = '"
				+ oUsuario.getsTelefono() + "' WHERE dni_cif = '" + oUsuario.getsDniCif() + "'";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public String mostrarUsuario() {
		String sResultado = "Mostrando listado de usuarios:\n";
		String sql = " SELECT * FROM usuario";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sDniCif = rs.getString(1);
				byte bTipoUs = rs.getByte(2);
				String scalle = rs.getString(3);
				int iNum = rs.getInt(4);
				String sNombre = rs.getString(5);
				String sPassword = rs.getString(6);
				String sMail = rs.getString(7);
				String sTlfn = rs.getString(8);
				sResultado += sDniCif + " " + bTipoUs + " " + scalle + " " + iNum + " " + sNombre + " " + sPassword
						+ " " + sMail + " " + sTlfn + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public Usuario search(Usuario oUs) {
		Usuario lUs = null;
		String sql = "SELECT * FROM usuario where dni_cif = \"" + oUs.getsDniCif() + "\"";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sDniCif = rs.getString(1);
				byte bTipoUs = rs.getByte(2);
				String sCalle = rs.getString(3);
				int iNum = rs.getInt(4);
				String sNombre = rs.getString(5);
				String sContrasena = rs.getString(6);
				String sMail = rs.getString(7);
				String sTelefono = rs.getString(8);
				Direccion oDir = new Direccion(sCalle, (byte) iNum);
				TipoUsuario oTipoUs = new TipoUsuario(bTipoUs);
				lUs = new Usuario(sDniCif, sNombre, sMail, sTelefono, sContrasena, oTipoUs, oDir);
			}
		} catch (SQLException ex) {
			lUs = null;
		}
		return lUs;
	}

	@Override
	public List<Usuario> searchUsuario(Usuario oUsuario) {
		List<Usuario> lUsuario = new ArrayList<Usuario>();
		String sql = " SELECT * FROM usuario WHERE DNI = (\"" + oUsuario.getsDniCif() + "\")";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				byte bTipo = rs.getByte(1);
				String sCalle = rs.getString(2);
				byte bNum = rs.getByte(3);
				String sNombre = rs.getString(4);
				String sContrasena = rs.getString(5);
				String sMail = rs.getString(6);
				String sTelefono = rs.getString(7);
				TipoUsuario oTUs = new TipoUsuario(bTipo);
				Direccion oDir = new Direccion(sCalle, bNum);
				lUsuario.add(new Usuario(sNombre, sMail, sTelefono, sContrasena, oTUs, oDir));
			}
			stm.close();
		} catch (SQLException ex) {
			lUsuario = null;
		}
		return lUsuario;
	}

	@Override
	public List<Usuario> ususarioPorDireccion(Usuario oUsuario) {
		List<Usuario> lUsuario = new ArrayList<Usuario>();
		String sql = " SELECT nombre_us, mail FROM usuario WHERE DNI = (\"" + oUsuario.getsDniCif() + "\")";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sNombre = rs.getString(1);
				String sMail = rs.getString(2);
				lUsuario.add(new Usuario(sNombre, sMail));
			}
			stm.close();
		} catch (SQLException ex) {
			lUsuario = null;
		}
		return lUsuario;
	}

	@Override
	public int existeUsuario(Usuario oUsuario) {
		String sql = "SELECT COUNT(*) FROM usuario WHERE dni_cif = ('" + oUsuario.getsDniCif() + "')";
		return ConexionDB.executeCount(sql);
	}
}
