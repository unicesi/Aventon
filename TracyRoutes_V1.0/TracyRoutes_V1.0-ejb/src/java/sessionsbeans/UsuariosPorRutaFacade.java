/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.UsuariosPorRuta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class UsuariosPorRutaFacade extends AbstractFacade<UsuariosPorRuta> implements UsuariosPorRutaFacadeLocal {
    @PersistenceContext(unitName = "ProjectoP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosPorRutaFacade() {
        super(UsuariosPorRuta.class);
    }

}
