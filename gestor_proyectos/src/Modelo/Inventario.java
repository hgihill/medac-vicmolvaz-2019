package Modelo;

public class Inventario {
	private int iIdInv, iCant;
	private Recurso oRec;

	public Inventario(int iIdInv, int iCant, Recurso oRec) {
		setiIdInv(iIdInv);
		setiCant(iCant);
		setoRec(oRec);
	}

	public Inventario(int iIdInv) {
		setiIdInv(iIdInv);
	}

	public int getiIdInv() {
		return iIdInv;
	}

	public void setiIdInv(int iIdInv) {
		this.iIdInv = iIdInv;
	}

	public int getiCant() {
		return iCant;
	}

	public void setiCant(int iCant) {
		this.iCant = iCant;
	}

	public Recurso getoRec() {
		return oRec;
	}

	public void setoRec(Recurso oRec) {
		this.oRec = oRec;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iCant;
		result = prime * result + iIdInv;
		result = prime * result + ((oRec == null) ? 0 : oRec.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Inventario otro = (Inventario) obj;
		if (this != null && otro != null && this.getiIdInv() == (otro.getiIdInv())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "ID inventario: " + getiIdInv() + ".\n";
		sResultado = "Recurso: " + getiCant() + ".\n";
		sResultado = "Cantidad de inventarios: " + getiCant() + ".";

		return sResultado;
	}
}