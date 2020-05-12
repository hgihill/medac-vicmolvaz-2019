package view.dir;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.dir.Pais;

public class PaisView implements LimitsDB {
	public static void subMenuPais(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;
		do {
			bOpcionSubMenu = DireccionView.subMenu(Pais.class.getSimpleName());
			errorControl = true;
			while(errorControl) {
				try {
					opcionGestionarPais(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				}catch(NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);
		
	}

	public static void opcionGestionarPais(byte bOpcion, GeneralController controller) {
			switch (bOpcion) {
			case 1: // Anadir
				
				if (anadir(controller) == true) {
					System.out.println("Pais anadido con exito.\n");
				}else {
					System.out.println("No se pudo anadir el pais.\n");
				}
				break;
			case 2: // Borrar
				if (eliminar(controller) == true) {
					System.out.println("Pais eliminado con exito.\n");
				} else {
					System.out.println("No se pudo eliminar el pais.\n");
				}
				break;
			case 3: // Modificar
				System.out.println("Opcion no disponible.");
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
		boolean errorControl = true, addPais = false;
		String sPais = null;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Introduzca un pais: ");
				if (sPais.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Pais oPais = new Pais(sPais);
		if (controller.direccionCtrl.getPaisCtrl().existePais(oPais) == 0) {
			if(controller.getDireccionCtrl().addPais(oPais) > 0) {
				addPais = true;
			}
		}
		return addPais;
	}

	public static boolean eliminar(GeneralController c) {
		int iRes = 0;
		boolean DelPais = false;

		iRes = c.getDireccionCtrl().getPaisCtrl()
				.remove(new Pais(LibFrontend.leer("Introduzca el nombre del pais a eliminar: ")));
		if(iRes > 0) {
			DelPais = true;
		}
		return DelPais;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getPaisCtrl().mostrarPais());
	}
}
