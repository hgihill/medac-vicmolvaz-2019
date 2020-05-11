package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.TipoRecurso;

public class RecursoView implements LimitsDB {

	public static void subMenuRecurso(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		do {
			bOpcionSubMenu = ProyectoView.subMenu(Recurso.class.getSimpleName());

			opcionMenuRecurso(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionMenuRecurso(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha anadido el recurso.\n");
			} else {
				System.out.println("No se pudo anadir el recurso.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("El recurso ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el recurso.\n");
			}
			break;

		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha modificado el recurso.");
			} else {
				System.out.println("No se pudo modificar el recurso.\n");
			}
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
		int iCantidad = 0;
		byte bTipoRec = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del recurso: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
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
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bTipoRec = (byte) LibFrontend.valida("Indique de que tipo de recurso se trata: ", 1, 5, 3);
				if (bTipoRec >= MINTIPOREC && bTipoRec <= MAXTIPOREC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
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

	public static int modificar(GeneralController controller) {
		boolean errorControl = true;
		Recurso objRecurso = null;
		String sNombre = null;
		int iCantidad = 0;
		byte bTipoRec = 0;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del recurso: ");
				if (sNombre.length() <= LIMITGENERICO) {
					objRecurso = controller.getProyectoCtrl().getRecCtrl().searchRecurso(objRecurso, controller);
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
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
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bTipoRec = (byte) LibFrontend.valida("Indique de que tipo de recurso se trata: ", 1, 5, 3);
				if (bTipoRec >= MINTIPOREC && bTipoRec <= MAXTIPOREC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		TipoRecurso oTipoRec = new TipoRecurso(bTipoRec);
		Recurso oRecurso = new Recurso(sNombre, iCantidad, oTipoRec);

		return controller.getProyectoCtrl().addRecurso(oRecurso);
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getRecCtrl());
	}
}