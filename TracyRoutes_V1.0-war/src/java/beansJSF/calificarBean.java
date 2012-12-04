/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beansJSF;

import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import entity.Estadisticas;
import entity.Rutas;
import entity.Usuarios;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import sessionsbeans.EstadisticasFacadeLocal;
import sessionsbeans.RutasFacadeLocal;
import sessionsbeans.UsuariosFacadeLocal;
import util.FacesUtils;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class calificarBean {

    @EJB
    private EstadisticasFacadeLocal daoEstadisticas;

    @EJB
    private RutasFacadeLocal daoRutas;

    @EJB
    private UsuariosFacadeLocal daoUsuarios;

    /** Creates a new instance of calificarBean */
    public calificarBean() {
    }

    private HtmlSelectOneMenu codRuta=new HtmlSelectOneMenu();
    private String codRutaValor=new String();
    private HtmlSelectOneRadio comodidad=new HtmlSelectOneRadio();
    private String comodidadValor=new String();
    private HtmlSelectOneRadio seguridad=new HtmlSelectOneRadio();
    private String seguridadValor=new String();
    private HtmlSelectOneRadio puntualidad=new HtmlSelectOneRadio();
    private String puntualidadValor=new String();
    private HtmlInputTextarea comentario=new HtmlInputTextarea();
    private String comentarioValor=new String();
    public ArrayList<SelectItem> listaItemsRutas = new ArrayList<SelectItem>();

    public String mensaje="";
    public boolean visible=false;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ArrayList<SelectItem> getListaItemsRutas() {

                listaItemsRutas.clear();
                List<Rutas> listaRutas=daoRutas.findAll();
		System.out.println(listaRutas.size());
		for (int i = 0; i < listaRutas.size(); i++)
		{
		listaItemsRutas.add(new SelectItem(listaRutas.get(i).getCodigo(),""+listaRutas.get(i).getCodigo()));
		}
		return listaItemsRutas;
	}


	public void setListaItemsRutas(ArrayList<SelectItem> listaItemsRutas) {
		this.listaItemsRutas = listaItemsRutas;
	}

    public void action(ActionEvent e){

        String codUsuario = (""
                + FacesUtils.getSession().getAttribute("Codigo"));

        codRutaValor=codRuta.getValue()+"";

        comodidadValor=(String)comodidad.getValue();
        seguridadValor=(String)seguridad.getValue();
        puntualidadValor=(String)puntualidad.getValue();

        comentarioValor=(String)comentario.getValue();

        Estadisticas calificacion=new Estadisticas();

        calificacion.setCodigo(daoEstadisticas.consecutivo());
        calificacion.setComodidad(Integer.parseInt(comodidadValor));
        calificacion.setSeguridad(Integer.parseInt(seguridadValor));
        calificacion.setPuntualidad(Integer.parseInt(puntualidadValor));
        calificacion.setDetalle(comentarioValor);
        

        Rutas ruta=(Rutas)daoRutas.find(new Integer(codRutaValor));
        Usuarios user=(Usuarios)daoUsuarios.find(codUsuario);

        calificacion.setRutas(ruta);
        calificacion.setUsuarios(user);

        try{
            daoEstadisticas.create(calificacion);
            mensaje="Servicio Evaluado Con Extio";
            codRutaValor="";

        comodidadValor="";
        seguridadValor="";
        puntualidadValor="";

        comentarioValor="";

            visible=true;
            
        }catch(Exception ex){
            System.out.println("Se presento una excepcion");
        }

        
    }

     public void clickBotonPopUp(ActionEvent e) {

      visible=false;
    }
    public HtmlSelectOneMenu getCodRuta() {
    return codRuta;
    }

    public void setCodRuta(HtmlSelectOneMenu ruta) {
    this.codRuta = ruta;
    }

    public String getCodRutaValor() {
    return codRutaValor;
    }

    public void setCodRutaValor(String rutaValor) {
    this.codRutaValor = rutaValor;
}


    public HtmlSelectOneRadio getComodidad() {
    return comodidad;
    }


    public void setComodidad(HtmlSelectOneRadio comodidad) {
    this.comodidad = comodidad;
    }

    public String getComodidadValor() {
    return comodidadValor;
    }


    public void setComodidadValor(String comodidadValor) {
    this.comodidadValor = comodidadValor;
    }


    public HtmlSelectOneRadio getSeguridad() {
    return seguridad;
    }


    public void setSeguridad(HtmlSelectOneRadio seguridad) {
    this.seguridad = seguridad;
    }

    public String getSeguridadValor() {
    return seguridadValor;
    }


    public void setSeguridadValor(String seguridadValor) {
    this.seguridadValor = seguridadValor;
    }


    public HtmlSelectOneRadio getPuntualidad() {
    return puntualidad;
    }


    public void setPuntualidad(HtmlSelectOneRadio puntualidad) {
    this.puntualidad = puntualidad;
    }

    public String getPuntualidadValor() {
    return puntualidadValor;
    }


    public void setPuntualidadValor(String puntualidadValor) {
    this.puntualidadValor = puntualidadValor;
    }


    public HtmlInputTextarea getComentario() {
    return comentario;
    }


    public void setComentario(HtmlInputTextarea comentario) {
    this.comentario = comentario;
    }


    public String getComentarioValor() {
    return comentarioValor;
    }


    public void setComentarioValor(String comentarioValor) {
    this.comentarioValor = comentarioValor;
    }

}