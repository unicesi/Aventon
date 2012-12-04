/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "PuntosIntermedios")
@NamedQueries({
    @NamedQuery(name = "PuntosIntermedios.findAll", query = "SELECT p FROM PuntosIntermedios p"),
    @NamedQuery(name = "PuntosIntermedios.findByCodigo", query = "SELECT p FROM PuntosIntermedios p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PuntosIntermedios.findByDireccion", query = "SELECT p FROM PuntosIntermedios p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PuntosIntermedios.findByDetalle", query = "SELECT p FROM PuntosIntermedios p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "PuntosIntermedios.findByLatitud", query = "SELECT p FROM PuntosIntermedios p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "PuntosIntermedios.findByLongitud", query = "SELECT p FROM PuntosIntermedios p WHERE p.longitud = :longitud")})
public class PuntosIntermedios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @Column(name = "latitud")
    private String latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private String longitud;
    @JoinColumn(name = "codigoZona", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Zona zona;
    @JoinColumn(name = "codigoRuta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Rutas rutas;

        public PuntosIntermedios(Integer codigo, Zona zona, Rutas rutas, String direccion, String detalle,String latitud,String altitud) {
        this.codigo = codigo;
        this.zona = zona;
        this.rutas = rutas;
        this.latitud=latitud;
        this.longitud=altitud;
        this.direccion = direccion;
        this.detalle = detalle;
    }

    public PuntosIntermedios() {
    }

    public PuntosIntermedios(Integer codigo) {
        this.codigo = codigo;
    }

    public PuntosIntermedios(Integer codigo, String direccion, String detalle, String latitud, String longitud) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.detalle = detalle;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getCodigo() {
        return codigo;
    }

     @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detalle == null) ? 0 : detalle.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntosIntermedios other = (PuntosIntermedios) obj;
		if (detalle == null) {
			if (other.detalle != null)
				return false;
		} else if (!detalle.equals(other.detalle))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.getCodigo().equals(other.zona.getCodigo()))
			return false;
		return true;
	}
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Rutas getRutas() {
        return rutas;
    }

    public void setRutas(Rutas rutas) {
        this.rutas = rutas;
    }

    

    @Override
    public String toString() {
        return "entity.PuntosIntermedios[codigo=" + codigo + "]";
    }

}
