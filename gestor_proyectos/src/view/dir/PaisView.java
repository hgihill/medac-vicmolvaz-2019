package view.dir;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.dir.Pais;

public class PaisView implements LimitsDB {
	public static void subMenuPais(GeneralController controller) {
		byte bOpcionSubMenu;
		boolean bOperacionExito = false;
		int iOpcion;
		do {
			bOpcionSubMenu = DireccionView.subMenu(Pais.class.getSimpleName());

			iOpcion = opcionGestionarPais(bOpcionSubMenu, controller);

			if (bOpcionSubMenu > 0 && bOpcionSubMenu <= 4) {
				if (bOperacionExito) {
					System.out.println("Operacion realizada con exito.\n");
				} else {
					System.out.println("ERROR: No se ha realizado la operacion.\n");
				}
			}
		} while (bOpcionSubMenu <= 4);
		if (iOpcion == 5)
			DireccionView.subMenuDireccion(controller);
	}

	public static int opcionGestionarPais(byte bOpcion, GeneralController controller) {
		int bOperacionExito = 0;
		do {
			switch (bOpcion) {
			case 1: // Anadir
				bOperacionExito = anadir(controller);
				if (bOperacionExito != 0)
					System.out.println("Pais anadido con exito.\n");
				else
					System.out.println("No se pudo anadir el pais.\n");
				bOpcion = DireccionView.subMenu(Pais.class.getSimpleName());
				break;
			case 2: // Borrar
				bOperacionExito = eliminar(controller);
				if (bOperacionExito != 0) {
					System.out.println("Pais eliminado con exito.\n");
				} else {
					System.out.println("No se pudo eliminar el pais.\n");
				}
				bOpcion = DireccionView.subMenu(Pais.class.getSimpleName());
				break;
			case 3: // Modificar
				bOperacionExito = modificar(controller);
				if (bOperacionExito != 0) {
					System.out.println("Pais modificado con exito..\n");
				} else {
					System.out.println("No se pudo modificar el pais.\n");
				}
				bOpcion = DireccionView.subMenu(Pais.class.getSimpleName());
				break;
			case 4: // Mostar
				mostrar(controller);
				bOpcion = DireccionView.subMenu(Pais.class.getSimpleName());
				break;

			default:
				System.out.println("Regreso al menu anterior");

			}

		} while (bOpcion > 0 && bOpcion < 5);

		return bOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sPais = null;
		int addPais = 0;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Introduzca un pais: ");
				if (sPais.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Pais oPais = new Pais(sPais);
		if (controller.direccionCtrl.getPaisCtrl().existePais(oPais) == 0) {
			addPais = controller.getDireccionCtrl().addPais(oPais);
		}
		return addPais;
	}

	public static int eliminar(GeneralController c) {
		int iRes = 0;

		iRes = c.getDireccionCtrl().getPaisCtrl()
				.remove(new Pais(LibFrontend.leer("Introduzca el nombre del pais a eliminar: ")));

		return iRes;
	}

	public static int modificar(GeneralController c) {
		int iRes = 0;
		Pais oPais = new Pais(LibFrontend.leer("Introduzca el nombre el pais que desee modificar:"));
		if (c.getDireccionCtrl().getPaisCtrl().existePais(oPais) != 0) {
			iRes = c.direccionCtrl.getPaisCtrl().Update(oPais,
					new Pais(LibFrontend.leer("Introduzca el nuevo nombre del pais: ")));
		}
		return iRes;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getPaisCtrl().mostrarPais());
	}
}
