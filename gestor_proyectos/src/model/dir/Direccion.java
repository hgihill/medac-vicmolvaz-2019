package model.dir;

import limites.LimitsDB;

public class Direccion implements IDreccion, LimitsDB {

	private String sCalle;
	private byte bNum, bBloque, bPortal;
	private Localidad oLoc;

	public Direccion(String sCalle, byte bNum, byte bBloque, byte bPortal, Localidad oLoc) {
		setsCalle(sCalle);
		setbNum(bNum);
		setbBloque(bBloque);
		setbPortal(bPortal);
		this.oLoc = oLoc;
	}

	public Direccion(String sCalle, byte bNum, Localidad oLoc) {
		setsCalle(sCalle);
		setbNum(bNum);
		this.oLoc = oLoc;
	}
	
	public Direccion(String sCalle, byte bNum) {
		setsCalle(sCalle);
		setbNum(bNum);
	}
	
	public Direccion(byte bBloque, byte bPortal) {
		setbBloque(bBloque);
		setbPortal(bPortal);
	}

	@Override
	public String getsCalle() {
		return sCalle;
	}

	private boolean setsCalle(String sCalle) {
		boolean bExito = false;
		if (sCalle != null && sCalle.length() > MINGENERICO && sCalle.length() <= LIMITGENERICO) {
			this.sCalle = sCalle;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public byte getbNum() {
		return bNum;
	}

	private boolean setbNum(byte bNum) {
		boolean bExito = false;
		if (bNum >= MINNUM && bNum <= MAXNUM) {
			this.bNum = bNum;
			bExito = true;
		}
		return bExito;
	}

	@Override
	public byte getbBloque() {
		return bBloque;
	}

	@Override
	public boolean setbBloque(byte bBloque) {
		boolean bExito = false;
		if (bBloque >= MINBLOQUE && bBloque <= MAXBLOQUE) {
			this.bBloque = bBloque;
			bExito = true;
		} else {
			bExito = false;
		}
		return bExito;
	}

	@Override
	public byte getbPortal() {
		return bPortal;
	}

	@Override
	public boolean setbPortal(byte bPortal) {
		boolean bExito = false;
		if (bPortal >= MINPORTAL && bPortal <= MAXPORTAL) {
			this.bPortal = bPortal;
			bExito = true;
		} else {
			bExito = false;
		}
		return bExito;
	}

	@Override
	public Localidad getoLoc() {
		return oLoc;
	}

	@Override
	public boolean checkDireccion() {
		boolean bExito = false;
		if (sCalle != null && bNum != 0 && oLoc.checkLocalidad()) {
			bExito = true;
		}
		return bExito;
	}

	@Override
	public String toString() {
		String sResultado = "";

		sResultado += "Calle: " + getsCalle() + "\n";
		sResultado += "Número: " + getbNum() + "\n";
		if (bBloque != 0) {
			sResultado += "Bloque: " + getbBloque() + "\n";
		}
		if (bPortal != 0) {
			sResultado += "Portal: " + getbPortal() + "\n";
		}
		sResultado += oLoc;

		return sResultado;
	}

}
