package view.dir;

import controllers.GeneralController;
import limites.LimitsDB;
import medac.validaciones.LibFrontend;
import model.dir.Localidad;
import model.dir.Pais;
import model.dir.Provincia;

public class LocalidadView implements LimitsDB {

	public static void subMenuLocalidad(GeneralController controller) {
		byte bOpcionSubMenu = 0;
		
		do {

			bOpcionSubMenu = DireccionView.subMenu(Localidad.class.getSimpleName());

			opcionGestionarLocalidad(bOpcionSubMenu, controller);

		} while (bOpcionSubMenu < 5);

	}

	public static int opcionGestionarLocalidad(byte bOpcion, GeneralController controller) {
		int iOperacionExito = 0;

		switch (bOpcion) {
		case 1: // Anadir
			iOperacionExito = anadir(controller);
			if (iOperacionExito != 0)
				System.out.println("Localidad anadida con exito.\n");
			else
				System.out.println("No se pudo anadir la localidad.\n");
			break;

		case 2: // Borrar
			iOperacionExito = eliminar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Localidad eliminada con exito.\n");
			} else {
				System.out.println("No se pudo eliminar la provincia.\n");
			}
			break;

		case 3: // Modificar
			iOperacionExito = modificar(controller);
			if (iOperacionExito != 0) {
				System.out.println("Localidad modificada con exito..\n");
			} else {
				System.out.println("No se pudo modificar la localidad.\n");
			}
			break;

		case 4: // Mostar
			mostrar(controller);

			break;

		default:
			System.out.println("Regreso al menu anterior");

		}

		return iOperacionExito;
	}

	public static int anadir(GeneralController controller) {
		boolean errorControl = true;
		String sCp = null, sLocalidad = null, sProvincia = null, sPais = null;
		int addLocalidad = 0;

		while (errorControl) {
			try {
				sPais = LibFrontend.leer("Indique en que pais se encuentra: ");
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
				sProvincia = LibFrontend.leer("Indique en que provincia se encuentra: ");
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
				sCp = LibFrontend.leer("Indique el CP de la localidad: ");
				if (sCp.length() == NUMCODPOSTAL) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
		errorControl = true;
		
		while (errorControl) {
			try {
				sLocalidad = LibFrontend.leer("Introduzca una localidad: ");
				if (sLocalidad.length() <= LIMITGENERICO) {
					errorControl = false;
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}

		Pais oPais = new Pais(sPais);
		Provincia oProvincia = new Provincia(sProvincia, oPais);
		Localidad oLocalidad = new Localidad(sCp, sLocalidad, oProvincia);
		if (controller.getDireccionCtrl().exisateLocalidad(oLocalidad) == 0) {
			System.out.println(oLocalidad);
			addLocalidad = controller.getDireccionCtrl().addLocalidad(oLocalidad);
		}
		return addLocalidad;
	}

	public static int eliminar(GeneralController c) {
		int iRes = 0;
		iRes = c.getDireccionCtrl().getLocalidadCtrl()
				.remove(new Localidad(LibFrontend.leer("Introduzca el CP de la localidad a eliminar: ")));
		return iRes;
	}
	
	public static int modificar(GeneralController c) {
		int iRes = 0;
		Localidad oLocalidad = null;
		String sCP, sNombre = "", sProvincia = "", sPais = ""; 
		
		sCP = LibFrontend.leer("Escribe el CP de la localidad a modificar: ");
		
		//Busqueda de la localiada por el cp.
		
		oLocalidad = c.getDireccionCtrl().getLocalidadCtrl().searchLocalidadByPk(new Localidad(sCP),c);
		
		if(oLocalidad != null) {
			sNombre = LibFrontend.leer("Escribe el nombre de la localidad (5 caracteres): ");
			sProvincia= LibFrontend.leer("Escribe el nombre de la provincia: ");
			sPais= LibFrontend.leer("Escribe el nombre del pais: ");
		}
		
		oLocalidad.setsNombreLoc(sNombre);
		oLocalidad.setoProv(new Provincia(sProvincia,new Pais(sPais)));
		
		
		
		iRes = c.direccionCtrl.getLocalidadCtrl().update(oLocalidad, c.direccionCtrl.getProvinciaCtrl(), c.direccionCtrl.getPaisCtrl());

		return iRes;
	}

	public static void mostrar(GeneralController controller) {
		System.out.println(controller.direccionCtrl.getLocalidadCtrl().mostrarLocalidad());
	}
}
