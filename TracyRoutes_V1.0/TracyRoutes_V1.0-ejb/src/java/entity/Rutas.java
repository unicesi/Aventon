/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "Rutas")
@NamedQueries({
    @NamedQuery(name = "Rutas.findAll", query = "SELECT r FROM Rutas r"),
    @NamedQuery(name = "Rutas.findByCodigo", query = "SELECT r FROM Rutas r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Rutas.findByDescripricion", query = "SELECT r FROM Rutas r WHERE r.descripricion = :descripricion"),
    @NamedQuery(name = "Rutas.findByFecha", query = "SELECT r FROM Rutas r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Rutas.findByPrecio", query = "SELECT r FROM Rutas r WHERE r.precio = :precio"),
    @NamedQuery(name = "Rutas.findByCupodisponible", query = "SELECT r FROM Rutas r WHERE r.cupodisponible = :cupodisponible"),
    @NamedQuery(name = "Rutas.findByEstadoruta", query = "SELECT r FROM Rutas r WHERE r.estadoruta = :estadoruta"),
    @NamedQuery(name = "Rutas.findByEdadInicial", query = "SELECT r FROM Rutas r WHERE r.edadInicial = :edadInicial"),
    @NamedQuery(name = "Rutas.findByEdadFinal", query = "SELECT r FROM Rutas r WHERE r.edadFinal = :edadFinal"),
    @NamedQuery(name = "Rutas.findBySexoPreferido", query = "SELECT r FROM Rutas r WHERE r.sexoPreferido = :sexoPreferido"),
    @NamedQuery(name = "Rutas.findByTipoUsuario", query = "SELECT r FROM Rutas r WHERE r.tipoUsuario = :tipoUsuario")})
public class Rutas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "descripricion")
    private String descripricion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "precio")
    private int precio;
    @Basic(optional = false)
    @Column(name = "cupodisponible")
    private int cupodisponible;
    @Basic(optional = false)
    @Column(name = "estadoruta")
    private int estadoruta;
    @Basic(optional = false)
    @Column(name = "edadInicial")
    private int edadInicial;
    @Basic(optional = false)
    @Column(name = "edadFinal")
    private int edadFinal;
    @Basic(optional = false)
    @Column(name = "sexoPreferido")
    private String sexoPreferido;
    @Basic(optional = false)
    @Column(name = "tipoUsuario")
    private int tipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutas")
    private Collection<PuntosIntermedios> puntosIntermediosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutas")
    private Collection<Estadisticas> estadisticasCollection;
    @JoinColumn(name = "codigovehiculo", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Vehiculos vehiculos;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuarios usuarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutas")
    private Collection<UsuariosPorRuta> usuariosPorRutaCollection;

    public Rutas() {
    }

    public Rutas(Integer codigo) {
        this.codigo = codigo;
    }

     public Rutas(Integer codigo, Usuarios usuarios, Vehiculos vehiculos, String descripricion, Date fecha, Integer precio, Integer cupodisponible, Integer estadoruta, Integer edadInicial, Integer edadFinal, String sexoPreferido, Integer tipoUsuario) {
        this.codigo = codigo;
        this.usuarios = usuarios;
        this.vehiculos = vehiculos;
        this.descripricion = descripricion;
        this.fecha = fecha;
        this.precio = precio;
        this.cupodisponible = cupodisponible;
        this.estadoruta = estadoruta;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.sexoPreferido = sexoPreferido;
        this.tipoUsuario = tipoUsuario;
    }
    public Rutas(Integer codigo, String descripricion, Date fecha, int precio, int cupodisponible, int estadoruta, int edadInicial, int edadFinal, String sexoPreferido, int tipoUsuario) {
        this.codigo = codigo;
        this.descripricion = descripricion;
        this.fecha = fecha;
        this.precio = precio;
        this.cupodisponible = cupodisponible;
        this.estadoruta = estadoruta;
        this.edadInicial = edadInicial;
        this.edadFinal = edadFinal;
        this.sexoPreferido = sexoPreferido;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripricion() {
        return descripricion;
    }

    public void setDescripricion(String descripricion) {
        this.descripricion = descripricion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCupodisponible() {
        return cupodisponible;
    }

    public void setCupodisponible(int cupodisponible) {
        this.cupodisponible = cupodisponible;
    }

    public int getEstadoruta() {
        return estadoruta;
    }

    public void setEstadoruta(int estadoruta) {
        this.estadoruta = estadoruta;
    }

    public int getEdadInicial() {
        return edadInicial;
    }

    public void setEdadInicial(int edadInicial) {
        this.edadInicial = edadInicial;
    }

    public int getEdadFinal() {
        return edadFinal;
    }

    public void setEdadFinal(int edadFinal) {
        this.edadFinal = edadFinal;
    }

    public String getSexoPreferido() {
        return sexoPreferido;
    }

    public void setSexoPreferido(String sexoPreferido) {
        this.sexoPreferido = sexoPreferido;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Collection<PuntosIntermedios> getPuntosIntermediosCollection() {
        return puntosIntermediosCollection;
    }

    public void setPuntosIntermediosCollection(Collection<PuntosIntermedios> puntosIntermediosCollection) {
        this.puntosIntermediosCollection = puntosIntermediosCollection;
    }

    public Collection<Estadisticas> getEstadisticasCollection() {
        return estadisticasCollection;
    }

    public void setEstadisticasCollection(Collection<Estadisticas> estadisticasCollection) {
        this.estadisticasCollection = estadisticasCollection;
    }

    public Vehiculos getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Vehiculos vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Collection<UsuariosPorRuta> getUsuariosPorRutaCollection() {
        return usuariosPorRutaCollection;
    }

    public void setUsuariosPorRutaCollection(Collection<UsuariosPorRuta> usuariosPorRutaCollection) {
        this.usuariosPorRutaCollection = usuariosPorRutaCollection;
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
        if (!(object instanceof Rutas)) {
            return false;
        }
        Rutas other = (Rutas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rutas[codigo=" + codigo + "]";
    }

}
