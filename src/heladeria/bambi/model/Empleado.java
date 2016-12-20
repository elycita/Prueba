package heladeria.bambi.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long ci;

	private String celular;

	private String correo;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	public Empleado() {
	}

	public long getCi() {
		return this.ci;
	}

	public void setCi(long ci) {
		this.ci = ci;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}