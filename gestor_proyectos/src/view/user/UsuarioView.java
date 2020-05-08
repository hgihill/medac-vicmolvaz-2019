package view.user;

import controllers.GeneralController;
import main.Main;
import medac.validaciones.LibFrontend;
import model.dir.Direccion;
import model.user.TipoUsuario;
import model.user.Usuario;

public class UsuarioView {

	public static void subMenuUsuario(GeneralController Controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false, errorControl;
		int iOpcion;
		do {
			bOpcionSubMenu = Main.subMenuUser(Usuario.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					iOpcion = opcionGestionarUsuario(bOpcionSubMenu, Controller);
					errorControl = false;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}

			if (bOpcionSubMenu > 0 && bOpcionSubMenu <= 5) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.\n");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.\n");
				}
			}
		} while (bOpcionSubMenu <= 4);
	}

	public static int opcionGestionarUsuario(byte bOpcion, GeneralController Controller) {
		int iOperacionExito = 0;
		switch (bOpcion) {
		case 1: // Anadir usuario
			iOperacionExito = anadirUsuario(Controller);
			break;
		case 2: // Eliminar usuario
			iOperacionExito = eliminarUsuario(Controller);
			break;
		case 3: // Modificar usuario
			iOperacionExito = modificarUsuario(Controller);
			break;
		case 4: // Buscar usuario
			iOperacionExito = buscarUsuario(Controller);
			break;
		case 5:
			AptitudView.subMenuAptitud(Controller);
			break;
		case 6:
			AptitudView.subMenuConocimineto(Controller);
			break;
		case 7:
			AptitudView.subMenuRol(Controller);
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
		System.out.println("5. Volver al menu principal.\n");
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

	private static int anadirUsuario(GeneralController Controller) {
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

	private static int eliminarUsuario(GeneralController Controller) {
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

	private static int modificarUsuario(GeneralController Controller) {
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

	private static int buscarUsuario(GeneralController Controller) {
		String sDniCif = null;
		boolean errorControl = false;
		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif del ususario que desea buscar: ");
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				errorControl = false;
				System.out.println("Error generico: " + ex.getMessage());
			}
		}
		return Controller.getUsuarioCtrl().existeUsuario(new Usuario(sDniCif));
	}
}
