package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Aptitud;

public class AptitudView implements LimitsDB {

	public static void subMenuAptitud(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = UsuarioView.subMenu(Aptitud.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuAptitud(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionMenuAptitud(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido la aptitud.\n");
			} else {
				System.out.println("No se pudo anadir la aptitud.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
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

	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addAptitud = false;
		String sNombre = null;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre de la aptitud: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		Aptitud oAptitud = new Aptitud(sNombre);
		if (controller.getUsuarioCtrl().existeAptitud(oAptitud) == 0) {
			System.out.println(oAptitud);
			if (controller.getUsuarioCtrl().addAptitud(oAptitud) > 0);
			addAptitud = true;
		}
		return addAptitud;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelAptitud = false;
		String sNombre = null;
		int iRes = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre de la aptitud que desea eliminiar: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Aptitud oAptitud = new Aptitud(sNombre);

		if (controller.getUsuarioCtrl().existeAptitud(oAptitud) > 0) {
			iRes = controller.getUsuarioCtrl().removeAptitud(oAptitud);
			if (iRes > 0) {
				DelAptitud = true;
			}

		}
		return DelAptitud;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getAptCtrl().mostrarAptitud());
	}
}