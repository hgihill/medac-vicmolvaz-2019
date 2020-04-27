package model.user;

import model.project.Proyecto;

public interface IRol {
	
	public final byte CREADOR = 1;
	public final byte PARTICIPANTE = 2;
	public final byte CANDIDATO = 3;
	
	
	//PK && FK
	public Usuario getoUs();

	public Proyecto getoProyecto();
	
	
	public byte getbRol();

	public boolean setbRol(byte bRol);

	public boolean checkRol();
}
