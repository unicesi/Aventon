/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.PuntosIntermedios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface PuntosIntermediosFacadeLocal {

    void create(PuntosIntermedios puntosIntermedios);
    int consecutivo();
    void edit(PuntosIntermedios puntosIntermedios);

    void remove(PuntosIntermedios puntosIntermedios);

    PuntosIntermedios find(Object id);

    List<PuntosIntermedios> findAll();

    List<PuntosIntermedios> findRange(int[] range);

    int count();

}
