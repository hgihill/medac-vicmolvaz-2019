package model.user;

import limites.LimitsDB;

public class Conocimiento implements IConocimiento, LimitsDB {

	private String sNombre;

	public Conocimiento(String sNombreCon) {
		setsNombre(sNombre);
	}

	@Override
	public String getsNombre() {
		return sNombre;
	}

	private boolean setsNombre(String sNombreCon) {
		boolean bExito = false;
		if (sNombreCon != null && sNombreCon.length() > 0 && sNombreCon.length() < LIMITGENERICO) {
			this.sNombre = sNombreCon;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkConocimiento() {
		boolean bExito = false;
		if (sNombre != null) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Conocimiento otro = (Conocimiento) obj;
		if (checkConocimiento() && otro.checkConocimiento() && this.sNombre == (otro.sNombre)) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Conocimiento requerido: " + getsNombre() + "\n";

		return sResultado;
	}

}
