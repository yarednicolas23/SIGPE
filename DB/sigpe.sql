create database sigpe;


use sigpe;

create table rol(
	idRol int PRIMARY KEY,
	rol varchar(40)
)ENGINE=InnoDB;

create table usuario(
	cedula bigint PRIMARY KEY not null,
	correo varchar(40),
	Nombres varchar (40),
	Apellidos varchar(40),
	sexo enum ('Femenino ','Maculino'),
	telefono bigint, 
	constrasena varchar(40),
	Rol	int,
	foto varchar (40),
	FOREIGN KEY (rol) REFERENCES rol(idRol)
    ON DELETE NO ACTION ON UPDATE CASCADE

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
	codigoCarrito int,
	montoTotal bigint,
	fechaPedido datetime NOT NULL,

	FOREIGN KEY (codigoCarrito) REFERENCES carrito(codigoCarrito)
    ON DELETE NO ACTION ON UPDATE CASCADE
)ENGINE=InnoDB;


CREATE TABLE productosencarrito(
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  codCarrito int(11),
  refereciaProducto int(11),
  cantidad int(11),
  FOREIGN KEY (codCarrito) REFERENCES carrito (codigoCarrito),
  FOREIGN KEY (refereciaProducto) REFERENCES producto (referecia) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB;


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

select pedido.id, pedido.codigoCarrito, pedido.montoTotal,pedido.fechaPedido from usuario,pedido where pedido.codigoCarrito=2 and usuario.cedula=


select pedido.id, pedido.codigoCarrito, pedido.montoTotal,pedido.fechaPedido from usuario,pedido,carrito where pedido.codigoCarrito=carrito.cedula=98082352922

select pedido.id, pedido.codigoCarrito, pedido.montoTotal,pedido.fechaPedido from usuario,pedido,carrito where usuario.cedula=98082352922;


select * from pedido where codigoCarrito=2;