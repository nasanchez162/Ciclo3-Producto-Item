package co.edu.uniandes.csw.producto.master.logic.ejb;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.producto.master.logic.api.IProductoMasterLogicService;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class ProductoMasterLogicService extends _ProductoMasterLogicService implements IProductoMasterLogicService {

    public String getAmmountProduct(Long id) {
       
        List<ItemDTO> lista = super.productoMasterPersistance.getItemListForProducto(id);
        int r=0;
        for(int i=0;i<lista.size();i++)
            r+=lista.get(i).getCantidadItem().intValue();
        return r+"";
    }
    
    
    public Boolean deleteItemProductsByNumber(Long id, Integer num){
        
        List<ItemDTO> lista = super.productoMasterPersistance.getItemListForProducto(id);
        
        Integer inter = 0;
        /*for(int i=0;i<lista.size();i++)
        {
            inter = inter + lista.get(i).getCantidadItem();
        }*/
        inter = num;
        
        //Integer inter = Integer.parseInt(getAmmountProduct(id))-num;
        System.out.println("inter es : "+inter);
        if(inter<0)
            return false;
        else
        {
            //List<ItemDTO> lista = super.productoMasterPersistance.getItemListForProducto(id);
            for(int i=0;i<lista.size();i++)
            {
                for(int j=i+1;j<lista.size();j++)
                {
                    if(lista.get(j).getFechaExpiracion().compareTo(lista.get(i).getFechaExpiracion())<0)
                    {
                        ItemDTO temp = lista.get(i);
                        lista.set(i, lista.get(j));
                        lista.set(j, temp);
                    }
                }
            }
            
            boolean rta = false;
            for(int i=0;i<lista.size()&&!rta;i++)
            {
                if(inter==0)
                    rta=true;
                else
                {
                    System.out.println(i+"..............."+lista.get(i).getCantidadItem());
                    if(inter>=lista.get(i).getCantidadItem())
                    {
                        /*inter = inter - lista.get(i).getCantidadItem();
                        //super.productoMasterPersistance.deleteProductoItem(id, lista.get(i).getId());
                        
                        itemPersistance.deleteItem(lista.get(i).getId());*/
                        
                        inter = inter - lista.get(i).getCantidadItem();
                        lista.get(i).setCantidadItem(0);
                        itemPersistance.updateItem(lista.get(i));
                        
                    }
                    else
                    {
                        lista.get(i).setCantidadItem(lista.get(i).getCantidadItem()-inter);
                        inter = 0;
                        itemPersistance.updateItem(lista.get(i));
                    }
                    
                }
            }
            return rta;
        }
    }
         

}