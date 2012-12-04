/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionsbeans;

import entity.UsuariosPorRuta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface UsuariosPorRutaFacadeLocal {

    void create(UsuariosPorRuta usuariosPorRuta);

    void edit(UsuariosPorRuta usuariosPorRuta);

    void remove(UsuariosPorRuta usuariosPorRuta);

    UsuariosPorRuta find(Object id);

    List<UsuariosPorRuta> findAll();

    List<UsuariosPorRuta> findRange(int[] range);

    int count();

}
