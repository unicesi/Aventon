/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Rutas;
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
public class RutasFacade extends AbstractFacade<Rutas> implements RutasFacadeLocal {
    @PersistenceContext(unitName = "ProjectoP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RutasFacade() {
        super(Rutas.class);
    }
    @EJB
    private RutasFacadeLocal ruta;
     public int consecutivo(){
       try{
         List<Rutas>rut= ruta.findAll();

       return rut.get(ruta.count()-1).getCodigo()+1;
         }catch(Exception e1)
       {
        return 1;
       }
   }


}
