package model.user;

import model.project.LimitsDB;

public class Tipo_Usuario implements ITipo_Usuario, LimitsDB {
	private byte bTipoUsuario;

	public Tipo_Usuario(byte bTipoUsuario) {
		setbTipoUsuario(bTipoUsuario);
	}

	@Override
	public byte getbTipoUsuario() {
		return bTipoUsuario;
	}

	private boolean setbTipoUsuario(byte bTipoUsuario) {
		boolean bExito = false;
		if (bTipoUsuario > 0 && bTipoUsuario <= LIMITBYTE) {
			this.bTipoUsuario = bTipoUsuario;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkTipoUsuario() {
		boolean bExito = false;
		if (this.bTipoUsuario != 0) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bTipoUsuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bExito = false;
		Tipo_Usuario otro = (Tipo_Usuario) obj;
		if (checkTipoUsuario() && otro.checkTipoUsuario() && this.bTipoUsuario == (otro.bTipoUsuario)) {
			bExito = true;
		}
		return bExito;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Tipo de usuario: " + getbTipoUsuario() + "\n";

		return sResultado;
	}
}
