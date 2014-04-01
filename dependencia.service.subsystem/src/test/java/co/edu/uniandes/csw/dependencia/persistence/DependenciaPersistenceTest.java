
package co.edu.uniandes.csw.dependencia.persistence;

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
import co.edu.uniandes.csw.dependencia.persistence.api.IDependenciaPersistence;
import co.edu.uniandes.csw.dependencia.persistence.entity.DependenciaEntity;

@RunWith(Arquillian.class)
public class DependenciaPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(DependenciaPersistence.class.getPackage())
				.addPackage(DependenciaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IDependenciaPersistence dependenciaPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from DependenciaEntity").executeUpdate();
	}

	private List<DependenciaEntity> data=new ArrayList<DependenciaEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			DependenciaEntity entity=new DependenciaEntity();
			entity.setCantidadDepen(generateRandom(Integer.class));
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createDependenciaTest(){
		DependenciaDTO dto=new DependenciaDTO();
		dto.setCantidadDepen(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		
		
		DependenciaDTO result=dependenciaPersistence.createDependencia(dto);
		
		Assert.assertNotNull(result);
		
		DependenciaEntity entity=em.find(DependenciaEntity.class, result.getId());
		
		Assert.assertEquals(dto.getCantidadDepen(), entity.getCantidadDepen());	
		Assert.assertEquals(dto.getName(), entity.getName());	
	}
	
	@Test
	public void getDependenciasTest(){
		List<DependenciaDTO> list=dependenciaPersistence.getDependencias();
		Assert.assertEquals(list.size(), data.size());
        for(DependenciaDTO dto:list){
        	boolean found=false;
            for(DependenciaEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getDependenciaTest(){
		DependenciaEntity entity=data.get(0);
		DependenciaDTO dto=dependenciaPersistence.getDependencia(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getCantidadDepen(), dto.getCantidadDepen());
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteDependenciaTest(){
		DependenciaEntity entity=data.get(0);
		dependenciaPersistence.deleteDependencia(entity.getId());
        DependenciaEntity deleted=em.find(DependenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateDependenciaTest(){
		DependenciaEntity entity=data.get(0);
		
		DependenciaDTO dto=new DependenciaDTO();
		dto.setId(entity.getId());
		dto.setCantidadDepen(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		
		
		dependenciaPersistence.updateDependencia(dto);
		
		
		DependenciaEntity resp=em.find(DependenciaEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getCantidadDepen(), resp.getCantidadDepen());	
		Assert.assertEquals(dto.getName(), resp.getName());	
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