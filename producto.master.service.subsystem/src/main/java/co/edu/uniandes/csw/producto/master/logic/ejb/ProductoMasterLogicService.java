package co.edu.uniandes.csw.producto.master.logic.ejb;

import co.edu.uniandes.csw.producto.master.logic.api.IProductoMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class ProductoMasterLogicService extends _ProductoMasterLogicService implements IProductoMasterLogicService {

}