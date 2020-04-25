package Modelo.Dir;
import Modelo.LimitsDB;

public class Pais {

	private String sNombrePais;

	public Pais(String sNombrePais) {
		setsNombrePais(sNombrePais);
	}

	public String getsNombrePais() {
		return sNombrePais;
	}

	public void setsNombrePais(String sNombrePais) {
		this.sNombrePais = sNombrePais;
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
		if (this != null && otro != null && this.getsNombrePais() == (otro.getsNombrePais())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Nombre del país: " + getsNombrePais() + ".";

		return sResultado;
	}

}

