package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Financiacion;
import model.project.Proyecto;
import model.project.TipoFinanciacion;

public class FinanciacionView implements LimitsDB {

	public static void menuAptitud(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuAptitud();
			switch (bOpcion) {
			case 1: // Anadir

				if (anadir(controller) != 0) {
					System.out.println("Se ha anadido cuenta para financiar el proyecto.");
				} else {
					System.out.println("La cuenta ya se habia registrado previamente.");
				}
				break;

			case 2: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("La cuenta ha sido eliminada.");
				} else {
					System.out.println("La cuenta no existe.");
				}
				break;

			case 3: // Buscar
				if (buscar(controller) != 0) {
					System.out.println("Se ha encontrado la financiacion.");
				} else {
					System.out.println("La cuenta no existe.");
				}
				break;

			default:
				System.out.println("Regreso al menu anterior.");
			}
		} while (bOpcion != 4);
	}

	public static byte opcionMenuAptitud() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de aptitudes");
		System.out.println("#############################");
		System.out.println("1. Anadir financiacion.");
		System.out.println("2. Eliminar financiacion.");
		System.out.println("3. Buscar financiacion.");
		System.out.println("4. Volver atras.");

		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Itroduzca una opcion: ", 1, 4, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		return bOpcion;
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
					sCuenta = LibFrontend.leer("Introduzca el la cuenta que empleará para financiar el proyecto: ");
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

	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		String sCuenta = null;
		byte bOpcion = 0;

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Indique una cuent para consultar la financiacion realizada: ");
				if (sCuenta.length() == NUMCUENTA) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			Financiacion oFinanciacion = new Financiacion(sCuenta);
			bOpcion = (byte) controller.getProyectoCtrl().existeFinanciacion(oFinanciacion);
		}
		return bOpcion;
	}
}
