package controllers.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controllers.ConexionDB;
import controllers.GeneralController;
import model.project.Inventario;
import model.project.Proyecto;
import model.user.Usuario;

public class ProyectoController implements IProyectoController {

	@Override
	public int add(Proyecto oProyecto) {
		String sql = "INSERT INTO proyecto VALUES (";
		sql += "" + oProyecto.getiId_Proyecto() + ",";
		sql += "\"" + oProyecto.getoUs().getsDniCif() + "\",";
		sql += "" + oProyecto.getoInv().getiIdInv() + ",";
		sql += "\"" + oProyecto.getsNombre() + "\",";
		sql += "\"" + oProyecto.getsDescripcion() + "\")";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int remove(Proyecto oProyecto) {
		String sql = "DELETE FROM proyecto WHERE ";
		sql += "id_proyecto = " + oProyecto.getiId_Proyecto() + "";
		return ConexionDB.executeUpdate(sql);
	}

	@Override
	public int update(Proyecto oProyecto) {
		String sql = "UPDATE proyecto SET ";
		sql += "id_proyecto = " + oProyecto.getiId_Proyecto() + ",";
		sql += "dni_cif = \"" + oProyecto.getoUs().getsDniCif() + "\",";
		sql += "id_inv = " + oProyecto.getoInv().getiIdInv() + ",";
		sql += "nombre = \"" + oProyecto.getsNombre() + "\",";
		sql += "descripcion = \"" + oProyecto.getsDescripcion() + "\" WHERE ";
		sql += "id_proyecto = " + oProyecto.getiId_Proyecto() + "";
		return ConexionDB.executeUpdate(sql);
	}
	
	@Override
	public String mostrarProyecto() {
		String sResultado = "Mostrando listado de proyectos:\n";
		String sql = " SELECT * FROM proyecto";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int iIdProyecto = rs.getInt(1);
				String sDniCif = rs.getString(2);
				int iIdInv = rs.getInt(3);
				String sNombre = rs.getString(4);
				String sDescripcion = rs.getString(5);
				sResultado += iIdProyecto + " " + sDniCif + " " + iIdInv + " " + sNombre + " " + sDescripcion + "\n";
			}
			stm.close();
		} catch (SQLException ex) {

		}
		return sResultado;
	}

	@Override
	public Proyecto searchProyecto(Proyecto oObjeto, GeneralController controller) {
		Proyecto oProyecto = null;
		String sql = " SELECT * FROM proyecto WHERE id_proyecto = " + oObjeto.getiId_Proyecto() + "";
		Statement stm = null;
		try {
			stm = ConexionDB.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int iIdProyecto = rs.getInt(1);
				String sDniCif = rs.getString(2);
				int iIdInv = rs.getInt(3);
				String sNombre = rs.getString(4);
				String sDescripcion = rs.getString(5);
				Inventario oInv = controller.getProyectoCtrl().getInvCtrl().searchInventario(new Inventario(iIdInv));
				Usuario oUs = controller.getUsuarioCtrl().getUsCtrl().search(new Usuario(sDniCif));
				oProyecto = new Proyecto(iIdProyecto, sNombre, sDescripcion, oUs, oInv);
			}
			stm.close();
		} catch (SQLException ex) {
			oProyecto = null;
		}
		return oProyecto;
	}

	@Override
	public int existeProyecto(Proyecto oProyecto) {
		String sql = "SELECT COUNT (*) FROM proyecto WHERE id_proyecto = (\"" + oProyecto.getiId_Proyecto() + "\")";
		return ConexionDB.executeCount(sql);
	}
}