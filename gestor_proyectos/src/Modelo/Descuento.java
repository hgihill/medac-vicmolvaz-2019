package Modelo;

public class Descuento {
	private Usuario oUs;
	private Inventario oInv;
	private float fPorcentaje;

	public Descuento(Usuario oUs, Inventario oInv, float fPorcentaje) {
		setoUs(oUs);
		setoInv(oInv);
		setfPorcentaje(fPorcentaje);
	}

	public Descuento(Usuario oUs, Inventario oInv) {
		setoUs(oUs);
		setoInv(oInv);
	}

	public Usuario getoUs() {
		return oUs;
	}

	public void setoUs(Usuario oUs) {
		this.oUs = oUs;
	}

	public Inventario getoInv() {
		return oInv;
	}

	public void setoInv(Inventario oInv) {
		this.oInv = oInv;
	}

	public float getfPorcentaje() {
		return fPorcentaje;
	}

	public void setfPorcentaje(float fPorcentaje) {
		this.fPorcentaje = fPorcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(fPorcentaje);
		result = prime * result + ((oInv == null) ? 0 : oInv.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Descuento otro = (Descuento) obj;
		if (this != null && otro != null && this.getoUs() == (otro.getoUs()) && this.getoInv() == (otro.getoInv())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Usuario: " + getoUs() + ".\n";
		sResultado = "Inventario: " + getoInv() + ".\n";
		sResultado = "Porcentaje decontado: " + getfPorcentaje() + ".";

		return sResultado;
	}

}
