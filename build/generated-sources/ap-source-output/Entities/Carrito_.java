package Entities;

import Entities.Envio;
import Entities.Pedido;
import Entities.Productosencarrito;
import Entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T16:49:20")
@StaticMetamodel(Carrito.class)
public class Carrito_ { 

    public static volatile CollectionAttribute<Carrito, Envio> envioCollection;
    public static volatile SingularAttribute<Carrito, Date> fechaCarrito;
    public static volatile CollectionAttribute<Carrito, Productosencarrito> productosencarritoCollection;
    public static volatile SingularAttribute<Carrito, Usuario> cedula;
    public static volatile SingularAttribute<Carrito, String> estadoPedido;
    public static volatile SingularAttribute<Carrito, Integer> codigoCarrito;
    public static volatile CollectionAttribute<Carrito, Pedido> pedidoCollection;

}