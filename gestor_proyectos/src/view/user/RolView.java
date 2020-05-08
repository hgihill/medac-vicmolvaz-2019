package view.user;

import controllers.GeneralController;
import main.Main;
import medac.validaciones.LibFrontend;
import model.project.Proyecto;
import model.user.Rol;
import model.user.TipoUsuario;
import model.user.Usuario;

public class RolView {

	public static void subMenuRol(GeneralController Controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;
		int iOpcion;
		do {
			bOpcionSubMenu = Main.subMenu(Rol.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					iOpcion = opcionGestionarRol(bOpcionSubMenu, Controller);
					errorControl = false;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}

			if (bOpcionSubMenu > 0 && bOpcionSubMenu <= 4) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.\n");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.\n");
				}
			}
		} while (bOpcionSubMenu != 4);
	}

	public static int opcionGestionarRol(byte bOpcion, GeneralController Controller) {
		int iOperacionExito = 0;
		switch (bOpcion) {
		case 1: // Anadir Rol
			iOperacionExito = anadirRol(Controller);
			break;
		case 2: // Eliminar Rol
			iOperacionExito = eliminarRol(Controller);
			break;
		case 3: // Buscar Rol
			iOperacionExito = buscarRol(Controller);
			break;
		case 4:
			System.out.println("Volviendo al menu anterior...");
		default:
			System.out.println("Opcion incorrecta.");
		}
		return iOperacionExito;
	}

	private static int anadirRol(GeneralController Controller) {
		byte bRol = 0;
		String sDniCif;
		boolean errorControl;
		int iIdProyecto, iExito = 0;

		// Control de usuario
		sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario al que vaya a asignar un rol: ");
		Usuario u1 = Controller.usuarioCtrl.existeUsuario(new Usuario(sDniCif));
	}

	private static int eliminarRol(GeneralController Controller) {
		byte bTipoUsuario = 0;
		boolean errorControl;
		int iExito = 0;

		// Control de tipo
		errorControl = true;
		while (errorControl) {
			try {
				bTipoUsuario = (byte) LibFrontend.valida(
						"Introduzca el tipo de usuario que desea eliminar (1 - particular, 2 - empresa): ", 1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		TipoUsuario oTipoUsuario = new TipoUsuario(bTipoUsuario);
		iExito = Controller.getUsuarioCtrl().removeTipoUsuario(oTipoUsuario);
		return iExito;
	}

	private static int buscarRol(GeneralController Controller) {
		byte bTipoUsuario = 0;
		boolean errorControl;
		int iExito = 0;

		// Control de tipo
		errorControl = true;
		while (errorControl) {
			try {
				bTipoUsuario = (byte) LibFrontend.valida(
						"Introduzca el tipo de usuario que desea buscar (1 - particular, 2 - empresa): ", 1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		TipoUsuario oTipoUsuario = new TipoUsuario(bTipoUsuario);
		iExito = Controller.getUsuarioCtrl().existeTipoUsuario(oTipoUsuario);
		return iExito;
	}
}