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
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = Main.subMenuDir(Direccion.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionGestionarDireccion(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 8);

	}

	public static void opcionGestionarDireccion(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir direccion
			if (anadir(controller) == true)
				System.out.println("Direccion anadida con exito.\n");
			else
				System.out.println("No se pudo anadir la direccion.\n");
			break;
		case 2: // Eliminar direccion
			if (eliminar(controller) == true)
				System.out.println("Direccion eliminada con exito.\n");
			else
				System.out.println("No se pudo eliminar la direccion.\n");
			break;
		case 3: // Modificar direccion
			if (modificar(controller) == true)
				System.out.println("Direccion actualizada con exito.\n");
			else
				System.out.println("No se pudo actualizar la direccion.\n");
			break;
		case 4: // Mostrar direccion
			if (mostrar(controller) == true) {
				System.out
						.println("La direccion es: " + controller.direccionCtrl.getDireccionCtrl().mostrarDireccion());
			} else {
				System.out.println("No se ha podido mostrar.");
			}
			break;
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
				bOpcion = (byte) LibFrontend.valida("Indique una opcion: ", 1, 5, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addDireccion = false;
		String sCalle = null, sCp = null, sProvincia = null, sPais = null;
		byte bNum = 0, bPortal = 0, bBloque = 0;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Indique pais en que se encuentra: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		controller.getDireccionCtrl().getPaisCtrl().add(new Pais(sPais));
		Pais oPais = controller.getDireccionCtrl().getPaisCtrl().searchPais(new Pais(sPais));
		errorControl = true;

		while (errorControl) {
			try {
				sProvincia = LibFrontend.leer("Indique la provincia en que se encuentra: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		controller.getDireccionCtrl().getProvinciaCtrl().add(new Provincia(sProvincia, oPais));
		Provincia oProvincia = controller.getDireccionCtrl().getProvinciaCtrl()
				.searchProvincia(new Provincia(sProvincia), controller);
		errorControl = true;

		while (errorControl) {
			try {
				sCp = LibFrontend.leer("Indique el CP de la localidad en que se encuentra: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		controller.getDireccionCtrl().getLocalidadCtrl().add(new Localidad(sCp, oProvincia));
		Localidad oLocalidad = controller.getDireccionCtrl().getLocalidadCtrl().searchLocalidadByPk(new Localidad(sCp),
				controller);
		errorControl = true;

		while (errorControl) {
			try {
				sCalle = LibFrontend.leer("Introduzca la calle: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bNum = (byte) LibFrontend.valida("Introduzca el numero: ", 0, 300, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bPortal = (byte) LibFrontend.valida("Introduzca el portal (0 si no tiene portal): ", 0, 10, 3);
				errorControl = false;
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bBloque = (byte) LibFrontend.valida("Introduzca el bloque (0 si no tiene bloque): ", 0, 10, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Direccion oDireccion = new Direccion(sCalle, bNum, bPortal, bBloque, oLocalidad);
		if (controller.getDireccionCtrl().getDireccionCtrl().existeDireccion(oDireccion) == 0) {
			System.out.println(oDireccion);
			if (controller.getDireccionCtrl().getDireccionCtrl().add(oDireccion) > 0)
				;
			addDireccion = true;
		}
		return addDireccion;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelDireccion = false;
		String sCalle = null;
		byte bNum = 0;
		int iRes = 0;

		errorControl = true;
		while (errorControl) {
			try {
				sCalle = LibFrontend.leer("Introduzca la calle: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bNum = (byte) LibFrontend.valida("Introduzca el numero: ", 0, 300, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Direccion oDir = new Direccion(sCalle, bNum);
		if (controller.getDireccionCtrl().getDireccionCtrl().existeDireccion(oDir) > 0) {
			iRes = controller.getDireccionCtrl().getDireccionCtrl().remove(oDir);
			if (iRes > 0) {
				DelDireccion = true;
			}
		}
		return DelDireccion;
	}

	public static boolean modificar(GeneralController controller) {
		boolean errorControl = true, ModDireccion = false;
		String sCalle = null;
		byte bNum = 0, bPortal = 0, bBloque = 0;

		while (errorControl) {
			try {
				sCalle = LibFrontend.leer("Introduzca la calle: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				bNum = (byte) LibFrontend.valida("Introduzca el numero: ", 0, 300, 3);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Direccion oDir = controller.getDireccionCtrl().getDireccionCtrl().search(new Direccion(sCalle, bNum),
				controller);

		if (oDir != null) {
			while (errorControl) {
				try {
					bPortal = (byte) LibFrontend.valida("Actualice el portal: ", 0, 10, 3);
					errorControl = false;
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					bBloque = (byte) LibFrontend.valida("Actualice el bloque: ", 0, 10, 3);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}
		errorControl = true;

		Direccion obDir = controller.getDireccionCtrl().getDireccionCtrl().search(new Direccion (sCalle, bNum), controller);
		obDir.setbPortal(bPortal);
		obDir.setbBloque(bBloque);
		if (controller.direccionCtrl.getDireccionCtrl().update(obDir) > 0) {
			ModDireccion = true;
		}
		return ModDireccion;
	}

	public static boolean mostrar(GeneralController controller) {
		boolean bMostrar = false;
		if (controller.direccionCtrl.getDireccionCtrl().mostrarDireccion() != null) {
			bMostrar = true;
		}
		return bMostrar;
	}
}