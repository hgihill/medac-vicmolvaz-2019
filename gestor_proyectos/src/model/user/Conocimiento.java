package model.user;

import model.project.LimitsDB;

public class Conocimiento implements IConocimiento, LimitsDB {

	private String sNombreCon;

	public Conocimiento(String sNombreCon) {
		setsNombreCon(sNombreCon);
	}

	@Override
	public String getsNombreCon() {
		return sNombreCon;
	}

	private boolean setsNombreCon(String sNombreCon) {
		boolean bExito = false;
		if (sNombreCon != null && sNombreCon.length() > 0 && sNombreCon.length() < MAXCHARACTERS) {
			this.sNombreCon = sNombreCon;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkConocimiento() {
		boolean bExito = false;
		if (sNombreCon != null) {
			bExito = true;
		}
		return bExito;
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
		if (checkConocimiento() && otro.checkConocimiento() && this.sNombreCon == (otro.sNombreCon)) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Conocimiento requerido: " + getsNombreCon() + "\n";

		return sResultado;
	}

}
