package model.project;

import java.util.Date;

import model.user.Usuario;

public interface IAporte {
	
	//PK && FK
	public Usuario getoUs();

	public Financiacion getoFin();

	
	public Date getdFecha();

	public void setdFecha(Date dFecha);

	public int getiImporte();

	public boolean setiImporte(int iImporte);


	public boolean checkAporte();

}
