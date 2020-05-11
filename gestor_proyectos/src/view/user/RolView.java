package view.user;

import controllers.GeneralController;
import main.Main;
import medac.validaciones.LibFrontend;
import model.project.Proyecto;
import model.project.Recurso;
import model.user.Rol;
import model.user.TipoUsuario;
import model.user.Usuario;
import view.project.ProyectoView;

public class RolView {

	public static void subMenuRol(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		do {
			bOpcionSubMenu = ProyectoView.subMenu(Recurso.class.getSimpleName());

			opcionMenuRol(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionMenuRol(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha asignado un rol al usuario.\n");
			} else {
				System.out.println("El usuario ya tiene rol asignado.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha eliminado el rol del usuario.");
			} else {
				System.out.println("El usuario no tiene rol asignado.\n");
			}
			break;

		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha modificado el rol del usuario.");
			} else {
				System.out.println("El usuario no tiene rol asignado.\\n");
			}
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}

		return iOperacionExito;
	}

	private static int anadir(GeneralController Controller) {
		byte bRol = 0;
		String sDniCif;
		boolean errorControl;
		int iIdProyecto, iExito = 0;

		// Control de usuario
		sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario al que vaya a asignar un rol: ");
		Usuario u1 = Controller.usuarioCtrl.existeUsuario(new Usuario(sDniCif));
	}

	private static int eliminar(GeneralController Controller) {
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

	private static int modificar(GeneralController Controller) {
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
	
	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getRolCtrl());
	}
}