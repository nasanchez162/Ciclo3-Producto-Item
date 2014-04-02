
package co.edu.uniandes.csw.producto.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ProductoEntity {

	@Id
	@GeneratedValue(generator = "Producto")
	private Long id;
	private String tipo;
	private Integer minimaCantidad;
	private String name;
	private Integer maximaCantidad;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public Integer getMinimaCantidad(){
		return minimaCantidad;
	}
	
	public void setMinimaCantidad(Integer minimaCantidad){
		this.minimaCantidad = minimaCantidad;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Integer getMaximaCantidad(){
		return maximaCantidad;
	}
	
	public void setMaximaCantidad(Integer maximaCantidad){
		this.maximaCantidad = maximaCantidad;
	}
}