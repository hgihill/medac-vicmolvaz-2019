package model.project;

import limites.LimitsDB;
import model.user.Usuario;

public class Proyecto implements IProyecto, LimitsDB {
	private int iId_Proyecto;
	private String sNombre, sDescripcion;
	private Usuario oUs;
	private Inventario oInv;

	public Proyecto(int iId_Proyecto, String sNombre, String sDescripcion, Usuario oUs, Inventario oInv) {
		setiId_Proyecto(iId_Proyecto);
		setsNombre(sNombre);
		setsDescripcion(sDescripcion);
		this.oUs = oUs;
		this.oInv = oInv;
	}
	
	public Proyecto(int iId_Proyecto, String sNombre, String sDescripcion) {
		setiId_Proyecto(iId_Proyecto);
		setsNombre(sNombre);
		setsDescripcion(sDescripcion);
	}

	public Proyecto(int iId_Proyecto) {
		setiId_Proyecto(iId_Proyecto);
	}

	@Override
	public int getiId_Proyecto() {
		return iId_Proyecto;
	}

	private boolean setiId_Proyecto(int iId_Proyecto) {
		boolean bExito = false;
		if (iId_Proyecto >= 0 && iId_Proyecto < 10000) {
			this.iId_Proyecto = iId_Proyecto;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsNombre() {
		return sNombre;
	}

	@Override
	public boolean setsNombre(String sNombre) {
		boolean bExito = false;
		if (sNombre != null && sNombre.length() > MINGENERICO && sNombre.length() <= LIMITGENERICO) {
			this.sNombre = sNombre;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String getsDescripcion() {
		return sDescripcion;
	}

	@Override
	public boolean setsDescripcion(String sDescripcion) {
		boolean bExito = false;
		if (sDescripcion != null && sDescripcion.length() > MINCHARDESC && sDescripcion.length() <= MAXCHARDESC) {
			this.sDescripcion = sDescripcion;
			bExito = true;
		}
		return bExito;
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
	public boolean checkProyecto() {
		boolean bExito = false;
		if (iId_Proyecto != 0 && oUs.checkUsuario() && oInv.checkInventario()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iId_Proyecto;
		result = prime * result + ((oInv == null) ? 0 : oInv.hashCode());
		result = prime * result + ((oUs == null) ? 0 : oUs.hashCode());
		result = prime * result + ((sDescripcion == null) ? 0 : sDescripcion.hashCode());
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Proyecto otro = (Proyecto) obj;
		if (checkProyecto() && otro.checkProyecto() && this.getiId_Proyecto() == (otro.getiId_Proyecto())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado += "ID proyecto: " + getiId_Proyecto() + "\n";
		sResultado += "Nombre: " + getsNombre() + "\n";
		sResultado += "Descripción: " + getsDescripcion() + "\n";
		sResultado += "Usuarios: " + getoUs() + "\n";
		sResultado += "Inventarios: " + getoInv();

		return sResultado;
	}

}
