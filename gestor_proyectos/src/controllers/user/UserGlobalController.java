package controllers.user;

import model.user.Apt_X_User;
import model.user.Aptitud;
import model.user.Con_X_User;
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
	private Apt_X_User_Controller aptXUsCtrl;
	private Con_X_User_Controller conXUsCtrl;

	public UserGlobalController() {
		aptCtrl = new AptitudController();
		conCtrl = new ConocimientoController();
		rolCtrl = new RolController();
		usCtrl = new UsuarioController();
		tipoUsCtrl = new TipoUsuarioController();
		aptXUsCtrl = new Apt_X_User_Controller();
		conXUsCtrl = new Con_X_User_Controller();
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
	
	public Apt_X_User_Controller getAptXUsCtrl() {
		return aptXUsCtrl;
	}

	public Con_X_User_Controller getConXUsCtrl() {
		return conXUsCtrl;
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
	
	// Aptitud x Usuario
	public int addApt_X_User(Apt_X_User oAptXUs) {
		return aptXUsCtrl.add(oAptXUs);
	}

	public int removeApt_X_User(Apt_X_User oAptXUs) {
		return aptXUsCtrl.remove(oAptXUs);
	}

	public String mostrarAptitudDeUsuario() {
		return aptXUsCtrl.mostrarAptitudDeUsuario();
	}

	public int existeAptitudDeUsuario(Apt_X_User oAptXUs) {
		return aptXUsCtrl.ExisteAptitudDeUsuario(oAptXUs);
	}
	
	// Concocimiento x Usuario
	public int addCon_X_User(Con_X_User oConXUs) {
		return conXUsCtrl.add(oConXUs);
	}

	public int removeCon_X_User(Con_X_User oConXUs) {
		return conXUsCtrl.remove(oConXUs);
	}

	public String mostrarConcocimientoDeUsuario() {
		return conXUsCtrl.mostrarConocimientoDeUsuario();
	}

	public int existeConocimientoDeUsuario(Con_X_User oConXUs) {
		return conXUsCtrl.existeConocimientoDeUsuario(oConXUs);
	}
}


