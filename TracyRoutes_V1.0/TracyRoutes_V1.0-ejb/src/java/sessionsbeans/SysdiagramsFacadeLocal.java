/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.Sysdiagrams;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface SysdiagramsFacadeLocal {

    void create(Sysdiagrams sysdiagrams);

    void edit(Sysdiagrams sysdiagrams);

    void remove(Sysdiagrams sysdiagrams);

    Sysdiagrams find(Object id);

    List<Sysdiagrams> findAll();

    List<Sysdiagrams> findRange(int[] range);

    int count();

}
