package beansJSF;

import java.util.ArrayList;
import java.util.List;

import entity.*;


public class RutaCompleta {
	
	public Rutas ruta;
	public ArrayList<PuntosIntermedios> listaPuntosIntermedios=new ArrayList<PuntosIntermedios>();
	
	public RutaCompleta(Rutas ruta){
		
		this.ruta=ruta;
		
	}

}
