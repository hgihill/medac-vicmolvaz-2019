package model.project;

import limites.LimitsDB;

public class TipoFinanciacion implements ITipo_Financiacion, LimitsDB {
	private byte bTipoFinanciacion;

	public TipoFinanciacion(byte bTipoFinanciacion) {
		setbTipoFinanciacion(bTipoFinanciacion);
	}

	@Override
	public byte getbTipoFinanciacion() {
		return bTipoFinanciacion;
	}

	private boolean setbTipoFinanciacion(byte bTipoFinanciacion) {
		boolean bExito = false;
		if (bTipoFinanciacion >= MINTIPOFIN && bTipoFinanciacion <= MAXTIPOFIN) {
			this.bTipoFinanciacion = bTipoFinanciacion;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkTipoFinanciacion() {
		boolean bExito = false;
		if (this.bTipoFinanciacion != 0) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bTipoFinanciacion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bExito = false;
		TipoFinanciacion otro = (TipoFinanciacion) obj;
		if (checkTipoFinanciacion() && otro.checkTipoFinanciacion() && this.bTipoFinanciacion == (otro.bTipoFinanciacion)) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Tipo de financiación: " + getbTipoFinanciacion() + "\n";

		return sResultado;
	}
}
