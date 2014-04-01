
package co.edu.uniandes.csw.item.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ItemEntity {

	@Id
	@GeneratedValue(generator = "Item")
	private Long id;
	private Integer cantidadItem;
	@Temporal(TemporalType.DATE)
	private Date fechaExpiracion;
	private String name;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Integer getCantidadItem(){
		return cantidadItem;
	}
	
	public void setCantidadItem(Integer cantidadItem){
		this.cantidadItem = cantidadItem;
	}
	public Date getFechaExpiracion(){
		return fechaExpiracion;
	}
	
	public void setFechaExpiracion(Date fechaExpiracion){
		this.fechaExpiracion = fechaExpiracion;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}