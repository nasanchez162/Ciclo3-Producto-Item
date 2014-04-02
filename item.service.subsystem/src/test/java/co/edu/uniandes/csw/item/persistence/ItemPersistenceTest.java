
package co.edu.uniandes.csw.item.persistence;

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


import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;

@RunWith(Arquillian.class)
public class ItemPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ItemPersistence.class.getPackage())
				.addPackage(ItemEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IItemPersistence itemPersistence;

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
		em.createQuery("delete from ItemEntity").executeUpdate();
	}

	private List<ItemEntity> data=new ArrayList<ItemEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ItemEntity entity=new ItemEntity();
			entity.setCantidadItem(generateRandom(Integer.class));
			entity.setFechaExpiracion(generateRandom(Date.class));
			entity.setName(generateRandom(String.class));
			entity.setPrecio(generateRandom(Double.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createItemTest(){
		ItemDTO dto=new ItemDTO();
		dto.setCantidadItem(generateRandom(Integer.class));
		dto.setFechaExpiracion(generateRandom(Date.class));
		dto.setName(generateRandom(String.class));
		dto.setPrecio(generateRandom(Double.class));
		
		
		ItemDTO result=itemPersistence.createItem(dto);
		
		Assert.assertNotNull(result);
		
		ItemEntity entity=em.find(ItemEntity.class, result.getId());
		
		Assert.assertEquals(dto.getCantidadItem(), entity.getCantidadItem());	
		Assert.assertEquals(dto.getFechaExpiracion(), entity.getFechaExpiracion());	
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getPrecio(), entity.getPrecio());	
	}
	
	@Test
	public void getItemsTest(){
		List<ItemDTO> list=itemPersistence.getItems();
		Assert.assertEquals(list.size(), data.size());
        for(ItemDTO dto:list){
        	boolean found=false;
            for(ItemEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getItemTest(){
		ItemEntity entity=data.get(0);
		ItemDTO dto=itemPersistence.getItem(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getCantidadItem(), dto.getCantidadItem());
		Assert.assertEquals(entity.getFechaExpiracion(), dto.getFechaExpiracion());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getPrecio(), dto.getPrecio());
        
	}
	
	@Test
	public void deleteItemTest(){
		ItemEntity entity=data.get(0);
		itemPersistence.deleteItem(entity.getId());
        ItemEntity deleted=em.find(ItemEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateItemTest(){
		ItemEntity entity=data.get(0);
		
		ItemDTO dto=new ItemDTO();
		dto.setId(entity.getId());
		dto.setCantidadItem(generateRandom(Integer.class));
		dto.setFechaExpiracion(generateRandom(Date.class));
		dto.setName(generateRandom(String.class));
		dto.setPrecio(generateRandom(Double.class));
		
		
		itemPersistence.updateItem(dto);
		
		
		ItemEntity resp=em.find(ItemEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getCantidadItem(), resp.getCantidadItem());	
		Assert.assertEquals(dto.getFechaExpiracion(), resp.getFechaExpiracion());	
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getPrecio(), resp.getPrecio());	
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