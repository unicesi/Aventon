/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beansJSF;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import util.FacesUtils;

import entity.*;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.panelcollapsible.PanelCollapsible;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessionsbeans.CarreraFacadeLocal;
import sessionsbeans.UsuariosFacade;
import sessionsbeans.UsuariosFacadeLocal;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class EditarUsuario {

    /** Creates a new instance of EditarUsuario */
    private String sexoValor;
    private HtmlCommandButton registar = new HtmlCommandButton();
    private HtmlInputText nombre = new HtmlInputText();
    private String codigoValor = new String();
    private String nombreValor = new String();
    private String apellidoValor = new String();
    private String correoValor = new String();
    private String tipoUsuarioValor = new String();
    private String contraseñaValor = new String();
    private String estadoCivilValor = new String();
    private String confirmarContraseñaValor = new String();
    private HtmlSelectOneMenu tipoUsuario = new HtmlSelectOneMenu();
    private HtmlInputText semestre = new HtmlInputText();
    private String semestreValor = new String();
    private HtmlOutputText semestreSalida = new HtmlOutputText();
    private HtmlOutputText carreraSalida = new HtmlOutputText();
    public ArrayList<SelectItem> listaItemsCarreras = new ArrayList<SelectItem>();
    private HtmlSelectOneMenu carrera = new HtmlSelectOneMenu();
    private String carreraValor = new String();
    private Date fechaValor = new Date();
    private SelectInputDate fecha = new SelectInputDate();
    private String mensaje = "";
    private boolean visible = false;
    private boolean visibleError = false;

    public boolean isVisibleError() {
        return visibleError;
    }

    public void setVisibleError(boolean visibleError) {
        this.visibleError = visibleError;
    }

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

    public SelectInputDate getFecha() {
        return fecha;
    }

    public void setFecha(SelectInputDate fecha) {
        this.fecha = fecha;
    }

    public Date getFechaValor() {
        return fechaValor;
    }

    public void setFechaValor(Date fechaValor) {
        this.fechaValor = fechaValor;
    }

    public HtmlSelectOneMenu getCarrera() {
        return carrera;
    }

    public void setCarrera(HtmlSelectOneMenu carrera) {
        this.carrera = carrera;
    }

    public String getCarreraValor() {
        return carreraValor;
    }

    public void setCarreraValor(String carreraValor) {
        this.carreraValor = carreraValor;
    }
    @EJB
    private CarreraFacadeLocal daoc;

    public ArrayList<SelectItem> getListaItemsCarreras() {
        List<Carrera> listaCarrerass = daoc.findAll();
        listaItemsCarreras.clear();
        System.out.println(listaCarrerass.size());
        for (int i = 0; i < listaCarrerass.size(); i++) {
            listaItemsCarreras.add(new SelectItem(listaCarrerass.get(i).getCodigo(), listaCarrerass.get(i).getNombre()));
        }
        return listaItemsCarreras;
    }

    public void setListaItemsCarreras(ArrayList<SelectItem> listaItemsCarreras) {
        this.listaItemsCarreras = listaItemsCarreras;
    }

    public HtmlOutputText getSemestreSalida() {
        return semestreSalida;
    }

    public void setSemestreSalida(HtmlOutputText semestreSalida) {
        this.semestreSalida = semestreSalida;
    }

    public HtmlOutputText getCarreraSalida() {
        return carreraSalida;
    }

    public void setCarreraSalida(HtmlOutputText carreraSalida) {
        this.carreraSalida = carreraSalida;
    }

    public HtmlSelectOneMenu getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(HtmlSelectOneMenu tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public HtmlInputText getSemestre() {
        return semestre;
    }

    public void setSemestre(HtmlInputText semestre) {
        this.semestre = semestre;
    }

    public void esEstudiante(ValueChangeEvent eve) {

        if (tipoUsuario.getValue().equals("1")) {
            semestre.setDisabled(false);
            carrera.setDisabled(false);
        } else {
            carrera.setDisabled(true);
            semestre.setDisabled(true);
        }
    }

    public String getSemestreValor() {
        return semestreValor;
    }

    public void setSemestreValor(String semestreValor) {
        this.semestreValor = semestreValor;
    }

    public String getContraseñaValor() {
        return contraseñaValor;
    }

    public void setContraseñaValor(String contraseñaValor) {
        this.contraseñaValor = contraseñaValor;
    }

    public String getConfirmarContraseñaValor() {
        return confirmarContraseñaValor;
    }

    public void setConfirmarContraseñaValor(String confirmarContraseñaValor) {
        this.confirmarContraseñaValor = confirmarContraseñaValor;
    }

    public String getCorreoValor() {
        return correoValor;
    }

    public void setCorreoValor(String correoValor) {
        this.correoValor = correoValor;
    }

    public String getTipoUsuarioValor() {
        return tipoUsuarioValor;
    }

    public void setTipoUsuarioValor(String tipoUsuarioValor) {
        this.tipoUsuarioValor = tipoUsuarioValor;
    }

    public String getEstadoCivilValor() {
        return estadoCivilValor;
    }

    public void setEstadoCivilValor(String estadoCivilValor) {
        this.estadoCivilValor = estadoCivilValor;
    }

    public String getNombreValor() {
        return nombreValor;
    }

    public void setNombreValor(String nombreValor) {
        this.nombreValor = nombreValor;
    }

    public String getApellidoValor() {
        return apellidoValor;
    }

    public void setApellidoValor(String apellidoValor) {
        this.apellidoValor = apellidoValor;
    }
    public HtmlCommandButton cargar = new HtmlCommandButton();

    public HtmlCommandButton getCargar() {
        return cargar;
    }

    public void setCargar(HtmlCommandButton cargar) {
        this.cargar = cargar;
    }

    public EditarUsuario() {

        nombre.setDisabled(true);
        semestre.setDisabled(true);
        fecha.setDisabled(true);
        tipoUsuario.setDisabled(true);
        carrera.setDisabled(true);
        registar.setDisabled(true);


    }

    private boolean contraseñasIguales() {
        if (getContraseñaValor().equals(getConfirmarContraseñaValor())) {
            return true;
        } else {
            return false;
        }
    }

    public void validateEmail(FacesContext context, UIComponent validate, Object value) {
        String correoValor = (String) value;

        if (correoValor.indexOf('@') == -1) {
            ((UIInput) validate).setValid(false);
            FacesMessage msg = new FacesMessage("No es una dirección de e-mail");
            context.addMessage(validate.getClientId(context), msg);
        }
    }

    public void validarNumeros(FacesContext context, UIComponent validate, Object value) {
        String valores = (String) value;
        try {
            Integer.parseInt(valores);
        } catch (Exception e) {
            // TODO: handle exception
            ((UIInput) validate).setValid(false);
            FacesMessage msg = new FacesMessage("Solo permite números");
            context.addMessage(validate.getClientId(context), msg);
        }

    }

    public void validarFecha(FacesContext context, UIComponent validate, Object value) {

        Date lafecha = (Date) value;
        Date ahora = new Date();
        if (lafecha.after(ahora)) {
            ((UIInput) validate).setValid(false);
            FacesMessage msg = new FacesMessage("No representa una fecha valida");
            context.addMessage(validate.getClientId(context), msg);
        }

    }

    public String validarRegistro() {
        if (mensaje.equalsIgnoreCase("Registro Exitoso")) {
            FacesUtils.getSession().setAttribute("Codigo", codigoValor);
            FacesUtils.getSession().setAttribute("Password", contraseñaValor);
            return "exitoRegistro";
        } else {
            visible = false;
            return "fracasoRegistro";
        }
    }
    @EJB
    private UsuariosFacadeLocal daoU;
    private Usuarios miUsuaro;

    public Usuarios getMiUsuaro() {
      
        miUsuaro = daoU.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
        System.out.println("get");
        return miUsuaro;
    }

    public void setMiUsuaro(Usuarios miUsuaro) {
        this.miUsuaro = miUsuaro;
    }

    public boolean registrarUsuario(String codigo, String contraseña, String nombre, String apellidos, Timestamp nacimiento, String mail, String sexo, Integer tipo, String estadoCivil) {


       
        Usuarios nuevo = daoU.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
        System.out.println("get");

        nuevo.setContraseña(contraseña);
        nuevo.setNombres(nombre + " " + apellidos);
        nuevo.setCorreo(mail);
        nuevo.setSexo(sexo);
        nuevo.setFechanacimiento(nacimiento);
        nuevo.setTipousuario(tipo);
        nuevo.setEstadocivil(estadoCivil);


        try {

            daoU.edit(nuevo);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean registrarUsuarioEstudiante(String codigo, String contraseña, String nombre, String apellidos, Timestamp nacimiento, String mail, String sexo, Integer tipo, String estadoCivil, String carrera, Integer semestre) {

        Usuarios nuevo = daoU.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
        System.out.println("get");

        nuevo.setContraseña(contraseña);
        nuevo.setNombres(nombre + " " + apellidos);
        nuevo.setCorreo(mail);
        nuevo.setSexo(sexo);
        nuevo.setFechanacimiento(nacimiento);
        nuevo.setTipousuario(tipo);
        nuevo.setEstadocivil(estadoCivil);
        nuevo.setSemestre(semestre);
        Carrera aux = daoc.find(new Integer(carrera));
        nuevo.setCarrera(aux);
        System.out.println("TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

        try {



            daoU.edit(nuevo);


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cargar(ActionEvent eve) {
        nombre.setDisabled(false);
        semestre.setDisabled(false);
        fecha.setDisabled(false);
        tipoUsuario.setDisabled(false);
        carrera.setDisabled(false);
        registar.setDisabled(false);
        cargar.setDisabled(true);
    
        Usuarios nuevo = daoU.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
        System.out.println("get");

        nombreValor = (nuevo.getNombres());
        fechaValor = (nuevo.getFechanacimiento());

        correoValor = (nuevo.getCorreo());
        sexoValor = nuevo.getSexo();
        tipoUsuarioValor = nuevo.getTipousuario() + "";
        carreraValor = nuevo.getCarrera().getCodigo() + "";
        estadoCivilValor = nuevo.getEstadocivil();
        contraseñaValor = nuevo.getContraseña();
        confirmarContraseñaValor = nuevo.getContraseña();

        esEstudiante(null);
        if (tipoUsuarioValor.equals("1")) {
            semestreValor = nuevo.getSemestre() + "";
        }

    }

    public void action(ActionEvent eve) {


        if (contraseñasIguales()) {


            Date fechaSeleccionada = getFechaValor();
            long inMilisegundos = fechaSeleccionada.getTime();
            Timestamp nacio = new Timestamp(inMilisegundos);

            if (!tipoUsuario.getValue().equals("1")) {

                boolean guardo = registrarUsuario(getCodigoValor(), getContraseñaValor(), getNombreValor(), getApellidoValor(), nacio, getCorreoValor(), getSexoValor(), Integer.parseInt(getTipoUsuarioValor()), getEstadoCivilValor());
                if (guardo) {
                    mensaje = "Registro Exitoso";
                    visible = true;
                        mensaje = "Registro Exitoso";
                    nombre.setDisabled(true);
                    semestre.setDisabled(true);
                    fecha.setDisabled(true);
                    tipoUsuario.setDisabled(true);
                    carrera.setDisabled(true);
                    registar.setDisabled(true);
                    cargar.setDisabled(false);
                    nombreValor = "";
                    fechaValor = new Date();

                    correoValor = "";
                    sexoValor = "";
                    tipoUsuarioValor = "";
                    carreraValor = "";
                    estadoCivilValor = "";
                    contraseñaValor = "";
                    confirmarContraseñaValor = "";

                } else {
                    mensaje = "Ese Codigo de usuario ya Existe";
                    visible = true;
                    visibleError = false;

                }


            } else {
                System.out.println("ENTRO AL REGISTRO DE ESTUDIANTES");
                boolean guardo = registrarUsuarioEstudiante(getCodigoValor(), getContraseñaValor(), getNombreValor(), getApellidoValor(), nacio, getCorreoValor(), getSexoValor(), Integer.parseInt(getTipoUsuarioValor()), getEstadoCivilValor(), getCarreraValor(), Integer.parseInt(getSemestreValor()));
                if (guardo) {
                    visible = true;
                    mensaje = "Registro Exitoso";
                    nombre.setDisabled(true);
                    semestre.setDisabled(true);
                    fecha.setDisabled(true);
                    tipoUsuario.setDisabled(true);
                    carrera.setDisabled(true);
                    registar.setDisabled(true);
                    cargar.setDisabled(false);
                    nombreValor = "";
                    fechaValor = new Date();

                    correoValor = "";
                    sexoValor = "";
                    tipoUsuarioValor = "";
                    carreraValor = "";
                    estadoCivilValor = "";
                    contraseñaValor = "";
                    confirmarContraseñaValor = "";

                } else {
                    visible = true;
                    mensaje = "Ese Codigo de usuario ya Existe";
                    visibleError = false;
                }
            }
        } else {

            visibleError = true;
            System.out.println("LAS CONTRASEÑA NO COINCIDE");
        }

    }

    public HtmlInputText getNombre() {
        return nombre;
    }

    public void setNombre(HtmlInputText codigo) {
        this.nombre = codigo;
    }

    public String getCodigoValor() {
        return codigoValor;
    }

    public void setCodigoValor(String codigoValor) {
        this.codigoValor = codigoValor;
    }

    public HtmlCommandButton getRegistar() {
        return registar;
    }

    public void setRegistar(HtmlCommandButton registar) {
        this.registar = registar;
    }

    public String getSexoValor() {
        return sexoValor;
    }

    public void setSexoValor(String sexoValor) {
        this.sexoValor = sexoValor;
    }
}
