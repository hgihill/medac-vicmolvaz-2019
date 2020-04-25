package Modelo;

public class Financiacion {
	private String sCuenta, sEntidad;
	private Tipo_Financiacion oTipoFin;
	private Proyecto oProyecto;

	public Financiacion(String sCuenta, String sEntidad, Tipo_Financiacion oTipoFin, Proyecto oProyecto) {
		setsCuenta(sCuenta);
		setsEntidad(sEntidad);
		setoTipoFin(oTipoFin);
		setoProyecto(oProyecto);
	}

	public Financiacion(String sCuenta) {
		setsCuenta(sCuenta);
	}

	public String getsCuenta() {
		return sCuenta;
	}

	public void setsCuenta(String sCuenta) {
		this.sCuenta = sCuenta;
	}

	public String getsEntidad() {
		return sEntidad;
	}

	public void setsEntidad(String sEntidad) {
		this.sEntidad = sEntidad;
	}

	public Tipo_Financiacion getoTipoFin() {
		return oTipoFin;
	}

	public void setoTipoFin(Tipo_Financiacion oTipoFin) {
		this.oTipoFin = oTipoFin;
	}

	public Proyecto getoProyecto() {
		return oProyecto;
	}

	public void setoProyecto(Proyecto oProyecto) {
		this.oProyecto = oProyecto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oProyecto == null) ? 0 : oProyecto.hashCode());
		result = prime * result + ((oTipoFin == null) ? 0 : oTipoFin.hashCode());
		result = prime * result + ((sCuenta == null) ? 0 : sCuenta.hashCode());
		result = prime * result + ((sEntidad == null) ? 0 : sEntidad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Financiacion otro = (Financiacion) obj;
		if (this != null && otro != null && this.getsCuenta() == (otro.getsCuenta())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Cuenta: " + getsCuenta() + ".\n";
		sResultado = "Entidad: " + getsEntidad() + ".\n";
		sResultado = "Tipo de financiación: " + getoTipoFin() + ".\n";
		sResultado = "Proyecto asociado: " + getoProyecto() + ".";

		return sResultado;
	}

}
