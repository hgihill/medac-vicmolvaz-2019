package limites;

public interface LimitsDB {

	// GENERAL
	public final int LIMITGENERICO = 50;
	public final int MINGENERICO = 0;

	// DIRECCION
	public final int NUMCODPOSTAL = 5;

	// USUARIO
	public final int MINCHARPASSWORD = 4;
	public final int MAXCHARPASSWORD = 15;
	public final int LIMITPHONE = 9;
	public final int LIMITDNI = 9;

	// RECURSOS
	public final int MINCANTRECURSOS = 1;
	public final int MAXCANTRECURSOS = 1000;

	// FINANCIACION
	public final int NUMCUENTA = 24;

	// PROYECTO
	public final int MINIDPROYECTO = 1;
	public final int MAXIDPROYECTO = 100000;
	public final int MINCHARDESC = 0;
	public final int MAXCHARDESC = 400;

	// TIPO_RECURSO
	public final int MINTIPOREC = 1;
	public final int MAXTIPOREC = 5;

	// TIPO_USUARIO
	public final int MINTIPOUS = 1;
	public final int MAXTIPOUS = 2;

	// TIPO_FINANCIACION
	public final int MINTIPOFIN = 1;
	public final int MAXTIPOFIN = 2;

	// APORTE
	public final int MINAPORTE = 5;
	public final int MAXAPORTE = 100000;

	// INVENTARIO
	public final int MININV = 1;
	public final int MAXINV = 1000;
	public final int MINCANT = 1;
	public final int MAXCANT = 1000;

	// DIRECCION
	public final int MINNUM = 0;
	public final int MAXNUM = 300;
	public final int MINPORTAL = 0;
	public final int MAXPORTAL = 10;
	public final int MINBLOQUE = 0;
	public final int MAXBLOQUE = 10;

	// DESCUENTO
	public final int MINDESC = 0;
	public final int MAXDESC = 100;
	
	// ROL
	public final int MINROL = 1;
	public final int MAXROL = 3;

}
