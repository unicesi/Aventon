/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Zona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface ZonaFacadeLocal {

    void create(Zona zona);

    void edit(Zona zona);

    void remove(Zona zona);

    Zona find(Object id);

    List<Zona> findAll();

    List<Zona> findRange(int[] range);

    int count();

}
