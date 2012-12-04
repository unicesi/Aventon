/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beansJSF;

import entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import sessionsbeans.*;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class Bean {


    @EJB
    private CarreraFacadeLocal ejb;
     @EJB
    private RutasFacadeLocal ejb2;
     @EJB
    private VehiculosFacadeLocal ejb3;
      @EJB
    private UsuariosFacadeLocal ejb4;


    /** Creates a new instance of Bean */
    public Bean() {
      
    }

     public void action(ActionEvent eve){
        System.out.println("hola");

Rutas ruta =new Rutas();

ruta.setCodigo(ejb2.consecutivo());
ruta.setCupodisponible(4);
    ruta.setDescripricion("des");
ruta.setEdadFinal(10);
ruta.setEdadInicial(0);
ruta.setEstadoruta(0);

ruta.setFecha(new Date());
ruta.setPrecio(100);
ruta.setSexoPreferido("F");
ruta.setTipoUsuario(1);
ruta.setUsuarios(ejb4.find("0723012"));
List<Vehiculos>ve=new ArrayList<Vehiculos>();
ve.addAll(ejb4.find("0723012").getVehiculosCollection());
ruta.setVehiculos(ve.get(0));
ejb2.create(ruta);

System.out.println("creo");
       
    }
}
