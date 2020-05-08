package model.user;

import model.dir.Direccion;

public interface IUsuario {
	
	//PK
	public String getsDniCif();

	
	public String getsNombre();
	
	public boolean setsNombre(String sNombre);
	
	public String getsMail();

	public boolean setsMail(String smail);

	public String getsTelefono();

	public boolean setsTelefono(String sTelefono);

	public String getsContrasena();

	public boolean setsContrasena(String sContrasena);
	
    //FK
	public TipoUsuario getoTipoUs();
	
	public Direccion getoDir();
	
	
	public boolean checkUsuario();
	

}
