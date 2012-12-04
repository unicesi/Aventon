package beansJSF;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;



import util.FacesUtils;
import util.SelectInputDateBean;

import entity.*;
import sessionsbeans.*;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectBooleanCheckbox;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;
import com.icesoft.faces.component.ext.RowSelectorEvent;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import java.util.Collection;
import java.util.Random;
import javax.ejb.EJB;



@ManagedBean
@RequestScoped
public class RutaBean {
      @EJB
    private RutasFacadeLocal daoR;
       @EJB
    private  UsuariosFacadeLocal daoU;
    @EJB
    private  PuntosIntermediosFacadeLocal daoPI;
    @EJB
    private  VehiculosFacadeLocal daoVeh;
      @EJB
    private  ZonaFacadeLocal daoZo;


	public void ActionFinalizar(ActionEvent eve){
            if(!Finalizar.isVisible()){
                return;
            }
		if (descipcion.getValue().equals("")) {
			mensaje="Por favor Escriba la Descpricion de La Ruta";
			visible=true;
			return;
		}
		if (cupoDisponible.getValue().equals("")) {
			mensaje="Por favor Escriba los Cupos Disponibles de La Ruta";
			visible=true;
			return;
		}
		if (tienePrecio.isSelected())  {
			if (precio.getValue().equals("")) {
				
			
			mensaje="Por favor Escriba El precio de La Ruta";
			visible=true;
			return;}
		}
		if (edadInicial.getValue().equals("")) {
			mensaje="Por favor Escriba La edad Inicial del Perfil de Pasajero";
			visible=true;
			return;
		}
		if (edadFinal.getValue().equals("")) {
			mensaje="Por favor Escriba La edad Final del Perfil de Pasajero";
			visible=true;
			return;
		}
		
		try {
			new Integer(edadInicial.getValue()+"");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			mensaje="Valor  Edad Inicial No Valido";
			visible=true;
			return;
		}
		try {
			new Integer(edadFinal.getValue()+"");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			mensaje="Valor  Edad Final No Valido";
			visible=true;
			return;
		}
		if(tienePrecio.isSelected()) {
		
		
		try {
			new Integer(precio.getValue()+"");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			mensaje="Valor Precio No Valido";
			visible=true;
			return;
		}
		}
		
		try {
			new Integer(cupoDisponible.getValue()+"");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			mensaje="Valor Cupo  No Valido";
			visible=true;
			return;
		}
		
		Vehiculos mivehiculo= daoVeh.find(vehiculo.getValue()+"");
		if (mivehiculo.getCupo()>new Integer(cupoDisponible.getValue()+"")) {
			Integer precio=0;
			if (tienePrecio.isSelected()) {
				precio=new Integer(this.precio.getValue()+"");
			}
                        System.out.println(new Integer(cupoDisponible.getValue()+""));
                        System.out.println(new Integer(edadInicial.getValue()+""));
                        System.out.println(new Integer(edadFinal.getValue()+""));
                        System.out.println(new Integer(tipoUsuario.getValue()+""));



			 int ruta=daoR.consecutivo();
                                System.out.println("consecutivo "+ruta);
                        Rutas miruta=new Rutas(0,usuario,mivehiculo,descipcion.getValue()+"",
						new Date(),precio,new Integer(cupoDisponible.getValue()+""),
						0,new Integer(edadInicial.getValue()+""),new Integer(edadFinal.getValue()+""),sexo.getValue()+"",new Integer(tipoUsuario.getValue()+""));

                             
				
                            try{

                               
                                miruta.setCodigo(ruta);
                                daoR.create(miruta);
                                Rutas mirutaGuardada=daoR.find(ruta);

                                    for(int i=0;i<puntos.size();i++){
                                         PuntosIntermedios punto=puntos.get(i);
                                        daoPI.create(new PuntosIntermedios(daoPI.consecutivo(),punto.getZona(),mirutaGuardada,punto.getDireccion(),punto.getDetalle(),punto.getLatitud(),punto.getLongitud()));

                                    }
				visible=true;

				mensaje="Ruta Ingresada Con Exito";
				recargar();

                    }catch(Exception e){
e.printStackTrace();
                    }
						}else{
			mensaje="Cupo no valido para el vehiculo " +mivehiculo.getPlaca() +" Recuerde que el conductor Ocupa un puesto";
			visible=true;
			return;
		}
		
	}

