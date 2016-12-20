package heladeria.bambi.bean;

import heladeria.bambi.business.GestionEmpleadoBL;
import heladeria.bambi.model.Empleado;
import heladeria.bambi.util.SysMessage;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class GestionEmpleadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestionEmpleadoBL bl;

	private List<Empleado> lst;
	private Empleado selected;
	private Empleado entidad;
	private boolean nuevo;
	private boolean visibleNuevoEditar;

	@PostConstruct
	public void init() {
		cargarLst();
		nuevo();
	}

	public void nuevo() {
		entidad = new Empleado();
		nuevo = true;
		visibleNuevoEditar = false;
	}

	public void nuevaEntidad() {
		nuevo();
		visibleNuevoEditar = true;
	}

	@SuppressWarnings("unchecked")
	private void cargarLst() {
		try {
			lst = bl.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar() {
		if (entidad != null) {

			String validacion = bl.validar(entidad, nuevo);
			if (validacion.trim().isEmpty()) {
				if (nuevo) {
					try {
						bl.save(entidad);
						SysMessage.info("Guardado correctamente.");
						nuevo();
						cargarLst();
						visibleNuevoEditar = true;
					} catch (Exception e) {
						SysMessage.error("Fallo al guardar.");
					}
				} else {
					try {
						bl.update(entidad);
						SysMessage.info("Guardado correctamente.");
						nuevo();
						cargarLst();
						visibleNuevoEditar = false;
					} catch (Exception e) {
						SysMessage.error("Fallo al guardar.");
					}
				}
			} else {
				SysMessage.warn("Se encontro observaciones al intentar guardar: " + validacion);
			}
		}
	}

	public void editar() {
		if (selected != null) {
			entidad = selected;
			nuevo = false;
			visibleNuevoEditar = true;
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void editar(Empleado obj) {
		selected = obj;
		editar();
	}

	public void eliminar() {
		if (selected != null) {
			try {
				bl.remove(selected);
				SysMessage.info("Eliminado correctamente.");
				cargarLst();
			} catch (Exception e) {
				SysMessage.info("Fallo al eliminar.");
			}
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void eliminar(Empleado obj) {
		selected = obj;
		eliminar();
	}

	public List<Empleado> getLst() {
		return lst;
	}

	public void setLst(List<Empleado> lst) {
		this.lst = lst;
	}

	public Empleado getSelected() {
		return selected;
	}

	public void setSelected(Empleado selected) {
		this.selected = selected;
	}

	public Empleado getEntidad() {
		return entidad;
	}

	public void setEntidad(Empleado entidad) {
		this.entidad = entidad;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isVisibleNuevoEditar() {
		return visibleNuevoEditar;
	}

	public void setVisibleNuevoEditar(boolean visibleNuevoEditar) {
		this.visibleNuevoEditar = visibleNuevoEditar;
	}

}
