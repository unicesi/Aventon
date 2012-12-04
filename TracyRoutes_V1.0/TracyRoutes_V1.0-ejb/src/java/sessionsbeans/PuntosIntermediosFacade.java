/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.PuntosIntermedios;
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
public class PuntosIntermediosFacade extends AbstractFacade<PuntosIntermedios> implements PuntosIntermediosFacadeLocal {
    @PersistenceContext(unitName = "ProjectoP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PuntosIntermediosFacade() {
        super(PuntosIntermedios.class);
    }
 @EJB
    private PuntosIntermediosFacadeLocal ruta;
    @Override
    public int consecutivo() {
      try{
         List<PuntosIntermedios>rut= ruta.findAll();

       return rut.get(ruta.count()-1).getCodigo()+1;
         }catch(Exception e1)
       {
        return 1;
       }
    }

}
