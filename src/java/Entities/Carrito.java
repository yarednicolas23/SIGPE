/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP PAVILION X360
 */
@Entity
@Table(name = "carrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c"),
    @NamedQuery(name = "Carrito.findByCodigoCarrito", query = "SELECT c FROM Carrito c WHERE c.codigoCarrito = :codigoCarrito"),
    @NamedQuery(name = "Carrito.findByFechaCarrito", query = "SELECT c FROM Carrito c WHERE c.fechaCarrito = :fechaCarrito"),
    @NamedQuery(name = "Carrito.findByEstadoPedido", query = "SELECT c FROM Carrito c WHERE c.estadoPedido = :estadoPedido")})
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCarrito")
    private Integer codigoCarrito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaCarrito")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarrito;
    @Size(max = 11)
    @Column(name = "estadoPedido")
    private String estadoPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCarrito")
    private Collection<Envio> envioCollection;
    @OneToMany(mappedBy = "codigoCarrito")
    private Collection<Pedido> pedidoCollection;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne
    private Usuario cedula;
    @OneToMany(mappedBy = "codCarrito")
    private Collection<Productosencarrito> productosencarritoCollection;

    public Carrito() {
    }

    public Carrito(Integer codigoCarrito) {
        this.codigoCarrito = codigoCarrito;
    }

    public Carrito(Integer codigoCarrito, Date fechaCarrito) {
        this.codigoCarrito = codigoCarrito;
        this.fechaCarrito = fechaCarrito;
    }

    public Integer getCodigoCarrito() {
        return codigoCarrito;
    }

    public void setCodigoCarrito(Integer codigoCarrito) {
        this.codigoCarrito = codigoCarrito;
    }

    public Date getFechaCarrito() {
        return fechaCarrito;
    }

    public void setFechaCarrito(Date fechaCarrito) {
        this.fechaCarrito = fechaCarrito;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @XmlTransient
    public Collection<Envio> getEnvioCollection() {
        return envioCollection;
    }

    public void setEnvioCollection(Collection<Envio> envioCollection) {
        this.envioCollection = envioCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    public Usuario getCedula() {
        return cedula;
    }

    public void setCedula(Usuario cedula) {
        this.cedula = cedula;
    }

    @XmlTransient
    public Collection<Productosencarrito> getProductosencarritoCollection() {
        return productosencarritoCollection;
    }

    public void setProductosencarritoCollection(Collection<Productosencarrito> productosencarritoCollection) {
        this.productosencarritoCollection = productosencarritoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCarrito != null ? codigoCarrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.codigoCarrito == null && other.codigoCarrito != null) || (this.codigoCarrito != null && !this.codigoCarrito.equals(other.codigoCarrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Carrito[ codigoCarrito=" + codigoCarrito + " ]";
    }
    
}
