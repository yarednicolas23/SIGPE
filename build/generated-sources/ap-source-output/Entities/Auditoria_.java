package Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-25T15:42:41")
@StaticMetamodel(Auditoria.class)
public class Auditoria_ { 

    public static volatile SingularAttribute<Auditoria, Integer> idAuditoria;
    public static volatile SingularAttribute<Auditoria, Date> fecha;
    public static volatile SingularAttribute<Auditoria, String> comentario;

}