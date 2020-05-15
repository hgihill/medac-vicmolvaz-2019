package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Aporte;
import model.project.Financiacion;
import model.user.Usuario;

public class AporteView implements LimitsDB {

	public static void subMenuAporte(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = ProyectoView.subMenu(Aporte.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuAporte(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);
	}

	public static void opcionMenuAporte(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido el aporte.\n");
			} else {
				System.out.println("No se pudo anadir el aporte.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("El aporte ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el aporte.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
				System.out.println("Se ha modificado el aporte.");
			} else {
				System.out.println("No se pudo modificar el aporte.\n");
			}
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addAporte = false;
		int iImporte = 0;
		String sDniCif = null, sCuenta = null;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni de un usuario existente: ");
				errorControl = false;
				
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Introduzca una cuenta bancaria existente: ");
				errorControl = false;
				
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		
		Financiacion oFin = controller.getProyectoCtrl().getFinCtrl().search(new Financiacion(sCuenta), controller);

		if (oUs != null && oFin != null) {
			errorControl = true;
			while (errorControl) {
				try {
					iImporte = (int) LibFrontend.valida("Introduzca el importe desee aportar: ", 5, 100000, 1);
					if (iImporte >= MINAPORTE && iImporte <= MAXAPORTE) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}

		Aporte oAporte = new Aporte(oUs, oFin, iImporte);
		if (controller.getProyectoCtrl().existeAporte(oAporte) == 0) {
			System.out.println(oAporte);
			if (controller.getProyectoCtrl().addAporte(oAporte) > 0) {
				addAporte = true;
			}
				
		}
		return addAporte;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelAporte = false;
		String sDniCif = null, sCuenta = null;
		int iRes = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario cuyo aporte desea retirar: ");
				if (sDniCif.length() == LIMITDNI) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Introduzca la cuenta que desea desvincular: ");
				if (sCuenta.length() == NUMCUENTA) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Financiacion oFin = controller.getProyectoCtrl().getFinCtrl().search(new Financiacion(sCuenta), controller);

		Aporte oAporte = new Aporte(oUs, oFin);
		System.out.println("\n--------------------------" + oAporte);
		if (controller.getProyectoCtrl().existeAporte(oAporte) > 0) {
			iRes = controller.getProyectoCtrl().getAptCtorl().remove(oAporte);
			if (iRes > 0) {
				DelAporte = true;
			}

		}
		return DelAporte;
	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModAporte = false;
		int iImporte = 0;
		String sDniCif = null, sCuenta = null;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni de un usuario existente: ");
				if (sDniCif.length() == LIMITDNI) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));

		while (errorControl) {
			try {
				sCuenta = LibFrontend.leer("Introduzca una cuenta bancaria existente: ");
				if (sCuenta.length() == NUMCUENTA) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Financiacion oFin = controller.getProyectoCtrl().getFinCtrl().search(new Financiacion(sCuenta), controller);

		if (oUs != null && oFin != null) {
			while (errorControl) {
				try {
					iImporte = (int) LibFrontend.valida("Introduzca el importe desee aportar: ", 5, 100000, 1);
					if (iImporte >= MINAPORTE && iImporte <= MAXAPORTE) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}

		Aporte oAporte = new Aporte(oUs, oFin, iImporte);
		if (controller.getProyectoCtrl().getAptCtorl().update(oAporte) > 0) {
			ModAporte = true;
		}
		return ModAporte;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getAptCtorl().mostrarAporte());
	}

}
