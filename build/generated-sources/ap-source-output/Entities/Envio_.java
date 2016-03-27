package Entities;

import Entities.Carrito;
import Entities.Empresa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-27T12:02:21")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, Integer> codigoEnvio;
    public static volatile SingularAttribute<Envio, Date> fechaEnvio;
    public static volatile SingularAttribute<Envio, Carrito> codigoCarrito;
    public static volatile SingularAttribute<Envio, Empresa> empresa;

}