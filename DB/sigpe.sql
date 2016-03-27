create database sigpe;


use sigpe;



create table usuario(
	cedula bigint PRIMARY KEY not null,
	correo varchar(40),
	Nombres varchar (40),
	Apellidos varchar(40),
	sexo enum ('Femenino ','Maculino'),
	telefono bigint,
	clave varchar(40),
	Rol	enum ('Cliente','Administrador','Empresa'),
	foto varchar (40)
)ENGINE=InnoDB;


create table producto(
	referecia int PRIMARY KEY not null auto_increment,
	nombre varchar(40),
	precio int,
	descripcion	varchar(300),
	foto varchar(45),
	cantidadDisponible int NOT NULL, 
	estadoProducto enum('Disponible', 'Por agotarse', 'Agotado')
)ENGINE=InnoDB;

create table carrito(
	codigoCarrito int NOT NULL auto_increment primary key,
	fechaCarrito	datetime NOT NULL,
    estadoPedido enum ('En proceso', 'Aprobado', 'Cancelado'),
    cedula bigint,

	FOREIGN KEY (cedula) REFERENCES usuario(cedula)
    ON DELETE NO ACTION ON UPDATE CASCADE

)ENGINE=InnoDB;
 
create table pedido(
	id int not null PRIMARY KEY auto_increment,
	codigoPedido int,
	fechaPedido datetime NOT NULL,
	referecia int,

	FOREIGN KEY (codigoPedido) REFERENCES carrito(codigoCarrito),
	FOREIGN KEY (referecia) REFERENCES producto(referecia)
    ON DELETE NO ACTION ON UPDATE CASCADE
)ENGINE=InnoDB;


create table Empresa(
	codigoEmpresa		int NOT NULL auto_increment primary key,
	nombreEmpresa	ENUM ('deprisa', 'enar', 'inter rapidisimo', 'servientrega') not null
)ENGINE=InnoDB;


create table Envio(
	codigoEnvio			int NOT NULL auto_increment primary key,
	codigoCarrito		int NOT NULL,
	fechaEnvio		datetime NOT NULL,
	empresa			int NOT NULL,

	FOREIGN KEY (codigoCarrito) REFERENCES Carrito(codigoCarrito)
    ON DELETE NO ACTION ON UPDATE CASCADE,

    FOREIGN KEY (empresa) REFERENCES Empresa(codigoEmpresa)
    ON DELETE NO ACTION ON UPDATE CASCADE
)ENGINE=InnoDB;



CREATE TABLE Auditoria (
	idAuditoria 	INT AUTO_INCREMENT PRIMARY KEY, 
	comentario	varchar (45),
	fecha 			datetime
)ENGINE=InnoDB;

create table auditoriaProductos(
id					int primary key not null  auto_increment,
nombreAnterior		varchar(25),
precioAnterior		int,
nombreNuevo 		varchar(25) ,
precioNuevo			int ,
fecha				datetime,
proceso				varchar(25)not null,
referecia			int
)ENGINE=InnoDB;


DELIMITER //
CREATE TRIGGER insertarProductos AFTER INSERT ON Producto
FOR EACH ROW
BEGIN
INSERT INTO auditoriaProductos(nombreNuevo, precioNuevo, fecha, proceso, referecia)
VALUES (NEW.nombre, NEW.precio, NOW(), 'Insertado', NEW.referecia);
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER modificarProductos BEFORE UPDATE ON Producto
FOR EACH ROW
BEGIN
INSERT INTO auditoriaProductos(nombreAnterior, precioAnterior, nombreNuevo, PrecioNuevo, fecha, proceso, referecia)
VALUES (OLD.nombre, OLD.precio, NEW.nombre, NEW.precio, NOW(), 'Modificado', NEW.referecia);
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER eliminarProductos AFTER DELETE ON Producto
FOR EACH ROW
BEGIN
INSERT INTO auditoriaProductos(nombreAnterior, precioAnterior, fecha, proceso, referecia)
VALUES (OLD.nombre, OLD.precio, NOW(), 'Eliminado', OLD.referecia);
END //
DELIMITER ;

DELIMITER // 
CREATE TRIGGER fechaenvio
BEFORE UPDATE
ON envio
FOR EACH ROW
BEGIN
SET NEW.fechaEnvio = NOW();
END//
DELIMITER ; 





listaR=getListaCuentas();
        for (int i = 0; i < listaR.size(); i++) {
            listaR.get(i).setSaldo(listaR.get(i).getSaldo()+Double.parseDouble(sald));            
            cuentaFacade.edit(cuenta);
            return "";
        }
        return "";







        <div class="row">
            <div class="col s8 offset-s3">
                <h4>Consignar Saldo</h4>
                <h:form>
                    <div class="row white-text">
                        <div class="col s5 offset-s3 black-transparent">
                            <div class="input-field col s10">
                                <i class="material-icons">monetization_on</i>
                                <h:inputText class="validate" value="#{controladorCuenta.sald}" required=""></h:inputText>
                                
                                <label for="icon_prefix" data-error="el campo es invalido">Monto a Consignar</label>
                            </div>
                            <div class="col s10">
                                <h:commandButton action="#{controladorCuenta.consignar()}" value="consignar" class="waves-effect waves-red btn pink col s12"></h:commandButton>                                
                            </div>
                        </div>
                    </div>            
                </h:form>
            </div>
        </div>