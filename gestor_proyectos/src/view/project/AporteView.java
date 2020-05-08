package view.project;


import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Aporte;
import model.project.Financiacion;
import model.user.Usuario;

public class AporteView implements LimitsDB {

	public static void menuConocimiento(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuAporte();
			switch (bOpcion) {
			case 1: // Anadir

				if (anadir(controller) != 0) {
					System.out.println("Ha realizado un aporte, gracias por su contribucion.");
				} else {
					System.out.println("El aporte ha sido previamente realizado.");
				}
				break;

			case 2: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("Se ha retirado el aporte.");
				} else {
					System.out.println("No se encuentra el aporte.");
				}
				break;
			
			case 3: // Modificar
				if (modificar(controller) != 0) {
					System.out.println("Se ha modificado el aporte.");
				} else {
					System.out.println("No se ha podido modificar el aporte.");
				}
				break;

			case 4: // Buscar
				if (buscar(controller) != 0) {
					System.out.println("Se ha encontrado el aporte.");
				} else {
					System.out.println("No se encuentra el aporte.");
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
		System.out.println("1. Anadir aporte.");
		System.out.println("2. Eliminar aporte.");
		System.out.println("3. Modificar aporte.");
		System.out.println("4. Buscar aporte.");
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
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().searchUsuario(new Usuario(sDniCif));

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
		Financiacion oFin = controller.getProyectoCtrl().getFinCtrl().searchFinanciacion(new Financiacion(sCuenta));

		if (oUs != null && oFin != null) {
			while (errorControl) {
				try {
					iImporte = (int) LibFrontend.valida("Introduzca la cantidad de un aporte que haya sido realizado: ",
							5, 100000, 1);
					if (iImporte >= MINAPORTE && iImporte <= MAXAPORTE) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
		}

		Aporte oAporte = new Aporte(oUs, oFin, iImporte);
		return controller.getProyectoCtrl().addAporte(oAporte);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sDniCif = null, sCuenta = null;
		int iError = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario cuya financiacion desea retirar: ");
				if (sDniCif.length() == LIMITDNI) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}

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
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().searchUsuario(new Usuario(sDniCif));

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
		Financiacion oFin = controller.getProyectoCtrl().getFinCtrl().searchFinanciacion(new Financiacion(sCuenta));

		if (oUs != null && oFin != null) {
			while (errorControl) {
				try {
					iImporte = (int) LibFrontend.valida("Introduzca la cantidad la nueva cantidad: ",
							5, 100000, 1);
					if (iImporte >= MINAPORTE && iImporte <= MAXAPORTE) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;
		}

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

}
