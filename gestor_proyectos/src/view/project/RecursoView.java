package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.TipoRecurso;

public class RecursoView implements LimitsDB {

	public static void menuRecurso(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuRecurso();
			switch (bOpcion) {
			case 1: // Anadir

				if (anadir(controller) != 0) {
					System.out.println("Se ha anadido el recurso.");
				} else {
					System.out.println("El recurso ya se habia registrado previamente.");
				}
				break;

			case 2: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("El recurso ha sido eliminado.");
				} else {
					System.out.println("El recurso no existe.");
				}
				break;

			case 3: // Buscar
				if (buscar(controller) != 0) {
					System.out.println("Se ha encontrado el recurso.");
				} else {
					System.out.println("El recurso no existe.");
				}
				break;

			default:
				System.out.println("Regreso al menu anterior.");
			}
		} while (bOpcion != 4);
	}

	public static byte opcionMenuRecurso() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de recursos");
		System.out.println("#############################");
		System.out.println("1. Anadir recurso.");
		System.out.println("2. Eliminar recurso.");
		System.out.println("3. Buscar recurso.");
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
		String sNombre = null;
		int iCantidad = 0;
		byte bTipoRec = 0;
		
		while (errorControl) {
			try {
				bTipoRec = (byte) LibFrontend.valida("Indique de que tipo de recurso se trata: ", 1, 20, 3);
				if (bTipoRec >= MINTIPOREC && bTipoRec <= MAXTIPOREC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del recurso: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				iCantidad = (int) LibFrontend.valida("Indique que cantidad desea de este recurso: ", 1, 1000, 1);
				if (iCantidad >= MINCANTRECURSOS && iCantidad <= MAXCANTRECURSOS) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}
		errorControl = true;

		TipoRecurso oTipoRec = new TipoRecurso(bTipoRec);
		Recurso oRecurso = new Recurso(sNombre, iCantidad, oTipoRec);

		return controller.getProyectoCtrl().addRecurso(oRecurso);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		int iError = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del recurso que desea eliminiar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Recurso oRecurso = new Recurso(sNombre);
			iError = controller.getProyectoCtrl().removeRecurso(oRecurso);
		}
		return iError;
	}
	
	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		byte bOpcion = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del recurso que desea buscar: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Recurso oRecurso = new Recurso(sNombre);
			bOpcion = (byte) controller.getProyectoCtrl().existeRecurso(oRecurso);
		}
		return bOpcion;
	}
}