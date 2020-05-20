package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import main.Main;
import medac.validaciones.LibFrontend;
import model.dir.Direccion;
import model.user.TipoUsuario;
import model.user.Usuario;

public class UsuarioView implements LimitsDB {

	public static void subMenuUsuario(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;

		do {
			bOpcionSubMenu = Main.subMenuUser(Usuario.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionGestionarUsuario(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 8);
	}

	public static void opcionGestionarUsuario(byte bOpcion, GeneralController controller) {
		switch (bOpcion) {
		case 1: // Anadir usuarios
			if (anadir(controller) == true) {
				System.out.println("Usuario anadido con exito.\n");
			} else {
				System.out.println("No se pudo anadir el usuario.\n");
			}
			break;
		case 2: // Eliminar usuarios
			if (eliminar(controller) == true) {
				System.out.println("Usuario eliminado con exito.\n");
			} else {
				System.out.println("No se pudo eliminar el usuario.\n");
			}
			break;
		case 3: // Modificar usuarios
			if (modificar(controller) == true) {
				System.out.println("Usuario actualizado con exito.\n");
			} else {
				System.out.println("No se pudo actualizar el usuario.\n");
			}
			break;
		case 4: // Mostrar usuarios
			mostrar(controller);
			break;
		case 5:
			AptitudView.subMenuAptitud(controller);
			break;
		case 6:
			Apt_X_User_View.subMenuAptXUser(controller);
			break;
		case 7:
			ConocimientoView.subMenuConocimineto(controller);
			break;
		case 8:
			Con_X_User_View.subMenuConXUser(controller);
			break;	
		case 9:
			RolView.subMenuRol(controller);
			break;
		case 10:
			System.out.println("Volviendo al menu anterior...");
			break;
		default:
			System.out.println("Opcion incorrecta.");
		}
	}

	public static byte subMenu(String sClase) {
		byte bOpcion = 0;
		System.out.println("MENU: " + sClase);
		System.out.println("-----------------------");
		System.out.println("1. Aniadir " + sClase);
		System.out.println("2. Eliminar " + sClase);
		System.out.println("3. Modificar " + sClase);
		System.out.println("4. Buscar " + sClase);
		System.out.println("5. Volver al menu anterior\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion: ", 1, 6, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	private static boolean anadir(GeneralController controller) {
		String sDniCif = null, sNombre = null, sMail = null, sTelefono = null, sContrasena = null, sCalle = null;
		byte bTipoUsuario = 0, bNum = 0;
		boolean errorControl = true, addUsuario = false;

		// Control calle
		errorControl = true;
		while (errorControl) {
			try {
				sCalle = LibFrontend.leer("Introduzca la calle del usuario que desea anadir: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		errorControl = true;

		// Control de numero de calle
		while (errorControl) {
			try {
				bNum = (byte) LibFrontend.valida("Introduzca el numero de la calle del usuario que desea anadir: ", 0,
						300, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		Direccion oDir = controller.getDireccionCtrl().getDireccionCtrl().search(new Direccion(sCalle, bNum),
				controller);
		errorControl = true;

		// Control de tipo de usuario
		while (errorControl) {
			try {
				bTipoUsuario = (byte) LibFrontend.valida("Introduzca el tipo de usuario que desea anadir: ", 1, 2, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		TipoUsuario oTUs = controller.getUsuarioCtrl().search(new TipoUsuario(bTipoUsuario));

		if (oDir != null && oTUs != null) {
			// Control de dni/cif
			errorControl = true;

			while (errorControl) {
				try {
					sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario que desea anadir: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control de nombre
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Introduzca el nombre del usuario que desea anadir: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control de mail
			while (errorControl) {
				try {
					sMail = LibFrontend.leer("Introduzca el mail del usuario que desea anadir: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control de contrasena
			while (errorControl) {
				try {
					sContrasena = LibFrontend.leer("Introduzca la contraseña del usuario que desea anadir: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control telefono
			while (errorControl) {
				try {
					sTelefono = LibFrontend.leer("Introduzca el telefono del usuario que desea anadir: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
		}

		Usuario oUs = new Usuario(sDniCif, sNombre, sMail, sTelefono, sContrasena, oTUs, oDir);

		if (controller.getUsuarioCtrl().getUsCtrl().existeUsuario(oUs) == 0) {
			if (controller.getUsuarioCtrl().getUsCtrl().add(oUs) > 0) {
				addUsuario = true;

			}
		}
		return addUsuario;
	}

	private static boolean eliminar(GeneralController controller) {
		int iRes = 0;
		String sDniCif = null;
		boolean errorControl = true, DelUsuario = false;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del ususario que desea eliminar: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		if (controller.getUsuarioCtrl().getUsCtrl().existeUsuario(oUs) > 0) {
			iRes = controller.getUsuarioCtrl().getUsCtrl().remove(oUs);
			if (iRes > 0) {
				DelUsuario = true;
			}
		}
		return DelUsuario;
	}

	private static boolean modificar(GeneralController controller) {
		String sDniCif = null, sNombre = null, sMail = null, sTelefono = null, sContrasena = null;
		boolean errorControl = true, ModUsuario = false;

		// Control de dni/cif
		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario que desea modificar: ");
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
		errorControl = true;

		if (oUs != null) {
			// Control de nombre
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Actualice el nombre del usuario que desea modificar: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control de mail
			while (errorControl) {
				try {
					sMail = LibFrontend.leer("Actualice el mail del usuario que desea modificar: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control de contrasena
			while (errorControl) {
				try {
					sContrasena = LibFrontend.leer("Actualice la contraseña del usuario que desea modificar: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			errorControl = true;

			// Control telefono
			while (errorControl) {
				try {
					sTelefono = LibFrontend.leer("Actualice el telefono del usuario que desea modificar: ");
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			oUs.setsNombre(sNombre);
			oUs.setsMail(sMail);
			oUs.setsContrasena(sContrasena);
			oUs.setsTelefono(sTelefono);
			if (controller.getUsuarioCtrl().getUsCtrl().update(oUs) > 0) {
				ModUsuario = true;
			}
		}
		return ModUsuario;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getUsCtrl().mostrarUsuario());
	}
}