	public RutaBean() {
		
		
		quitarBoton.setVisible(false);
		Finalizar.setVisible(false);

		precio.setDisabled(true);
		edadInicialValor = "0";
		edadFinalValor = "100";
		// TODO Auto-generated constructor stub
	}



        public Usuarios usuario;
	public void recargar(){
		puntos.clear();
		direccionValor = "";
		 detalleValor = "";
		 Finalizar.setVisible(false);
	 zonaValor = "";
	 edadInicialValor="0";
	 edadFinalValor="100";
	 tipoUsuarioValor="0";
	 sexoValor="";
	 cupoDisponibleValor="";
	 desValor="";
	 vehiculoValor="";
	 controlfecha.setDate1(new Date());
	 controlfecha.setDate2(new Date());
	 tienePrecioValor=false;
	 precioValor="";
	 
	 
	}
	public HtmlSelectOneMenu vehiculo = new HtmlSelectOneMenu();
	public HtmlCommandButton quitarBoton = new HtmlCommandButton();
	public HtmlCommandButton Finalizar = new HtmlCommandButton();

	public HtmlSelectOneMenu sexo = new HtmlSelectOneMenu();

	public HtmlSelectOneMenu tipoUsuario = new HtmlSelectOneMenu();
	public HtmlInputText descipcion = new HtmlInputText();
	public HtmlInputText precio = new HtmlInputText();
	public HtmlInputText cupoDisponible = new HtmlInputText();
	public HtmlInputText edadInicial = new HtmlInputText();
	public HtmlInputText edadFinal = new HtmlInputText();

	public HtmlInputText direccion = new HtmlInputText();
	public HtmlInputText detalle = new HtmlInputText();
	public HtmlSelectOneMenu zona = new HtmlSelectOneMenu();
	public String direccionValor = "";
	public String detalleValor = "";
	public String zonaValor = "";
	public ArrayList<PuntosIntermedios> puntos = new ArrayList<PuntosIntermedios>();

	public void descripcionValor(ValueChangeEvent ac) {
            direccionValor=ac.getNewValue()+"";
            
        }


        public void AgregarPuntoIntermedio(ActionEvent ac) {
        ArrayList<PuntosIntermedios>list=new ArrayList<PuntosIntermedios>();
            for (PuntosIntermedios puntosIntermedios : puntos) {
                if (!list.contains(puntosIntermedios)) {
                    list.add(puntosIntermedios);
                }
            }
            puntos=list;
           
            if (detalleValor.equals(""))
                return;
        
            int latitud=new Random().nextInt(30)+20;
             int longitud=new Random().nextInt(30)+20;
             double lonm=-76.50394-(1/latitud);
             double lam=3.52369 +(1/longitud);

             String lam1=(lam+"");
             String lonm1=(lonm+"");

             PuntosIntermedios ent2=(new PuntosIntermedios(new Integer(0), daoZo
					.find(new Integer(zona.getValue() + "")), null,
					direccion.getValue() + "", detalle.getValue() + "",lam1+"",lonm1+""));
		if (!puntos.contains(ent2)) {


                    PuntosIntermedios ent=(new PuntosIntermedios(new Integer(0), daoZo
					.find(new Integer(zona.getValue() + "")), null,
					direccion.getValue() + "", detalle.getValue() + "",lam1+"",lonm1+""));
                   
                    puntos.add(ent);
                    System.out.print("tamañp" + puntos.size() );
                    
			detalleValor = "";
			direccionValor = "";
			zonaValor = "";
			detalle.setValue("");
			direccion.setValue("");
			zona.setValue("");
			Finalizar.setVisible(true);
		} else {
			visible = true;
			mensaje = "Este Punto Intermedio ya fue Ingresado";

			detalleValor = "";
			direccionValor = "";
			zonaValor = "";
			detalle.setValue("");
			direccion.setValue("");
			zona.setValue("");
		}
	}

    public PuntosIntermediosFacadeLocal getDaoPI() {
        return daoPI;
    }

    public void setDaoPI(PuntosIntermediosFacadeLocal daoPI) {
        this.daoPI = daoPI;
    }

    public RutasFacadeLocal getDaoR() {
        return daoR;
    }

    public void setDaoR(RutasFacadeLocal daoR) {
        this.daoR = daoR;
    }

    public UsuariosFacadeLocal getDaoU() {
        return daoU;
    }

    public void setDaoU(UsuariosFacadeLocal daoU) {
        this.daoU = daoU;
    }

    public VehiculosFacadeLocal getDaoVeh() {
        return daoVeh;
    }

