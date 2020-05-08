package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Conocimiento;

public class ConocimientoView implements LimitsDB {

	public static void menuConocimiento(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuConocimiento();
			switch (bOpcion) {
			case 1: // Anadir

				if (anadir(controller) != 0) {
					System.out.println("Se ha anadido el conocimiento.");
				} else {
					System.out.println("El conocimiento ya se habia registrado previamente.");
				}
				break;

			case 2: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("El conocimiento ha sido eliminado.");
				} else {
					System.out.println("El conocimiento no existe.");
				}
				break;

			case 3: // Buscar
				if (buscar(controller) != 0) {
					System.out.println("Se ha encontrado el conocimiento.");
				} else {
					System.out.println("La conocimiento no existe.");
				}
				break;

			default:
				System.out.println("Regreso al menu anterior");
			}
		} while (bOpcion != 4);
	}

	public static byte opcionMenuConocimiento() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de conocimientos");
		System.out.println("#############################");
		System.out.println("1. Anadir conocimiento.");
		System.out.println("2. Eliminar conocimiento.");
		System.out.println("3. Buscar conocimiento.");
		System.out.println("4. Volver atras");

		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Itroduzca una opcion: ", 1, 4, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		return bOpcion;
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

	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		byte bOpcion = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del conocimiento que desea buscar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Conocimiento oConocimiento = new Conocimiento(sNombre);
			bOpcion = (byte) controller.getUsuarioCtrl().existeConocimiento(oConocimiento);
		}
		return bOpcion;
	}

}
