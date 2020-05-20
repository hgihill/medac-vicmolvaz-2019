package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.user.Con_X_User;
import model.user.Conocimiento;
import model.user.Usuario;

public class Con_X_User_View implements LimitsDB {
	public static void subMenuConXUser(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;

		do {
			bOpcionSubMenu = UsuarioView.subMenu(Con_X_User.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuConocimientoXUser(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);

	}

	public static void opcionMenuConocimientoXUser(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido el conocimiento.\n");
			} else {
				System.out.println("No se pudo anadir el conocimiento.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("El conocimiento ha sido eliminada.");
			} else {
				System.out.println("No se pudo eliminar el conocimiento.\n");
			}
			break;

		case 3: // Buscar
			System.out.println("Opción no contemplada, puede anadir conocimientos a usuarios o eliminarlas");
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addConXUs = false;
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
				sNombre = LibFrontend.leer("Introduzca el conocimiento que desee vincular: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		Conocimiento oCon = controller.getUsuarioCtrl().getConCtrl().search(new Conocimiento(sNombre));
		if (controller.getUsuarioCtrl().getUsCtrl().existeUsuario(oUs) == 0 && controller.getUsuarioCtrl().getConCtrl().existeConocimiento(oCon) == 0) {
			System.out.println(oUs);
			System.out.println(oCon);
			if (controller.getUsuarioCtrl().getConCtrl().add(oCon) > 0)
				;
			addConXUs = true;
		}
		return addConXUs;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelConXUs = false;
		String sNombre = null, sDniCif = null;
		int iRes = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario al que vaya a eliminar conocimientos: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del conocimiento que desea eliminiar: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		Conocimiento oCon = controller.getUsuarioCtrl().getConCtrl().search(new Conocimiento(sNombre));

		if (controller.getUsuarioCtrl().getUsCtrl().existeUsuario(oUs) > 0 && controller.getUsuarioCtrl().getConCtrl().existeConocimiento(oCon) > 0) {
			iRes = controller.getUsuarioCtrl().getConCtrl().remove(oCon);
			if (iRes > 0) {
				DelConXUs = true;
			}

		}
		return DelConXUs;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getConXUsCtrl().mostrarConocimientoDeUsuario());
	}
}