    public void setDaoVeh(VehiculosFacadeLocal daoVeh) {
        this.daoVeh = daoVeh;
    }

    public ZonaFacadeLocal getDaoZo() {
        return daoZo;
    }

    public void setDaoZo(ZonaFacadeLocal daoZo) {
        this.daoZo = daoZo;
    }

	 public void validarFecha(FacesContext context, UIComponent validate, Object value){

		 Date lafecha=(Date)value;
		 Date ahora=new Date();
		 if(lafecha.after(ahora)){
			 ((UIInput)validate).setValid(false);
	           FacesMessage msg = new FacesMessage("No representa una fecha valida");
	           context.addMessage(validate.getClientId(context), msg);
		 }
		 
	 }

	public String cupoDisponibleValor = "";
	public String edadInicialValor = "";
	public String edadFinalValor = "";
	public String sexoValor = "";

	public String tipoUsuarioValor = "0";

	public HtmlInputText getCupoDisponible() {
		return cupoDisponible;
	}

	public void setCupoDisponible(HtmlInputText cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
	}

	public String getCupoDisponibleValor() {
		return cupoDisponibleValor;
	}

	public void setCupoDisponibleValor(String cupoDisponibleValor) {
		this.cupoDisponibleValor = cupoDisponibleValor;
	}

	public HtmlSelectBooleanCheckbox tienePrecio = new HtmlSelectBooleanCheckbox();

	public SelectInputDate fecha = new SelectInputDate();
	public SelectInputDateBean controlfecha = new SelectInputDateBean();

	public SelectInputDate getFecha() {
		return fecha;
	}

	public void setFecha(SelectInputDate fecha) {
		this.fecha = fecha;
	}

	public SelectInputDateBean getControlfecha() {
		return controlfecha;
	}

	public void setControlfecha(SelectInputDateBean controlfecha) {
		this.controlfecha = controlfecha;
	}

	public String vehiculoValor = "";
	public String desValor = "";
	public String precioValor = "";
	public boolean tienePrecioValor = false;

	public HtmlInputText getPrecio() {
		return precio;
	}

	public void setPrecio(HtmlInputText precio) {
		this.precio = precio;
	}

	public HtmlSelectBooleanCheckbox getTienePrecio() {
		return tienePrecio;
	}

	public void setTienePrecio(HtmlSelectBooleanCheckbox tienePrecio) {
		this.tienePrecio = tienePrecio;
	}

