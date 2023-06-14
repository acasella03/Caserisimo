create table camameros
(
    id_camarero INTEGER primary key AUTOINCREMENT,
    nombre      TEXT
)

create table mesas
(
    id_mesa INTEGER primary key AUTOINCREMENT,
    nombre  TEXT
)

create table servicios
(
    id_servicio INTEGER primary key AUTOINCREMENT,
    fecha       DATE,
    hora        TIME,
    id_camarero INTEGER,
    id_mesa     INTEGER,
    foreign key (id_camarero) references camareros (id_camarero),
    foreign key (id_mesa) references mesas (id_mesa)
)

create table categorias
(
    id_categoria INTEGER primary key AUTOINCREMENT,
    nombre       TEXT
)

create table productos
(
    id_producto  INTEGER primary key AUTOINCREMENT,
    nombre       TEXT,
    precio       FLOAT,
    id_categoria INTEGER,
    foreign key (id_categoria) references categorias (id_categoria)
)

create table servicios_productos
(
    id_servicios_productos INTEGER primary key AUTOINCREMENT,
    id_servicio            INTEGER,
    id_producto            INTEGER,
    cantidad               INTEGER,
    foreign key (id_servicio) references servicios (id_servicio),
    foreign key (id_producto) references productos (id_producto)
);