/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP PAVILION X360
 */
@Entity
@Table(name = "envio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envio.findAll", query = "SELECT e FROM Envio e"),
    @NamedQuery(name = "Envio.findByCodigoEnvio", query = "SELECT e FROM Envio e WHERE e.codigoEnvio = :codigoEnvio"),
    @NamedQuery(name = "Envio.findByFechaEnvio", query = "SELECT e FROM Envio e WHERE e.fechaEnvio = :fechaEnvio")})
public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoEnvio")
    private Integer codigoEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @JoinColumn(name = "codigoCarrito", referencedColumnName = "codigoCarrito")
    @ManyToOne(optional = false)
    private Carrito codigoCarrito;
    @JoinColumn(name = "empresa", referencedColumnName = "codigoEmpresa")
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Envio() {
    }

    public Envio(Integer codigoEnvio) {
        this.codigoEnvio = codigoEnvio;
    }

    public Envio(Integer codigoEnvio, Date fechaEnvio) {
        this.codigoEnvio = codigoEnvio;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getCodigoEnvio() {
        return codigoEnvio;
    }

    public void setCodigoEnvio(Integer codigoEnvio) {
        this.codigoEnvio = codigoEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Carrito getCodigoCarrito() {
        return codigoCarrito;
    }

    public void setCodigoCarrito(Carrito codigoCarrito) {
        this.codigoCarrito = codigoCarrito;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEnvio != null ? codigoEnvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envio)) {
            return false;
        }
        Envio other = (Envio) object;
        if ((this.codigoEnvio == null && other.codigoEnvio != null) || (this.codigoEnvio != null && !this.codigoEnvio.equals(other.codigoEnvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Envio[ codigoEnvio=" + codigoEnvio + " ]";
    }
    
}
