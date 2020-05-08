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
		if (sCalle != null && sCalle.length() > 0 && sCalle.length() < MAXCHARACTERS) {
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
		if (bNum >= 0 && bNum < LIMITBYTE) {
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
		if (bBloque >= 0 && bBloque < LIMITBYTE) {
			this.bBloque = bBloque;
			bExito = true;
		} else {
			bBloque = -1;
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
		if (bPortal >= 0 && bPortal < LIMITBYTE) {
			this.bPortal = bPortal;
			bExito = true;
		} else {
			bPortal = 0;
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
		if (bBloque != -1) {
			sResultado += "Bloque: " + getbBloque() + "\n";
		}
		if (bPortal != 0) {
			sResultado += "Portal: " + getbPortal() + "\n";
		}
		sResultado += oLoc;

		return sResultado;
	}

}
