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
@Table(name = "estadisticas")
@NamedQueries({
    @NamedQuery(name = "Estadisticas.findAll", query = "SELECT e FROM Estadisticas e"),
    @NamedQuery(name = "Estadisticas.findByCodigo", query = "SELECT e FROM Estadisticas e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Estadisticas.findByComodidad", query = "SELECT e FROM Estadisticas e WHERE e.comodidad = :comodidad"),
    @NamedQuery(name = "Estadisticas.findBySeguridad", query = "SELECT e FROM Estadisticas e WHERE e.seguridad = :seguridad"),
    @NamedQuery(name = "Estadisticas.findByPuntualidad", query = "SELECT e FROM Estadisticas e WHERE e.puntualidad = :puntualidad"),
    @NamedQuery(name = "Estadisticas.findByDetalle", query = "SELECT e FROM Estadisticas e WHERE e.detalle = :detalle")})
public class Estadisticas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "comodidad")
    private int comodidad;
    @Basic(optional = false)
    @Column(name = "seguridad")
    private int seguridad;
    @Basic(optional = false)
    @Column(name = "puntualidad")
    private int puntualidad;
    @Basic(optional = false)
    @Column(name = "detalle")
    private String detalle;
    @JoinColumn(name = "codigoUsuarioCalificador", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuarios usuarios;
    @JoinColumn(name = "codigoRuta", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Rutas rutas;

    public Estadisticas() {
    }

    public Estadisticas(Integer codigo) {
        this.codigo = codigo;
    }

    public Estadisticas(Integer codigo, int comodidad, int seguridad, int puntualidad, String detalle) {
        this.codigo = codigo;
        this.comodidad = comodidad;
        this.seguridad = seguridad;
        this.puntualidad = puntualidad;
        this.detalle = detalle;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getComodidad() {
        return comodidad;
    }

    public void setComodidad(int comodidad) {
        this.comodidad = comodidad;
    }

    public int getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(int seguridad) {
        this.seguridad = seguridad;
    }

    public int getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(int puntualidad) {
        this.puntualidad = puntualidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Rutas getRutas() {
        return rutas;
    }

    public void setRutas(Rutas rutas) {
        this.rutas = rutas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadisticas)) {
            return false;
        }
        Estadisticas other = (Estadisticas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Estadisticas[codigo=" + codigo + "]";
    }

}
