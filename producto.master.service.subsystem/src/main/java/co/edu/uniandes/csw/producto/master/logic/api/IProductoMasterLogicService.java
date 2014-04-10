 

package co.edu.uniandes.csw.producto.master.logic.api;

public interface IProductoMasterLogicService extends _IProductoMasterLogicService {

    public String getAmmountProduct(Long id);

    
    public Boolean deleteItemProductsByNumber(Long id, Integer num);
        
}