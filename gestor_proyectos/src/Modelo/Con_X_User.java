package Modelo;

public class Con_X_User {
	private Conocimiento oCon;
	private Usuario oUs;

	public Con_X_User(Conocimiento oCon, Usuario oUs) {
		setoCon(oCon);
		setoUs(oUs);
	}

	public Conocimiento getoCon() {
		return oCon;
	}

	public void setoCon(Conocimiento oCon) {
		this.oCon = oCon;
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
		result = prime * result + ((oCon == null) ? 0 : oCon.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Con_X_User otro = (Con_X_User) obj;
		if (this != null && otro != null && this.getoUs() == (otro.getoUs()) && this.getoCon() == (otro.getoCon())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Usuario: " + getoUs() + ".\n";
		sResultado = "Conocimiento: " + getoCon() + ".";

		return sResultado;
	}

}
