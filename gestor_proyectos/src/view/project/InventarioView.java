package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.Inventario;

public class InventarioView implements LimitsDB {

	public static void subMenuInventario(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = ProyectoView.subMenu(Inventario.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuInventario(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionMenuInventario(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido el inventario.\n");
			} else {
				System.out.println("No se pudo anadir el inventario.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("El inventario ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el inventario.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
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

	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addInventario = false;
		int iIdInv = 0, iCantidad = 0;
		String sNombre = null;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID del invetario: ", 1, 1000, 1);
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


		Recurso oRec = new Recurso(sNombre);
		Inventario oInventario = new Inventario(iIdInv, oRec, iCantidad);
		if (controller.getProyectoCtrl().existeInventario(oInventario) == 0) {
			System.out.println(oInventario);
			if (controller.getProyectoCtrl().addInventario(oInventario) > 0)
				addInventario = true;
		}
		return addInventario;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean DelInventario = false;
		int iRes = 0;

		iRes = controller.getProyectoCtrl().getInvCtrl().remove(new Inventario(
				(int) LibFrontend.valida("Introduzca el ID del inventario que desee eliminar: )", 1, 1000, 1)));
		if (iRes > 0) {
			DelInventario = true;
		}
		return DelInventario;
	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModInventario = false;
		int iIdInv = 0, iCantidad = 0;
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
				sNombre = LibFrontend.leer("Introduzca el recurso deseado: ");
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

		Recurso oRec = new Recurso(sNombre);
		Inventario oInventario = new Inventario(iIdInv, oRec, iCantidad);
		if (controller.getProyectoCtrl().updateInventario(oInventario) > 0) {
			ModInventario = true;
		}
		return ModInventario;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getInvCtrl().mostrarInventario());
	}
}
