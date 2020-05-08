package controllers.user;

import model.user.Aptitud;
import model.user.Conocimiento;
import model.user.Rol;
import model.user.TipoUsuario;
import model.user.Usuario;

public class UserGlobalController {
	private AptitudController aptCtrl;
	private ConocimientoController conCtrl;
	private RolController rolCtrl;
	private TipoUsuarioController tUsCtrl;
	private UsuarioController usCtrl;

	public UserGlobalController() {
		aptCtrl = new AptitudController();
		conCtrl = new ConocimientoController();
		rolCtrl = new RolController();
		tUsCtrl = new TipoUsuarioController();
		usCtrl = new UsuarioController();
	}

	public AptitudController getAptCtrl() {
		return aptCtrl;
	}

	public ConocimientoController getConCtrl() {
		return conCtrl;
	}

	public RolController getRolCtrl() {
		return rolCtrl;
	}

	public TipoUsuarioController gettUsCtrl() {
		return tUsCtrl;
	}

	public UsuarioController getUsCtrl() {
		return usCtrl;
	}

	// Aptitud
	public int addAptitud(Aptitud oAptitud) {
		return aptCtrl.add(oAptitud);
	}

	public int removeAptitud(Aptitud oAptitud) {
		return aptCtrl.remove(oAptitud);
	}

	public int existeAptitud(Aptitud oAptitud) {
		return aptCtrl.ExisteAptitud(oAptitud);
	}

	// Conocimiento
	public int addConocimiento(Conocimiento oConocimiento) {
		return conCtrl.add(oConocimiento);
	}

	public int removeConocimiento(Conocimiento oConocimiento) {
		return conCtrl.remove(oConocimiento);
	}

	public int existeConocimiento(Conocimiento oConocimiento) {
		return conCtrl.existeConocimiento(oConocimiento);
	}

	// Rol
	public int addRol(Rol oRol) {
		return rolCtrl.add(oRol);
	}

	public int removeRol(Rol oRol) {
		return rolCtrl.remove(oRol);
	}

	public int updateRol(Rol oRol) {
		return rolCtrl.update(oRol);
	}

	public int existeRol(Rol oRol) {
		return rolCtrl.existeRol(oRol);
	}

	// Tipo Usuario
	public int addTipoUsuario(TipoUsuario oTipoUsuario) {
		return tUsCtrl.add(oTipoUsuario);
	}

	public int removeTipoUsuario(TipoUsuario oTipoUsuario) {
		return tUsCtrl.remove(oTipoUsuario);
	}

	public int existeTipoUsuario(TipoUsuario oTipoUsuario) {
		return tUsCtrl.existeTipoUsuario(oTipoUsuario);
	}

	// Usuario
	public int addUsuario(Usuario oUsuario) {
		return usCtrl.add(oUsuario);
	}

	public int removeUsuario(Usuario oUsuario) {
		return usCtrl.remove(oUsuario);
	}

	public int updateUsuario(Usuario oUsuario) {
		return usCtrl.update(oUsuario);
	}

	public int existeUsuario(Usuario oUsuario) {
		return usCtrl.existeUsuario(oUsuario);
	}
}
