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
				System.out.println("La financiacion ha sido eliminado.\n");
			} else {
				System.out.println("No se pudo eliminar la financiacion.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
				System.out.println("Se ha modificado la financiacion.\n");
			} else {
				System.out.println("No se pudo modificar la financiacion.\n");
			}
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.\n");
		}

	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addFinanciacion = false;
		String sCuenta = null, sEntidad = null;
		byte bTipoFinanciacion = 0;
		int iId_Proyecto = 0;

		while (errorControl) {
			try {
				iId_Proyecto = (int) LibFrontend.valida("Indique el ID del proyecto que desee financiar: ", 1, 100000,
						1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Proyecto oProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iId_Proyecto), controller);

		while (errorControl) {
			try {
				bTipoFinanciacion = (byte) LibFrontend.valida("Indique el tipo de finaciación (1.Total 2.Parcial): ", 1,
						2, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		TipoFinanciacion oTipoFin = controller.getProyectoCtrl().getTFinCtrl()
				.search(new TipoFinanciacion(bTipoFinanciacion));

		if (oProyecto != null && oTipoFin != null) {
			while (errorControl) {
				try {
					sCuenta = LibFrontend.leer("Introduzca la cuenta que empleará para financiar el proyecto: ");
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					sEntidad = LibFrontend.leer("Introduzca su la entidad bancaria: ");
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
		}
		System.out.println(sCuenta);
		System.out.println(sEntidad);
		System.out.println(oTipoFin);
		System.out.println(oProyecto);
		Financiacion oFinanciacion = new Financiacion(sCuenta, sEntidad, oTipoFin, oProyecto);
		if (controller.getProyectoCtrl().getFinCtrl().existeFinanciacion(oFinanciacion) == 0) {
			System.out.println(oFinanciacion);
			if (controller.getProyectoCtrl().getFinCtrl().add(oFinanciacion) > 0) {
				addFinanciacion = true;
			}
		}
		return addFinanciacion;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelFinanciacion = false;
		int iRes = 0;
		String sCuenta = null;

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Introduzca la cuenta para eliminarla: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Financiacion oFinanciacion = new Financiacion(sCuenta);

		if (controller.getProyectoCtrl().getFinCtrl().existeFinanciacion(oFinanciacion) > 0) {
			iRes = controller.getProyectoCtrl().getFinCtrl().remove(oFinanciacion);

			if (iRes > 0) {
				DelFinanciacion = true;
			}
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
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Financiacion oFinanciacion = controller.getProyectoCtrl().getFinCtrl().search(new Financiacion(sCuenta),
				controller);
		if (oFinanciacion != null) {

			while (errorControl) {
				try {
					iId_Proyecto = (int) LibFrontend.valida("Actualice el ID del proyecto que desee financiar: ", 1,
							100000, 1);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
			Proyecto oProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iId_Proyecto), controller);

			while (errorControl) {
				try {
					bTipoFinanciacion = (byte) LibFrontend
							.valida("Actualice el tipo de finaciación (1.Total 2.Parcial): ", 1, 2, 3);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
			TipoFinanciacion oTipoFin = controller.getProyectoCtrl().getTFinCtrl()
					.search(new TipoFinanciacion(bTipoFinanciacion));

			while (errorControl) {
				try {
					sEntidad = LibFrontend.leer("Actualice la entidad bancaria: ");
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
			
			System.out.println(sCuenta);
			System.out.println(sEntidad);
			System.out.println(oTipoFin);
			System.out.println(oProyecto);
			
			oFinanciacion = new Financiacion(sCuenta, sEntidad, oTipoFin, oProyecto);
		}

		if (controller.getProyectoCtrl().getFinCtrl().update(oFinanciacion) > 0) {
			ModFinanciacion = true;
		}
		return ModFinanciacion;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.getProyectoCtrl().getFinCtrl().mostrarFinanciacion());
	}
}
