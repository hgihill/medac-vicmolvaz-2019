package view.dir;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.dir.Pais;
import model.dir.Provincia;

public class ProvinciaView implements LimitsDB {
	
	public static void subMenuProvincia(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;
		do {
			bOpcionSubMenu = DireccionView.subMenu(Provincia.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionGestionarProvincia(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				}catch(NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionGestionarProvincia(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Provincia anadida con exito.\n");
			}else {
				System.out.println("No se pudo anadir la provincia.\n");
			}
			break;
			
		case 2: // Borrar
			if (eliminar(controller) == true) {
				System.out.println("Provincia eliminada con exito.\n");
			} else {
				System.out.println("No se pudo eliminar la provincia.\n");
			}
			break;
			
		case 3: // Modificar
			System.out.println("Opcion no disponible, puede crear o eliminar provincias.");
			
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
		boolean errorControl = true, addProvincia = false;
		String sProvincia = null, sPais = null;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Introduzca el pais al que pertenezca la provincia: ");
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
				sProvincia = LibFrontend.leer("Introduzca una provincia: ");
				if (sProvincia.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		Pais oPais = new Pais(sPais);
		Provincia oProvincia = new Provincia(sProvincia, oPais);
		if (controller.getDireccionCtrl().existeProvincia(oProvincia) == 0) {
			System.out.println(oProvincia);
			if (controller.getDireccionCtrl().addProvincia(oProvincia) > 0);
			addProvincia = true;
		}
		return addProvincia;
	}

	public static boolean eliminar(GeneralController c) {
		int iRes = 0;
		boolean DelProvincia = false;

		iRes = c.getDireccionCtrl().getProvinciaCtrl()
				.remove(new Provincia(LibFrontend.leer("Introduzca el nombre de la provincia a eliminar: ")));
		if(iRes > 0) {
			DelProvincia = true;
		}
		return DelProvincia;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getProvinciaCtrl().mostrarProvincia());
	}
}
