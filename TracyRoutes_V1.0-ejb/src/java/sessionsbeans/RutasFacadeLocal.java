/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Rutas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface RutasFacadeLocal {
        int consecutivo();
    void create(Rutas rutas);

    void edit(Rutas rutas);

    void remove(Rutas rutas);

    Rutas find(Object id);

    List<Rutas> findAll();

    List<Rutas> findRange(int[] range);

    int count();

}
