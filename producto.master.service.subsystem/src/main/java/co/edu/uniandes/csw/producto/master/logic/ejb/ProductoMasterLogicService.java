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

}