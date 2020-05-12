package view.dir;

import controllers.GeneralController;
import controllers.dir.PaisController;
import controllers.dir.ProvinciaController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public class LocalidadView implements LimitsDB {

	public static void subMenuLocalidad(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = DireccionView.subMenu(Localidad.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionGestionarLocalidad(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionGestionarLocalidad(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Localidad anadida con exito.\n");
			} else {
				System.out.println("No se pudo anadir la localidad.\n");
			}
			break;

		case 2: // Borrar
			if (eliminar(controller) == true) {
				System.out.println("Localidad eliminada con exito.\n");
			} else {
				System.out.println("No se pudo eliminar la localidad.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
				System.out.println("Localidad modificada con exito.\n");
			} else {
				System.out.println("No se pudo modificar la localidad.\n");
			}
			break;

		case 4: // Mostar
			mostrar(controller);
			break;

		case 5:
			System.out.println("Regreso al menu anterior");
			break;

		default:
			System.out.println("Introduzca una opcion valida.");
		}
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addLocalidad = false;
		String sCp = null, sLocalidad = null, sProvincia = null, sPais = null;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Indique en que pais se encuentra: ");
				if (sPais.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sProvincia = LibFrontend.leer("Indique en que provincia se encuentra: ");
				if (sProvincia.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sCp = LibFrontend.leer("Indique el CP de la localidad: ");
				if (sCp.length() == NUMCODPOSTAL) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sLocalidad = LibFrontend.leer("Introduzca una localidad: ");
				if (sLocalidad.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		Pais oPais = new Pais(sPais);
		Provincia oProvincia = new Provincia(sProvincia, oPais);
		Localidad oLocalidad = new Localidad(sCp, sLocalidad, oProvincia);
		if (controller.getDireccionCtrl().existeLocalidad(oLocalidad) == 0) {
			System.out.println(oLocalidad);
			if (controller.getDireccionCtrl().addLocalidad(oLocalidad) > 0);
			addLocalidad = true;
		}
		return addLocalidad;
	}

	public static boolean eliminar(GeneralController c) {
		int iRes = 0;
		boolean DelLocalidad = false;

		iRes = c.getDireccionCtrl().getLocalidadCtrl()
				.remove(new Localidad(LibFrontend.leer("Introduzca el CP de la localidad a eliminar: ")));
		if (iRes > 0) {
			DelLocalidad = true;
		}
		return DelLocalidad;
	}

	public static boolean modificar(GeneralController c) {
		boolean errorControl = true, ModLocalidad = false;
		String sCp = null, sNombre = null, sProvincia = null, sPais = null;

		while (errorControl) {
			try {
				sCp = LibFrontend.leer("Introduzca el CP de la localidad que desee modificar: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre actualizado de la localidad: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sProvincia = LibFrontend.leer("Indique a qué provincia pertenece la localidad: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Indique a qué pais pertenece la localidad: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		PaisController paisCtrl = new PaisController();
		ProvinciaController provinciaCtrl = new ProvinciaController();
		Pais oPais = new Pais(sPais);
		Provincia oProvincia = new Provincia(sProvincia, oPais);
		Localidad oLocalidad = new Localidad(sCp, sNombre, oProvincia);
		if(c.direccionCtrl.getLocalidadCtrl().update(oLocalidad, provinciaCtrl, paisCtrl) > 0) {
			ModLocalidad = true;
		}
		return ModLocalidad;

	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getLocalidadCtrl().mostrarLocalidad());
	}
}
