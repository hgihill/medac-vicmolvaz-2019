package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Conocimiento;

public class ConocimientoView implements LimitsDB {

	public static void subMenuConocimineto(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = UsuarioView.subMenu(Conocimiento.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuConocimiento(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionMenuConocimiento(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido el conocimiento.\n");
			} else {
				System.out.println("No se pudo anadir el conocimiento.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("El conocimiento ha sido eliminado.\n");
			} else {
				System.out.println("No se pudo eliminar el conocimiento.\n");
			}
			break;

		case 3: // Buscar
			System.out.println("Opción no contemplada, puede generar conocimientos o eliminarlos.\n");
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.\n");
		}
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addConocimiento = false;
		String sNombre = null;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del conocimiento: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}

		Conocimiento oConocimiento = new Conocimiento(sNombre);

		if (controller.getUsuarioCtrl().getConCtrl().existeConocimiento(oConocimiento) == 0) {
			System.out.println(oConocimiento);
			if (controller.getUsuarioCtrl().getConCtrl().add(oConocimiento) > 0);
				addConocimiento = true;
		}
		return addConocimiento;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelConocimiento = false;
		String sNombre = null;
		int iRes = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del conocimiento que desea eliminiar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Conocimiento oConocimiento = new Conocimiento(sNombre);

		if (controller.getUsuarioCtrl().getConCtrl().existeConocimiento(oConocimiento) > 0) {
			iRes = controller.getUsuarioCtrl().getConCtrl().remove(oConocimiento);
			if (iRes > 0) {
				DelConocimiento = true;
			}
		}
		return DelConocimiento;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getConCtrl().mostrarConocimiento());
	}
}
