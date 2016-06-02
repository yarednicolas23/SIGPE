package Entities;

import Entities.Productosencarrito;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T16:49:19")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Integer> precio;
    public static volatile SingularAttribute<Producto, String> estadoProducto;
    public static volatile SingularAttribute<Producto, String> foto;
    public static volatile CollectionAttribute<Producto, Productosencarrito> productosencarritoCollection;
    public static volatile SingularAttribute<Producto, Integer> favoritos;
    public static volatile SingularAttribute<Producto, Integer> referecia;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile SingularAttribute<Producto, Integer> cantidadDisponible;

}