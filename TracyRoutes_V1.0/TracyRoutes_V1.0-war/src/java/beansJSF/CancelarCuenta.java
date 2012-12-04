/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beansJSF;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import sessionsbeans.RutasFacadeLocal;
import sessionsbeans.UsuariosFacadeLocal;
import util.FacesUtils;
import entity.*;
import java.io.IOException;
import java.util.Collection;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionsbeans.EstadisticasFacadeLocal;
import sessionsbeans.PuntosIntermediosFacadeLocal;
import sessionsbeans.UsuariosPorRutaFacadeLocal;
import sessionsbeans.VehiculosFacadeLocal;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class CancelarCuenta {

    /** Creates a new instance of CancelarCuenta */
    
    
        public boolean visibe=true;

    public boolean isVisibe() {
        return visibe;
    }

    public void setVisibe(boolean visibe) {
        this.visibe = visibe;
    }
        
        @EJB
        private UsuariosFacadeLocal daou;
        @EJB
        private RutasFacadeLocal daoR;
           @EJB
        private UsuariosPorRutaFacadeLocal daoUR;

             @EJB
        private EstadisticasFacadeLocal daoE;
           
                       @EJB

        private PuntosIntermediosFacadeLocal daoP;
             @EJB

        private VehiculosFacadeLocal daoV;
        public void Si(ActionEvent eve){

           
        Usuarios usuario = daou.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
       Collection<UsuariosPorRuta> usuarioR= usuario.getUsuariosPorRutaCollection();
            for (UsuariosPorRuta usuariosPorRuta : usuarioR) {
                daoUR.remove(usuariosPorRuta);
            }
        Collection<Rutas> rutas= usuario.getRutasCollection();
            for (Rutas rutas1 : rutas) {

        Collection<PuntosIntermedios> puntos= rutas1.getPuntosIntermediosCollection();
                for (PuntosIntermedios puntosIntermedios : puntos) {
                 daoP.remove(puntosIntermedios);   
                }
                daoR.remove(rutas1);
            }

             Collection<Vehiculos> veh= usuario.getVehiculosCollection();
             for (Vehiculos vehiculos : veh) {
                daoV.remove(vehiculos);
            }
             Collection<Estadisticas> est =usuario.getEstadisticasCollection();
            for (Estadisticas estadisticas : est) {
             daoE.remove(estadisticas);
            }
            daou.remove(usuario);
          

		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

	



			try {
				response.sendRedirect(response
						.encodeRedirectURL("Autenticacion.jsf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
        }
          public void No(ActionEvent eve){
           visibe=false;
        }



}
