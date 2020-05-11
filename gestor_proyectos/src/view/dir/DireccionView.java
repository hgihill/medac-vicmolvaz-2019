package view.dir;

import controllers.GeneralController;
import limites.LimitsDB;
import main.Main;
import medac.validaciones.LibFrontend;
import model.dir.Direccion;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public class DireccionView implements LimitsDB {

	public static void subMenuDireccion(GeneralController controller) {
		byte bOpcionSubMenu;

		do {
			bOpcionSubMenu = Main.subMenuDir(Direccion.class.getSimpleName());

			opcionGestionarDireccion(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu != 8);

	}

	public static int opcionGestionarDireccion(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;
		switch (bOpcion) {
		case 1: // Anadir direccion
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0)
				System.out.println("Direccion anadida con exito.\n");
			else
				System.out.println("No se pudo anadir la direccion.\n");
			break;
		case 2: // Eliminar direccion
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0)
				System.out.println("Direccion eliminada con exito.\n");
			else
				System.out.println("No se pudo eliminar la direccion.\n");
			break;
//		case 3: // Modificar direccion
//			iOperacionExito = modificar(controller);
//			if (iOperacionExito != 0)
//				System.out.println("Direccion actualizada con exito.\n");
//			else
//				System.out.println("No se pudo actualizar la direccion.\n");
//			break;
		case 4: // Mostrar direccion
			mostrar(controller);

		case 5: // Gestionar localidades
			LocalidadView.subMenuLocalidad(controller);
			break;
		case 6: // Gestionar provincias
			ProvinciaView.subMenuProvincia(controller);
			break;
		case 7: // Gestionar paises
			PaisView.subMenuPais(controller);
			break;
		case 8:
			System.out.println("Volviendo al menu anterior");
			break;
		default:
			System.out.println("Introduzca una opcion valida");
			break;
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
		System.out.println("4. Mostrar " + sClase);
		System.out.println("5. Volver al menu anterior\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion", 1, 5, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sCalle = null, sCp = null, sProvincia = null, sPais = null;
		byte bNum = 0, bPortal = 0, bBloque = 0;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Indique pais en que se encuentra: ");
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
				sProvincia = LibFrontend.leer("Indique la provincia en que se encuentra: ");
				if (sProvincia.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sCp = LibFrontend.leer("Indique el CP de la localidad en que se encuentra: ");
				if (sCp.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				sCalle = LibFrontend.leer("Introduzca la calle: ");
				if (sCalle.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bNum = (byte) LibFrontend.valida("Introduzca el numero: ", 0, 300, 3);
				if (bNum >= MINNUM && bNum <= MAXNUM) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bPortal = (byte) LibFrontend.valida("Introduzca el portal: ", 1, 10, 3);
				if (bPortal >= MINPORTAL && bPortal <= MAXPORTAL) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bBloque = (byte) LibFrontend.valida("Introduzca el bloque: ", 1, 10, 3);
				if (bBloque >= MINBLOQUE && bBloque <= MAXBLOQUE) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Pais oPais = new Pais(sPais);
		Provincia oProvincia = new Provincia(sProvincia, oPais);
		Localidad oLocalidad = new Localidad(sCp, oProvincia);
		Direccion oDireccion = new Direccion(sCalle, bNum, bPortal, bBloque, oLocalidad);
		return controller.getDireccionCtrl().addDireccion(oDireccion);
	}

	public static int eliminar(GeneralController controller) {
		int iRes = 0;
		iRes = controller.getDireccionCtrl()
				.removeDireccion(new Direccion(LibFrontend.leer("Introduzca el nombre de la calle a eliminar: "),
						(byte) LibFrontend.valida("Introduzca el numero de la calle a eliminar: ", 0, 300, 3)));
		return iRes;
	}

//	public static int modificar(GeneralController controller) {
//		int iRes = 0;
//		iRes = controller.direccionCtrl.getDireccionCtrl().update(
//				oDireccion, oOtra, localidadCtrl, provinciaCtrl, paisCtrl)
//	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getDireccionCtrl().mostrarDireccion());
	}
}