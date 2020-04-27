package model.project;

public interface ITipo_Recurso {

	public final byte SOFTWARE = 1;
	public final byte HARDWARE = 2;
	public final byte MATERIAL = 3;
	public final byte PERMISOS = 4;
	public final byte DOCUMENTACION = 5;
	
	//PK
	public byte getbTipoRecurso();
	
	public boolean checkTipoRecurso();
	
	
}
