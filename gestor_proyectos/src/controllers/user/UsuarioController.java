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

public class UsuarioController implements IUsuarioController{
	
	@Override
	public int add(Usuario oUsuario) {
		String sql = "INSERT INTO usuario VALUES (\"" + oUsuario.getsDniCif() + "\", " 
				+ "\"" + oUsuario.getoTipoUs() + "\"," 
				+ "\"" + oUsuario.getoDir() + "\",  " 
				+ "\"" + oUsuario.getsNombre() + "\", " 
				+ "\"" + oUsuario.getsContrasena() + "\", " 
				+ "\"" + oUsuario.getsMail() + "\"," 
				+ "\"" + oUsuario.getsTelefono() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Usuario oUsuario) {
		String sql = "DELETE FROM usuario WHERE dni_cif = (\"" + oUsuario.getsDniCif() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Usuario oUsuario) {
		String sql = "UPDATE usuario SET (\"" + oUsuario.getoTipoUs() + "\"," 
				+ "\"" + oUsuario.getoDir() + "\",  "
				+ "\"" + oUsuario.getsNombre() + "\", " 
				+ "\"" + oUsuario.getsContrasena() + "\", " 
				+ "\"" + oUsuario.getsMail() + "\"," 
				+ "\"" + oUsuario.getsTelefono() + "\" WHERE DNI LIKE \""+oUsuario.getsDniCif()+"\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int existeUsuario(Usuario oUsuario) {
		String sql = "SELECT COUNT(*) FROM usuario WHERE DNI/CIF LIKE (\"" + oUsuario.getsDniCif() + "\")";
		return ConexionDB.executeCount(sql);
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
			TipoUsuario oTUs = new TipoUsuario (bTipo);
			Direccion oDir = new Direccion (sCalle, bNum);
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
}
