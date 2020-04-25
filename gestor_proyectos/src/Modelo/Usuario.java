package Modelo;

import Modelo.Dir.Direccion;

public class Usuario {
	private String sDniCif, sNombre, sMail, sTelefono, sContrasena;
	private Tipo_Usuario oTipoUs;
	private Direccion oDir;

	public Usuario(String sDniCif, String sNombre, String smail, String sTelefono, String sContrasena, Tipo_Usuario oTipoUs,
			Direccion oDir) {
		setsDniCif(sDniCif);
		setsNombre(sNombre);
		setsMail(smail);
		setsTelefono(sTelefono);
		setsContrasena(sContrasena);
		setoTipoUs(oTipoUs);
		setoDir(oDir);
	}

	public Usuario(String sDniCif) {
		setsDniCif(sDniCif);
	}

	public String getsDniCif() {
		return sDniCif;
	}

	public void setsDniCif(String sDniCif) {
		this.sDniCif = sDniCif;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getsMail() {
		return sMail;
	}

	public void setsMail(String smail) {
		this.sMail = smail;
	}

	public String getsTelefono() {
		return sTelefono;
	}

	public void setsTelefono(String sTelefono) {
		this.sTelefono = sTelefono;
	}

	public String getsContrasena() {
		return sContrasena;
	}

	public void setsContrasena(String sContrasena) {
		this.sContrasena = sContrasena;
	}

	public Tipo_Usuario getoTipoUs() {
		return oTipoUs;
	}

	public void setoTipoUs(Tipo_Usuario oTipoUs) {
		this.oTipoUs = oTipoUs;
	}

	public Direccion getoDir() {
		return oDir;
	}

	public void setoDir(Direccion oDir) {
		this.oDir = oDir;
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
		if (this != null && otro != null && this.getsDniCif() == (otro.getsDniCif())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Tipo de usuario: " + getoTipoUs() + ".";
		sResultado = "DNI/CIF: " + getsDniCif() + ".";
		sResultado = "Nombre: " + getsNombre() + ".";
		sResultado = "Mail: " + getsMail() + ".";
		sResultado = "Teléfono: " + getsTelefono() + ".";
		sResultado = "Contraseña: " + getsContrasena() + ".";
		sResultado = "Dirección: " + getoDir() + ".";

		return sResultado;
	}

}
