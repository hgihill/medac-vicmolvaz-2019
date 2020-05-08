package model.project;

import model.user.Usuario;

public interface IAporte {
	
	//PK && FK
	public Usuario getoUs();

	public Financiacion getoFin();


	public int getiImporte();

	public boolean setiImporte(int iImporte);


	public boolean checkAporte();

}
