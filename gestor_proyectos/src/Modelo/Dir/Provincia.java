package Modelo.Dir;

public class Provincia {

	private String sNombreProv;
	private Pais oPais;

	public Provincia(String sNombreProv, Pais oPais) {
		setsNombreProv(sNombreProv);
		setoPais(oPais);
	}

	public Provincia(String sNombreProv) {
		setsNombreProv(sNombreProv);
	}

	public String getsNombreProv() {
		return sNombreProv;
	}

	public void setsNombreProv(String sNombreProv) {
		this.sNombreProv = sNombreProv;
	}

	public Pais getoPais() {
		return oPais;
	}

	public void setoPais(Pais oPais) {
		this.oPais = oPais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oPais == null) ? 0 : oPais.hashCode());
		result = prime * result + ((sNombreProv == null) ? 0 : sNombreProv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Provincia otro = (Provincia) obj;
		if (this != null && otro != null && this.getsNombreProv() == (otro.getsNombreProv())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "Nombre de la provincia: " + getsNombreProv() + ".";

		return sResultado;
	}
}
