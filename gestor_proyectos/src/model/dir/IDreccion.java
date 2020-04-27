package model.dir;

public interface IDreccion {

	public String getsCalle();

	public byte getbNum();

	public byte getbBloque();

	public boolean setbBloque(byte bBloque);

	public byte getbPortal();

	public boolean setbPortal(byte bPortal);

	public Localidad getoLoc();

	public boolean checkDireccion();
}
