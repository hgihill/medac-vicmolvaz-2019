package Modelo;

public class Recurso {
	private String sNombre;
	private int iCant;
	private Tipo_Recurso oTipoRec;

	public Recurso(String sNombre, int iCant, Tipo_Recurso oTipoRec) {
		setsNombre(sNombre);
		setiCant(iCant);
		setoTipoRec(oTipoRec);
	}

	public Recurso(String sNombre) {
		setsNombre(sNombre);
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public int getiCant() {
		return iCant;
	}

	public void setiCant(int iCant) {
		this.iCant = iCant;
	}

	public Tipo_Recurso getoTipoRec() {
		return oTipoRec;
	}

	public void setoTipoRec(Tipo_Recurso oTipoRec) {
		this.oTipoRec = oTipoRec;
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
		if (this != null && otro != null && this.getsNombre() == (otro.getsNombre())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Tipo de Recurso: " + getoTipoRec() + ".\n";
		sResultado = "Nombre: " + getsNombre() + ".\n";
		sResultado = "Cantidad: " + getiCant() + ".";

		return sResultado;
	}
}
