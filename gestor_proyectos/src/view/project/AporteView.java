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
		do {
			bOpcionSubMenu = ProyectoView.subMenu(Aporte.class.getSimpleName());

			opcionMenuAporte(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionMenuAporte(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha anadido el aporte.\n");
			} else {
				System.out.println("No se pudo anadir el aporte.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("El aporte ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el aporte.\n");
			}
			break;

		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
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

		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
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
		errorControl = true;

		Usuario oUs = new Usuario(sDniCif);
		Financiacion oFin = new Financiacion(sCuenta);
		Aporte oAporte = new Aporte(oUs, oFin, iImporte);

		return controller.getProyectoCtrl().addAporte(oAporte);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sDniCif = null, sCuenta = null;
		int iError = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario cuyo aporte desea retirar: ");
				if (sDniCif.length() == LIMITDNI) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			errorControl = true;

			while (errorControl) {
				try {
					sCuenta = LibFrontend.leer("Introduzca la cuenta que desea desvincular: ");
					if (sCuenta.length() == NUMCUENTA) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}

				Usuario oUs = new Usuario(sDniCif);
				Financiacion oFin = new Financiacion(sCuenta);
				Aporte oAporte = new Aporte(oUs, oFin);
				iError = controller.getProyectoCtrl().removeAporte(oAporte);
			}
		}
		return iError;
	}

	public static int modificar(GeneralController controller) {
		boolean errorControl = true;
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

		while (errorControl) {
			try {
				iImporte = (int) LibFrontend.valida("Introduzca la cantidad la nueva cantidad: ", 5, 100000, 1);
				if (iImporte >= MINAPORTE && iImporte <= MAXAPORTE) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Usuario oUs = new Usuario(sDniCif);
		Financiacion oFin = new Financiacion(sCuenta);
		Aporte oAporte = new Aporte(oUs, oFin, iImporte);
		return controller.getProyectoCtrl().updateAporte(oAporte);
	}

	public static int buscar(GeneralController controller) {
		boolean errorControl = true;
		String sDniCif = null, sCuenta = null;
		byte bOpcion = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif de usuario para localizar el aporte: ");
				if (sDniCif.length() == LIMITDNI) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}

			while (errorControl) {
				try {
					sCuenta = LibFrontend.leer("Introduzca la cuenta del mismo usuario para localizar el aporte: ");
					if (sCuenta.length() == NUMCUENTA) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}

				Usuario oUs = new Usuario(sDniCif);
				Financiacion oFin = new Financiacion(sCuenta);
				Aporte oAporte = new Aporte(oUs, oFin);
				bOpcion = (byte) controller.getProyectoCtrl().existeAporte(oAporte);
			}
		}
		return bOpcion;
	}
	
	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getAptCtorl());
	}

}
