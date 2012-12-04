/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beansJSF;

import com.icesoft.faces.component.ext.HtmlInputText;

import entity.Usuarios;
import entity.Vehiculos;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionsbeans.UsuariosFacadeLocal;
import sessionsbeans.VehiculosFacadeLocal;
import util.FacesUtils;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class VehiculoBean {

    @EJB
    private VehiculosFacadeLocal daoVehiculos;

    @EJB
    private UsuariosFacadeLocal daoUsuarios;

public boolean visible=false;
	public String mensaje="";
	public Usuarios usuario;




	public Usuarios getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}


	public void actionok(ActionEvent e) {
		visible = false;
             
	}


	public void AgregarVehiculo(ActionEvent ac) {

         usuario = daoUsuarios.find(""
                + FacesUtils.getSession().getAttribute("Codigo"));
    

		if (placa.getValue().equals("")){
			mensaje= "Por favor digite el campo de la placa";
			visible= true;
			return;
		}
		if (marca.getValue().equals("")){
			mensaje= "Por favor digite el campo de la marca";
			visible= true;
			return;
		}
		if (capacidad.getValue().equals("")){

				mensaje= "Por favor digite el campo de la capacidad";
				visible= true;
			return;
		}
		try {
			new Integer(capacidad.getValue()+"");
			} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			mensaje="El tipo de valor indicado para la capacidad no es valido";
			visible=true;
			return;
	}
		if (modelo.getValue().equals("")){


				mensaje= "Por favor digite el campo del modelo";
				visible= true;
				return;

		}
	try {

		new Integer(modelo.getValue()+"");
	} catch (NumberFormatException e) {
	// TODO Auto-generated catch block
	mensaje="El tipo de valor indicado para el modelo no es valido";
	visible=true;
	return;
	}
		if (daoVehiculos.find(placa.getValue()+"")!=null ) {
			
                visibleP=true;
		
			return;

		}
		//Vehiculos vehiculo= new Vehiculos(placa.getValue()+"",usuario,new Integer(modelo.getValue()+""),marca.getValue()+"",new Integer(capacidad.getValue()+""));

                Vehiculos vehiculo= new Vehiculos(placa.getValue()+"",new Integer(modelo.getValue()+""),marca.getValue()+"",new Integer(capacidad.getValue()+""));
                vehiculo.setUsuarios(usuario);

		//ControlVehiculos control= new ControlVehiculos();
		//control.guardar(vehiculo);
//----------------------------------------------------
                try{
            daoVehiculos.create(vehiculo);
        }catch(Exception e){
            System.out.println("Se presento una excepcion");
        }
//----------------------------------------------------
		mensaje= "El Vehiculo fue agregado satisfactoriamente";
		visible=true;
		recargar();
	}


        public boolean visibleP=false;


        public void modi(ActionEvent eve){
            visibleP=false;
  Vehiculos veh=daoVehiculos.find(placa.getValue()+"");

veh.setModelo(new Integer(modelo.getValue()+""));
veh.setMarca(marca.getValue()+"");
veh.setCupo( new Integer(capacidad.getValue()+""));
                daoVehiculos.edit(veh);
                mensaje= "El Vehiculo fue editado satisfactoriamente";
		visible=true;
                  recargar();
        }

        public void delete(ActionEvent eve){
              visibleP=false;

            Vehiculos veh=daoVehiculos.find(placa.getValue()+"");
          try{
            daoVehiculos.remove(veh);

                 visible=true;
                mensaje="Eliminado con exito";

          } catch (Exception e) {
            visibleP=false;

                visible=true;
                mensaje="No se puede eliminar ya que este elemento esta siendo usado";
            }

            recargar();
        }

        public void cancelar(ActionEvent eve){
            visibleP=false;
            placa.setValue("");
        }
    public boolean isVisibleP() {
        return visibleP;
    }

    public void setVisibleP(boolean visibleP) {
        this.visibleP = visibleP;
    }

	public void recargar(){
		placaValor="";
		modeloValor="";
		capacidadValor="";
		marcaValor="";

	}


	public VehiculoBean() {
		//usuario = daoUsuarios.find(""+ FacesUtils.getSession().getAttribute("Codigo"));
                
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




	public HtmlInputText placa = new HtmlInputText();
	public HtmlInputText modelo = new HtmlInputText();
	public HtmlInputText marca = new HtmlInputText();
	public HtmlInputText capacidad = new HtmlInputText();

	public String placaValor = new String();
	public String modeloValor = new String();
	public String marcaValor = new String();
	public String capacidadValor = new String();

	public HtmlInputText getPlaca() {
		return placa;
	}
	public void setPlaca(HtmlInputText placa) {
		this.placa = placa;
	}
	public HtmlInputText getModelo() {
		return modelo;
	}
	public void setModelo(HtmlInputText modelo) {
		this.modelo = modelo;
	}
	public HtmlInputText getMarca() {
		return marca;
	}
	public void setMarca(HtmlInputText marca) {
		this.marca = marca;
	}
	public HtmlInputText getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(HtmlInputText capacidad) {
		this.capacidad = capacidad;
	}
	public String getPlacaValor() {
		return placaValor;
	}
	public void setPlacaValor(String placaValor) {
		this.placaValor = placaValor;
	}
	public String getModeloValor() {
		return modeloValor;
	}
	public void setModeloValor(String modeloValor) {
		this.modeloValor = modeloValor;
	}
	public String getMarcaValor() {
		return marcaValor;
	}
	public void setMarcaValor(String marcaValor) {
		this.marcaValor = marcaValor;
	}
	public String getCapacidadValor() {
		return capacidadValor;
	}
	public void setCapacidadValor(String capacidadValor) {
		this.capacidadValor = capacidadValor;
	}
}
