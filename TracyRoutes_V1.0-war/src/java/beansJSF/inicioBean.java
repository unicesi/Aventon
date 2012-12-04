/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beansJSF;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputSecret;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;
import entity.Usuarios;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionsbeans.UsuariosFacadeLocal;
import util.FacesUtils;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class inicioBean {

    @EJB
    private UsuariosFacadeLocal daoUsuarios;


    /** Creates a new instance of inicioBean */
    public inicioBean() {
        FacesUtils.getSession().setAttribute("Cargar","0");
    }

    private HtmlInputText codigo=new SelectInputDate();
	private String codigoValor=new String();
	private HtmlInputSecret contraseña=new HtmlInputSecret();
	private String contraseñaValor=new String();
	private HtmlCommandButton boton=new HtmlCommandButton();
	public boolean visible=false;



	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

        public boolean isVisible2() {
		return visible;
	}

	public void setVisible2(boolean visible) {
		this.visible = visible;
	}

	public HtmlCommandButton getBoton() {
		return boton;
	}

	public void setBoton(HtmlCommandButton boton) {
		this.boton = boton;
	}

	public void validarUsuario(ActionEvent eve){
		boolean valida=validaUsuario(codigoValor, contraseñaValor);
		if(valida){
		visible=true;
                    FacesUtils.getSession().setAttribute("Codigo", codigoValor);
			FacesUtils.getSession().setAttribute("Password", contraseñaValor);
                       
                    
                
                
			
		}else{
			System.out.println("USUARIO O CONTRASEÑA INCORRECTAS");
			
                      
			
		}
                
	}


	public HtmlInputText getCodigo() {
		return codigo;
	}
	public void setCodigo(HtmlInputText codigo) {
		this.codigo = codigo;
	}
	public String getCodigoValor() {
		return codigoValor;
	}
	public void setCodigoValor(String codigoValor) {
		this.codigoValor = codigoValor;
	}
	public HtmlInputSecret getContraseña() {
		return contraseña;
	}
	public void setContraseña(HtmlInputSecret contraseña) {
		this.contraseña = contraseña;
	}
	public String getContraseñaValor() {
		return contraseñaValor;
	}
	public void setContraseñaValor(String contraseñaValor) {
		this.contraseñaValor = contraseñaValor;
	}


        public boolean validaUsuario(String codigo,String password){
		try{
		Usuarios user=(Usuarios)daoUsuarios.find(codigo);
		if(user.getContraseña().equals(password)){
			System.out.println("USUARIO AUTENTICADO");
			return true;
		}else{
			System.out.println(" LA CONTRASEÑA ES INCORRECTOS");
			return false;
		}
		}catch(Exception e){
			System.out.println("EL USUARIO Y/O LA CONTRASEÑA SON INCORRECTOS");
			return false;
		}

	}


}
