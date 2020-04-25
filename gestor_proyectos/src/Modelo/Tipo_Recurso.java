package Modelo;

public class Tipo_Recurso {
	private boolean bTipoRecurso;

	public Tipo_Recurso(boolean tipoRecurso) {
		setTipoRecurso(tipoRecurso);
	}

	public boolean isTipoRecurso() {
		return bTipoRecurso;
	}

	public void setTipoRecurso(boolean tipoRecurso) {
		this.bTipoRecurso = tipoRecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bTipoRecurso ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Tipo_Recurso otro = (Tipo_Recurso) obj;
		if (this != null && otro != null && this.isTipoRecurso() == (otro.isTipoRecurso())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Tipo de recurso: " + isTipoRecurso() + ".";

		return sResultado;
	}
}
