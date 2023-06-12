create table camameros
(
    idcamarero        INTEGER primary key,
    nombrecamarero    TEXT,
    apellidoscamarero TEXT,
    sueldo            FLOAT
)

create table mesas
(
    numeromesa INTEGER primary key
)

create table servicios
(
    idservicio INTEGER primary key AUTOINCREMENT,
    fecha      DATE,
    hora       TIME,
    idcamarero INTEGER,
    numeromesa INTEGER,
    foreign key (idcamarero) references camareros (idcamarero),
    foreign key (numeromesa) references mesas (numeromesa)
)

create table categorias
(
    idcategoria     INTEGER primary key AUTOINCREMENT,
    nombrecategoria TEXT
)

create table productos
(
    idproducto     INTEGER primary key AUTOINCREMENT,
    nombreproducto TEXT,
    precio         FLOAT,
    idcategoria    INTEGER,
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