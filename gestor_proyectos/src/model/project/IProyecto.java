package model.project;

import model.user.Usuario;

public interface IProyecto {
	
	//PK
	public int getiId_Proyecto();

	
	public String getsNombre();

	public boolean setsNombre(String sNombre);

	public String getsDescripcion();

	public boolean setsDescripcion(String sDescripcion);
	
	//FK
	public Usuario getoUs();

	public Inventario getoInv();
	
	
	public boolean checkProyecto();

}
