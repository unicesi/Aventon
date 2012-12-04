/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "usuariosPorRuta")
@NamedQueries({
    @NamedQuery(name = "UsuariosPorRuta.findAll", query = "SELECT u FROM UsuariosPorRuta u"),
    @NamedQuery(name = "UsuariosPorRuta.findByCodigoRuta", query = "SELECT u FROM UsuariosPorRuta u WHERE u.usuariosPorRutaPK.codigoRuta = :codigoRuta"),
    @NamedQuery(name = "UsuariosPorRuta.findByCodigoUsuario", query = "SELECT u FROM UsuariosPorRuta u WHERE u.usuariosPorRutaPK.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuariosPorRuta.findByFecha", query = "SELECT u FROM UsuariosPorRuta u WHERE u.fecha = :fecha")})
public class UsuariosPorRuta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariosPorRutaPK usuariosPorRutaPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;
    @JoinColumn(name = "codigoRuta", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rutas rutas;

    public UsuariosPorRuta() {
    }

    public UsuariosPorRuta(UsuariosPorRutaPK usuariosPorRutaPK) {
        this.usuariosPorRutaPK = usuariosPorRutaPK;
    }

    public UsuariosPorRuta(UsuariosPorRutaPK usuariosPorRutaPK, Date fecha) {
        this.usuariosPorRutaPK = usuariosPorRutaPK;
        this.fecha = fecha;
    }

    public UsuariosPorRuta(int codigoRuta, String codigoUsuario) {
        this.usuariosPorRutaPK = new UsuariosPorRutaPK(codigoRuta, codigoUsuario);
    }

    public UsuariosPorRutaPK getUsuariosPorRutaPK() {
        return usuariosPorRutaPK;
    }

    public void setUsuariosPorRutaPK(UsuariosPorRutaPK usuariosPorRutaPK) {
        this.usuariosPorRutaPK = usuariosPorRutaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (usuariosPorRutaPK != null ? usuariosPorRutaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosPorRuta)) {
            return false;
        }
        UsuariosPorRuta other = (UsuariosPorRuta) object;
        if ((this.usuariosPorRutaPK == null && other.usuariosPorRutaPK != null) || (this.usuariosPorRutaPK != null && !this.usuariosPorRutaPK.equals(other.usuariosPorRutaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UsuariosPorRuta[usuariosPorRutaPK=" + usuariosPorRutaPK + "]";
    }

}
