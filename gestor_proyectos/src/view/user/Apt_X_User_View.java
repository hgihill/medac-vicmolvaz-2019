package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Apt_X_User;
import model.user.Aptitud;
import model.user.Usuario;

public class Apt_X_User_View implements LimitsDB {

	public static void subMenuAptXUser(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;

		do {
			bOpcionSubMenu = UsuarioView.subMenu(Apt_X_User.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuAptituXUser(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionMenuAptituXUser(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido la aptitud.\n");
			} else {
				System.out.println("No se pudo anadir la aptitud.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("La aptitud ha sido eliminada.");
			} else {
				System.out.println("No se pudo eliminar la aptitud.\n");
			}
			break;

		case 3: // Buscar
			System.out.println("Opción no contemplada, puede anadir aptitudes a usuarios o eliminarlas");
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addAptXUs = false;
		String sDniCif = null, sNombre = null;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif de un usuario: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		
		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca la aptitud que desee vincular: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		Aptitud oAptitud = controller.getUsuarioCtrl().getAptCtrl().search(new Aptitud(sNombre));
		if (controller.getUsuarioCtrl().existeUsuario(oUs) == 0 && controller.getUsuarioCtrl().existeAptitud(oAptitud) == 0) {
			System.out.println(oUs);
			System.out.println(oAptitud);
			if (controller.getUsuarioCtrl().addAptitud(oAptitud) > 0)
				;
			addAptXUs = true;
		}
		return addAptXUs;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelAptXUs = false;
		String sNombre = null, sDniCif = null;
		int iRes = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario al que vaya a eliminar aptitudes: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		
		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre de la aptitud que desea eliminiar: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		Aptitud oAptitud = controller.getUsuarioCtrl().getAptCtrl().search(new Aptitud(sNombre));
		
		if (controller.getUsuarioCtrl().getAptCtrl().ExisteAptitud(oAptitud) > 0 && controller.getUsuarioCtrl().getUsCtrl().existeUsuario(oUs) > 0) {
			iRes = controller.getUsuarioCtrl().removeAptitud(oAptitud);
			if (iRes > 0) {
				DelAptXUs = true;
			}

		}
		return DelAptXUs;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getAptXUsCtrl().mostrarAptitudDeUsuario());
	}
}
