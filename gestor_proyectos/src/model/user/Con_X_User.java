package model.user;

public class Con_X_User implements ICon_X_User {
	private Conocimiento oCon;
	private Usuario oUs;

	public Con_X_User(Conocimiento oCon, Usuario oUs) {
		this.oCon = oCon;
		this.oUs = oUs;
	}

	@Override
	public Conocimiento getoCon() {
		return oCon;
	}

	@Override
	public Usuario getoUs() {
		return oUs;
	}

	@Override
	public boolean checkConXUser() {
		boolean bExito = false;
		if (oUs.checkUsuario() && oCon.checkConocimiento()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oCon == null) ? 0 : oCon.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Con_X_User otro = (Con_X_User) obj;
		if (checkConXUser() && otro.checkConXUser() && this.oUs == (otro.oUs) && this.oCon == (otro.oCon)) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Usuario: " + getoUs() + "\n";
		sResultado += "Conocimiento: " + getoCon();

		return sResultado;
	}

}
