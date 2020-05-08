package controllers.project;

import model.project.Aporte;
import model.project.Descuento;
import model.project.Financiacion;
import model.project.Inventario;
import model.project.Proyecto;
import model.project.Recurso;
import model.project.TipoFinanciacion;
import model.project.TipoRecurso;

public class ProjectGlobalController {
	private AporteController aptCtorl;
	private DescuentoController descCtrl;
	private FinanciacionController finCtrl;
	private InventarioController invCtrl;
	private ProyectoController proCtrl;
	private RecursoController recCtrl;
	private TipoFinanciacionController tFiCtrl;
	private TipoRecursoController tRecCtrl;

	public ProjectGlobalController() {
		aptCtorl = new AporteController();
		descCtrl = new DescuentoController();
		finCtrl = new FinanciacionController();
		invCtrl = new InventarioController();
		proCtrl = new ProyectoController();
		recCtrl = new RecursoController();
		tFiCtrl = new TipoFinanciacionController();
		tRecCtrl = new TipoRecursoController();
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

	public TipoFinanciacionController gettFiCtrl() {
		return tFiCtrl;
	}

	public TipoRecursoController gettRecCtrl() {
		return tRecCtrl;
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

	// Tipo Financiacion
	public int addTipoFinanciacion(TipoFinanciacion oTipoFinanciacion) {
		return tFiCtrl.add(oTipoFinanciacion);
	}

	public int removeTipoFinanciacion(TipoFinanciacion oTipoFinanciacion) {
		return tFiCtrl.remove(oTipoFinanciacion);
	}

	public int existeTipoFinanciacion(TipoFinanciacion oTipoFinanciacion) {
		return tFiCtrl.existeTipoFinanciacion(oTipoFinanciacion);
	}

	// Tipo Recurso
	public int addTipoRecurso(TipoRecurso oTipoRecurso) {
		return tRecCtrl.add(oTipoRecurso);
	}

	public int removeTipoRecurso(TipoRecurso oTipoRecurso) {
		return tRecCtrl.remove(oTipoRecurso);
	}

	public int existeTipoRecurso(TipoRecurso oTipoRecurso) {
		return tRecCtrl.existeTipoRecurso(oTipoRecurso);
	}

}