	public HtmlSelectOneMenu getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(HtmlSelectOneMenu tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoUsuarioValor() {
		return tipoUsuarioValor;
	}

	public void setTipoUsuarioValor(String tipoUsuarioValor) {
		this.tipoUsuarioValor = tipoUsuarioValor;
	}

	public String getPrecioValor() {
		return precioValor;
	}

	public void setPrecioValor(String precioValor) {
		this.precioValor = precioValor;
	}

	

	public boolean isTienePrecioValor() {
		return tienePrecioValor;
	}

	public void setTienePrecioValor(boolean tienePrecioValor) {
		this.tienePrecioValor = tienePrecioValor;
	}

	public HtmlInputText getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(HtmlInputText descipcion) {
		this.descipcion = descipcion;
	}

	public String getDesValor() {
		return desValor;
	}

	public void setDesValor(String desValor) {
		this.desValor = desValor;
	}

	public Usuarios getUsuario() {
         
	usuario =  daoU.find(""
				+ FacesUtils.getSession().getAttribute("Codigo"));
            return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getVehiculoValor() {
		return vehiculoValor;
	}

	public void setVehiculoValor(String vehiculoValor) {
		this.vehiculoValor = vehiculoValor;
	}

	public ArrayList<SelectItem> vehiculoslita = new ArrayList<SelectItem>();

	public ArrayList<SelectItem> zonalist = new ArrayList<SelectItem>();

	public HtmlSelectOneMenu getVehiculo() {
		return vehiculo;
	}

	public void cambioTienePrecio(ValueChangeEvent eve) {
		if (tienePrecio.isSelected()) {
			precio.setDisabled(false);

		} else {
			precio.setDisabled(true);
			precioValor = "";
			precio.setValue("");

		}
	}

	public void setVehiculo(HtmlSelectOneMenu vehiculo) {
		this.vehiculo = vehiculo;
	}

	public HtmlInputText getEdadInicial() {
		return edadInicial;
	}

	public void setEdadInicial(HtmlInputText edadInicial) {
		this.edadInicial = edadInicial;
	}

	public HtmlInputText getEdadFinal() {
		return edadFinal;
	}

	public void setEdadFinal(HtmlInputText edadFinal) {
		this.edadFinal = edadFinal;
	}

	public String getEdadInicialValor() {
		return edadInicialValor;
	}

	public void setEdadInicialValor(String edadInicialValor) {
		this.edadInicialValor = edadInicialValor;
	}

	public String getEdadFinalValor() {
		return edadFinalValor;
	}

	public void setEdadFinalValor(String edadFinalValor) {
		this.edadFinalValor = edadFinalValor;
	}

	public ArrayList<SelectItem> getVehiculoslita() {
		vehiculoslita.clear();

                usuario=usuario =  daoU.find(""
				+ FacesUtils.getSession().getAttribute("Codigo"));
                
		Collection <Vehiculos> set = usuario.getVehiculosCollection();
		List<Vehiculos> lista = new ArrayList<Vehiculos>();
		lista.addAll(set);
		for (Vehiculos vehiculos : lista) {

			vehiculoslita.add(new SelectItem(vehiculos.getPlaca(), vehiculos
					.getPlaca()));
		}

		return vehiculoslita;
	}

	public void setVehiculoslita(ArrayList<SelectItem> vehiculoslita) {
		this.vehiculoslita = vehiculoslita;
	}

	public HtmlSelectOneMenu getSexo() {
		return sexo;
	}

	public void setSexo(HtmlSelectOneMenu sexo) {
		this.sexo = sexo;
	}

	public String getSexoValor() {
		return sexoValor;
	}

	public void setSexoValor(String sexoValor) {
		this.sexoValor = sexoValor;
	}

	public HtmlInputText getDireccion() {
		return direccion;
	}

	public void setDireccion(HtmlInputText direccion) {
		this.direccion = direccion;
	}

	public HtmlInputText getDetalle() {
		return detalle;
	}

	public void setDetalle(HtmlInputText detalle) {
		this.detalle = detalle;
	}

	public HtmlSelectOneMenu getZona() {
		return zona;
	}

	public void setZona(HtmlSelectOneMenu zona) {
		this.zona = zona;
	}

	public String getDireccionValor() {
		return direccionValor;
	}

	public void setDireccionValor(String direccionValor) {
		this.direccionValor = direccionValor;
	}

	public String getDetalleValor() {
		return detalleValor;
	}

	public void setDetalleValor(String detalleValor) {
		this.detalleValor = detalleValor;
	}

	public String getZonaValor() {
		return zonaValor;
	}

	public void setZonaValor(String zonaValor) {
		this.zonaValor = zonaValor;
	}

	public ArrayList<PuntosIntermedios> getPuntos() {
		return puntos;
	}

	public void setPuntos(ArrayList<PuntosIntermedios> puntos) {
		this.puntos = puntos;
	}

	public ArrayList<SelectItem> getZonalist() {
		zonalist.clear();

		List<Zona> lista =  daoZo.findAll();

		for (Zona zona : lista) {

			zonalist.add(new SelectItem(zona.getCodigo(), zona.getNombre()));
		}

		return zonalist;
	}

	public void setZonalist(ArrayList<SelectItem> zonalist) {
		this.zonalist = zonalist;
	}

	public HtmlCommandButton getQuitarBoton() {
		return quitarBoton;
	}

	public void setQuitarBoton(HtmlCommandButton quitarBoton) {
		this.quitarBoton = quitarBoton;
	}

	public HtmlCommandButton getFinalizar() {
		return Finalizar;
	}

	public void setFinalizar(HtmlCommandButton finalizar) {
		Finalizar = finalizar;
	}

	
	public void QuitarPunto(ActionEvent eve) {
            

            HtmlCommandButton bor=(HtmlCommandButton) eve.getSource();
                PuntosIntermedios en=(PuntosIntermedios) bor.getValue();

               
              
                

                   

                 ArrayList<PuntosIntermedios>list=new ArrayList<PuntosIntermedios>();
            for (PuntosIntermedios puntosIntermedios : puntos) {

                if (!list.contains(puntosIntermedios)||!puntosIntermedios.equals(en)) {
                    list.add(puntosIntermedios);
                }
            }
            puntos=list;
             


		if (puntos.isEmpty()) {
			Finalizar.setVisible(false);
		}
	}

	public boolean visible = false;
	public String mensaje = "";

	public void actionok(ActionEvent e) {
		visible = false;
	}

	

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
