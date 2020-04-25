package Modelo;

public class Aptitud {

	private String sNombreApt;

	public Aptitud(String sNombreApt) {
		setsNombreApt(sNombreApt);
	}

	public String getsNombreApt() {
		return sNombreApt;
	}

	public void setsNombreApt(String sNombreApt) {
		this.sNombreApt = sNombreApt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreApt == null) ? 0 : sNombreApt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Aptitud otro = (Aptitud) obj;
		if (this != null && otro != null && this.getsNombreApt() == (otro.getsNombreApt())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Aptitud requerida: " + getsNombreApt() + ".";

		return sResultado;
	}

}
