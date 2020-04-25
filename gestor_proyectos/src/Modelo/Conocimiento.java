package Modelo;

public class Conocimiento {

	private String sNombreCon;

	public Conocimiento(String sNombreCon) {
		setsNombreCon(sNombreCon);
	}

	public String getsNombreCon() {
		return sNombreCon;
	}

	public void setsNombreCon(String sNombreCon) {
		this.sNombreCon = sNombreCon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreCon == null) ? 0 : sNombreCon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Conocimiento otro = (Conocimiento) obj;
		if (this != null && otro != null && this.getsNombreCon() == (otro.getsNombreCon())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Conocimiento requerido: " + getsNombreCon() + ".";

		return sResultado;
	}

}
