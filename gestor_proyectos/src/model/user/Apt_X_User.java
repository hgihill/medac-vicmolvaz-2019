package model.user;

public class Apt_X_User implements IApt_X_User {
	private Aptitud oApt;
	private Usuario oUs;

	public Apt_X_User(Aptitud oApt, Usuario oUs) {
		this.oApt = oApt;
		this.oUs = oUs;
	}

	@Override
	public Aptitud getoApt() {
		return oApt;
	}

	@Override
	public Usuario getoUs() {
		return oUs;
	}

	@Override
	public boolean checkAptXUser() {
		boolean bExito = false;
		if (oUs.checkUsuario() && oApt.checkAptitud()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oApt == null) ? 0 : oApt.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Apt_X_User otro = (Apt_X_User) obj;
		if (checkAptXUser() && otro.checkAptXUser() && this.oUs == (otro.oUs) && this.oApt == (otro.oApt)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Usuario: " + getoUs() + "\n";
		sResultado += "Aptitud: " + getoApt();

		return sResultado;
	}

}
