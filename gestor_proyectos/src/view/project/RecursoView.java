package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.TipoRecurso;

public class RecursoView implements LimitsDB {

	public static void subMenuRecurso(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = ProyectoView.subMenu(Recurso.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuRecurso(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);
	}

	public static void opcionMenuRecurso(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido el recurso.\n");
			} else {
				System.out.println("No se pudo anadir el recurso.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("El recurso ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el recurso.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
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
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addRecurso = false;
		String sNombre = null;
		int iCantidad = 0;
		byte bTipoRec = 0;

		while (errorControl) {
			try {
				bTipoRec = (byte) LibFrontend.valida("Indique de que tipo de recurso se trata: ", 1, 5, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		TipoRecurso oTipoRec = controller.getProyectoCtrl().getTRecCtrl().search(new TipoRecurso(bTipoRec));
		if (oTipoRec != null) {
			errorControl = true;
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
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}

		Recurso oRecurso = new Recurso(sNombre, iCantidad, oTipoRec);
		if (controller.getProyectoCtrl().getRecCtrl().existeRecurso(oRecurso) == 0) {
			System.out.println(oRecurso);
			if (controller.getProyectoCtrl().addRecurso(oRecurso) > 0)
				;
			addRecurso = true;
		}
		return addRecurso;
	}

	public static boolean eliminar(GeneralController controller) {
		int iRes = 0;
		boolean errorControl = true, DelRecurso = false;
		String sNombre = null;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del recurso: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Recurso oRecurso = new Recurso(sNombre);

		if (controller.proyectoCtrl.existeRecurso(oRecurso) > 0) {
			iRes = controller.getProyectoCtrl().getRecCtrl().remove(oRecurso);
			if (iRes > 0) {
				DelRecurso = true;
			}
		}
		return DelRecurso;
	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModRecurso = false;
		String sNombre = null;
		int iCantidad = 0;
		byte bTipoRec = 0;

		while (errorControl) {
			try {
				bTipoRec = (byte) LibFrontend.valida("Indique de que tipo de recurso se trata: ", 1, 5, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		TipoRecurso oTipoRec = controller.getProyectoCtrl().getTRecCtrl().search(new TipoRecurso(bTipoRec));
		if (oTipoRec != null) {
			errorControl = true;
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Introduzca el nombre del recurso: ");
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					iCantidad = (int) LibFrontend.valida("Indique que cantidad desea de este recurso: ", 1, 1000, 1);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
		}

		Recurso oRecurso = new Recurso(sNombre, iCantidad, oTipoRec);
		if (controller.proyectoCtrl.getRecCtrl().update(oRecurso) > 0) {
			ModRecurso = true;
		}
		return ModRecurso;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getRecCtrl().mostrarRecurso());
	}
}