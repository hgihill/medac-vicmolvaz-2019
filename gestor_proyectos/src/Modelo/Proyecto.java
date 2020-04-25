package Modelo;

public class Proyecto {
	private int iId_Proyecto;
	private String sNombre, sDescripcion;
	private Usuario oUs;
	private Inventario oInv;

	public Proyecto(int iId_Proyecto, String sNombre, String sDescripcion, Usuario oUs, Inventario oInv) {
		setiId_Proyecto(iId_Proyecto);
		setsNombre(sNombre);
		setsDescripcion(sDescripcion);
		setoUs(oUs);
		setoInv(oInv);
	}

	public Proyecto(int iId_Proyecto) {
		setiId_Proyecto(iId_Proyecto);
	}

	public int getiId_Proyecto() {
		return iId_Proyecto;
	}

	public void setiId_Proyecto(int iId_Proyecto) {
		this.iId_Proyecto = iId_Proyecto;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getsDescripcion() {
		return sDescripcion;
	}

	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
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
		if (this != null && otro != null && this.getiId_Proyecto() == (otro.getiId_Proyecto())) {
			bIgual = true;
		}
		return bIgual;
	}

	public String toString() {
		String sResultado = "";

		sResultado = "ID proyecto: " + getiId_Proyecto() + ".\n";
		sResultado = "Nombre: " + getsNombre() + ".\n";
		sResultado = "Descripción: " + getsDescripcion() + ".\n";
		sResultado = "Usuarios: " + getoUs() + ".\n";
		sResultado = "Inventarios: " + getoInv() + ".";

		return sResultado;
	}

}
