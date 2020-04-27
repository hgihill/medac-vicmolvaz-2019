package model.project;

public interface ITipo_Financiacion {
	
	public final byte TOTAL = 1;
	public final byte PARCIAL = 2;
	
	//PK
	public byte getbTipoFinanciacion();
	
	public boolean checkTipoFinanciacion();

}
