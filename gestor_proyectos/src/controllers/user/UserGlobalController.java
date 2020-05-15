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
	private UsuarioController usCtrl;
	private TipoUsuarioController tipoUsCtrl;

	public UserGlobalController() {
		aptCtrl = new AptitudController();
		conCtrl = new ConocimientoController();
		rolCtrl = new RolController();
		usCtrl = new UsuarioController();
		tipoUsCtrl = new TipoUsuarioController();
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

	public UsuarioController getUsCtrl() {
		return usCtrl;
	}
	
	public TipoUsuarioController getTipoUsCtrl() {
		return tipoUsCtrl;
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
	
	// Tipo Usuario
	public TipoUsuario search(TipoUsuario oTipo) {
		return tipoUsCtrl.search(oTipo);
	}

	public int existeTipoUsuario(TipoUsuario oTipo) {
		return tipoUsCtrl.existeTipoU(oTipo);
	}
}
