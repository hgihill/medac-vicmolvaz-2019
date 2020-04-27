package model.dir;

public interface ILocalidad {
	
	// PK
    public String getsCP();
    
    public String getsNombreLoc();       
    public boolean setsNombreLoc (String sNombreLoc);
    
    // FK: NN
    public Provincia getoProv();
    public boolean setoProv (Provincia oProv);
    
    // Control methods
    public boolean checkLocalidad();

}
