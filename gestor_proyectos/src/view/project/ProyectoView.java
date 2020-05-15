package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import main.Main;
import medac.validaciones.LibFrontend;
import model.project.Inventario;
import model.project.Proyecto;
import model.user.Usuario;

public class ProyectoView implements LimitsDB {

	public static void subMenuProyecto(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = Main.subMenuProject(Proyecto.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionGestionarProyecto(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 10);

	}

	public static void opcionGestionarProyecto(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir proyecto
			if (anadir(controller) == true) {
				System.out.println("Proyecto anadido con exito.\n");
			} else {
				System.out.println("No se pudo anadir el proyecto.\n");
			}
			break;
		case 2: // Eliminar proyecto
			if (eliminar(controller) == true) {
				System.out.println("Proyecto eliminado con exito.\n");
			} else {
				System.out.println("No se pudo eliminar el proyecto.\n");
			}
			break;
		case 3: // Modificar proyecto
			if (modificar(controller) == true) {
				System.out.println("Proyecto actualizado con exito.\n");
			} else {
				System.out.println("No se pudo actualizar el proyecto.\n");
			}
			break;
		case 4: // Mostrar proyectos
			mostrar(controller);

		case 5: // Gestionar financiaciones
			FinanciacionView.subMenuFinanciacion(controller);
			break;
		case 6: // Gestionar aportes
			AporteView.subMenuAporte(controller);
			break;
		case 7: // Gestionar recursos
			RecursoView.subMenuRecurso(controller);
			break;
		case 8: // Gestionar inventarios
			InventarioView.subMenuInventario(controller);
			break;
		case 9: // Gestionar descuentos
			DescuentoView.subMenuDescuento(controller);
			break;
		case 10:
			System.out.println("Volviendo al menu anterior");
			break;
		default:
			System.out.println("Introduzca una opcion valida");
			break;
		}
	}

	public static byte subMenu(String sClase) {
		byte bOpcion = 0;
		System.out.println("MENU: " + sClase);
		System.out.println("-----------------------");
		System.out.println("1. Aniadir " + sClase);
		System.out.println("2. Eliminar " + sClase);
		System.out.println("3. Modificar " + sClase);
		System.out.println("4. Mostrar " + sClase);
		System.out.println("5. Volver al menu anterior\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion: ", 1, 5, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addProyecto = true;
		String sNombre = null, sDescripcion = null, sDniCif = null;
		int iIdProyecto = 0, iIdInv = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca dni/cif para crear un proyecto: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		errorControl = true;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Indique el ID del invetario asociado: ", 1, 1000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Inventario oInv = (Inventario) controller.getProyectoCtrl().getInvCtrl()
				.searchInventario(new Inventario(iIdInv));
		errorControl = true;

		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Indique el ID de este proyecto: ", 1, 100000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del proyecto: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sDescripcion = LibFrontend.leer("Introduzca la descripción del proyecto: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Proyecto oProyecto = new Proyecto(iIdProyecto, sNombre, sDescripcion, oUs, oInv);

		System.out.println(oProyecto);
		if (controller.getProyectoCtrl().getProCtrl().existeProyecto(oProyecto) == 0) {
			if (controller.getProyectoCtrl().getProCtrl().add(oProyecto) > 0) {
				addProyecto = true;
			}
		}
		return addProyecto;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelProyecto = true;
		int iIdProyectro = 0;
		int iRes = 0;

		while (errorControl) {
			try {
				iIdProyectro = (int) LibFrontend.valida("Introduzca el ID del proyecto que desea eliminiar: ", 1, 5000,
						1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Proyecto oProyecto = new Proyecto(iIdProyectro);

		if (controller.getProyectoCtrl().existeProyecto(oProyecto) > 0) {
			iRes = controller.getProyectoCtrl().removeProyecto(oProyecto);
			if (iRes > 0) {
				DelProyecto = true;
			}

		}
		return DelProyecto;
	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModProyecto = true;
		String sDniCif = null, sNombre = null, sDescripcion = null;
		int iIdInv = 0, iIdProyecto = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca dni/cif para crear un proyecto: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		errorControl = true;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("IndIntroduzcaique el ID del invetario asociado: ", 1, 1000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Inventario oInv = (Inventario) controller.getProyectoCtrl().getInvCtrl()
				.searchInventario(new Inventario(iIdInv));
		errorControl = true;

		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Indique el ID de este proyecto: ", 1, 100000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del proyecto: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sDescripcion = LibFrontend.leer("Introduzca la descripción del proyecto: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Proyecto oProyecto = new Proyecto(iIdProyecto, sNombre, sDescripcion, oUs, oInv);
		if (controller.getProyectoCtrl().getProCtrl().update(oProyecto) > 0) {
			ModProyecto = true;
		}
		return ModProyecto;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getProCtrl().mostrarProyecto());
	}

}
