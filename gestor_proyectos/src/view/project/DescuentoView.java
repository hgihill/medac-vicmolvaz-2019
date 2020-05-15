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
		boolean bOperacionExito = false, errorControl;

		do {

			bOpcionSubMenu = ProyectoView.subMenu(Descuento.class.getSimpleName());
			errorControl = true;
			while (errorControl) {
				try {
					opcionMenuDescuento(bOpcionSubMenu, controller);
					errorControl = false;
					bOperacionExito = true;
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (bOpcionSubMenu != 5);
	}

	public static void opcionMenuDescuento(byte bOpcion, GeneralController controller) {

		switch (bOpcion) {
		case 1: // Anadir
			if (anadir(controller) == true) {
				System.out.println("Se ha anadido el descuento.\n");
			} else {
				System.out.println("No se pudo anadir el descuento.\n");
			}
			break;

		case 2: // Eliminar
			if (eliminar(controller) == true) {
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

	}

	public static boolean anadir(GeneralController controller) {
		boolean errorControl = true, addDescuento = false;
		String sDniCif = null;
		int iIdInv = 0;
		float fPorcentaje = 0;

		while (errorControl) {
			try {
				sDniCif = LibFrontend.leer("Introduzca el dni/cif de un usuario existente: ");
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca el ID de un inventario existente: ", 1, 1000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		Inventario oInv = (Inventario) controller.getProyectoCtrl().getInvCtrl()
				.searchInventario(new Inventario(iIdInv));

		if (oUs != null && oInv != null) {
			while (errorControl) {
				try {
					fPorcentaje = (float) LibFrontend.valida("Indique el porcentaje que se va a aplicar: ", 0, 100, 2);
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}

		Descuento oDesc = new Descuento(oUs, oInv, fPorcentaje);
		if (controller.getProyectoCtrl().getDescCtrl().existeDescuento(oDesc) == 0) {
			System.out.println(oDesc);
			if (controller.getProyectoCtrl().getDescCtrl().add(oDesc) > 0) {
				addDescuento = true;
			}
		}

		return addDescuento;
	}

	public static boolean eliminar(GeneralController controller) {
		boolean errorControl = true, DelDescuento = false;
		String sDniCif = null;
		int iIdInv = 0;
		int iRes = 0;

		while (errorControl) {
			{
				try {
					sDniCif = LibFrontend.leer("Introduzca dni/cif asociado para eliminar descuento: ");
					errorControl = false;

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
		}
		errorControl = true;
		Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));

		while (errorControl) {
			try {
				iIdInv = (int) LibFrontend.valida("Introduzca ID de inventario asociado para eliminar descuento: ", 1,
						1000, 1);
				errorControl = false;

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		Inventario oInv = (Inventario) controller.getProyectoCtrl().getInvCtrl()
				.searchInventario(new Inventario(iIdInv));

		Descuento oDesc = new Descuento(oUs, oInv);
		if (controller.getProyectoCtrl().getDescCtrl().existeDescuento(oDesc) > 0) {
			iRes = controller.getProyectoCtrl().removeDescuento(oDesc);
			if (iRes > 0) {
				DelDescuento = true;
			}
		}
		return DelDescuento;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.proyectoCtrl.getDescCtrl().mostrarDescuento());
	}

}
