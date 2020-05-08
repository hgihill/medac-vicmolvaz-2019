package model.project;

import limites.LimitsDB;
import model.user.Usuario;

public class Descuento implements IDescuento, LimitsDB {
	private Usuario oUs;
	private Inventario oInv;
	private float fPorcentaje;

	public Descuento(Usuario oUs, Inventario oInv, float fPorcentaje) {
		this.oUs = oUs;
		this.oInv = oInv;
		setfPorcentaje(fPorcentaje);
	}

	public Descuento(Usuario oUs, Inventario oInv) {
		this.oUs = oUs;
		this.oInv = oInv;
	}

	@Override
	public Usuario getoUs() {
		return oUs;
	}

	@Override
	public Inventario getoInv() {
		return oInv;
	}

	@Override
	public float getfPorcentaje() {
		return fPorcentaje;
	}

	@Override
	public boolean setfPorcentaje(float fPorcentaje) {
		boolean bExito = false;
		if (fPorcentaje >= 0 && fPorcentaje <= 100) {
			this.fPorcentaje = fPorcentaje;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkdescuento() {
		boolean bExito = false;
		if (oUs.checkUsuario() && oInv.checkInventario()) {
			bExito = true;
		}
		return bExito;
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
		if (checkdescuento() && otro.checkdescuento() && this.getoUs() == (otro.getoUs())
				&& this.getoInv() == (otro.getoInv())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Usuario: " + getoUs() + "\n";
		sResultado += "Inventario: " + getoInv() + "\n";
		sResultado += "Porcentaje decontado: " + getfPorcentaje();

		return sResultado;
	}

}
