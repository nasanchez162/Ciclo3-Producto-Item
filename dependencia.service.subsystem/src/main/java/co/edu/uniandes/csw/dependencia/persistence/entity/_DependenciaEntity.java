
package co.edu.uniandes.csw.dependencia.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _DependenciaEntity {

	@Id
	@GeneratedValue(generator = "Dependencia")
	private Long id;
	private Integer cantidadDepen;
	private String name;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Integer getCantidadDepen(){
		return cantidadDepen;
	}
	
	public void setCantidadDepen(Integer cantidadDepen){
		this.cantidadDepen = cantidadDepen;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}