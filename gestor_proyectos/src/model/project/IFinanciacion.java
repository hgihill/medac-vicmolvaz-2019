package model.project;

public interface IFinanciacion {
	
	//PK
	public String getsCuenta();

	
	public String getsEntidad();

	public boolean setsEntidad(String sEntidad);
	
	//FK
	public TipoFinanciacion getoTipoFin();

	public Proyecto getoProyecto();


	public boolean checkFinanciacion();

}
