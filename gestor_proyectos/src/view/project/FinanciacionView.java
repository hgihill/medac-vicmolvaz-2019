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
		do {
			bOpcionSubMenu = ProyectoView.subMenu(Financiacion.class.getSimpleName());

			opcionMenuFinanciacion(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionMenuFinanciacion(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha anadido la financiacion.\n");
			} else {
				System.out.println("No se pudo anadir la financiacion.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("La financiacion ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar la financiacion.\n");
			}
			break;

		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
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

		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
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
		return controller.getProyectoCtrl().addFinanciacion(oFinanciacion);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sCuenta = null;
		int iError = 0;

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Introduzca su cuenta bancaria para eliminarla de la aplicacion: ");
				if (sCuenta.length() == NUMCUENTA) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Financiacion oFinanciacion = new Financiacion(sCuenta);
			iError = controller.getProyectoCtrl().removeFinanciacion(oFinanciacion);
		}
		return iError;
	}

	public static int modificar(GeneralController controller) {
		boolean errorControl = true;
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
		Financiacion oFin = (Financiacion) controller.getProyectoCtrl().getFinCtrl().searchFinanciacion(new Financiacion(sCuenta));

		if (oFin != null) {
			
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
		Proyecto oProyecto = new Proyecto (iId_Proyecto);
		TipoFinanciacion oTipoFin = new TipoFinanciacion(bTipoFinanciacion);
		Financiacion oFinanciacion = new Financiacion(sCuenta, sEntidad, oTipoFin, oProyecto);
		return controller.getProyectoCtrl().addFinanciacion(oFinanciacion);
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getFinCtrl());
	}
}
