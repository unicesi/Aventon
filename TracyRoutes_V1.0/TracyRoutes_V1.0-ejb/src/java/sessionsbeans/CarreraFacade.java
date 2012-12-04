/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Carrera;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> implements CarreraFacadeLocal {
    @PersistenceContext(unitName = "ProjectoP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }

}
