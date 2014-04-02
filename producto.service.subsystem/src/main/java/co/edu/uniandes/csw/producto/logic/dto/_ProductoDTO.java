
package co.edu.uniandes.csw.producto.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ProductoDTO {

	private Long id;
	private String tipo;
	private Integer minimaCantidad;
	private String name;
	private Integer maximaCantidad;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
 
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getMinimaCantidad() {
		return minimaCantidad;
	}
 
	public void setMinimaCantidad(Integer minimacantidad) {
		this.minimaCantidad = minimacantidad;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMaximaCantidad() {
		return maximaCantidad;
	}
 
	public void setMaximaCantidad(Integer maximacantidad) {
		this.maximaCantidad = maximacantidad;
	}
	
}