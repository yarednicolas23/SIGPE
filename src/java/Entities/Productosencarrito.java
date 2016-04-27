/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP PAVILION X360
 */
@Entity
@Table(name = "productosencarrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productosencarrito.findAll", query = "SELECT p FROM Productosencarrito p"),
    @NamedQuery(name = "Productosencarrito.findById", query = "SELECT p FROM Productosencarrito p WHERE p.id = :id")})
public class Productosencarrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "codCarrito", referencedColumnName = "codigoCarrito")
    @ManyToOne
    private Carrito codCarrito;
    @JoinColumn(name = "refereciaProducto", referencedColumnName = "referecia")
    @ManyToOne
    private Producto refereciaProducto;

    public Productosencarrito() {
    }

    public Productosencarrito(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Carrito getCodCarrito() {
        return codCarrito;
    }

    public void setCodCarrito(Carrito codCarrito) {
        this.codCarrito = codCarrito;
    }

    public Producto getRefereciaProducto() {
        return refereciaProducto;
    }

    public void setRefereciaProducto(Producto refereciaProducto) {
        this.refereciaProducto = refereciaProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productosencarrito)) {
            return false;
        }
        Productosencarrito other = (Productosencarrito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Productosencarrito[ id=" + id + " ]";
    }
    
}
