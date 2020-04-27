package model.project;

public interface LimitsDB {
    
    // GENERAL
    public final int MAXCHARACTERS = 255;    
    public final int LIMITBYTE = 127;
    public final int LIMITINT = 32000;
    
    // ADDRESS
    public final int NUMCODPOSTAL = 5;
      
    // RRHH
    public final int MINCHARPASSWORD = 6;
    public final int MAXCHARPASSWORD = 15;
    public final int LIMITCREDITCARD = 16;
    public final int LIMITSEGURIDADSOCIAL = 12;
    public final int LIMITPHONE = 9;
    public final int LIMITDNI = 9;
    
}
