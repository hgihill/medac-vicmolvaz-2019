package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Conocimiento;

public class ConocimientoView implements LimitsDB {

	public static void subMenuConocimineto(GeneralController controller) {
		byte bOpcionSubMenu;
		do {
			bOpcionSubMenu = UsuarioView.subMenu(Conocimiento.class.getSimpleName());
			opcionMenuConocimiento(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);
	}

	public static int opcionMenuConocimiento(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (anadir(controller) != 0) {
				System.out.println("Se ha anadido el conocimiento.");
			} else {
				System.out.println("El conocimiento ya se habia registrado previamente.");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (eliminar(controller) != 0) {
				System.out.println("El conocimiento ha sido eliminado.");
			} else {
				System.out.println("El conocimiento no existe.");
			}
			break;

		case 3: // Buscar
			System.out.println("Opción no contemplada, puede generar conocimientos o eliminarlos");
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior");
		}
		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del conocimiento: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}
		errorControl = true;

		Conocimiento oConocimiento = new Conocimiento(sNombre);
		return controller.getUsuarioCtrl().addConocimiento(oConocimiento);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		int iError = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del conocimiento que desea eliminiar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Conocimiento oConocimiento = new Conocimiento(sNombre);
			iError = controller.getUsuarioCtrl().removeConocimiento(oConocimiento);
		}
		return iError;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getConCtrl());
	}
}
