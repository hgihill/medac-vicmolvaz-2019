package model.dir;

import limites.LimitsDB;

public class Provincia implements IProvincia, LimitsDB {

	private String sProv;
	private Pais oPais;

	public Provincia(String sNombreProv, Pais oPais) {
		setsNombreProv(sNombreProv);
		setoPais(oPais);
	}

	public Provincia(String sNombreProv) {
		setsNombreProv(sNombreProv);
	}

	@Override
	public String getsNombreProv() {
		return sProv;
	}

	public boolean setsNombreProv(String sNombreProv) {
		boolean bExito = false;
		if (sNombreProv != null && sNombreProv.length() > 0 && sNombreProv.length() <= MAXCHARACTERS) {
			this.sProv = sNombreProv;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Pais getoPais() {
		return oPais;
	}

	@Override
	public boolean setoPais(Pais oPais) {
		boolean bExito = false;
		if (oPais.checkPais()) {
			this.oPais = oPais;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkProvincia() {
		boolean bExito = false;
		if (this.sProv != null && oPais.checkPais()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oPais == null) ? 0 : oPais.hashCode());
		result = prime * result + ((sProv == null) ? 0 : sProv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Provincia otro = (Provincia) obj;
		if (checkProvincia() && otro.checkProvincia() && sProv.equals(otro.sProv)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Provincia: " + getsNombreProv() + "\n";
		sResultado += "País: " + getoPais();

		return sResultado;
	}
}
