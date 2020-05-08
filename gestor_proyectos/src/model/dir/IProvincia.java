package model.dir;

public interface IProvincia {

	// PK
	public String getsNombreProv();

	// FK: NN
	public Pais getoPais();
	
	boolean setoPais(Pais oPais);

	// Control methods
	public boolean checkProvincia();

	
}
