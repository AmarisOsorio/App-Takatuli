```SQL

Create table tbRol(
UUID_Rol varchar2 (50) Primary key,
Nombre_Rol varchar2(20) UNIQUE not null
);

Create table tbUsuario(
UUID_Usuario varchar2 (50)Primary key,
Nombre_Usuario varchar2(20) UNIQUE not null,
Password_Usuario varchar2 (64) Unique not null,
Edad_Usuario Int not null CHECK (Edad_Usuario>=18),
Telefono_Usuario varchar2(9) not null Unique,
Fotos_usuario varchar2 (200),
Correo_Usuario varchar2(30) not null Unique,
DUI_Usuario varchar2(10) not null Unique,
UUID_Rol varchar2 (50),
CONSTRAINT fk_UUID_Rol
FOREIGN key (UUID_Rol)
References tbRol (UUID_Rol)
)

Create table tbTipoTuristico(
UUID_TipoLugarTuristico varchar2 (50) Primary key,
NombreTipo varchar2 (25) not null
);

Create table tbLugarTuristico(
UUID_LugarTuristico varchar2 (50) Primary key,
Nombre_LugarTuristico varchar2 (50) not null Unique,
Detalles_Lugar_Turistico varchar2(200) not null,
Fotos_Lugar_Turistico varchar2 (200) not null,
UUID_TipoLugarTuristico varchar2 (50),
CONSTRAINT FK_UUID_TipoLugarTuristico
FOREIGN key (UUID_TipoLugarTuristico)
References tbTipoTuristico (UUID_TipoLugarTuristico)
)
drop table tbPaquetesViajes
Create table tbPaquetesViajes(
UUID_Paquetes varchar2 (50) Primary key,
Nombre_Paquete varchar2 (25) not null,
Detalles_Paquete varchar2 (75) not null,
Transporte_Paquete number not null,
Precios_Entrada number not null,
Precio_Paquetes number  not null,
fecha_Paquete date not null,
UUID_LugarTuristico varchar2 (50),
Constraint Fk_UUID_LugarTuristico
FOREIGN key (UUID_LugarTuristico)
References tbLugarTuristico (UUID_LugarTuristico)
)


Create table tbRestaurante (
UUID_Restaurante varchar2 (50) Primary key,
Nombre_Restaurante varchar2 (50) not null Unique,
Menu_Restaurante varchar2 (400)not null,
Foto_Menu varchar2 (200) not null,
Fotos_Restaurante varchar2 (200) not null
)


Create table tbHospedaje (
UUID_Hospedaje varchar2 (50) Primary key,
Nombre_Hospedaje varchar2 (50) not null Unique,
Precio_Hospedaje number(4,2) not null,
Detalles_Hospedaje varchar2 (200) not null,
Fotos_Hospedaje varchar2 (200) not null
)

Create table tbUbicaciones(
UUID_Ubicacion varchar2 (50) Primary key,
Departamento varchar2 (20) not null
)

Create table tbDestinos(
UUID_Destinos varchar2 (50)Primary key,
UUID_LugarTuristico varchar2 (50),
UUID_Hospedaje varchar2 (50),
UUID_Restaurante varchar2 (50),
UUID_Ubicacion varchar2 (50),
Constraint fk_UUID_LugarTuristico2
FOREIGN KEY (UUID_LugarTuristico)
References tbLugarTuristico (UUID_LugarTuristico),
Constraint Fk_UUID_Hospedaje1
FOREIGN key  (UUID_Hospedaje)
References tbHospedaje (UUID_Hospedaje),
Constraint Fk_UUID_Restaurante1
FOREIGN key (UUID_Restaurante)
References tbRestaurante (UUID_Restaurante),
Constraint Fk_UUID_Ubicacion
FOREIGN Key (UUID_Ubicacion)
References tbUbicaciones (UUID_Ubicacion)
)

Create table tbDetallesDestinos(
UUID_DetallesDestinos varchar2 (50) Primary key,
UUID_Destinos varchar2 (50),
UUID_Usuario varchar2 (50),
Constraint Fk_UUID_Destinos1
FOREIGN key (UUID_Destinos)
REFERENCES tbDestinos (UUID_Destinos),
Constraint  fk_UUID_Usuario1
FOREIGN KEY (UUID_Usuario)
REFERENCES tbUsuario (UUID_Usuario)
)

Create table tbReseñas (
UUID_Reseña varchar2 (50) Primary key,
Reseña_Viaje varchar2 (200),
UUID_DetallesDestinos varchar2 (50),
Constraint Fk_UUID_DetallesDestinos
FOREIGN key (UUID_DetallesDestinos)
References TBDETALLESDESTINOS (UUID_DetallesDestinos)
)


//Guardara todas las inserciones y eliminaciones de la app
Create table tbAuditoria(
Id_Auditoria number,
Nombre_Tabla varchar2 (50),
Ejecucion_Realizada varchar2(50),
Fecha varchar2 (25),
Usuario varchar2 (50),
Pk_Modificacion number,
Modificaciones varchar2 (50)
)

///Datos insertados a todas las tablas
insert all
into tbRol (UUID_Rol,Nombre_Rol ) values ('1', 'Administrador')
into tbRol (UUID_Rol,Nombre_Rol ) values ('2', 'Turista')
into tbRol (UUID_Rol,Nombre_Rol ) values ('3', 'Dueño')
select *from dual;

insert all
 into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario,
Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('1', 'Edenilson Amaya','Ede12345', 18, '1234-3333',
'Edenilson1@gmail.com', '12345678-9', '1' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario,
Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('2', 'Jose Luis', 'Jose1234', 20, '1234-5677',
'ChepeLuis1@gmail.com', '11345678-9', '1' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario,
Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('3', 'Amaris Osorio', 'Ama12345', 21, '1234-5688',
'Amaris1@gmail.com', '11145678-9', '2' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario,
Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('4', 'Bryan Cornejo', 'Cor12345', 22, '1234-5555',
'Cornejo1@gmail.com', '22345678-9', '3' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario,
Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('5', 'Rafael Menendez', 'Rafa1234', 25, '1111-5678',
'Rafael1@gmail.com', '33345678-9', '2' )
select * from dual;

insert all
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante,
Menu_Restaurante, Foto_Menu, Fotos_Restaurante)
Values ('1', 'Tacos Hermanos','Taco, Tortas, Sopa de tortilla', 'img1', 'img2' )
into tbRestaurante (UUID_Restaurante, Nombre_Restaurante,
Menu_Restaurante, Foto_Menu, Fotos_Restaurante)
Values ('2', 'La pampa','Cafe ', 'img3', 'img4' )
into tbRestaurante (UUID_Restaurante, Nombre_Restaurante,
Menu_Restaurante, Foto_Menu, Fotos_Restaurante)
Values ('3', 'Restaurante Don Li','Watan de Pollo' , 'img5', 'img6')
into tbRestaurante (UUID_Restaurante, Nombre_Restaurante,
Menu_Restaurante, Foto_Menu, Fotos_Restaurante)
Values ('4', 'Pizza Boom','Diferentes tipos de Pizza', 'img7', 'img8' )
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante,
Menu_Restaurante, Foto_Menu, Fotos_Restaurante)
Values ('5', 'La Hola ','Pescado,Camarones al ajillo, ' , 'img9', 'img10')
select * from dual;

insert all
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('1',
'Balñario')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('2', 'Playa')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('3',
'Montaña')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('4', 'Volcan')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('5', 'Lago')
select * from dual;

insert all
into tblugarturistico(UUID_LugarTuristico,
Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico,
uuid_tipolugarturistico )
Values ('1', 'Termos del Rio' ,'Balñario ubicado en Ciudad Arce, con 8
piscinas', 'urlimg1', '1')
into tblugarturistico(UUID_LugarTuristico,
Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico,
uuid_tipolugarturistico )
Values ('2', 'El tunco' ,'Ubicada en la libertad', 'urlimg2', '2')
into tblugarturistico(UUID_LugarTuristico,
Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico,
uuid_tipolugarturistico )
Values ('3', 'Cerro Verde' ,'Este es un volcan extinto', 'urlimg3', '3')
into tblugarturistico(UUID_LugarTuristico,
Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico,
uuid_tipolugarturistico )
Values ('4', 'Izalco' ,'Se origeno en el año 1770', 'urlimg4', '4')
into tblugarturistico(UUID_LugarTuristico,
Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico,
uuid_tipolugarturistico )
Values ('5', 'Coatepeque' ,'Era un volcan', 'urlimg5', '5')
select * from dual;

insert all
into tbHospedaje (uuid_hospedaje, nombre_hospedaje,precio_hospedaje,
fotos_hospedaje, detalles_hospedaje)
vALUES (1, 'Hotel de Montaña Buena Vista', 66, 'img1', 'Hola')
into tbHospedaje (uuid_hospedaje, nombre_hospedaje,precio_hospedaje,
fotos_hospedaje, detalles_hospedaje)
vALUES (2, 'Hotel Entre Pinos', 45, 'img2', 'Adios')
into tbHospedaje (uuid_hospedaje, nombre_hospedaje,precio_hospedaje,
fotos_hospedaje, detalles_hospedaje)
vALUES (3, 'La Casona del Abuelo', 25, 'img3', 'Nose')
into tbHospedaje (uuid_hospedaje, nombre_hospedaje,precio_hospedaje,
fotos_hospedaje, detalles_hospedaje)
vALUES (4, 'Brisas de San Ignacio', 66, 'img4', 'Somos')
into tbHospedaje (uuid_hospedaje, nombre_hospedaje,precio_hospedaje,
fotos_hospedaje, detalles_hospedaje)
vALUES (5, 'Hostal Posada Real', 31, 'img5', 'Hotel')
select * from dual

insert all
into tbUbicaciones (UUID_Ubicacion, Departamento) values('1', 'Santa Ana')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('2', 'SonSonate')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('3', 'Ahuchapan')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('4', 'La Libertad')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('5', 'Chalatenango')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('6', 'San Salvador')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('7', 'Cuscatlan')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('8', 'Cabañas')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('9', 'Morazan')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('10', 'La paz')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('11', 'San Vicente')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('12', 'Usulutan')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('13', 'San Miguel')
into tbUbicaciones (UUID_Ubicacion, Departamento) values('14', 'La union')
select * from dual;

insert all
into tbDestinos (UUID_Destinos, uuid_lugarturistico,
uuid_ubicacion)VALUES (1,1,1 )
into tbDestinos (UUID_Destinos, UUID_Restaurante, uuid_ubicacion)VALUES (2,2,2 )
into tbDestinos (UUID_Destinos, UUID_Restaurante, uuid_ubicacion)VALUES (3,3,3 )
into tbDestinos (UUID_Destinos, uuid_lugarturistico,
uuid_ubicacion)VALUES (4,4,4 )
into tbDestinos (UUID_Destinos, UUID_Hospedaje, uuid_ubicacion)VALUES (5,5,5 )
select * from dual


insert all
into tbPaquetesViajes (UUID_Paquetes, Nombre_Paquete, Detalles_Paquete, Transporte_Paquete, Precios_Entrada, Precio_Paquetes, fecha_Paquete, UUID_LugarTuristico)
Values (1, 'Familiar', 'Paquete especial para 4 personas', 60, 9, 69, '2/9/24', 1)
into tbPaquetesViajes (UUID_Paquetes, Nombre_Paquete, Detalles_Paquete, Transporte_Paquete, Precios_Entrada,Precio_Paquetes, fecha_Paquete, UUID_LugarTuristico)
Values (2, 'Paquete Aventurero','Para 1 persona', 9, 5, 14, '3/9/24', 2 )
into tbPaquetesViajes (UUID_Paquetes, Nombre_Paquete, Detalles_Paquete, Transporte_Paquete, Precios_Entrada, Precio_Paquetes, fecha_Paquete, UUID_LugarTuristico)
Values (3, 'Paquete Soñador','1 persona dia y noche', 10, 5, 15, '4/9/24', 3 )
into tbPaquetesViajes (UUID_Paquetes, Nombre_Paquete, Detalles_Paquete, Transporte_Paquete, Precios_Entrada, Precio_Paquetes, fecha_Paquete, UUID_LugarTuristico)
Values (4, 'Paquete Volador','Una gran aventura a Colombia', 80, 15, 95, '5/9/24', 4 )
into tbPaquetesViajes (UUID_Paquetes, Nombre_Paquete, Detalles_Paquete, Transporte_Paquete, Precios_Entrada,Precio_Paquetes, fecha_Paquete, UUID_LugarTuristico)
Values (5, 'Paquete Acuatico','Sumerjete en las profundidades', 15, 5, 20, '6/9/24', 5 )
select * from dual

insert all
into tbReseñas (UUID_Reseña, Reseña_Viaje, UUID_DetallesDestinos)
Values (1, 'Fue aceptable', 1)
into tbReseñas (UUID_Reseña, Reseña_Viaje, UUID_DetallesDestinos)
Values (2, 'Pesimo servicio', 2)
into tbReseñas (UUID_Reseña, Reseña_Viaje, UUID_DetallesDestinos)
Values (3, 'Servicio Aceptable', 3)
into tbReseñas (UUID_Reseña, Reseña_Viaje, UUID_DetallesDestinos)
Values (4, 'Excelente', 4)
into tbReseñas (UUID_Reseña, Reseña_Viaje, UUID_DetallesDestinos)
Values (5, 'Mejorable', 5)
select * from dual

insert all
into tbDetallesDestinos (UUID_DetallesDestinos, UUID_Destinos,  UUID_Usuario)
Values (1, 1, 1)
into tbDetallesDestinos (UUID_DetallesDestinos, UUID_Destinos,  UUID_Usuario)
Values (2, 2, 1)
into tbDetallesDestinos (UUID_DetallesDestinos, UUID_Destinos,  UUID_Usuario)
Values (3, 3, 1)
into tbDetallesDestinos (UUID_DetallesDestinos, UUID_Destinos,  UUID_Usuario)
Values (4, 4, 4)
into tbDetallesDestinos (UUID_DetallesDestinos, UUID_Destinos,  UUID_Usuario)
Values (5, 5, 4)
select* from dual


///Iner joins
select * from tbDestinos

//inner join de usuario y detalles destinos
select
k.Nombre_Usuario,
l.UUID_Destinos as Destino
from
tbDetallesDestinos j
left join
tbDestinos l ON j.UUID_Destinos  = l.UUID_Destinos
left join
tbUsuario k On j.UUID_Usuario = k.UUID_Usuario;


//inner join de usaurio y rol
SELECT
    a.UUID_Usuario,
    a.Nombre_Usuario,
    a.Edad_Usuario ,
    a.Telefono_Usuario ,
    a.Correo_Usuario,
    a.DUI_Usuario,
    b.Nombre_Rol as Rol
FROM
    tbUsuario a
INNER JOIN
    tbRol b  ON a.UUID_Rol = b.UUID_Rol;


//iner join tinpo turistico con lugar turisitico
    SELECT
    c.UUID_LugarTuristico,
    c.Nombre_LugarTuristico,
    c.Detalles_Lugar_Turistico,
    c.Fotos_Lugar_Turistico,
    d.NombreTipo as TipoTuristico
FROM
    tbLugarTuristico c
INNER JOIN
    tbTipoTuristico d  ON c.UUID_TipoLugarTuristico = d.UUID_TipoLugarTuristico;

//left join de lugar turistico, Restaurante, Hospedaje a destinos
      select
    f.Nombre_LugarTuristico as LugarTuristico,
    G.Nombre_Restaurante as Restaurante,
    h.Nombre_Hospedaje as Hospedaje,
    i.Departamento as Ubicacion
    from
    tbDestinos e
    Left join
    tbLugarTuristico f On e.uuid_lugarturistico = f.uuid_lugarturistico
    Left join
    tbRestaurante G On e.UUID_Restaurante = G.UUID_Restaurante
    Left join
    tbHospedaje h on e.UUID_Hospedaje = h.UUID_Hospedaje
    Left join
    tbUbicaciones i on e.UUID_Ubicacion = i.UUID_Ubicacion;

//de lugar turistico a paquetes viajes
select
n.Nombre_Paquete,
n.Detalles_Paquete,
n.Transporte_Paquete,
n.Precios_Entrada,
n.Precio_Paquetes,
n.Fecha_Paquete,
m.Nombre_LugarTuristico
from 
tbPaquetesViajes n
inner join 
tbLugarTuristico m On n.UUID_LugarTuristico = m.UUID_LugarTuristico;

select
o.Reseña_Viaje,
p.UUID_DETALLESDESTINOS
FROM 
TbReseñaS o
inner join
tbDetallesDestinos p ON o.UUID_DETALLESDESTINOS = p.UUID_DETALLESDESTINOS;


//Secuencia de la tabla auditoria
    create SEQUENCE Auditoria
start with 1
increment by 1;

//Procesos Para guardar insert y delet en la tabla auditoria

//Proceso para tabla usuario
    CREATE OR REPLACE TRIGGER trg_audi_usuarioS
AFTER INSERT OR DELETE ON tbUsuario
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO tbAuditoria(
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Agregar Usuario',
             SYSDATE,
              USER,

            :NEW.UUID_Usuario,
            'Correo=' || :NEW.Correo_Usuario
        );

    ELSIF DELETING THEN
        INSERT INTO tbAuditoria (
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Eliminar Usuario',
            USER,
            SYSDATE,
            :OLD.UUID_usuario,
            'Correo=' || :OLD.Correo_Usuario
        );
    END IF;
END;


//Proceso para guardar los lugares turisticos
CREATE OR REPLACE TRIGGER trg_audi_Turistico
AFTER INSERT OR DELETE ON tbLugarTuristico
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO tbAuditoria(
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Agregar Lugar',
             SYSDATE,
              USER,
            :NEW.UUID_LugarTuristico,
            'Nombre del Lugar=' || :NEW.Nombre_LugarTuristico
        );

    ELSIF DELETING THEN
        INSERT INTO tbAuditoria (
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Eliminar Luagr',
            USER,
            SYSDATE,
            :OLD.UUID_LugarTuristico,
            'Nombre del Lugar=' || :OLD.Nombre_LugarTuristico
        );
    END IF;
END;

//Proceso para guardar los Restaurantes en AUditoria
CREATE OR REPLACE TRIGGER trg_audi_Restaurante
AFTER INSERT OR DELETE ON tbRestaurante
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO tbAuditoria(
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Agregar Restaurante',
             SYSDATE,
              USER,
            :NEW.UUID_Restaurante,
            'Nombre del Restaurante=' || :NEW.Nombre_Restaurante
        );

    ELSIF DELETING THEN
        INSERT INTO tbAuditoria (
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Eliminar Restaurante',
            USER,
            SYSDATE,
            :OLD.UUID_Restaurante,
            'Nombre del Restaurante=' || :OLD.Nombre_Restaurante
        );
    END IF;
END;

//Proceso para guardar los Hospedajes en Auditoria
CREATE OR REPLACE TRIGGER trg_audi_Hospedaje
AFTER INSERT OR DELETE ON tbHospedaje
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO tbAuditoria(
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Agregar Hospedaje',
             SYSDATE,
              USER,
            :NEW.UUID_Hospedaje,
            'Nombre del Hospedaje=' || :NEW.Nombre_Hospedaje
        );

    ELSIF DELETING THEN
        INSERT INTO tbAuditoria (
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Eliminar Hospedaje',
            USER,
            SYSDATE,
            :OLD.UUID_Hospedaje,
            'Nombre del Hospedaje=' || :OLD.Nombre_Hospedaje
        );
    END IF;
END;

//Proceso para guardar los Paquetes de viajes en Auditoria
CREATE OR REPLACE TRIGGER trg_audi_PaqueteViajes
AFTER INSERT OR DELETE ON tbPaquetesViajes
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO tbAuditoria(
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Agregado Paquete',
             SYSDATE,
              USER,
            :NEW.UUID_Paquetes,
            'Nombre del Paquete=' || :NEW.Nombre_Paquete
        );

    ELSIF DELETING THEN
        INSERT INTO tbAuditoria (
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Eliminar Paquete',
            USER,
            SYSDATE,
            :OLD.UUID_Paquetes,
            'Nombre del Paquete=' || :OLD.Nombre_Paquete
        );
    END IF;
END;


//Proceso para guardar las reseñas en auditoria
CREATE OR REPLACE TRIGGER trg_audi_Reseñas
AFTER INSERT OR DELETE ON tbReseñas
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO tbAuditoria(
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Reseña Agregada',
             SYSDATE,
              USER,
            :NEW.UUID_Reseña,
            'Reseña =' || :NEW.Reseña_Viaje
        );

    ELSIF DELETING THEN
        INSERT INTO tbAuditoria (
            Id_Auditoria,
            Nombre_Tabla,
            Ejecucion_Realizada,
            Fecha,
            Usuario,
            Pk_Modificacion,
            Modificaciones
        ) VALUES (
            Auditoria.NEXTVAL,
            'Usuario',
            'Eliminar Reseña',
            USER,
            SYSDATE,
            :OLD.UUID_Reseña,
            'Reseña nueva=' || :OLD.Reseña_Viaje
        );
    END IF;
END;


//Proceso de actualizar en la tabla de Hospedaje
    Create or replace PROCEDURE Actualizar_Hospedaje(
p_UUID_Hospedaje In tbHospedaje.UUID_Hospedaje%TYPE,
p_nombre_Hospedaje IN tbHospedaje.nombre_Hospedaje%TYPE,
p_Precio_Hospedaje IN tbHospedaje.Precio_Hospedaje%TYPE,
p_Destales_Hospedaje IN tbHospedaje.Detalles_Hospedaje%TYPE,
p_Fotos_Hospedaje in tbHospedaje.Fotos_Hospedaje%TYPE
)
AS
BEGIN
Update tbHospedaje
set nombre_Hospedaje= p_nombre_Hospedaje,
Precio_Hospedaje = p_Precio_Hospedaje,
Detalles_Hospedaje = p_Destales_Hospedaje,
Fotos_Hospedaje = p_Fotos_Hospedaje
Where UUID_Hospedaje = p_UUID_Hospedaje;
End;

//Select de todas las tablas
    select * from tbRol
    Select * from tbUsuario
    select * from tbAuditoria
    Select * from tbTipoTuristico
    select * from tbLugarTuristico
    select * from tbRestaurante
    select * from tbHospedaje
    select * from tbPaquetesViajes
    select * from tbDestinos
    select * from tbDetallesDestinos
    select * from tbUbicaciones
    select * from tbReseñas
    
    //fin de la base
    
    
    

```



    

    


