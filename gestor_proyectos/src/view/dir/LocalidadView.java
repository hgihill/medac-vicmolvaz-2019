package view.dir;

import controllers.GeneralController;
import medac.validaciones.LibFrontend;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;
import model.project.LimitsDB;

public class LocalidadView implements LimitsDB {

	public static void menuLocalidad(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenuLocalidad();
			switch (bOpcion) {
			case 1: // Alta

				if (alta(controller) != 0) {
					System.out.println("Se ha registrado la localidad.");
				} else {
					System.out.println("Ya existe una localidad con este nombre.");
				}

				break;
			case 2: // Modificar
				int iError = modificar(controller);
				if (iError == 0) {
					System.out.println("No se ha podido modificar la localidad.");
				} else if (iError == -1) {
					System.out.println("No existe nigunauna localidad con el codigo postal introducido.");
				} else {
					System.out.println("La localidad se ha  modificado con exito.");
				}
				break;
			case 3: // Eliminar
				if (eliminar(controller) != 0) {
					System.out.println("La localidad se ha  eliminado con exito.");
				} else {
					System.out.println("Esta localidad no existe en el sistema.");
				}
				break;
			case 4: // Busquedas
				menuBusquedasLocalidad(controller);
				break;
			default:
				System.out.println("Volviendo...");
			}

		} while (bOpcion != 5);
	}

	public static byte opcionMenuLocalidad() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de localidades");
		System.out.println("#############################");
		System.out.println("1. Alta.");
		System.out.println("2. Modificar.");
		System.out.println("3. Eliminar.");
		System.out.println("4. Busquedas.");
		System.out.println("5. Volver atras.");

		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Introduce una opcion: ", 1, 5, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		return bOpcion;
	}

	public static byte menuBusquedasLocalidad(GeneralController controller) {
		byte bOpcion = 0;
		do {
			bOpcion = opcionMenusBusquedasLocalidad();
			switch (bOpcion) {
			case 1: // Visualizar todas las localidades.

				break;
			case 2: // Visualizar localidades por provincia.

				break;
			case 3: // Visualizar provincias por pais.

				break;
			case 4: // .....

				break;
			default:
				System.out.println("Volviendo...");
			}

		} while (bOpcion != 5);
		return bOpcion;
	}

	public static byte opcionMenusBusquedasLocalidad() {
		byte bOpcion = 0;
		boolean errorControl = true;

		System.out.println("\n\nGestion de localidades");
		System.out.println("#############################");
		System.out.println("1. Visualizar todas las localidades.");
		System.out.println("2. Visualizar localidades por provincia.");
		System.out.println("3. Visualizar provincias por pais.");
		System.out.println("4. .....");
		System.out.println("5. Volver atras.");

		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Introduce una opcion: ", 1, 5, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		return bOpcion;
	}

	public static int alta(GeneralController controller) {
		boolean errorControl = true;
		String sCodigoPostal = null, sLocalidad = null, sProvincia = null, sPais = null;

		while (errorControl) {
			try {
				sCodigoPostal = LibFrontend.leer("Introduce un codigo postal: ");
				if (sCodigoPostal.length() == NUMCODPOSTAL) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}

		errorControl = true;
		while (errorControl) {
			try {
				sLocalidad = LibFrontend.leer("Introduce una localidad: ");
				if (sLocalidad.length() <= MAXCHARACTERS) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}

		errorControl = true;
		while (errorControl) {
			try {
				sProvincia = LibFrontend.leer("Introduce una provincia: ");
				if (sProvincia.length() <= MAXCHARACTERS) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}

		errorControl = true;
		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Introduce un pais: ");
				if (sPais.length() <= MAXCHARACTERS) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}

		Localidad oLocalidad = new Localidad(sCodigoPostal, sLocalidad, new Provincia(sProvincia, new Pais(sPais)));

		return controller.getAddressCtrl().addLocalidad(oLocalidad);
	}

	public static int modificar(GeneralController controller) {

		boolean errorControl = true;
		Localidad oLocalidad = null;
		String sCodigoPostal = null, sLocalidad = null, sProvincia = null, sPais = null;
		int iError = 0;

		// 1) Busco en la base de datos la localidad por su codigo postal
		while (errorControl) {
			try {
				sCodigoPostal = LibFrontend.leer("Introduce el codigo postal de una localidad que deseas modificar: ");
				if (sCodigoPostal.length() == NUMCODPOSTAL) {
					oLocalidad = controller.getAddressCtrl().getLocalidadCtrl().searchLocalidadByPk(sCodigoPostal);
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error en email: " + ex.getMessage());
			}
		}

		if (oLocalidad != null) {
			// 2) Pido el nombre, la provincia y el pais de la localidad a modificar
			errorControl = true;
			while (errorControl) {
				try {
					sLocalidad = LibFrontend
							.leer("Introduce el nombre de una localidad (" + oLocalidad.getsNombre() + "): ");
					if (sLocalidad.length() <= MAXCHARACTERS) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error en email: " + ex.getMessage());
				}
			}

			errorControl = true;
			while (errorControl) {
				try {
					sProvincia = LibFrontend
							.leer("Introduce una provincia (" + oLocalidad.getoProvincia().getsNombre() + "): ");
					if (sProvincia.length() <= MAXCHARACTERS) {

						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error en email: " + ex.getMessage());
				}
			}

			errorControl = true;
			while (errorControl) {
				try {
					sPais = LibFrontend
							.leer("Introduce un pais (" + oLocalidad.getoProvincia().getoPais().getsPais() + "): ");
					if (sPais.length() <= MAXCHARACTERS) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error en email: " + ex.getMessage());
				}
			}

			// 3) Modifico los valores nuevos
			oLocalidad.setsNombre(sLocalidad);
			oLocalidad.setoProvincia(new Provincia(sProvincia, new Pais(sPais)));

			iError = controller.getAddressCtrl().updateLocalidad(oLocalidad);

		} else {
			iError = -1;
		}

		return iError;
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sNombre = null;
		int iError = 0;

		System.out.println("¿Que deseas eliminar?");
		System.out.println("Introduce 1 para localidad");
		System.out.println("Introduce 2 para provincia");
		System.out.println("Introduce 3 para pais");
		int iOpcion = (int) LibFrontend.valida("Escriba una opcion (1-3): ", 1, 3, 1);

		if (iOpcion == 1) {
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Introduce el codigo postal de la localidad: ");
					if (sNombre.length() == NUMCODPOSTAL) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			iError = controller.getAddressCtrl().getLocalidadCtrl().remove(new Localidad(sNombre));
		} else if (iOpcion == 2) {
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Introduce el nombre de la provincia: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			iError = controller.getAddressCtrl().getProvinciaCtrl().remove(new Provincia(sNombre));
		} else {
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Introduce el nombre del pais: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			iError = controller.getAddressCtrl().getPaisCtrl().remove(new Pais(sNombre));
		}
		return iError;
	}

}