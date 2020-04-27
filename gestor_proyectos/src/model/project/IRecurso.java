package model.project;

public interface IRecurso {
	
	//PK
	public String getsNombre();
	

	public int getiCant();

	public boolean setiCant(int iCant);

	//FK
	public Tipo_Recurso getoTipoRec();

	
	public boolean checkRecurso();

}
