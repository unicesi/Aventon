/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrador
 */
@Embeddable
public class UsuariosPorRutaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "codigoRuta")
    private int codigoRuta;
    @Basic(optional = false)
    @Column(name = "codigoUsuario")
    private String codigoUsuario;

    public UsuariosPorRutaPK() {
    }

    public UsuariosPorRutaPK(int codigoRuta, String codigoUsuario) {
        this.codigoRuta = codigoRuta;
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(int codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoRuta;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosPorRutaPK)) {
            return false;
        }
        UsuariosPorRutaPK other = (UsuariosPorRutaPK) object;
        if (this.codigoRuta != other.codigoRuta) {
            return false;
        }
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UsuariosPorRutaPK[codigoRuta=" + codigoRuta + ", codigoUsuario=" + codigoUsuario + "]";
    }

}
