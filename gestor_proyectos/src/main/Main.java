package main;

import controllers.GeneralController;
import medac.validaciones.LibFrontend;
import view.dir.DireccionView;
import view.user.UsuarioView;

public class Main {
	public static void main(String[] args) {
		boolean errorControl;
		byte bOpcionMenuPrincipal = 0;

		String sDatabase = "victor_proyecto_prog";

		GeneralController Controller = new GeneralController(sDatabase);

		do {

			bOpcionMenuPrincipal = menuPrincipal();
			switch (bOpcionMenuPrincipal) {
			case 1: // Gestion de usuarios
				UsuarioView.subMenuUsuario(Controller);
				break;
			case 2: // Gestion de proyectos
				// vistaProyecto.subMenuProyecto(Controller);
				break;
			case 3: // Gestion de direcciones
				DireccionView.subMenuDireccion(Controller);
				break;
			case 4:
				System.out.println("Fin del programa");
				break;
			default:
				System.out.println("Operacion incorrecta");
			}
		} while (bOpcionMenuPrincipal != 4);
	}

	public static byte menuPrincipal() {
		byte bOpcion = 0;
		System.out.println("############################################");
		System.out.println("MENU PRINCIPAL");
		System.out.println("--------------------------------------------");
		System.out.println("¿Que desea gestionar?");
		System.out.println("1.Usuario");
		System.out.println("2.Proyecto");
		System.out.println("3.Direccion");
		System.out.println("4.Salir");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("\nElija una opcion: ", 1, 4, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	public static byte subMenuUser(String sClase) {
		byte bOpcion = 0;
		System.out.println("MENU: " + sClase);
		System.out.println("-----------------------");
		System.out.println("1. Aniadir " + sClase);
		System.out.println("2. Eliminar " + sClase);
		System.out.println("3. Modificar " + sClase);
		System.out.println("4. Buscar " + sClase);
		System.out.println("5. Aptitud");
		System.out.println("6. Conocimiento");
		System.out.println("7. Rol");
		System.out.println("8. Volver al menu anteior\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion: ", 1, 8, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

	public static byte subMenuProject(String sClase) {
		byte bOpcion = 0;
		System.out.println("MENU: " + sClase);
		System.out.println("-----------------------");
		System.out.println("1. Aniadir " + sClase);
		System.out.println("2. Eliminar " + sClase);
		System.out.println("3. Modificar " + sClase);
		System.out.println("4. Buscar " + sClase);
		System.out.println("5. Financiacion");
		System.out.println("6. Aporte");
		System.out.println("7. Tipo de recurso");
		System.out.println("8. Recurso");
		System.out.println("9. Inventario");
		System.out.println("10. Descuento");
		System.out.println("11. Volver al menu anteior\n");
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

	public static byte subMenuDir(String sClase) {
		byte bOpcion = 0;
		System.out.println("MENU: " + sClase);
		System.out.println("-----------------------");
		System.out.println("1. Aniadir " + sClase);
		System.out.println("2. Eliminar " + sClase);
		System.out.println("3. Modificar " + sClase);
		System.out.println("4. Buscar " + sClase);
		System.out.println("5. Gestionar Localidad");
		System.out.println("6. Gestionar Provincia");
		System.out.println("7. Gestionar Pais");
		System.out.println("8. Volver al menu anteior\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion: ", 1, 8, 3);
				errorControl = false;
			} catch (NumberFormatException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return bOpcion;
	}

}
