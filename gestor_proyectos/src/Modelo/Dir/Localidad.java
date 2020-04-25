package Modelo.Dir;

public class Localidad {
	private String sNombreLoc;
	private Provincia oProv;

	public Localidad(String sNombreLoc, Provincia oProv) {
		setsNombreLoc(sNombreLoc);
		setoProv(oProv);
	}

	public Localidad(String sNombreLoc) {
		setsNombreLoc(sNombreLoc);
	}

	public String getsNombreLoc() {
		return sNombreLoc;
	}

	public void setsNombreLoc(String sNombreLoc) {
		this.sNombreLoc = sNombreLoc;
	}

	public Provincia getoProv() {
		return oProv;
	}

	public void setoProv(Provincia oProv) {
		this.oProv = oProv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oProv == null) ? 0 : oProv.hashCode());
		result = prime * result + ((sNombreLoc == null) ? 0 : sNombreLoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Localidad otro = (Localidad) obj;
		if (this != null && otro != null && this.getsNombreLoc() == (otro.getsNombreLoc())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Nombre de la localidad: " + getsNombreLoc() + ".";

		return sResultado;
	}
}
