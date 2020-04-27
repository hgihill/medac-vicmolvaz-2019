package model.project;

public interface IInventario {
	
	//PK
	public int getiIdInv();
	

	public int getiCant();

	public boolean setiCant(int iCant);

	//FK
	public Recurso getoRec();

	
	public boolean checkInventario();

}
