package Entities;

import Entities.Carrito;
import Entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-22T09:46:37")
@StaticMetamodel(Productosencarrito.class)
public class Productosencarrito_ { 

    public static volatile SingularAttribute<Productosencarrito, Producto> refereciaProducto;
    public static volatile SingularAttribute<Productosencarrito, Carrito> codCarrito;
    public static volatile SingularAttribute<Productosencarrito, Integer> id;

}