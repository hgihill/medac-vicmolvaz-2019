package model.user;

import model.project.Proyecto;

public interface IRol {
	
	//PK && FK
	public Usuario getoUs();

	public Proyecto getoProyecto();
	
	
	public byte getbRol();

	public boolean setbRol(byte bRol);

	public boolean checkRol();
}
