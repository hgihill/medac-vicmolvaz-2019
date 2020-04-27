package model.user;

public interface ITipo_Usuario {
	public final byte PARTICULAR = 1;
	public final byte EMPRESA = 2;
	
	//PK
	public byte getbTipoUsuario();
	
	public boolean checkTipoUsuario();

}
