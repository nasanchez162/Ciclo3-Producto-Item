
package co.edu.uniandes.csw.dependencia.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.dependencia.logic.dto.DependenciaDTO;
import co.edu.uniandes.csw.dependencia.logic.api.IDependenciaLogicService;
import co.edu.uniandes.csw.dependencia.persistence.DependenciaPersistence;
import co.edu.uniandes.csw.dependencia.persistence.api.IDependenciaPersistence;
import co.edu.uniandes.csw.dependencia.persistence.entity.DependenciaEntity;

@RunWith(Arquillian.class)
public class DependenciaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(DependenciaLogicService.class.getPackage())
				.addPackage(DependenciaPersistence.class.getPackage())
				.addPackage(DependenciaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IDependenciaLogicService dependenciaLogicService;
	
	@Inject
	private IDependenciaPersistence dependenciaPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<DependenciaDTO> dtos=dependenciaPersistence.getDependencias();
		for(DependenciaDTO dto:dtos){
			dependenciaPersistence.deleteDependencia(dto.getId());
		}
	}

	private List<DependenciaDTO> data=new ArrayList<DependenciaDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			DependenciaDTO pdto=new DependenciaDTO();
			pdto.setCantidadDepen(generateRandom(Integer.class));
			pdto.setName(generateRandom(String.class));
			pdto=dependenciaPersistence.createDependencia(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createDependenciaTest(){
		DependenciaDTO ldto=new DependenciaDTO();
		ldto.setCantidadDepen(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		
		
		DependenciaDTO result=dependenciaLogicService.createDependencia(ldto);
		
		Assert.assertNotNull(result);
		
		DependenciaDTO pdto=dependenciaPersistence.getDependencia(result.getId());
		
		Assert.assertEquals(ldto.getCantidadDepen(), pdto.getCantidadDepen());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getDependenciasTest(){
		List<DependenciaDTO> list=dependenciaLogicService.getDependencias();
		Assert.assertEquals(list.size(), data.size());
        for(DependenciaDTO ldto:list){
        	boolean found=false;
            for(DependenciaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getDependenciaTest(){
		DependenciaDTO pdto=data.get(0);
		DependenciaDTO ldto=dependenciaLogicService.getDependencia(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getCantidadDepen(), ldto.getCantidadDepen());
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteDependenciaTest(){
		DependenciaDTO pdto=data.get(0);
		dependenciaLogicService.deleteDependencia(pdto.getId());
        DependenciaDTO deleted=dependenciaPersistence.getDependencia(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateDependenciaTest(){
		DependenciaDTO pdto=data.get(0);
		
		DependenciaDTO ldto=new DependenciaDTO();
		ldto.setId(pdto.getId());
		ldto.setCantidadDepen(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		
		
		dependenciaLogicService.updateDependencia(ldto);
		
		
		DependenciaDTO resp=dependenciaPersistence.getDependencia(pdto.getId());
		
		Assert.assertEquals(ldto.getCantidadDepen(), resp.getCantidadDepen());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}