/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entity.Carrera;
import entity.PuntosIntermedios;
import entity.Usuarios;
import entity.Zona;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import sessionsbeans.CarreraFacadeLocal;
import sessionsbeans.PuntosIntermediosFacadeLocal;
import sessionsbeans.UsuariosFacadeLocal;
import sessionsbeans.VehiculosFacadeLocal;
import sessionsbeans.ZonaFacadeLocal;

/**
 *
 * @author Administrador
 */
public class ControlDatos {

    @EJB
    private static ZonaFacadeLocal dao;
    @EJB
    private static CarreraFacadeLocal daoC;
    @EJB
    private static UsuariosFacadeLocal daoU;
    @EJB
    private static PuntosIntermediosFacadeLocal daoPI;





            public  List<Zona> listaZonas(){

		List<Zona> lista=dao.findAll();
		
		return lista;
	}



	public static List<Carrera> listaCarreras(){

		List<Carrera> listaC=daoC.findAll();
		return listaC;

	}
        public static boolean validarUsuario(String codigo,String password){
		try{
		Usuarios user=daoU.find(codigo);
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

        public static boolean registrarUsuario(String codigo,String contraseña,String nombre, String apellidos,Timestamp nacimiento, String mail, String sexo, Integer tipo, String estadoCivil){

		Usuarios nuevoUsuario=new Usuarios();
		nuevoUsuario.setCodigo(codigo);
		nuevoUsuario.setContraseña(contraseña);
		nuevoUsuario.setNombres(nombre+" "+apellidos);
		nuevoUsuario.setCorreo(mail);
		nuevoUsuario.setSexo(sexo);
		nuevoUsuario.setFechanacimiento(nacimiento);
		nuevoUsuario.setTipousuario(tipo);
		nuevoUsuario.setEstadocivil(estadoCivil);


		try{
		daoU.create(nuevoUsuario);
		return true;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

        public static boolean registrarUsuarioEstudiante(String codigo,String contraseña,String nombre, String apellidos,Timestamp nacimiento, String mail, String sexo, Integer tipo, String estadoCivil,String carrera, Integer semestre){

		Usuarios nuevoUsuario=new Usuarios();
		nuevoUsuario.setCodigo(codigo);
		nuevoUsuario.setContraseña(contraseña);
		nuevoUsuario.setNombres(nombre+" "+apellidos);
		nuevoUsuario.setCorreo(mail);
		nuevoUsuario.setSexo(sexo);
		nuevoUsuario.setFechanacimiento(nacimiento);
		nuevoUsuario.setTipousuario(tipo);
		nuevoUsuario.setEstadocivil(estadoCivil);
		nuevoUsuario.setSemestre(semestre);
		Carrera aux=daoC.find(new Integer(carrera));
		nuevoUsuario.setCarrera(aux);
		System.out.println("TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

		try{



		daoU.create(nuevoUsuario);


		return true;
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}






public static List<PuntosIntermedios> listaPuntosIntermedios(){

	List<PuntosIntermedios> lista=daoPI.findAll();

	return lista;
}

}
