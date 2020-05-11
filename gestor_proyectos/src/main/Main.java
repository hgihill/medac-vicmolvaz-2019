package main;

import controllers.GeneralController;
import medac.validaciones.LibFrontend;
import view.dir.DireccionView;
import view.project.ProyectoView;
import view.user.UsuarioView;

public class Main {
	public static void main(String[] args) {
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
				ProyectoView.subMenuProyecto(Controller);
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
		System.out.println("5. Gestionar aptitud");
		System.out.println("6. Gestionar conocimiento");
		System.out.println("7. Gestionar rol");
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
		System.out.println("5. Gestionar financiacion");
		System.out.println("6. Gestionar aporte");
		System.out.println("7. Gestionar recurso");
		System.out.println("8. Gestionar inventario");
		System.out.println("9. Gestionar descuento");
		System.out.println("10. Volver al menu anterior\n");
		boolean errorControl = true;
		while (errorControl) {
			try {
				bOpcion = (byte) LibFrontend.valida("Indique una opcion: ", 1, 10, 3);
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
