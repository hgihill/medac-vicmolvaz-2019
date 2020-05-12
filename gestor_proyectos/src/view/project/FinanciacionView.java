package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Financiacion;
import model.project.Proyecto;
import model.project.TipoFinanciacion;

public class FinanciacionView implements LimitsDB {

	public static void subMenuFinanciacion(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = ProyectoView.subMenu(Financiacion.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuFinanciacion(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionMenuFinanciacion(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido la financiacion.\n");
			} else {
				System.out.println("No se pudo anadir la financiacion.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("La financiacion ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar la financiacion.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
				System.out.println("Se ha modificado la financiacion.");
			} else {
				System.out.println("No se pudo modificar la financiacion.\n");
			}
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}

	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addFinanciacion = false;
		String sCuenta = null, sEntidad = null;
		byte bTipoFinanciacion = 0;
		int iId_Proyecto = 0;

		while (errorControl) {
			try {
				iId_Proyecto = (int) LibFrontend.valida("Indique el ID del fProyecto que desee financiar", 1, 100000,
						1);
				if (iId_Proyecto >= MINIDPROYECTO && iId_Proyecto <= MAXIDPROYECTO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Proyecto oProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iId_Proyecto));

		if (oProyecto != null) {
			while (errorControl) {
				try {
					bTipoFinanciacion = (byte) LibFrontend
							.valida("Indique el tipo de finaciación:\n1. Total\n2. Parcial", 1, 2, 3);
					if (bTipoFinanciacion >= MINTIPOFIN && bTipoFinanciacion <= MAXTIPOFIN) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					sCuenta = LibFrontend.leer("Introduzca la cuenta que empleará para financiar el proyecto: ");
					if (sCuenta.length() == NUMCUENTA) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					sCuenta = LibFrontend.leer("Introduzca su la entidad bancaria: ");
					if (sCuenta.length() <= LIMITGENERICO) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
		}
		TipoFinanciacion oTipoFin = new TipoFinanciacion(bTipoFinanciacion);
		Financiacion oFinanciacion = new Financiacion(sCuenta, sEntidad, oTipoFin, oProyecto);
		if (controller.getProyectoCtrl().existeFinanciacion(oFinanciacion) == 0) {
			System.out.println(oFinanciacion);
			if (controller.getProyectoCtrl().addFinanciacion(oFinanciacion) > 0)
				;
			addFinanciacion = true;
		}
		return addFinanciacion;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean DelFinanciacion = false;
		int iRes = 0;

		iRes = controller.getProyectoCtrl().getFinCtrl().remove(
				new Financiacion(LibFrontend.leer("Introduzca su cuenta bancaria para eliminarla de la aplicacion: ")));

		if (iRes > 0) {
			DelFinanciacion = true;

		}
		return DelFinanciacion;

	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModFinanciacion = false;
		String sCuenta = null, sEntidad = null;
		byte bTipoFinanciacion = 0;
		int iId_Proyecto = 0;

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Introduzca la cuenta que empleará para financiar el proyecto: ");
				if (sCuenta.length() == NUMCUENTA) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Financiacion oFin = (Financiacion) controller.getProyectoCtrl().getFinCtrl()
				.searchFinanciacion(new Financiacion(sCuenta));

		if (oFin != null) {

			while (errorControl) {
				try {
					iId_Proyecto = (int) LibFrontend.valida("Indique el ID del fProyecto que desee financiar", 1,
							100000, 1);
					if (iId_Proyecto >= MINIDPROYECTO && iId_Proyecto <= MAXIDPROYECTO) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					bTipoFinanciacion = (byte) LibFrontend
							.valida("Indique el tipo de finaciación:\n1. Total\n2. Parcial", 1, 2, 3);
					if (bTipoFinanciacion >= MINTIPOFIN && bTipoFinanciacion <= MAXTIPOFIN) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					sCuenta = LibFrontend.leer("Introduzca su la entidad bancaria: ");
					if (sCuenta.length() <= LIMITGENERICO) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
		}
		Proyecto oProyecto = new Proyecto(iId_Proyecto);
		TipoFinanciacion oTipoFin = new TipoFinanciacion(bTipoFinanciacion);
		Financiacion oFinanciacion = new Financiacion(sCuenta, sEntidad, oTipoFin, oProyecto);
		if (controller.getProyectoCtrl().getFinCtrl().update(oFinanciacion) > 0){
			ModFinanciacion = true;
		}
		return ModFinanciacion;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getFinCtrl());
	}
}
