package model.project;

public class Financiacion implements IFinanciacion, LimitsDB {
	private String sCuenta, sEntidad;
	private TipoFinanciacion oTipoFin;
	private Proyecto oProyecto;

	public Financiacion(String sCuenta, String sEntidad, TipoFinanciacion oTipoFin, Proyecto oProyecto) {
		setsCuenta(sCuenta);
		setsEntidad(sEntidad);
		this.oTipoFin = oTipoFin;
		this.oProyecto = oProyecto;
	}

	public Financiacion(String sCuenta) {
		setsCuenta(sCuenta);
	}

	@Override
	public String getsCuenta() {
		return sCuenta;
	}

	private boolean setsCuenta(String sCuenta) {
		boolean bExito = false;
		if (sCuenta != null && sCuenta.length() == 24) {
			this.sCuenta = sCuenta;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsEntidad() {
		return sEntidad;
	}

	@Override
	public boolean setsEntidad(String sEntidad) {
		boolean bExito = false;
		if (sEntidad != null && sEntidad.length() > 0 && sEntidad.length() < MAXCHARACTERS) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public TipoFinanciacion getoTipoFin() {
		return oTipoFin;
	}

	@Override
	public Proyecto getoProyecto() {
		return oProyecto;
	}

	@Override
	public boolean checkFinanciacion() {
		boolean bExito = false;
		if (sCuenta != null && oTipoFin.checkTipoFinanciacion() && oProyecto.checkProyecto()) {
			bExito = true;
		}
		return bExito;
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
		if (checkFinanciacion() && otro.checkFinanciacion() && this.getsCuenta() == (otro.getsCuenta())) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Cuenta: " + getsCuenta() + "\n";
		sResultado += "Entidad: " + getsEntidad() + "\n";
		sResultado += "Tipo de financiación: " + getoTipoFin() + ".\n";
		sResultado += "Proyecto asociado: " + getoProyecto();

		return sResultado;
	}

}
