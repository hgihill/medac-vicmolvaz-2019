package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.Inventario;

public class InventarioView implements LimitsDB {

	public static void subMenuInventario(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		do {
			bOpcionSubMenu = ProyectoView.subMenu(Inventario.class.getSimpleName());

			opcionMenuInventario(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionMenuInventario(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha anadido el inventario.\n");
			} else {
				System.out.println("No se pudo anadir el inventario.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("El inventario ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el inventario.\n");
			}
			break;

		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha modificado el inventario.");
			} else {
				System.out.println("No se pudo modificar el inventario.\n");
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
		int iIdInv = 0, iCantidad = 0, iAdd = 0;
		String sNombre = null;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID del invetario: ", 1,
						1000, 1);
				if (iIdInv >= MININV && iIdInv <= MAXINV) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el recurso que desea anadir al inventario: ");
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
				iCantidad = (int) LibFrontend.valida("Introduzca la cantidad de inventario que desea generar: ", 1,
						1000, 1);
				if (iCantidad >= MINCANT && iCantidad <= MAXCANT) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Recurso oRec = new Recurso (sNombre);
		Inventario oInventario = new Inventario(iIdInv, iCantidad, oRec);
		if(controller.getProyectoCtrl().existeInventario(oInventario) == 0) {
			iAdd = controller.getProyectoCtrl().updateInventario(oInventario);
		}
		return iAdd;
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		int iError = 0, iIdInv = 0;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID del invantario que desea eliminar: ", 1, 1000, 1);
				if (iIdInv >= MININV && iIdInv <= MAXINV) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}

			Inventario oInventario = new Inventario(iIdInv);
			iError = controller.getProyectoCtrl().removeInventario(oInventario);
		}
		return iError;
	}

	public static int modificar(GeneralController controller) {
		boolean errorControl = true;
		int iIdInv = 0, iCantidad = 0, iUpdate = 0;
		String sNombre = null;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca un ID de inventario existente: ", 1, 1000, 1);
				if (iIdInv >= MINAPORTE && iIdInv <= MAXAPORTE) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				iCantidad = (int) LibFrontend.valida("Introduzca la nueva cantidad de inventarios que desea generar: ",
						1, 1000, 1);
				if (iCantidad >= MINCANT && iCantidad <= MAXCANT) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el recurso deseado: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Recurso oRec = new Recurso(sNombre);
		Inventario oInventario = new Inventario(iIdInv, iCantidad, oRec);
		if(controller.getProyectoCtrl().existeInventario(oInventario) > 0) {
			iUpdate = controller.getProyectoCtrl().updateInventario(oInventario);
		}
		
		return iUpdate;

	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getInvCtrl());
	}
}
