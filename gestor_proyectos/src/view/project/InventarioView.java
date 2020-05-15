package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.Financiacion;
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
				sNombre = LibFrontend.leer("Introduzca el recurso que desea anadir al inventario: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Recurso oRec = controller.getProyectoCtrl().getRecCtrl().search(new Recurso(sNombre));

		if (oRec != null) {
			while (errorControl) {
				try {
					iIdInv = (int) LibFrontend.valida("Introduzca el ID del invetario: ", 1, 1000, 1);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					iCantidad = (int) LibFrontend.valida("Introduzca la cantidad de inventario que desea generar: ", 1,
							1000, 1);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}

		Inventario oInventario = new Inventario(iIdInv, oRec, iCantidad);

		if (controller.getProyectoCtrl().getInvCtrl().add(oInventario) > 0)
			addInventario = true;
		return addInventario;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelInventario = false;
		int iRes = 0, iIdInv = 0;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID del inventario: ", 1, 1000, 1);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Inventario oInv = new Inventario(iIdInv);

		if (controller.getProyectoCtrl().getInvCtrl().existeInventario(oInv) > 0) {
			iRes = controller.getProyectoCtrl().getInvCtrl().remove(oInv);
			if (iRes > 0) {
				DelInventario = true;
			}
		}
		return DelInventario;
	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModInventario = false;
		int iIdInv = 0, iCantidad = 0;
		String sNombre = null;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID del invetario: ", 1, 1000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Inventario iInv = controller.getProyectoCtrl().getInvCtrl().searchInventario(new Inventario(iIdInv));
		errorControl = true;

		if (iInv != null) {
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Actualice el recurso que desea anadir al inventario: ");
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
			Recurso oRec = controller.getProyectoCtrl().getRecCtrl().search(new Recurso(sNombre));

			while (errorControl) {
				try {
					iCantidad = (int) LibFrontend.valida("Actualice la cantidad de inventario que desea generar: ", 1,
							1000, 1);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			
			Inventario oInventario = new Inventario(iIdInv, oRec, iCantidad);
			
			if (controller.getProyectoCtrl().updateInventario(oInventario) > 0) {
				ModInventario = true;
			}

		}
		return ModInventario;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getInvCtrl().mostrarInventario());
	}
}
