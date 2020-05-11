package view.user;

import controllers.GeneralController;
import limites.LimitsDB;
import main.Main;
import medac.validaciones.LibFrontend;
import model.dir.Direccion;
import model.user.TipoUsuario;
import model.user.Usuario;

public class UsuarioView implements LimitsDB{

	public static void subMenuUsuario(GeneralController controller) {
		byte bOpcionSubMenu;

		do {
			bOpcionSubMenu = Main.subMenuUser(Usuario.class.getSimpleName());

			opcionGestionarUsuario(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu != 8);
	}

	public static int opcionGestionarUsuario(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;
		switch (bOpcion) {
		case 1: // Anadir usuarios
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0)
				System.out.println("Usuario anadido con exito.\n");
			else
				System.out.println("No se pudo anadir el usuario.\n");
			break;
		case 2: // Eliminar usuarios
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0)
				System.out.println("Proyecto eliminado con exito.\n");
			else
				System.out.println("No se pudo eliminar el proyecto.\n");
			break;
		case 3: // Modificar usuarios
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0)
				System.out.println("Proyecto actualizado con exito.\n");
			else
				System.out.println("No se pudo actualizar el proyecto.\n");
			break;
		case 4: // Mostrar usuarios
			mostrar(controller);
			
		case 5:
			AptitudView.subMenuAptitud(controller);
			break;
		case 6:
			ConocimientoView.subMenuConocimineto(controller);
			break;
		case 7:
			RolView.subMenuRol(controller);
			break;
		case 8:
			System.out.println("Volviendo al menu anterior...");
			break;
		default:
			System.out.println("Opcion incorrecta.");
		}
		return iOperacionExito;
	}

	public static byte subMenu(String sClase) {
		byte bOpcion = 0;
		System.out.println("MENU: " + sClase);
		System.out.println("-----------------------");
		System.out.println("1. Aniadir " + sClase);
		System.out.println("2. Eliminar " + sClase);
		System.out.println("3. Modificar " + sClase);
		System.out.println("4. Buscar " + sClase);
		System.out.println("5. Volver al menu anterior.\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion.", 1, 6, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	private static int anadir(GeneralController Controller) {
		String sDniCif = null, sNombre = null, sMail = null, sTelefono = null, sContrasena = null, sCalle = null;
		byte bTipoUsuario = 0, bNum = 0;
		boolean errorControl;
		int iExito = 0;

		// Control de dni/cif
		errorControl = true;
		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del usuario que desea anadir: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control de tipo de usuario
		errorControl = true;
		while (errorControl) {
			try {
				bTipoUsuario = (byte) LibFrontend.valida("Introduzca el tipo de usuario que desea anadir: ", 1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control de nombre
		errorControl = true;
		while (errorControl) {
			try {
				sNombre = LibFrontend.leer("Introduzca el nombre del usuario que desea anadir: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control de mail
		errorControl = true;
		while (errorControl) {
			try {
				sMail = LibFrontend.leer("Introduzca el mail del usuario que desea anadir: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control de contrasena
		errorControl = true;
		while (errorControl) {
			try {
				sContrasena = LibFrontend.leer("Introduzca la contraseña del usuario que desea anadir: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control telefono
		errorControl = true;
		while (errorControl) {
			try {
				sTelefono = LibFrontend.leer("Introduzca el telefono del usuario que desea anadir: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control calle
		errorControl = true;
		while (errorControl) {
			try {
				sCalle = LibFrontend.leer("Introduzca la calle del usuario que desea anadir: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		// Control de numero de calle
		errorControl = true;
		while (errorControl) {
			try {
				bNum = (byte) LibFrontend.valida("Introduzca el numero de la calle del usuario que desea anadir: ", 0,
						200, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
			Direccion oDir = new Direccion(sCalle, bNum);
			TipoUsuario oTUs = new TipoUsuario(bTipoUsuario);
			iExito = Controller.getUsuarioCtrl()
					.addUsuario(new Usuario(sDniCif, sNombre, sMail, sTelefono, sContrasena, oTUs, oDir));
		}

		return iExito;

	}

	private static int eliminar(GeneralController Controller) {
		String sDniCif = null;
		boolean errorControl = false;
		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del ususario que desea eliminar: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		return Controller.getUsuarioCtrl().removeUsuario(new Usuario(sDniCif));
	}

	private static int modificar(GeneralController Controller) {
		String sDniCif = null, sNombre = null, sMail = null, sTelefono = null, sContrasena = null, sCalle = null;
		byte bTipoUsuario = 0, bNum = 0;
		boolean errorControl = false;
		int iExito = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del ususario cuyos datos desea modificar: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		Usuario oUs = (Usuario) Controller.getUsuarioCtrl().getUsCtrl().searchUsuario(new Usuario(sDniCif));

		if (oUs != null) {
			// Control de tipo de usuario
			errorControl = true;
			while (errorControl) {
				try {
					bTipoUsuario = (byte) LibFrontend.valida("Introduzca tipo de usuario: ", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
			}

			// Control de nombre
			errorControl = true;
			while (errorControl) {
				try {
					sNombre = LibFrontend.leer("Introduzca nombre: ");
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			oUs.setsNombre(sNombre);

			// Control de mail
			errorControl = true;
			while (errorControl) {
				try {
					sMail = LibFrontend.leer("Introduzca mail: ");
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			oUs.setsMail(sMail);

			// Control de contrasena
			errorControl = true;
			while (errorControl) {
				try {
					sContrasena = LibFrontend.leer("Introduzca contrasena: ");
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			oUs.setsContrasena(sContrasena);

			// Control telefono
			errorControl = true;
			while (errorControl) {
				try {
					sTelefono = LibFrontend.leer("Introduzca telefono: ");
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			oUs.setsTelefono(sTelefono);

			// Control calle
			errorControl = true;
			while (errorControl) {
				try {
					sCalle = LibFrontend.leer("Introduzca calle: ");
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
			}
			// Control de numero de calle
			errorControl = true;
			while (errorControl) {
				try {
					bNum = (byte) LibFrontend.valida("Introduzca numero de la calle: ", 0, 200, 3);
					errorControl = false;
				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					errorControl = false;
					System.out.println("Error generico: " + ex.getMessage());
				}
				Direccion oDir = new Direccion(sCalle, bNum);
				TipoUsuario oTUs = new TipoUsuario(bTipoUsuario);
				iExito = Controller.getUsuarioCtrl()
						.addUsuario(new Usuario(sDniCif, sNombre, sMail, sTelefono, sContrasena, oTUs, oDir));
			}
		}
		return iExito;
	}
	
	public static void mostrar(GeneralController controller) {
		System.out.println(controller.usuarioCtrl.getUsCtrl());
	}
}
