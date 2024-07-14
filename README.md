Create table tbRol(
UUID_Rol varchar2 (50) Primary key,
Nombre_Rol varchar2(20) UNIQUE not null
);


Create table tbUsuario(
UUID_Usuario varchar2 (50)Primary key,
Nombre_Usuario varchar2(20) UNIQUE not null,
Password_Usuario varchar2 (20) Unique not null,
Edad_Usuario Int not null CHECK (Edad_Usuario>=18), 
Telefono_Usuario varchar2(9) not null Unique,
Correo_Usuario varchar2(30) not null Unique,
DUI_Usuario varchar2(10) not null Unique, 
UUID_Rol varchar2 (50),
CONSTRAINT fk_UUID_Rol
FOREIGN key (UUID_Rol)
References tbRol (UUID_Rol)
)
//Esta bmodo Int por un error en la app 

Create table tbTipoTuristico(
UUID_TipoLugarTuristico varchar2 (50) Primary key,
NombreTipo varchar2 (25) not null
);

Create table tbPaquetesViajes(
UUID_Paquetes varchar2 (50) Primary key,
Nombre_Paquete varchar2 (25) not null,
Detalles_Paquete varchar2 (75) not null,
Precio_Paquetes number (3,2) not null, 
fecha_Paquete date not null,
UUID_LugarTuristico varchar2 (50),
Constraint Fk_UUID_LugarTuristico
FOREIGN key (UUID_LugarTuristico)
References tbLugarTuristico (UUID_LugarTuristico)
)

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

Create table tbRestaurante (
UUID_Restaurante varchar2 (50) Primary key,
Nombre_Restaurante varchar2 (50) not null Unique,
Menu_Restaurante varchar2 (400)not null)
//Foto_Menu varchar2 (200) not null,
//Fotos_Restaurante varchar2 (200) not null


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
UUUID_Destinos varchar2 (50)Primary key,
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
UUUID_Destinos varchar2 (50),
UUID_Usuario varchar2 (50),
Constraint Fk_UUUID_Destinos1
FOREIGN key (UUUID_Destinos)
REFERENCES tbDestinos (UUUID_Destinos),
Constraint  fk_UUID_Usuario1
FOREIGN KEY (UUID_Usuario)
REFERENCES tbUsuario (UUID_Usuario)
)

Create table tbReseñas (
UUUID_Reseña varchar2 (50) Primary key,
Reseña_Viaje varchar2 (200),
UUID_DetallesDestinos varchar2 (50),
Constraint Fk_UUID_DetallesDestinos
FOREIGN key (UUID_DetallesDestinos)
References TBDETALLESDESTINOS (UUID_DetallesDestinos)
)


///Datos insertados 

insert all 
into tbRol (UUID_Rol,Nombre_Rol ) values ('1', 'Administrador')
into tbRol (UUID_Rol,Nombre_Rol ) values ('2', 'Turista')
into tbRol (UUID_Rol,Nombre_Rol ) values ('3', 'Dueño')
select *from dual;

insert all
 into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario, Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('1', 'Edenilson Amaya','Ede12345', 18, '1234-3333', 'Edenilson1@gmail.com', '12345678-9', '1' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario, Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('2', 'Jose Luis', 'Jose1234', 20, '1234-5677', 'ChepeLuis1@gmail.com', '11345678-9', '1' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario, Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('3', 'Amaris Osorio', 'Ama12345', 21, '1234-5688', 'Amaris1@gmail.com', '11145678-9', '2' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario, Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('4', 'Bryan Cornejo', 'Cor12345', 22, '1234-5555', 'Cornejo1@gmail.com', '22345678-9', '3' )
into tbUsuario (UUID_Usuario, Nombre_Usuario, Password_usuario, Edad_Usuario, Telefono_Usuario, Correo_Usuario, Dui_Usuario, UUID_Rol)
Values ('5', 'Rafael Menendez', 'Rafa1234', 25, '1111-5678', 'Rafael1@gmail.com', '33345678-9', '2' )
select * from dual;

insert all
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante)
Values ('1', 'Tacos Hermanos','Taco, Tortas, Sopa de tortilla' )
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante)
Values ('2', 'La pampa','Cafe ' )
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante)
Values ('3', 'Restaurante Don Li','Watan de Pollo' )
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante)
Values ('4', 'Pizza Boom','Diferentes tipos de Pizza' )
 into tbRestaurante (UUID_Restaurante, Nombre_Restaurante, Menu_Restaurante)
Values ('5', 'La Hola ','Pescado,Camarones al ajillo, ' )
select * from dual;

insert all 
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('1', 'Balñario')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('2', 'Playa')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('3', 'Montaña')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('4', 'Volcan')
into tbtipoturistico(UUID_TipoLugarTuristico, NombreTipo) Values ('5', 'Lago')
select * from dual;

insert all
into tblugarturistico(UUID_LugarTuristico, Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico, uuid_tipolugarturistico )
Values ('1', 'Termos del Rio' ,'Balñario ubicado en Ciudad Arce, con 8 piscinas', 'urlimg1', '1')
into tblugarturistico(UUID_LugarTuristico, Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico, uuid_tipolugarturistico )
Values ('2', 'El tunco' ,'Ubicada en la libertad', 'urlimg2', '2')
into tblugarturistico(UUID_LugarTuristico, Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico, uuid_tipolugarturistico )
Values ('3', 'Cerro Verde' ,'Este es un volcan extinto', 'urlimg3', '3')
into tblugarturistico(UUID_LugarTuristico, Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico, uuid_tipolugarturistico )
Values ('4', 'Izalco' ,'Se origeno en el año 1770', 'urlimg4', '4')
into tblugarturistico(UUID_LugarTuristico, Nombre_LugarTuristico,Detalles_Lugar_Turistico, Fotos_Lugar_Turistico, uuid_tipolugarturistico )
Values ('5', 'Coatepeque' ,'Era un volcan', 'urlimg5', '5')
select * from dual;

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
///Iner joins
 
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
    
    
    
    SELECT 
    c.UUID_LugarTuristico,
    c.Nombre_LugarTuristico,
    c.Detalles_Lugar_Turistico,
    c.Fotos_Lugar_Turistico,
    d.NombreTipo as TipoTuristico
FROM 
    tbLugarTuristico c
INNER JOIN 
    tbTipoTuristico d  ON c.UUUID_TipoLugarTuristico = d.UUUID_TipoLugarTuristico;
    





    

    


