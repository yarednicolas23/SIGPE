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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP PAVILION X360
 */
@Entity
@Table(name = "auditoriaproductos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditoriaproductos.findAll", query = "SELECT a FROM Auditoriaproductos a"),
    @NamedQuery(name = "Auditoriaproductos.findById", query = "SELECT a FROM Auditoriaproductos a WHERE a.id = :id"),
    @NamedQuery(name = "Auditoriaproductos.findByNombreAnterior", query = "SELECT a FROM Auditoriaproductos a WHERE a.nombreAnterior = :nombreAnterior"),
    @NamedQuery(name = "Auditoriaproductos.findByPrecioAnterior", query = "SELECT a FROM Auditoriaproductos a WHERE a.precioAnterior = :precioAnterior"),
    @NamedQuery(name = "Auditoriaproductos.findByNombreNuevo", query = "SELECT a FROM Auditoriaproductos a WHERE a.nombreNuevo = :nombreNuevo"),
    @NamedQuery(name = "Auditoriaproductos.findByPrecioNuevo", query = "SELECT a FROM Auditoriaproductos a WHERE a.precioNuevo = :precioNuevo"),
    @NamedQuery(name = "Auditoriaproductos.findByFecha", query = "SELECT a FROM Auditoriaproductos a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Auditoriaproductos.findByProceso", query = "SELECT a FROM Auditoriaproductos a WHERE a.proceso = :proceso"),
    @NamedQuery(name = "Auditoriaproductos.findByReferecia", query = "SELECT a FROM Auditoriaproductos a WHERE a.referecia = :referecia")})
public class Auditoriaproductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 25)
    @Column(name = "nombreAnterior")
    private String nombreAnterior;
    @Column(name = "precioAnterior")
    private Integer precioAnterior;
    @Size(max = 25)
    @Column(name = "nombreNuevo")
    private String nombreNuevo;
    @Column(name = "precioNuevo")
    private Integer precioNuevo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "proceso")
    private String proceso;
    @Column(name = "referecia")
    private Integer referecia;

    public Auditoriaproductos() {
    }

    public Auditoriaproductos(Integer id) {
        this.id = id;
    }

    public Auditoriaproductos(Integer id, String proceso) {
        this.id = id;
        this.proceso = proceso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreAnterior() {
        return nombreAnterior;
    }

    public void setNombreAnterior(String nombreAnterior) {
        this.nombreAnterior = nombreAnterior;
    }

    public Integer getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(Integer precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public Integer getPrecioNuevo() {
        return precioNuevo;
    }

    public void setPrecioNuevo(Integer precioNuevo) {
        this.precioNuevo = precioNuevo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Integer getReferecia() {
        return referecia;
    }

    public void setReferecia(Integer referecia) {
        this.referecia = referecia;
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
        if (!(object instanceof Auditoriaproductos)) {
            return false;
        }
        Auditoriaproductos other = (Auditoriaproductos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Auditoriaproductos[ id=" + id + " ]";
    }
    
}
