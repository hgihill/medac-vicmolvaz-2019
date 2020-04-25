package Modelo;

public class Tipo_Financiacion {
	private boolean bTipoFinanciacion;

	public Tipo_Financiacion(boolean tipoFinanciacion) {
		setTipoFinanciacion(tipoFinanciacion);
	}

	public boolean isTipoFinanciacion() {
		return bTipoFinanciacion;
	}

	public void setTipoFinanciacion(boolean tipoFinanciacion) {
		this.bTipoFinanciacion = tipoFinanciacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bTipoFinanciacion ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Tipo_Financiacion otro = (Tipo_Financiacion) obj;
		if (this != null && otro != null && this.isTipoFinanciacion() == (otro.isTipoFinanciacion())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Tipo de financiación: " + isTipoFinanciacion() + ".";

		return sResultado;
	}
}
