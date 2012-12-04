/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Vehiculos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class VehiculosFacade extends AbstractFacade<Vehiculos> implements VehiculosFacadeLocal {
    @PersistenceContext(unitName = "ProjectoP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculosFacade() {
        super(Vehiculos.class);
    }

}
