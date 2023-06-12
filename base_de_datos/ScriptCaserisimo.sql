create table camameros
(
    idcamarero INTEGER primary key AUTOINCREMENT,
    nombre     TEXT,
)

create table mesas
(
    idmesa     INTEGER primary key AUTOINCREMENT,
    numeromesa INTEGER
)

create table servicios
(
    idservicio INTEGER primary key AUTOINCREMENT,
    fecha      DATE,
    hora       TIME,
    idcamarero INTEGER,
    idmesa     INTEGER,
    foreign key (idcamarero) references camareros (idcamarero),
    foreign key (idmesa) references mesas (idmesa)
)

create table categorias
(
    idcategoria INTEGER primary key AUTOINCREMENT,
    nombre      TEXT
)

create table productos
(
    idproducto  INTEGER primary key AUTOINCREMENT,
    nombre      TEXT,
    precio      FLOAT,
    idcategoria INTEGER,
    foreign key (idcategoria) references categorias (idcategoria)
)

create table consta
(
    idservicio INTEGER,
    idproducto INTEGER,
    cantidad   INTEGER,
    primary key (idservicio, idproducto),
    foreign key (idservicio) references servicios (idservicio),
    foreign key (idproducto) references productos (idproducto)
);