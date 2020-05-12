package model.dir;

import limites.LimitsDB;

public class Localidad implements ILocalidad, LimitsDB {
	private String sLoc, sCP;
	private Provincia oProv;

	public Localidad(String sCP, String sNombreLoc, Provincia oProv) {
		setsCP(sCP);
		setsNombreLoc(sNombreLoc);
		setoProv(oProv);
	}
	
	public Localidad(String sCP, Provincia oProv) {
		setsCP(sCP);
		setoProv(oProv);
	}

	public Localidad(String sCP) {
		setsCP(sCP);
	}

	@Override
	public String getsCP() {
		return sCP;
	}

	public boolean setsCP(String sCP) {
		boolean bExito = false;
		if (sCP != null && sCP.length() == NUMCODPOSTAL) {
			this.sCP = sCP;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsNombreLoc() {
		return sLoc;
	}

	@Override
	public boolean setsNombreLoc(String sNombreLoc) {
		boolean bExito = false;
		if (sNombreLoc != null && sNombreLoc.length() > 0 && sNombreLoc.length() <= MAXCHARACTERS) {
			this.sLoc = sNombreLoc;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public Provincia getoProv() {
		return oProv;
	}

	@Override
	public boolean setoProv(Provincia oProv) {
		boolean bExito = false;
		if (oProv.checkProvincia()) {
			this.oProv = oProv;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public boolean checkLocalidad() {
		boolean bExito = false;
		if (this.sCP != null && oProv.checkProvincia()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oProv == null) ? 0 : oProv.hashCode());
		result = prime * result + ((sCP == null) ? 0 : sCP.hashCode());
		result = prime * result + ((sLoc == null) ? 0 : sLoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bExito = false;
		Localidad otro = (Localidad) obj;
		if (checkLocalidad() && otro.checkLocalidad() && this.sCP.equals(otro.sCP)) {
			bExito = true;
		}
		return bExito;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "Codigo Postal: " + getsCP() + "\n";
		sResultado += "Localidad: " + getsNombreLoc() + "\n";
		sResultado += oProv;

		return sResultado;
	}
}
