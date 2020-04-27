package model.project;

public class Recurso implements IRecurso, LimitsDB {
	private String sNombre;
	private int iCant;
	private Tipo_Recurso oTipoRec;

	public Recurso(String sNombre, int iCant, Tipo_Recurso oTipoRec) {
		setsNombre(sNombre);
		setiCant(iCant);
		this.oTipoRec = oTipoRec;
	}

	public Recurso(String sNombre) {
		setsNombre(sNombre);
	}

	@Override
	public String getsNombre() {
		return sNombre;
	}

	private boolean setsNombre(String sNombre) {
		boolean bExito = false;
		if (sNombre != null && sNombre.length() > 0 && sNombre.length() < MAXCHARACTERS) {
			this.sNombre = sNombre;
			bExito = true;
		}
		return bExito;
	}

	public int getiCant() {
		return iCant;
	}

	@Override
	public boolean setiCant(int iCant) {
		boolean bExito = false;
		if (iCant > 0 && iCant <= 5) {
			this.iCant = iCant;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Tipo_Recurso getoTipoRec() {
		return oTipoRec;
	}

	@Override
	public boolean checkRecurso() {
		boolean bExito = false;
		if (sNombre != null && oTipoRec.checkTipoRecurso()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iCant;
		result = prime * result + ((oTipoRec == null) ? 0 : oTipoRec.hashCode());
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Recurso otro = (Recurso) obj;
		if (checkRecurso() && otro.checkRecurso() && this.sNombre == (otro.sNombre)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Tipo de Recurso: " + getoTipoRec() + "\n";
		sResultado += "Nombre: " + getsNombre() + "\n";
		sResultado += "Cantidad: " + getiCant();

		return sResultado;
	}
}
