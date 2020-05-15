package controllers.project;

import model.project.Aporte;
import model.project.Descuento;
import model.project.Financiacion;
import model.project.Inventario;
import model.project.Proyecto;
import model.project.Recurso;

public class ProjectGlobalController {
	private AporteController aptCtorl;
	private DescuentoController descCtrl;
	private FinanciacionController finCtrl;
	private InventarioController invCtrl;
	private ProyectoController proCtrl;
	private RecursoController recCtrl;
	private TipoRecursoController tRecCtrl;
	private TipoFinanciacionController tFinCtrl;

	public ProjectGlobalController() {
		aptCtorl = new AporteController();
		descCtrl = new DescuentoController();
		finCtrl = new FinanciacionController();
		invCtrl = new InventarioController();
		proCtrl = new ProyectoController();
		recCtrl = new RecursoController();
		tRecCtrl = new TipoRecursoController();
		tFinCtrl = new TipoFinanciacionController();
	}

	public AporteController getAptCtorl() {
		return aptCtorl;
	}

	public DescuentoController getDescCtrl() {
		return descCtrl;
	}

	public FinanciacionController getFinCtrl() {
		return finCtrl;
	}

	public InventarioController getInvCtrl() {
		return invCtrl;
	}

	public ProyectoController getProCtrl() {
		return proCtrl;
	}

	public RecursoController getRecCtrl() {
		return recCtrl;
	}

	public TipoRecursoController getTRecCtrl() {
		return tRecCtrl;
	}

	public TipoFinanciacionController getTFinCtrl() {
		return tFinCtrl;
	}

	// Aporte
	public int addAporte(Aporte oAporte) {
		return aptCtorl.add(oAporte);
	}

	public int removeAporte(Aporte oAporte) {
		return aptCtorl.remove(oAporte);
	}

	public int updateAporte(Aporte oAporte) {
		return aptCtorl.update(oAporte);
	}

	public int existeAporte(Aporte oAporte) {
		return aptCtorl.existeAporte(oAporte);
	}

	// Descuento
	public int addDescuento(Descuento oDescuento) {
		return descCtrl.add(oDescuento);
	}

	public int removeDescuento(Descuento oDescuento) {
		return descCtrl.remove(oDescuento);
	}

	public int updateDescuento(Descuento oDescuento) {
		return descCtrl.update(oDescuento);
	}

	public int existeDescuento(Descuento oDescuento) {
		return descCtrl.existeDescuento(oDescuento);
	}

	// Financiacion
	public int addFinanciacion(Financiacion oFinanciacion) {
		return finCtrl.add(oFinanciacion);
	}

	public int removeFinanciacion(Financiacion oFinanciacion) {
		return finCtrl.remove(oFinanciacion);
	}

	public int updateFinanciacion(Financiacion oFinanciacion) {
		return finCtrl.update(oFinanciacion);
	}

	public int existeFinanciacion(Financiacion oFinanciacion) {
		return finCtrl.existeFinanciacion(oFinanciacion);
	}
	
	public String mostrarFinanciacion(Financiacion oFinanciacion) {
		return finCtrl.mostrarFinanciacion();
	}

	// Inventario
	public int addInventario(Inventario oInventario) {
		return invCtrl.add(oInventario);
	}

	public int removeInventario(Inventario oInventario) {
		return invCtrl.remove(oInventario);
	}

	public int updateInventario(Inventario oInventario) {
		return invCtrl.update(oInventario);
	}

	public int existeInventario(Inventario oInventario) {
		return invCtrl.existeInventario(oInventario);
	}

	// Proyecto
	public int addProyecto(Proyecto oProyecto) {
		return proCtrl.add(oProyecto);
	}

	public int removeProyecto(Proyecto oProyecto) {
		return proCtrl.remove(oProyecto);
	}

	public int updateProyecto(Proyecto oProyecto) {
		return proCtrl.update(oProyecto);
	}

	public int existeProyecto(Proyecto oProyecto) {
		return proCtrl.existeProyecto(oProyecto);
	}

	// Recurso
	public int addRecurso(Recurso oRecurso) {
		return recCtrl.add(oRecurso);
	}

	public int removeRecurso(Recurso oRecurso) {
		return recCtrl.remove(oRecurso);
	}

	public int updateRecurso(Recurso oRecurso) {
		return recCtrl.update(oRecurso);
	}

	public int existeRecurso(Recurso oRecurso) {
		return recCtrl.existeRecurso(oRecurso);
	}

}
