package model.user;

import limites.LimitsDB;
import model.dir.Direccion;

public class Usuario implements IUsuario, LimitsDB {
	private String sDniCif, sNombre, sMail, sTelefono, sContrasena;
	private TipoUsuario oTipoUs;
	private Direccion oDir;

	public Usuario(String sDniCif, String sNombre, String sMail, String sTelefono, String sContrasena,
			TipoUsuario oTipoUs, Direccion oDir) {
		setsDniCif(sDniCif);
		setsNombre(sNombre);
		setsMail(sMail);
		setsTelefono(sTelefono);
		setsContrasena(sContrasena);
		this.oTipoUs = oTipoUs;
		this.oDir = oDir;
	}
	
	public Usuario(String sDniCif, String sNombre, String sMail, String sContrasena,
			TipoUsuario oTipoUs, Direccion oDir) {
		setsDniCif(sDniCif);
		setsNombre(sNombre);
		setsMail(sMail);
		setsContrasena(sContrasena);
		this.oTipoUs = oTipoUs;
		this.oDir = oDir;
	}

	public Usuario(String sDniCif) {
		setsDniCif(sDniCif);
	}
	
	public Usuario(String sNombre, String sMail) {
		setsNombre(sNombre);
		setsMail(sMail);
	}
		

	@Override
	public String getsDniCif() {
		return sDniCif;
	}

	private boolean setsDniCif(String sDniCif) {
		boolean bExito = false;
		if (sDniCif != null && sDniCif.length() == LIMITDNI) {
			this.sDniCif = sDniCif;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsNombre() {
		return sNombre;
	}

	@Override
	public boolean setsNombre(String sNombre) {
		boolean bExito = false;
		if (sNombre != null && sNombre.length() > MINGENERICO && sNombre.length() <= LIMITGENERICO) {
			this.sNombre = sNombre;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsMail() {
		return sMail;
	}

	@Override
	public boolean setsMail(String sMail) {
		boolean bExito = false;
		if (sMail != null && sMail.length() > MINGENERICO && sMail.length() < LIMITGENERICO) {
			this.sMail = sMail;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsTelefono() {
		return sTelefono;
	}

	@Override
	public boolean setsTelefono(String sTelefono) {
		boolean bExito = false;
		if (sTelefono != null && sTelefono.length() == LIMITPHONE) {
			this.sTelefono = sTelefono;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsContrasena() {
		return sContrasena;
	}

	@Override
	public boolean setsContrasena(String sContrasena) {
		boolean bExito = false;
		if (sContrasena != null && sContrasena.length() >= MINCHARPASSWORD && sContrasena.length() <= MAXCHARPASSWORD) {
			this.sContrasena = sContrasena;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public TipoUsuario getoTipoUs() {
		return oTipoUs;
	}

	@Override
	public Direccion getoDir() {
		return oDir;
	}

	@Override
	public boolean checkUsuario() {
		boolean bExito = false;
		if (sDniCif != null && oTipoUs.checkTipoUsuario() && oDir.checkDireccion()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oDir == null) ? 0 : oDir.hashCode());
		result = prime * result + ((oTipoUs == null) ? 0 : oTipoUs.hashCode());
		result = prime * result + ((sContrasena == null) ? 0 : sContrasena.hashCode());
		result = prime * result + ((sDniCif == null) ? 0 : sDniCif.hashCode());
		result = prime * result + ((sMail == null) ? 0 : sMail.hashCode());
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		result = prime * result + ((sTelefono == null) ? 0 : sTelefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Usuario otro = (Usuario) obj;
		if (checkUsuario() && otro.checkUsuario() && this.sDniCif == (otro.sDniCif)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Tipo de usuario: " + getoTipoUs() + "\n";
		sResultado += "DNI/CIF: " + getsDniCif() + "\n";
		sResultado += "Nombre: " + getsNombre() + "\n";
		sResultado += "Mail: " + getsMail() + "\n";
		if(getsTelefono() != null) {
			sResultado += "Teléfono: " + getsTelefono() + "\n";
		}
		sResultado += "Contraseña: " + getsContrasena() + "\n";
		sResultado += "Dirección: " + getoDir();

		return sResultado;
	}

}
