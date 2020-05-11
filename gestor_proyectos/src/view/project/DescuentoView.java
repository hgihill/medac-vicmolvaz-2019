package view.project;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.project.Descuento;
import model.project.Inventario;
import model.user.Usuario;

public class DescuentoView implements LimitsDB {

	public static void subMenuDescuento(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		do {
			bOpcionSubMenu = ProyectoView.subMenu(Descuento.class.getSimpleName());

			opcionMenuDescuento(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionMenuDescuento(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0) {
				System.out.println("Se ha anadido el descuento.\n");
			} else {
				System.out.println("No se pudo anadir el descuento.\n");
			}
			break;

		case 2: // Eliminar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("El descuento ha sido eliminado.");
			} else {
				System.out.println("No se pudo eliminar el descuento.\n");
			}
			break;

		case 3: // Modificar
			System.out.println("Opción no contemplada, elimine el decuento para generar uno nuevo.");

		case 4: // Mostrar
			mostrar(controller);

		default:
			System.out.println("Regreso al menu anterior.");
		}

		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sDniCif = null;
		int iIdInv = 0;
		float fPorcentaje = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif de un usuario existente: ");
				if (sDniCif.length() == LIMITDNI) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID de un inventario existente: ", 1, 1000, 1);
				if (iIdInv >= MINCANT && iIdInv <= MAXCANT) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		while (errorControl) {
			try {
				fPorcentaje = (byte) LibFrontend.valida("Indique el porcentaje que se va a aplicar: ", 0, 100, 4);
				if (fPorcentaje >= MINPORC && fPorcentaje <= MAXPORC) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;

		Usuario oUs = new Usuario(sDniCif);
		Inventario oInv = new Inventario(iIdInv);
		Descuento oDesc = new Descuento(oUs, oInv, fPorcentaje);

		return controller.getProyectoCtrl().addDescuento(oDesc);
	}

	public static int eliminar(GeneralController controller) {
		boolean errorControl = true;
		String sDniCif = null;
		int iIdInv = 0;
		int iError = 0;

		while (errorControl) {
			{
				try {
					sDniCif = LibFrontend.leer("Introduzca un dni/cif para dejar de aplciarle descuento: ");
					if (sDniCif.length() == LIMITDNI) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			errorControl = true;

			while (errorControl) {
				try {
					iIdInv = (int) LibFrontend.valida("Introduzca el ID para dejar de aplciarle descuento: ", 1, 1000,
							1);
					if (iIdInv >= MINCANT && iIdInv <= MAXCANT) {
						errorControl = false;
					}
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}

			Usuario oUs = new Usuario(sDniCif);
			Inventario oInv = new Inventario(iIdInv);
			Descuento oDesc = new Descuento(oUs, oInv);
			iError = controller.getProyectoCtrl().removeDescuento(oDesc);
		}
		return iError;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getRecCtrl());
	}

}
