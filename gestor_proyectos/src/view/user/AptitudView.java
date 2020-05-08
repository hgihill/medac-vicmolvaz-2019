package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Aptitud;

public class AptitudView implements LimitsDB {

	
	public static void subMenuAptitud(GeneralController Controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;
		int iOpcion = 0;
		do {
			bOpcionSubMenu = UsuarioView.subMenu(AptitudView.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					iOpcion = opcionGestionarAptitud(bOpcionSubMenu, Controller);
					errorControl = false;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}

			if (bOpcionSubMenu > 0 && bOpcionSubMenu <= 4) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.\n");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.\n");
				}
			}
		} while (bOpcionSubMenu != 4);
	}
	
	public static int  opcionGestionarAptitud(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;
		do {
			switch (bOpcion) {
			case 1: // Anadir
				iOperacionExito = anadir(controller);
				break;

			case 2: // Eliminar
				iOperacionExito = eliminar(controller);
				break;

			case 3: // Buscar
				iOperacionExito = buscar(controller);
				break;

			default:
				System.out.println("Regreso al menu anterior.");
			}
		} while (bOpcion != 4);
		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre de la aptitud: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		Aptitud oAptitud = new Aptitud(sNombre);
		return controller.getUsuarioCtrl().addAptitud(oAptitud);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		int iError = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre de la aptitud que desea eliminiar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Aptitud oAptitud = new Aptitud(sNombre);
			iError = controller.getUsuarioCtrl().removeAptitud(oAptitud);
		}
		return iError;
	}
	
	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		byte bOpcion = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre de la aptitud que desea buscar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Aptitud oAptitud = new Aptitud(sNombre);
			bOpcion = (byte) controller.getUsuarioCtrl().existeAptitud(oAptitud);
		}
		return bOpcion;
	}
}