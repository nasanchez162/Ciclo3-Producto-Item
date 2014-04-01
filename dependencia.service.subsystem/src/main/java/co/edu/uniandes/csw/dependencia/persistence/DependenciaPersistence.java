
package co.edu.uniandes.csw.dependencia.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.dependencia.persistence.api.IDependenciaPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class DependenciaPersistence extends _DependenciaPersistence  implements IDependenciaPersistence {

}