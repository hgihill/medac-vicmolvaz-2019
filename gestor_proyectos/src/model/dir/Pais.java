package model.dir;

import limites.LimitsDB;

public class Pais implements IPais, LimitsDB {

	private String sNombre;

	public Pais(String sNombre) {
		setsNombre(sNombre);
	}

	@Override
	public String getsNombre() {
		return sNombre;
	}

	public boolean setsNombre(String sNombre) {
		boolean bExito = false;
		if (sNombre != null && sNombre.length() > MINGENERICO && sNombre.length() <= LIMITGENERICO) {
			this.sNombre = sNombre;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkPais() {
		boolean bExito = false;
		if (this.sNombre != null) {
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
		Pais otro = (Pais) obj;
		if (checkPais() && otro.checkPais() && sNombre.equals(otro.sNombre)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "País: " + getsNombre() + "\n";

		return sResultado;
	}

}
