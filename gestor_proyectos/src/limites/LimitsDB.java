package limites;

public interface LimitsDB {

	// GENERAL
	public final int MAXCHARACTERS = 255;
	public final int LIMITBYTE = 127;
	public final int LIMITINT = 32000;
	public final int LIMITGENERICO = 50;

	// ADDRESS
	public final int NUMCODPOSTAL = 5;

	// RRHH
	public final int MINCHARPASSWORD = 6;
	public final int MAXCHARPASSWORD = 15;
	public final int LIMITPHONE = 9;
	public final int LIMITDNI = 9;

	// RECURSOS
	public final int MINCANTRECURSOS = 1;
	public final int MAXCANTRECURSOS = 1000;

	// FINANCIACION
	public final int NUMCUENTA = 24;
	public final int MINTIPOFIN = 1;
	public final int MAXTIPOFIN = 2;

	// PROYECTO
	public final int MINIDPROYECTO = 1;
	public final int MAXIDPROYECTO = 100000;
	public final int MINCHARDESC = 0;
	public final int MAXCHARDESC = 400;

	// TIPO_RECURSO
	public final int MINTIPOREC = 1;
	public final int MAXTIPOREC = 5;

	// APORTE
	public final int MINAPORTE = 5;
	public final int MAXAPORTE = 100000;

	// INVENTARIO
	public final int MININV = 1;
	public final int MAXINV = 1000;
	public final int MINCANT = 1;
	public final int MAXCANT = 1000;
	public final int MINPORC = 0;
	public final int MAXPORC = 100;

	// DIRECCION
	public final int MINNUM = 0;
	public final int MAXNUM = 300;
	public final int MINPORTAL = 1;
	public final int MAXPORTAL = 10;
	public final int MINBLOQUE = 1;
	public final int MAXBLOQUE = 10;
	
	
}
