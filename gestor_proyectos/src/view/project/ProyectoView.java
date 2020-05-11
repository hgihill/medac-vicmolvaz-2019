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

		do {
			bOpcionSubMenu = Main.subMenuProject(Proyecto.class.getSimpleName());

			opcionGestionarProyecto(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu != 10);
	}

	public static int opcionGestionarProyecto(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;
		switch (bOpcion) {
		case 1: // Anadir proyecto
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0)
				System.out.println("Proyecto anadido con exito.\n");
			else
				System.out.println("No se pudo anadir el proyecto.\n");
			break;
		case 2: // Eliminar proyecto
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0)
				System.out.println("Proyecto eliminado con exito.\n");
			else
				System.out.println("No se pudo eliminar el proyecto.\n");
			break;
		case 3: // Modificar proyecto
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0)
				System.out.println("Proyecto actualizado con exito.\n");
			else
				System.out.println("No se pudo actualizar el proyecto.\n");
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
		return iOperacionExito;
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
				bOpcion = (byte) LibFrontend.valida("Indique una opcion", 1, 5, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}
	
	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null, sDescripcion = null, sDniCif = null;
		int iIdProyecto = 0, iIdInv = 0;
		

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca dni/cif para crear un proyecto: ");
				if (sDniCif.length() == LIMITDNI) {
					
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("IndIntroduzcaique el ID del invetario asociado: ", 1, 1000, 1);
				if (iIdInv >= MININV && iIdProyecto <= MAXINV) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Indique el ID de este proyecto: ", 1, 100000, 1);
				if (iIdProyecto >= MINIDPROYECTO && iIdProyecto <= MAXIDPROYECTO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del proyecto: ");
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
				sDescripcion = LibFrontend.leer("Introduzca la descripción del proyecto: ");
				if (sDescripcion.length() >= MINCHARDESC && sDescripcion.length() <= MAXCHARDESC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Usuario oUs = new Usuario (sDniCif);
		Inventario oInv = new Inventario(iIdInv);
		Proyecto oProyecto = new Proyecto(iIdProyecto, sNombre, sDescripcion, oUs, oInv);

		return controller.getProyectoCtrl().addProyecto(oProyecto);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		int iIdProyectro = 0;
		int iError = 0;

		while (errorControl) {
			try {
				iIdProyectro = (int) LibFrontend.valida("Introduzca el ID del proyecto que desea eliminiar: ",1, 5000, 1);
				if (iIdProyectro <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Proyecto oProyecto = new Proyecto(iIdProyectro);
			iError = controller.getProyectoCtrl().removeProyecto(oProyecto);
		}
		return iError;
	}

	public static int modificar(GeneralController controller) {
		boolean errorControl = true;
		Proyecto objProyecto = null;
		String sNombre = null, sDescripcion = null;
		int iIdProyecto = 0;

		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Indique el ID de este proyecto: ", 1, 100000, 1);
				if (iIdProyecto >= MINIDPROYECTO && iIdProyecto <= MAXIDPROYECTO) {
					objProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(objProyecto);
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del proyecto: ");
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
				sDescripcion = LibFrontend.leer("Introduzca la descripción del proyecto: ");
				if (sDescripcion.length() >= MINCHARDESC && sDescripcion.length() <= MAXCHARDESC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Proyecto oProyecto = new Proyecto(iIdProyecto, sNombre, sDescripcion);

		return controller.getProyectoCtrl().updateProyecto(oProyecto);
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getProCtrl());
	}
	
	

}
