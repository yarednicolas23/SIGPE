package Entities;

import Entities.Envio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T16:49:20")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, Integer> codigoEmpresa;
    public static volatile CollectionAttribute<Empresa, Envio> envioCollection;
    public static volatile SingularAttribute<Empresa, String> nombreEmpresa;

}