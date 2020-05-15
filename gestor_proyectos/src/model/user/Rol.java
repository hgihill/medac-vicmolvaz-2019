package model.user;

import limites.LimitsDB;
import model.project.Proyecto;

public class Rol implements IRol, LimitsDB {
	private byte bRol;
	private Usuario oUs;
	private Proyecto oProyecto;

	public Rol(byte bRol, Usuario oUs, Proyecto oProyecto) {
		setbRol(bRol);
		this.oUs = oUs;
		this.oProyecto = oProyecto;
	}

	public Rol(Usuario oUs, Proyecto oProyecto) {
		this.oUs = oUs;
		this.oProyecto = oProyecto;
	}

	@Override
	public byte getbRol() {
		return bRol;
	}

	@Override
	public boolean setbRol(byte bRol) {
		boolean bExito = false;
		if (bRol >= MINROL && bRol <= MAXROL) {
			this.bRol = bRol;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Usuario getoUs() {
		return oUs;
	}

	@Override
	public Proyecto getoProyecto() {
		return oProyecto;
	}

	@Override
	public boolean checkRol() {
		boolean bExito = false;
		if (oUs.checkUsuario() && oProyecto.checkProyecto()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bRol;
		result = prime * result + ((oProyecto == null) ? 0 : oProyecto.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Rol otro = (Rol) obj;
		if (checkRol() && otro.checkRol() && this.oUs == (otro.oUs) && this.oProyecto == (otro.oProyecto)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";
		sResultado += "Rol: " + getbRol() + "\n";
		sResultado += "Usuario: " + getoUs() + "\n";
		sResultado += "Proyecto asociado: " + getoProyecto();
		return sResultado;
	}
}
