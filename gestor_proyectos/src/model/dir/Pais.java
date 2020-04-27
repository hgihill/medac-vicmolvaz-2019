package model.dir;

import model.project.LimitsDB;

public class Pais implements IPais, LimitsDB {

	private String sNombrePais;

	public Pais(String sNombrePais) {
		setsNombrePais(sNombrePais);
	}

	@Override
	public String getsNombrePais() {
		return sNombrePais;
	}

	public boolean setsNombrePais(String sNombrePais) {
		boolean bExito = false;
		if (sNombrePais != null && sNombrePais.length() > 0 && sNombrePais.length() < MAXCHARACTERS) {
			this.sNombrePais = sNombrePais;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkPais() {
		boolean bExito = false;
		if (this.sNombrePais != null) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombrePais == null) ? 0 : sNombrePais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Pais otro = (Pais) obj;
		if (checkPais() && otro.checkPais() && sNombrePais.equals(otro.sNombrePais)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "País: " + getsNombrePais() + "\n";

		return sResultado;
	}

}
