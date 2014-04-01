
package co.edu.uniandes.csw.dependencia.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _DependenciaDTO {

	private Long id;
	private Integer cantidadDepen;
	private String name;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCantidadDepen() {
		return cantidadDepen;
	}
 
	public void setCantidadDepen(Integer cantidaddepen) {
		this.cantidadDepen = cantidaddepen;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
}