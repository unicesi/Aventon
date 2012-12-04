/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Estadisticas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class EstadisticasFacade extends AbstractFacade<Estadisticas> implements EstadisticasFacadeLocal {
    @PersistenceContext(unitName = "ProjectoP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadisticasFacade() {
        super(Estadisticas.class);
    }

     @EJB
    private EstadisticasFacadeLocal ruta;
    @Override
    public int consecutivo() {
      try{
         List<Estadisticas>rut= ruta.findAll();

       return rut.get(ruta.count()-1).getCodigo()+1;
         }catch(Exception e1)
       {
        return 1;
       }
    }
}
