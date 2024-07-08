Base de datos TAKATULI

Create table tbTipo_Usuario(
UUID_TipoUsuario varchar2 (50)Primary key,
Nombre_Tipo varchar2 (15) not null
)

Create table tbDetallesUsuario(
UUID_Detalleuser varchar2 (50)Primary key,
Nombre_Usuario varchar2(20) UNIQUE not null,
Password_Usuario varchar2 (20) Unique CHECK (Password_Usuario=8) not null,
UUID_TipoUsuario varchar2 (50),
CONSTRAINT FK_UUID_TipoUsuario
FOREIGN Key (UUID_TipoUsuario)
References tbTipo_Usuario (UUID_TipoUsuario)
                 
Create table tbUsuarios(
UUID_Usuario varchar2 (50) Primary key,
Nombre_Usuario varchar2 (30)  Unique not null,
Edad_Usuario number (2) not null CHECK (Edad_Usuario>=18),
Telefono_Usuario varchar2(9) not null Unique,
Coreo_Usuario varchar2(30) not null Unique,
DUI_Usuario varchar2(10) not null Unique, 
UUID_Detalleuser varchar2 (50),
Constraint FK_UUID_Detalleuser
FOREIGN KEY (UUID_Detalleuser)
REFERENCES tbDetallesUsuario (UUID_Detalleuser)
)

Create table tbTipoLugarTuristirco(
UUUID_TipoLugarTuristico varchar2 (50) Primary key,
NombreTipo varchar2 (25) not null
)

Create table tbLugarTuristico(
UUID_LugarTuristico varchar2 (50) Primary key,
Nombre_LugarTuristico varchar2 (50) not null Unique,
Detalles_Lugar_Turistico varchar2(200) not null,
Fotos_Lugar_Turistico varchar2 (200) not null,
UUUID_TipoLugarTuristico varchar2 (50),
CONSTRAINT FK_UUUID_TipoLugarTuristico
FOREIGN key (UUUID_TipoLugarTuristico)
References tbTipoLugarTuristirco (UUUID_TipoLugarTuristico)
)

Create table tbHospedaje (
UUID_Hospedaje varchar2 (50) Primary key,
Nombre_Hospedaje varchar2 (50) not null Unique,
Precio_Hospedaje number(4,2) not null,
Detalles_Hospedaje varchar2 (200) not null,
Fotos_Hospedaje varchar2 (200) not null
)

Create table tbRestaurante (
UUID_Restaurante varchar2 (50) Primary key,
Nombre_Restaurante varchar2 (50) not null Unique,
Precios_Restaurante number (2,3),
Menu_Restaurante varchar2 (400)not null,
Foto_Menu varchar2 (200) not null,
Fotos_Restaurante varchar2 (200) not null
)

Create table tbTipoDestino(
UUID_TipoDestino varchar2(50) Primary key,
UUID_LugarTuristico varchar2 (50),
UUID_Hospedaje varchar2 (50),
UUID_Restaurante varchar2 (50),
Constraint Fk_UUID_LugarTuristico
FOREIGN key (UUID_LugarTuristico)
References tbLugarTuristico (UUID_LugarTuristico),
Constraint Fk_UUID_Hospedaje
FOREIGN key  (UUID_Hospedaje)
References tbHospedaje (UUID_Hospedaje),
Constraint Fk_UUID_Restaurante
FOREIGN key (UUID_Restaurante)
References tbRestaurante (UUID_Restaurante)
)

Create table tbTipoActividad (
UUID_TipoActividad varchar2 (50) Primary key,
NombreActividad varchar2 (50) not null
)

Create table tbActividad(
UUID_Actividad varchar2 (50) Primary key,
Nombre_Actividad varchar2 (30) not null,
Precio_Actividad number (3,2) not null,
UUID_TipoActividad varchar2 (50),
Constraint Fk_UUID_TipoActividad
FOREIGN key (UUID_TipoActividad)
References tbTipoActividad (UUID_TipoActividad)
)

Create table tbUbicaciones(
UUID_Ubicacion varchar2 (50) Primary key,
Departamento varchar2 (20) not null 
)

Create table tbPaquetes (
UUID_Paquetes varchar2 (50) Primary key,
Nombre_Paquete varchar2 (25) not null,
Detalles_Paquete varchar2 (75) not null,
Precio_Paquetes number (3,2) not null
)

Create table tbDestinos(
UUID_Destinos varchar2 (50) Primary key,
UUID_Paquetes varchar2 (50),
UUID_Actividad varchar2 (50),
UUID_TipoDestino varchar2 (50),
Constraint Fk_UUID_Paquetes
FOREIGN key (UUID_Paquetes)
References tbPaquetes (UUID_Paquetes),
Constraint Fk_UUID_Actividad
FOREIGN key (UUID_Actividad)
References tbActividad (UUID_Actividad),
Constraint Fk_UUID_TipoDestino
FOREIGN key (UUID_TipoDestino)
References tbTipoDestino (UUID_TipoDestino)
)


Create table tbDetallesVisitas(
UUID_Usuario varchar2 (50),
UUID_Destinos varchar2 (50),
Constraint Fk_UUID_Usuario
FOREIGN key (UUID_Usuario)
References tbUsuarios (UUID_Usuario),
Constraint Fk_UUID_Destinos
FOREIGN key (UUID_Destinos)
References tbDestinos (UUID_Destinos)
)
