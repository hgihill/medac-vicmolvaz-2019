package view.dir;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.dir.Pais;
import model.dir.Provincia;

public class ProvinciaView implements LimitsDB {

	public static void subMenuProvincia(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		do {

			bOpcionSubMenu = DireccionView.subMenu(Provincia.class.getSimpleName());

			opcionGestionarProvincia(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionGestionarProvincia(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0)
				System.out.println("Provincia anadida con exito.\n");
			else
				System.out.println("No se pudo anadir la provincia.\n");
			break;
			
		case 2: // Borrar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Provincia eliminada con exito.\n");
			} else {
				System.out.println("No se pudo eliminar la provincia.\n");
			}
			break;
			
		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Provincia modificada con exito..\n");
			} else {
				System.out.println("No se pudo modificar la provincia.\n");
			}
			break;
			
		case 4: // Mostar
			mostrar(controller);

			break;

		default:
			System.out.println("Regreso al menu anterior");

		}

		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sProvincia = null, sPais = null;
		int addProvincia = 0;

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
			addProvincia = controller.getDireccionCtrl().addProvincia(oProvincia);
		}
		return addProvincia;
	}

	public static int eliminar(GeneralController c) {
		int iRes = 0;

		iRes = c.getDireccionCtrl().getProvinciaCtrl()
				.remove(new Provincia(LibFrontend.leer("Introduzca el nombre de la provincia a eliminar: ")));
		return iRes;
	}

	public static int modificar(GeneralController c) {
		int iRes = 0;
		iRes = c.direccionCtrl.getProvinciaCtrl().Update(
				new Provincia(LibFrontend.leer("Introduzca el nombre el provincia que desee modificar: ")),
				new Provincia(LibFrontend.leer("Introduzca el nuevo nombre del Provincia: ")),
				c.direccionCtrl.getPaisCtrl());

		return iRes;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getProvinciaCtrl().mostrarProvincia());
	}
}
