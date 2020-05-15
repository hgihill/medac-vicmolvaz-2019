package view.user;

import controllers.GeneralController;
import medac.validaciones.LibFrontend;
import model.project.Proyecto;
import model.user.Rol;
import model.user.Usuario;
import view.project.ProyectoView;

public class RolView {

	public static void subMenuRol(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = ProyectoView.subMenu(Rol.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuRol(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);
	}

	public static void opcionMenuRol(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha asignado un rol al usuario.\n");
			} else {
				System.out.println("El usuario ya tiene rol asignado.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
				System.out.println("Se ha eliminado el rol del usuario.\n");
			} else {
				System.out.println("El usuario no tiene rol asignado.\n");
			}
			break;

		case 3: // Modificar
			if (modificar(controller) == true) {
				System.out.println("Se ha modificado el rol del usuario.\n");
			} else {
				System.out.println("El usuario no tiene rol asignado.\n");
			}
			break;

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.\n");
		}
	}

	private static boolean anadir(GeneralController controller) {
		byte bRol = 0;
		String sDniCif = null;
		boolean errorControl = true, addRol = false;
		int iIdProyecto = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca dni/cif de un usuario para asignarle rol: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		errorControl = true;

		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Introduzca ID de un proyecto al que asignarle rol: ", 1, 1000,
						1);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Proyecto oProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iIdProyecto),
				controller);
		errorControl = true;

		if (oUs != null && oProyecto != null) {
			while (errorControl) {
				try {
					bRol = (byte) LibFrontend.valida("Introduzca un rol: ", 1, 3, 3);
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}

			System.out.println(bRol);
			System.out.println(oUs);
			System.out.println(oProyecto);

			Rol oRol = new Rol(bRol, oUs, oProyecto);

			if (controller.getUsuarioCtrl().getRolCtrl().existeRol(oRol) == 0) {
				if (controller.getUsuarioCtrl().getRolCtrl().add(oRol) > 0) {
					addRol = true;
				}
			}

		}
		return addRol;
	}

	private static boolean eliminar(GeneralController controller) {
		String sDniCif = null;
		boolean errorControl = true, DelRol = false;
		int iRes = 0, iIdProyecto = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca dni/cif de un usuario para eliminar rol: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		System.out.println(oUs);
		errorControl = true;

		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Introduzca ID de un proyecto al que eliminar rol: ", 1, 1000,
						1);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Proyecto oProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iIdProyecto),
				controller);
		System.out.println(oProyecto);
		errorControl = true;

		Rol oRol = new Rol(oUs, oProyecto);

		if (controller.getUsuarioCtrl().getRolCtrl().existeRol(oRol) > 0) {
			System.out.println("Entro aquí " + oRol);
			iRes = controller.getUsuarioCtrl().getRolCtrl().remove(oRol);
			if (iRes > 0) {
				DelRol = true;
			}
		}
		return DelRol;
	}

	private static boolean modificar(GeneralController controller) {
		byte bRol = 0;
		String sDniCif = null;
		boolean errorControl = true, ModRol = false;
		int iIdProyecto = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca dni/cif de un usuario para modificar rol: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		errorControl = true;

		while (errorControl) {
			try {
				iIdProyecto = (int) LibFrontend.valida("Introduzca ID de un proyecto al que modificar rol: ", 1, 1000,
						1);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Proyecto oProyecto = controller.getProyectoCtrl().getProCtrl().searchProyecto(new Proyecto(iIdProyecto),
				controller);
		errorControl = true;

		if (oUs != null && oProyecto != null) {
			while (errorControl) {
				try {
					bRol = (byte) LibFrontend.valida("Introduzca nuevo rol: ", 1, 3, 3);
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}
		Rol oRol = new Rol(bRol, oUs, oProyecto);
		if (controller.getUsuarioCtrl().getRolCtrl().existeRol(oRol) == 1) {
			System.out.println("Entra en " + oRol);
			if (controller.getUsuarioCtrl().getRolCtrl().update(oRol) > 0) {
				ModRol = true;
			}
		}
		return ModRol;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getRolCtrl().mostrarRol());
	}
}