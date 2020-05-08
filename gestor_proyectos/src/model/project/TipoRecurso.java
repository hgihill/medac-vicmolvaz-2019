package model.project;

import limites.LimitsDB;

public class TipoRecurso implements ITipo_Recurso, LimitsDB {

	private byte bTipoRecurso;

	public TipoRecurso(byte bTipoRecurso) {
		setbTipoRecurso(bTipoRecurso);
	}

	@Override
	public byte getbTipoRecurso() {
		return bTipoRecurso;
	}

	private boolean setbTipoRecurso(byte bTipoRecurso) {
		boolean bExito = false;
		if (bTipoRecurso > 0 && bTipoRecurso <= LIMITBYTE) {
			this.bTipoRecurso = bTipoRecurso;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkTipoRecurso() {
		boolean bExito = false;
		if (this.bTipoRecurso != 0) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bTipoRecurso;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bExito = false;
		TipoRecurso otro = (TipoRecurso) obj;
		if (checkTipoRecurso() && otro.checkTipoRecurso() && this.bTipoRecurso == (otro.bTipoRecurso)) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Tipo de recurso: " + getbTipoRecurso() + "\n";

		return sResultado;
	}
}
