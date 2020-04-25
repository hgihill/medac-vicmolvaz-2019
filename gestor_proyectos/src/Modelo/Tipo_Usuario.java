package Modelo;

public class Tipo_Usuario {
	private boolean bTipoUsuario;

	public Tipo_Usuario(boolean tipoUsuario) {
		setTipoUsuario(tipoUsuario);
	}

	public boolean isTipoUsuario() {
		return bTipoUsuario;
	}

	public void setTipoUsuario(boolean tipoUsuario) {
		this.bTipoUsuario = tipoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bTipoUsuario ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Tipo_Usuario otro = (Tipo_Usuario) obj;
		if (this != null && otro != null && this.isTipoUsuario() == (otro.isTipoUsuario())) {
			bIgual = true;
		}
		return bIgual;
	}
	
	public String toString() {
		String sResultado = "";

		sResultado = "Tipo de usuario: " + isTipoUsuario() + ".";

		return sResultado;
	}
}
