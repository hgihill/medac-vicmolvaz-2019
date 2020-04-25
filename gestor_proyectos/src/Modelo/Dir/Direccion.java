package Modelo.Dir;

public class Direccion {

	private String sCalle;
	private int iCp, iNum, iBloque, iPortal;
	private Localidad oLoc;

	public Direccion(int iCp, String sCalle, int iNum, int iBloque, int iPortal, Localidad oLoc) {
		setiCp(iCp);
		setsCalle(sCalle);
		setiNum(iNum);
		setiBloque(iBloque);
		setiPortal(iPortal);
		setoLoc(oLoc);
	}

	public Direccion(int iCp, String sCalle, int iNum) {
		setiCp(iCp);
		setsCalle(sCalle);
		setiNum(iNum);
	}

	public String getsCalle() {
		return sCalle;
	}

	public void setsCalle(String sCalle) {
		this.sCalle = sCalle;
	}

	public int getiCp() {
		return iCp;
	}

	public void setiCp(int iCp) {
		this.iCp = iCp;
	}

	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public int getiBloque() {
		return iBloque;
	}

	public void setiBloque(int iBloque) {
		this.iBloque = iBloque;
	}

	public int getiPortal() {
		return iPortal;
	}

	public void setiPortal(int iPortal) {
		this.iPortal = iPortal;
	}

	public Localidad getoLoc() {
		return oLoc;
	}

	public void setoLoc(Localidad oLoc) {
		this.oLoc = oLoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iBloque;
		result = prime * result + iCp;
		result = prime * result + iNum;
		result = prime * result + iPortal;
		result = prime * result + ((oLoc == null) ? 0 : oLoc.hashCode());
		result = prime * result + ((sCalle == null) ? 0 : sCalle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Direccion otro = (Direccion) obj;
		if (this != null && otro != null && this.getsCalle() == (otro.getsCalle()) && this.getiCp() == (otro.getiCp())
				&& this.getiNum() == (otro.getiNum())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "CP: " + getiCp() + ".";
		sResultado = "Calle: " + getsCalle() + ".";
		sResultado = "Número: " + getiNum() + ".";
		sResultado = "Bloque: " + getiBloque() + ".";
		sResultado = "Portal: " + getiPortal() + ".";
		sResultado = "Localidad: " + getoLoc() + ".";

		return sResultado;
	}

}
