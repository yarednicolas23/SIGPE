package Entities;

import Entities.Pedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-02T21:04:05")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Integer> precio;
    public static volatile SingularAttribute<Producto, String> estadoProducto;
    public static volatile SingularAttribute<Producto, String> foto;
    public static volatile CollectionAttribute<Producto, Pedido> pedidoCollection;
    public static volatile SingularAttribute<Producto, Integer> referecia;
    public static volatile SingularAttribute<Producto, String> nombre;
    public static volatile SingularAttribute<Producto, Integer> cantidadDisponible;

}