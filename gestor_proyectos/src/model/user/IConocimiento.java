package model.user;

public interface IConocimiento {
	
	public final byte TECNICOS = 1;
	public final byte ARTISTICOS = 2;
	public final byte PROGRAMACION = 3;
	public final byte MEDICOS = 4;
	public final byte ECONOMICOS = 5;
	public final byte GESTION = 6;
	
	
	//PK
	public String getsNombre();
	
	public boolean checkConocimiento();

}
