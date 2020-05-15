package model.user;

import limites.LimitsDB;

public class Aptitud implements IAptitud, LimitsDB {

	private String sNombreApt;

	public Aptitud(String sNombreApt) {
		setsNombreApt(sNombreApt);
	}

	@Override
	public String getsNombreApt() {
		return sNombreApt;
	}

	private boolean setsNombreApt(String sNombreApt) {
		boolean bExito = false;
		if (sNombreApt != null && sNombreApt.length() > MINGENERICO && sNombreApt.length() < LIMITGENERICO) {
			this.sNombreApt = sNombreApt;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkAptitud() {
		boolean bExito = false;
		if (sNombreApt != null) {
			bExito = true;
		}
		return bExito;
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
		boolean bExito = false;
		Aptitud otro = (Aptitud) obj;
		if (checkAptitud() && otro.checkAptitud() && this.sNombreApt.equals(otro.sNombreApt)) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Aptitud: " + getsNombreApt() + "\n";

		return sResultado;
	}

}
