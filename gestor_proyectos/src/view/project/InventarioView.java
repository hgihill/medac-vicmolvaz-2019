package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Recurso;
import model.project.Inventario;

public class InventarioView implements LimitsDB {

	public static void menuConocimiento(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuAporte();
			switch (bOpcion) {
			case 1: // Anadir

				if (anadir(controller) != 0) {
					System.out.println("Se ha generado un inventario.");
				} else {
					System.out.println("No ha podido generarse el inventario.");
				}
				break;

			case 2: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("Se ha eliminado el inventario.");
				} else {
					System.out.println("No se encuentra el inventario.");
				}
				break;

			case 3: // Modificar
				if (modificar(controller) != 0) {
					System.out.println("Se ha modificado el inventario.");
				} else {
					System.out.println("No se ha podido modificar el inventario.");
				}
				break;

			case 4: // Buscar
				if (buscar(controller) != 0) {
					System.out.println("Se ha encontrado el inventario.");
				} else {
					System.out.println("No se encuentra el inventario.");
				}
				break;

			default:
				System.out.println("Regreso al menu anterior");
			}
		} while (bOpcion != 5);
	}

	public static byte opcionMenuAporte() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de conocimientos");
		System.out.println("#############################");
		System.out.println("1. Anadir inventario.");
		System.out.println("2. Eliminar inventario.");
		System.out.println("3. Modificar inventario.");
		System.out.println("4. Buscar inventario.");
		System.out.println("5. Volver atras");

		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Itroduzca una opcion: ", 1, 5, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		return bOpcion;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		int iIdInv = 0, iCantidad = 0, iAdd = 0;
		String sNombre = null;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca la cantidad de un aporte que haya sido realizado: ", 1,
						1000, 1);
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
				sNombre = LibFrontend.leer("Introduzca el recurso que desea anadir al inventario: ");
				if (sNombre.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Recurso oRec = controller.getProyectoCtrl().getRecCtrl().searchRecurso(new Recurso(sNombre));

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
				if (iIdInv >= MINAPORTE && iIdInv <= MAXAPORTE) {
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

	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		byte bOpcion = 0;
		int iIdInv = 0;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID del inventario que desea localizar: ", 1, 1000, 1);
				if (iIdInv >= MINAPORTE && iIdInv <= MAXAPORTE) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}

			Inventario oInventario = new Inventario(iIdInv);
			bOpcion = (byte) controller.getProyectoCtrl().existeInventario(oInventario);
		}
		return bOpcion;
	}
}
