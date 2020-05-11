package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Aptitud;

public class AptitudView implements LimitsDB {

	public static void subMenuAptitud(GeneralController controller) {
		byte bOpcionSubMenu;
		do {
			bOpcionSubMenu = UsuarioView.subMenu(Aptitud.class.getSimpleName());
			opcionMenuAptitud(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);
	}

	public static int opcionMenuAptitud(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha anadido la aptitud.\n");
			} else {
				System.out.println("No se pudo anadir la aptitud.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("La aptitud ha sido eliminada.");
			} else {
				System.out.println("No se pudo eliminar la aptitud.\n");
			}
			break;

		case 3: // Buscar
			System.out.println("Opción no contemplada, puede generar aptitudes o eliminarlas");
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}

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

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getAptCtrl());
	}
}