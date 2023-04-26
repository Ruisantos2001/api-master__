ALTER TABLE paises
CHANGE COLUMN nome name varchar(20) not null,
CHANGE COLUMN capital capital varchar(20) not null,
CHANGE COLUMN regiao region varchar(20) not null,
CHANGE COLUMN subregiao subregion varchar(20) not null,
CHANGE COLUMN area area varchar(15) not null;