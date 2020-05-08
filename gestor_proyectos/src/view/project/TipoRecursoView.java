package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.TipoRecurso;

public class TipoRecursoView implements LimitsDB {

	public static void MenuTipoRecurso(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuTipoRecurso();
			switch (bOpcion) {
			case 1: // Anadir
				if (anadir(controller) != 0) {
					System.out.println("Se ha anadido este nuevo tipo de recurso.");
				} else {
					System.out.println("Este tipo de recurso ya se habia registrado previamente.");
				}
				break;

			case 2: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("El tipo de recurso ha sido eliminada.");
				} else {
					System.out.println("El tipo de recurso no existe.");
				}
				break;

			case 3: // Buscar
				if (buscar(controller) != 0) {
					System.out.println("Se ha encontrado el tipo de recurso.");
				} else {
					System.out.println("El tipo de recurso no existe.");
				}
				break;

			default:
				System.out.println("Regreso al menu anterior.");
			}
		} while (bOpcion != 4);
	}

	public static byte opcionMenuTipoRecurso() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de tipos de recurso");
		System.out.println("#############################");
		System.out.println("1. Anadir tipo de recurso.");
		System.out.println("2. Eliminar tipo de recurso.");
		System.out.println("3. Buscar tipo de recurso.");
		System.out.println("4. Volver atras.");

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
		byte bTipoRecurso = 0;

		while (errorControl) {
			try {
				bTipoRecurso = (byte) LibFrontend.valida("Introduzca el tipo de recurso: ", 1, 20, 3);
				if (bTipoRecurso >= MINTIPOREC && bTipoRecurso <= MAXTIPOREC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		TipoRecurso oTipoRecurso = new TipoRecurso(bTipoRecurso);
		return controller.getProyectoCtrl().addTipoRecurso(oTipoRecurso);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		byte bTipoRecurso = 0;
		int iError = 0;

		while (errorControl) {
			try {
				bTipoRecurso = (byte) LibFrontend.valida("Introduzca el nombre de la aptitud: ", 1, 20, 3);
				if (bTipoRecurso >= MINTIPOREC && bTipoRecurso <= MAXTIPOREC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			TipoRecurso oTipoRecurso = new TipoRecurso(bTipoRecurso);
			return controller.getProyectoCtrl().removeTipoRecurso(oTipoRecurso);
		}
		return iError;
	}
	
	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		byte bTipoRecurso = 0;
		byte bOpcion = 0;

		while (errorControl) {
			try {
				bTipoRecurso = (byte) LibFrontend.valida("Introduzca el nombre de la aptitud que desea buscar: ", 1, 20, 3);
				if (bTipoRecurso >= MINTIPOREC && bTipoRecurso <= MAXTIPOREC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			TipoRecurso oTipoRecurso = new TipoRecurso(bTipoRecurso);
			bOpcion = (byte) controller.getProyectoCtrl().existeTipoRecurso(oTipoRecurso);
		}
		return bOpcion;
	}
}
