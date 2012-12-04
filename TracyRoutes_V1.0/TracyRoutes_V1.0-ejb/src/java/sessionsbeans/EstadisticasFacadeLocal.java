/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Estadisticas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface EstadisticasFacadeLocal {
int consecutivo() ;
    void create(Estadisticas estadisticas);

    void edit(Estadisticas estadisticas);

    void remove(Estadisticas estadisticas);

    Estadisticas find(Object id);

    List<Estadisticas> findAll();

    List<Estadisticas> findRange(int[] range);

    int count();

}
