package Modelo;

import java.util.Date;

public class Aporte {
	private Usuario oUs;
	private Financiacion oFin;
	private Date dFecha;
	private int iImporte;

	public Aporte(Usuario oUs, Financiacion oFin, Date dFecha, int iImporte) {

	}

	public Aporte(Usuario oUs, Financiacion oFin) {

	}

	public Usuario getoUs() {
		return oUs;
	}

	public void setoUs(Usuario oUs) {
		this.oUs = oUs;
	}

	public Financiacion getoFin() {
		return oFin;
	}

	public void setoFin(Financiacion oFin) {
		this.oFin = oFin;
	}

	public Date getdFecha() {
		return dFecha;
	}

	public void setdFecha(Date dFecha) {
		this.dFecha = dFecha;
	}

	public int getiImporte() {
		return iImporte;
	}

	public void setiImporte(int iImporte) {
		this.iImporte = iImporte;
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
		if (this != null && otro != null && this.getoUs() == (otro.getoUs()) && this.getoFin() == (otro.getoFin())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Usuario que realice el aporte: " + getoUs() + ".\n";
		sResultado = "Financiación escogida: " + getoFin() + ".\n";
		sResultado = "Fecha del aporte: " + getdFecha() + ".";
		sResultado = "Importe: " + getiImporte() + ".";

		return sResultado;
	}

}
