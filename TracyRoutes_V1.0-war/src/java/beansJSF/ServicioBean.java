/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beansJSF;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;



import util.FacesUtils;
import util.SelectInputDateBean;

import jxl.write.DateTime;
import entity.*;

import com.icesoft.faces.component.PanelGridTag;
import com.icesoft.faces.component.ext.HtmlForm;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlPanelGrid;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;
import com.icesoft.faces.component.gmap.GMapLatLng;
import com.icesoft.faces.component.panelborder.PanelBorder;
import com.icesoft.faces.component.panelpopup.PanelPopup;
import com.icesoft.faces.component.paneltabset.PanelTab;
import com.icesoft.faces.component.paneltabset.PanelTabSet;
import com.icesoft.faces.component.paneltabset.TabChangeListener;
import com.icesoft.faces.component.paneltabset.TabChangeListenerTag;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import com.icesoft.faces.component.tree.IceUserObject;
import com.icesoft.faces.component.tree.Tree;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entity.Carrera;
import entity.PuntosIntermedios;
import entity.Usuarios;
import entity.Zona;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import sessionsbeans.CarreraFacadeLocal;
import sessionsbeans.PuntosIntermediosFacadeLocal;
import sessionsbeans.RutasFacadeLocal;
import sessionsbeans.UsuariosFacadeLocal;
import sessionsbeans.UsuariosPorRutaFacadeLocal;
import sessionsbeans.VehiculosFacadeLocal;
import sessionsbeans.ZonaFacadeLocal;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class ServicioBean {

    @EJB
    private ZonaFacadeLocal dao;
    @EJB
    private CarreraFacadeLocal daoC;
    @EJB
    private UsuariosFacadeLocal daoU;
    @EJB
    private PuntosIntermediosFacadeLocal daoPI;

    public int getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(int labelCount) {
        this.labelCount = labelCount;
    }

    public ArrayList<RutaCompleta> getListaRutasCompletas() {
        return listaRutasCompletas;
    }

    public void setListaRutasCompletas(ArrayList<RutaCompleta> listaRutasCompletas) {
        this.listaRutasCompletas = listaRutasCompletas;
    }

    public Usuarios getUsuario() {
  
        usuario = daoU.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    public HtmlSelectOneMenu selectOneMenuTiposUsuarios = new HtmlSelectOneMenu();
    public String tiposUsuarios = "";
    public HtmlSelectOneMenu selectOneMenuSexo = new HtmlSelectOneMenu();
    public String sexo = "";
    public HtmlSelectOneMenu selectOneMenuZonas = new HtmlSelectOneMenu();
    public ArrayList<SelectItem> listaItemsZonas = new ArrayList<SelectItem>();
    public String zonas = "";
    public HtmlInputText inputTextEdadInicial = new HtmlInputText();
    public String edadInicial = "";
    public HtmlInputText inputTextEdadFinal = new HtmlInputText();
    public String edadFinal = "";
    public PanelPopup panelPopUp = new PanelPopup();
    public String panelPopUpMensaje;
    public SelectInputDate selectInputDateFecha = new SelectInputDate();
    public SelectInputDateBean validarSelectInputDateFecha = new SelectInputDateBean();
    public PanelTab panelTabRutasFiltradas = new PanelTab();
    public PanelTabSet panelTablaSolicitud = new PanelTabSet();
    public DefaultTreeModel arbolRutasFiltradas;
    public ArrayList<RutaCompleta> listaRutasCompletas = new ArrayList<RutaCompleta>();
    // root node label, used to insure that it can't be deleted.
    public static final String ROOT_NODE_TEXT = "Rutas";
    // label count increases one for each new node
    public int labelCount = 0;

    public List<Zona> listaZonas() {

        List<Zona> lista = dao.findAll();

        return lista;
    }

    private List<GMapLatLng> puntosMapa = new ArrayList();

	public List getPuntosMapa() {

           List<GMapLatLng> puntosMapa2=(List<GMapLatLng>) FacesUtils.getSession().getAttribute("mapas");
            if (puntosMapa2==null) {
                return new ArrayList<GMapLatLng>();
            }
		return puntosMapa2;
	}

	public void setPuntosMapa(List puntosMapa) {
		this.puntosMapa = puntosMapa;
	}

        public PanelTab panelTabMapa=new PanelTab();

	public PanelTab getPanelTabMapa() {
		return panelTabMapa;
	}


	public void setPanelTabMapa(PanelTab panelTabMapa) {
		this.panelTabMapa = panelTabMapa;
	}


        
    // object reference used to delete and copy the node
    public DynamicNodeUserObject selectedNodeObject = null;
    private static final String XP_BRANCH_CONTRACTED_ICON = "./xmlhttp/css/xp/css-images/tree_folder_open.gif";
    private static final String XP_BRANCH_EXPANDED_ICON = "./xmlhttp/css/xp/css-images/tree_folder_close.gif";
    private static final String XP_BRANCH_LEAF_ICON = "./xmlhttp/css/xp/css-images/tree_document.gif";
    public HtmlPanelGrid panelRutaSeleccionada = new HtmlPanelGrid();
    public HtmlPanelGrid panelPuntoIntermedioSeleccionado = new HtmlPanelGrid();
    public String usuarioS;
    public String descripcionS;
    public String FechaS;
    public String VehiculoS;
    public String PrecioS;
    public String CupoS;
    public String zonaS;
    public String direccionS;
    public String detalleS;
    public Usuarios usuario;

    public HtmlSelectOneMenu getSelectOneMenuTiposUsuarios() {
        return selectOneMenuTiposUsuarios;
    }

    public void setSelectOneMenuTiposUsuarios(
            HtmlSelectOneMenu selectOneMenuTiposUsuarios) {
        this.selectOneMenuTiposUsuarios = selectOneMenuTiposUsuarios;
    }


    public ServicioBean(){

            
     
     
        
    }


    public ArrayList<SelectItem>item=new ArrayList<SelectItem>();
    public String value="";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<SelectItem> getItem() {


        ArrayList<SelectItem>items=  (ArrayList<SelectItem>) FacesUtils.getSession().getAttribute("items");
        if(items==null)
                return new ArrayList<SelectItem>();
        return items;
    }

    public void setItem(ArrayList<SelectItem> item) {
        this.item = item;
    }

    
    public String getTiposUsuarios() {

        return tiposUsuarios;
    }

    public void setTiposUsuarios(String tiposUsuarios) {
        this.tiposUsuarios = tiposUsuarios;
    }

    public HtmlSelectOneMenu getSelectOneMenuSexo() {
        return selectOneMenuSexo;
    }

    public void setSelectOneMenuSexo(HtmlSelectOneMenu selectOneMenuSexo) {
        this.selectOneMenuSexo = selectOneMenuSexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void cargarListaZonas() {

        List<Zona> listaZonas = listaZonas();
        for (int i = 0; i < listaZonas.size(); i++) {
            listaItemsZonas.add(new SelectItem(listaZonas.get(i).getCodigo(), listaZonas.get(i).getNombre()));
        }

    }

    public HtmlSelectOneMenu getSelectOneMenuZonas() {
        return selectOneMenuZonas;
    }

    public void setSelectOneMenuZonas(HtmlSelectOneMenu selectOneMenuZonas) {
        this.selectOneMenuZonas = selectOneMenuZonas;
    }

    public ArrayList<SelectItem> getListaItemsZonas() {
        listaItemsZonas.clear();
        cargarListaZonas();
        return listaItemsZonas;
    }

    public void setListaItemsZonas(ArrayList<SelectItem> listaItemsZonas) {
        this.listaItemsZonas = listaItemsZonas;
    }

    public String getZonas() {
        return zonas;
    }

    public void setZonas(String zonas) {
        this.zonas = zonas;
    }

    public HtmlInputText getInputTextEdadInicial() {
        return inputTextEdadInicial;
    }

    public void setInputTextEdadInicial(HtmlInputText inputTextEdadInicial) {
        this.inputTextEdadInicial = inputTextEdadInicial;
    }

    public String getEdadInicial() {
        return edadInicial;
    }

    public void setEdadInicial(String edadInicial) {
        this.edadInicial = edadInicial;
    }

    public HtmlInputText getInputTextEdadFinal() {
        return inputTextEdadFinal;
    }

    public void setInputTextEdadFinal(HtmlInputText inputTextEdadFinal) {
        this.inputTextEdadFinal = inputTextEdadFinal;
    }

    public String getEdadFinal() {
        return edadFinal;
    }

    public void setEdadFinal(String edadFinal) {
        this.edadFinal = edadFinal;
    }

    public SelectInputDate getSelectInputDateFecha() {
        return selectInputDateFecha;
    }

    public void setSelectInputDateFecha(SelectInputDate selectInputDateFecha) {
        this.selectInputDateFecha = selectInputDateFecha;
    }

    public SelectInputDateBean getValidarSelectInputDateFecha() {
        return validarSelectInputDateFecha;
    }

    public void setValidarSelectInputDateFecha(SelectInputDateBean valdarSelectInputDateFecha) {
        this.validarSelectInputDateFecha = validarSelectInputDateFecha;
    }

    public List<PuntosIntermedios> listaPuntosIntermedios() {

        List<PuntosIntermedios> lista = daoPI.findAll();

        return lista;
    }


    private boolean rutas=false;
    private   List<RutaCompleta> listaR = new ArrayList<RutaCompleta> ();
    public void clickBotonFiltrar(ActionEvent e) {

        try {
            Integer edadInicial = new Integer(inputTextEdadInicial.getValue().toString());
            Integer edadFinal = new Integer(inputTextEdadFinal.getValue().toString());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFormato = (formato.format(validarSelectInputDateFecha.getDate2()));
            Timestamp fecha = Timestamp.valueOf(fechaFormato);


            List<PuntosIntermedios> lista = listaPuntosIntermedios();
            listaRutasCompletas = new ArrayList<RutaCompleta>();
            item.clear();
            for (int i = 0; i < lista.size(); i++) {

                //System.out.println(selectOneMenuZonas.getValue().toString()+"_"+lista.get(i).getZona().getCodigo()+"_"+selectOneMenuTiposUsuarios.getValue().toString()+"_"+lista.get(i).getRutas().getTipoUsuario().toString()+"_"+selectOneMenuSexo.getValue().toString()+"_"+lista.get(i).getRutas().getSexoPreferido().toString()+"_"+new Integer(inputTextEdadInicial.getValue().toString())+"_"+lista.get(i).getRutas().getEdadInicial()+"_"+new Integer(inputTextEdadFinal.getValue().toString())+"_"+lista.get(i).getRutas().getEstadoruta()+"_"+lista.get(i).getRutas().getCupodisponible());

                if (selectOneMenuTiposUsuarios.getValue().toString().equalsIgnoreCase(lista.get(i).getRutas().getTipoUsuario() + "") && selectOneMenuSexo.getValue().toString().equalsIgnoreCase(lista.get(i).getRutas().getSexoPreferido().toString()) && selectOneMenuZonas.getValue().toString().equalsIgnoreCase(lista.get(i).getZona().getCodigo().toString()) && edadInicial >= lista.get(i).getRutas().getEdadInicial() && edadFinal <= lista.get(i).getRutas().getEdadFinal() && fecha.before(lista.get(i).getRutas().getFecha()) && lista.get(i).getRutas().getEstadoruta() == 0 && lista.get(i).getRutas().getCupodisponible() > 0) {

                    boolean agregarRuta = true;

                    for (int j = 0; j < listaRutasCompletas.size(); j++) {

                        if (lista.get(i).getRutas().getCodigo().toString().equalsIgnoreCase(listaRutasCompletas.get(j).ruta.getCodigo().toString())) {

                            agregarRuta = false;
                        }

                    }

                    if (agregarRuta) {
                        listaRutasCompletas.add(new RutaCompleta(lista.get(i).getRutas()));

                        item.add(new SelectItem(lista.get(i).getRutas().getCodigo(),lista.get(i).getRutas().getDescripricion()));

                    }
                    FacesUtils.getSession().setAttribute("items", item);


                }

            }

            if (listaRutasCompletas.isEmpty()) {

                panelTabRutasFiltradas.setDisabled(true);
                panelTablaSolicitud.setSelectedIndex(0);
                mostrarPanelPop("El Filtro No Encuentra Rutas Disponibles");

            } else {


                for (int i = 0; i < lista.size(); i++) {

                    for (int j = 0; j < listaRutasCompletas.size(); j++) {

                        if (lista.get(i).getRutas().getCodigo().toString().equalsIgnoreCase(listaRutasCompletas.get(j).ruta.getCodigo().toString())) {

                            listaRutasCompletas.get(j).listaPuntosIntermedios.add(lista.get(i));
                        }

                    }



                }

                for (int i = 0; i < listaRutasCompletas.size(); i++) {
                    System.out.println(listaRutasCompletas.get(i).ruta.getDescripricion() + "_" + listaRutasCompletas.get(i).listaPuntosIntermedios.size());

                }

                listaR=listaRutasCompletas;
                cargarArbolRutas(listaRutasCompletas);
               FacesUtils.getSession().setAttribute("Cargar", "1");

                panelTabRutasFiltradas.setDisabled(false);
                panelTablaSolicitud.setSelectedIndex(1);

            }

        } catch (Exception ex) {
            mostrarPanelPop("Verifique Los Datos Ingresados");
        }


    }

    public void clickBotonPopUp(ActionEvent e) {

        panelPopUp.setVisible(false);
    }

    public PanelPopup getPanelPopUp() {
        return panelPopUp;
    }

    public void setPanelPopUp(PanelPopup panelPopUp) {
        this.panelPopUp = panelPopUp;
    }

    public String getPanelPopUpMensaje() {
        return panelPopUpMensaje;
    }

    public void setPanelPopUpMensaje(String panelPopUpMensaje) {
        this.panelPopUpMensaje = panelPopUpMensaje;
    }

    public void mostrarPanelPop(String mensaje) {

        panelPopUpMensaje = mensaje;
        panelPopUp.setVisible(true);

    }

    public PanelTab getPanelTabRutasFiltradas() {
        return panelTabRutasFiltradas;
    }

    public void setPanelTabRutasFiltradas(PanelTab panelTabRutasFiltradas) {
        this.panelTabRutasFiltradas = panelTabRutasFiltradas;
    }

    public PanelTabSet getPanelTablaSolicitud() {
        return panelTablaSolicitud;
    }

    public void setPanelTablaSolicitud(PanelTabSet panelTablaSolicitud) {
        this.panelTablaSolicitud = panelTablaSolicitud;
    }

    public DefaultTreeModel getArbolRutasFiltradas() {
        return arbolRutasFiltradas;
    }

    public void setArbolRutasFiltradas(DefaultTreeModel arbolRutasFiltradas) {
        this.arbolRutasFiltradas = arbolRutasFiltradas;
    }

    public void cargarArbolRutas(ArrayList<RutaCompleta> list) {

        
        // create root node with its children expanded
       

        if (list==null||list.isEmpty()) {
            return;
        }

             DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode();
        DynamicNodeUserObject rootObject = new DynamicNodeUserObject(rootTreeNode, this, "", 0);
        rootObject.setText(ROOT_NODE_TEXT);
        rootTreeNode.setUserObject(rootObject);

        // model is accessed by by the ice:tree component
        arbolRutasFiltradas = new DefaultTreeModel(rootTreeNode);
        // add some child notes
        for (int i = 0; i < list.size(); i++) {
            DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode();
            DynamicNodeUserObject branchObject =
                    new DynamicNodeUserObject(branchNode, this, list.get(i).ruta.getCodigo().toString(), 1);
            branchObject.setText(list.get(i).ruta.getDescripricion());
            branchNode.setUserObject(branchObject);
            rootTreeNode.add(branchNode);
            // add some more sub children
            for (int k = 0; k < list.get(i).listaPuntosIntermedios.size(); k++) {
                DefaultMutableTreeNode subBranchNode = new DefaultMutableTreeNode();
                DynamicNodeUserObject subBranchObject =
                        new DynamicNodeUserObject(subBranchNode, this, list.get(i).listaPuntosIntermedios.get(k).getCodigo().toString(), 2);
                subBranchObject.setText(list.get(i).listaPuntosIntermedios.get(k).getDireccion());
                subBranchObject.setLeaf(true);
                subBranchNode.setUserObject(subBranchObject);
                branchNode.add(subBranchNode);
            }
        }


    }

    /**
     * Gets the tree node.
     *
     * @return the tree node
     */
    public DynamicNodeUserObject getSelectedNodeObject() {
        return selectedNodeObject;
    }

    /**
     * Sets the tree node.
     *
     * @param selectedNodeObject the new tree node
     */
    public void setSelectedNodeObject(DynamicNodeUserObject selectedNodeObject) {
        this.selectedNodeObject = selectedNodeObject;
    }

    /**
     * Deletes the selected tree node. The node
     * object reference is set to null so that
     * the delete and copy buttons will be disabled.
     *
     * @param event that fired this method
     * @see #isDeleteDisabled(), isCopyDisabled()
     */
    public void deleteSelectedNode(ActionEvent event) {
        // can't delete the root node; this check is a failsafe in case
        // the delete method is somehow activated despite the button being disabled
        if (selectedNodeObject != null
                && !selectedNodeObject.getText().equals(ROOT_NODE_TEXT)) {
            selectedNodeObject.deleteNode(event);
            selectedNodeObject = null;
        }
    }

    /**
     * Copies the selected node in the tree.
     *
     * @param event that fired this method
     */
    public void copySelectedNode(ActionEvent event) {
        if (selectedNodeObject != null) {
            selectedNodeObject.copyNode(event);
        }
    }

    /**
     * Determines whether the delete button is disabled.
     * The delete button should be disabled if the node that was
     * previously selected was deleted or if no node is otherwise selected.
     * The root node is a special case and cannot be deleted.
     *
     * @return the disabled status of the delete button
     */
    public boolean isDeleteDisabled() {
        //can't delete the root node
        return selectedNodeObject == null
                || selectedNodeObject.getText().equals(ROOT_NODE_TEXT);
    }

    /**
     * Determines whether the copy button is disabled.
     * This should only occur when there is no node selected,
     * which occurs at initialization and when a node is deleted.
     *
     * @return the disabled status of the copy button
     */
    public boolean isCopyDisabled() {
        return selectedNodeObject == null;
    }

    /**
     * Increment the label count and return it.
     *
     * @return the new label count
     */
    public int getIncreasedLabelCount() {
        return ++labelCount;
    }
    @EJB
    private RutasFacadeLocal daoR;
  public void cargarPuntosIntermediosEnMapa(){

    	puntosMapa.clear();


    
 Rutas ruta = daoR.find(new Integer(radio.getValue()+""));

 Collection<PuntosIntermedios>puntos=ruta.getPuntosIntermediosCollection();
      for (PuntosIntermedios puntosIntermedios : puntos) {
          puntosMapa.add(new GMapLatLng(puntosIntermedios.getLatitud(),puntosIntermedios.getLongitud()));

      }
    			
    	FacesUtils.getSession().setAttribute("mapas",puntosMapa);

		


    }

  public HtmlSelectOneRadio radio=new HtmlSelectOneRadio();

    public HtmlSelectOneRadio getRadio() {
        return radio;
    }

    public void setRadio(HtmlSelectOneRadio radio) {
        this.radio = radio;
    }

    public void mostrarRutaSeleccionada() {

        Rutas ruta = daoR.find(new Integer(radio.getValue()+""));

        usuarioS = ruta.getUsuarios().getNombres();
        descripcionS = ruta.getDescripricion();
        FechaS = ruta.getFecha().toString();
        VehiculoS = ruta.getVehiculos().getPlaca() + "," + ruta.getVehiculos().getMarca() + "," + ruta.getVehiculos().getModelo();
        PrecioS = ruta.getPrecio() + "";
        CupoS = ruta.getCupodisponible() + "";

        panelRutaSeleccionada.setVisible(true);
        panelPuntoIntermedioSeleccionado.setVisible(false);
        cargarPuntosIntermediosEnMapa();

    	panelTabMapa.setDisabled(false);

    }

    public void selectRadio(ValueChangeEvent eve){
        System.out.print("entro");
        mostrarRutaSeleccionada();
    }

    public void mostrarPuntoIntermedioSeleccionado() {

        PuntosIntermedios punto = daoPI.find(new Integer(getSelectedNodeObject().codigoNodo));

        zonaS = punto.getZona().getNombre();
        direccionS = punto.getDireccion();
        detalleS = punto.getDetalle();

        panelRutaSeleccionada.setVisible(false);
        panelPuntoIntermedioSeleccionado.setVisible(true);
        panelTabMapa.setDisabled(true);

    }

    public HtmlPanelGrid getPanelPuntoIntermedioSeleccionado() {
        return panelPuntoIntermedioSeleccionado;
    }

    public void setPanelPuntoIntermedioSeleccionado(
            HtmlPanelGrid panelPuntoIntermedioSeleccionado) {
        this.panelPuntoIntermedioSeleccionado = panelPuntoIntermedioSeleccionado;
    }

    public HtmlPanelGrid getPanelRutaSeleccionada() {
        return panelRutaSeleccionada;
    }

    public void setPanelRutaSeleccionada(HtmlPanelGrid panelRutaSeleccionada) {
        this.panelRutaSeleccionada = panelRutaSeleccionada;
    }

    public String getUsuarioS() {
        return usuarioS;
    }

    public void setUsuarioS(String usuarioS) {
        this.usuarioS = usuarioS;
    }

    public String getDescripcionS() {
        return descripcionS;
    }

    public void setDescripcionS(String descripcionS) {
        this.descripcionS = descripcionS;
    }

    public String getFechaS() {
        return FechaS;
    }

    public void setFechaS(String fechaS) {
        FechaS = fechaS;
    }

    public String getVehiculoS() {
        return VehiculoS;
    }

    public void setVehiculoS(String vehiculoS) {
        VehiculoS = vehiculoS;
    }

    public String getPrecioS() {
        return PrecioS;
    }

    public void setPrecioS(String precioS) {
        PrecioS = precioS;
    }

    public String getCupoS() {
        return CupoS;
    }

    public void setCupoS(String cupoS) {
        CupoS = cupoS;
    }

    public String getZonaS() {
        return zonaS;
    }

    public void setZonaS(String zonaS) {
        this.zonaS = zonaS;
    }

    public String getDireccionS() {
        return direccionS;
    }

    public void setDireccionS(String direccionS) {
        this.direccionS = direccionS;
    }

    public String getDetalleS() {
        return detalleS;
    }

    public void setDetalleS(String detalleS) {
        this.detalleS = detalleS;
    }
    @EJB
    private UsuariosPorRutaFacadeLocal daoUR;

    public void guardarSolicitud(ActionEvent e) {
        if (!radio.getValue().equals("")) {
        usuario=daoU.find(FacesUtils.getSession().getAttribute("Codigo")+"");

        //    daoUR.create(new UsuariosPorRuta(new UsuariosPorRutaPK(new Integer(radio.getValue()+""), usuario.getCodigo())));
            Rutas miruta = daoR.find(new Integer(radio.getValue()+""));
            miruta.setCupodisponible((miruta.getCupodisponible() - 1));
            daoR.edit(miruta);
            try {


                mostrarPanelPop("Solicitud de Servicio Agregada");
                  limpiarDatosSolicitudServicio();

            } catch (Exception e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();

                mostrarPanelPop("Solicitud de Servicio No Agregada");
                limpiarDatosSolicitudServicio();
            }

        } else {
            mostrarPanelPop("Seleccione Una Ruta");
        }
    }

    public void limpiarDatosSolicitudServicio() {

        inputTextEdadInicial.setValue("");
        inputTextEdadFinal.setValue("");
        edadInicial = "";
        edadFinal = "";

        panelTabRutasFiltradas.setDisabled(true);
        panelTablaSolicitud.setSelectedIndex(0);
FacesUtils.getSession().setAttribute("mapas",new ArrayList<GMapLatLng>());

               FacesUtils.getSession().setAttribute("Cargar", "0");
    }
}
