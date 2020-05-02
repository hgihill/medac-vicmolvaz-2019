package controllers.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controllers.ConexionDB;
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
	public int ExisteUsuario(Usuario oUsuario) {
		String sql = "SELECT COUNT(*) FROM usuario WHERE DNI/CIF LIKE (\"" + oUsuario.getsDniCif() + "\")";
		return ConexionDB.executeCount(sql);
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
