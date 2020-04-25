package Modelo;

public class Apt_X_User {
	private Aptitud oApt;
	private Usuario oUs;

	public Apt_X_User(Aptitud oApt, Usuario oUs) {
		setoApt(oApt);
		setoUs(oUs);
	}

	public Aptitud getoApt() {
		return oApt;
	}

	public void setoApt(Aptitud oApt) {
		this.oApt = oApt;
	}

	public Usuario getoUs() {
		return oUs;
	}

	public void setoUs(Usuario oUs) {
		this.oUs = oUs;
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
		if (this != null && otro != null && this.getoUs() == (otro.getoUs()) && this.getoApt() == (otro.getoApt())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Usuario: " + getoUs() + ".\n";
		sResultado = "Aptitud: " + getoApt() + ".";

		return sResultado;
	}

}
