package model.project;

import model.user.Usuario;

public interface IDescuento {
	
	//PK && FK
	public Usuario getoUs();

	public Inventario getoInv();

	
	public float getfPorcentaje();

	public boolean setfPorcentaje(float fPorcentaje);


	public boolean checkdescuento();

}
