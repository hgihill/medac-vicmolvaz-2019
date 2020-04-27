package model.project;

import java.util.Date;

import model.user.Usuario;

public class Aporte implements IAporte, LimitsDB {
	private Usuario oUs;
	private Financiacion oFin;
	private Date dFecha;
	private int iImporte;

	public Aporte(Usuario oUs, Financiacion oFin, Date dFecha, int iImporte) {
		this.oUs = oUs;
		this.oFin = oFin;
		setdFecha(dFecha);
		setiImporte(iImporte);
	}

	public Aporte(Usuario oUs, Financiacion oFin) {
		this.oUs = oUs;
		this.oFin = oFin;
	}

	@Override
	public Usuario getoUs() {
		return oUs;
	}

	@Override
	public Financiacion getoFin() {
		return oFin;
	}

	@Override
	public Date getdFecha() {
		return dFecha;
	}

	@Override
	public void setdFecha(Date dFecha) {
		this.dFecha = dFecha;
	}

	@Override
	public int getiImporte() {
		return iImporte;
	}

	@Override
	public boolean setiImporte(int iImporte) {
		boolean bExito = false;
		if (iImporte >= 0 && iImporte < LIMITINT) {
			this.iImporte = iImporte;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkAporte() {
		boolean bExito = false;
		if (oUs.checkUsuario() && oFin.checkFinanciacion()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dFecha == null) ? 0 : dFecha.hashCode());
		result = prime * result + iImporte;
		result = prime * result + ((oFin == null) ? 0 : oFin.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Aporte otro = (Aporte) obj;
		if (checkAporte() && otro.checkAporte() && this.getoUs() == (otro.getoUs()) && this.getoFin() == (otro.getoFin())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Usuario que realice el aporte: " + getoUs() + "\n";
		sResultado += "Financiación escogida: " + getoFin() + "\n";
		sResultado += "Fecha del aporte: " + getdFecha() + "\n";
		sResultado += "Importe: " + getiImporte();

		return sResultado;
	}

}
