package model.project;

import limites.LimitsDB;

public class Inventario implements IInventario, LimitsDB {
	private int iIdInv, iCant;
	private Recurso oRec;

	public Inventario(int iIdInv, Recurso oRec, int iCant) {
		setiIdInv(iIdInv);
		this.oRec = oRec;
		setiCant(iCant);
	}

	public Inventario(int iIdInv) {
		setiIdInv(iIdInv);
	}

	@Override
	public int getiIdInv() {
		return iIdInv;
	}

	private boolean setiIdInv(int iIdInv) {
		boolean bExito = false;
		if (iIdInv >= 0 && iIdInv < 100) {
			this.iIdInv = iIdInv;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int getiCant() {
		return iCant;
	}

	@Override
	public boolean setiCant(int iCant) {
		boolean bExito = false;
		if (iCant >= 0 && iCant < 20) {
			this.iCant = iCant;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Recurso getoRec() {
		return oRec;
	}

	@Override
	public boolean checkInventario() {
		boolean bExito = false;
		if (iIdInv != 0 && oRec.checkRecurso()) {
			bExito = true;
		}
		return bExito;
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
		if (checkInventario() && otro.checkInventario() && this.iIdInv == otro.iIdInv) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "ID inventario: " + getiIdInv() + "\n";
		sResultado += "Recurso: " + getiCant() + "\n";
		sResultado += "Cantidad de inventarios: " + getiCant();

		return sResultado;
	}
}