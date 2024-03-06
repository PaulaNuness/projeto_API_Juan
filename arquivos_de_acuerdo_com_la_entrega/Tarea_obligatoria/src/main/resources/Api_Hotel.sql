drop database if exists api_hotel;
Create database api_hotel;
use api_hotel;
CREATE TABLE  hotel (
						id integer auto_increment PRIMARY KEY,
						nombre_hotel VARCHAR(100) NOT NULL ,
						descripcion_hotel VARCHAR(100) NOT NULL ,
						categoria VARCHAR(1) NOT NULL ,
						tem_piscina boolean NOT NULL,
						localidade VARCHAR(100) NOT NULL

);

INSERT INTO hotel VALUES
					  (1, 'hotel_uno', 'aaaaa', '1',true,'Valladoilid'),
					  (2, 'hotel_dos', 'eeeee', '2',false,'Salamanca'),
					  (3, 'hotel_tres', 'iiiii', '3',true,'Madrid'),
					  (4, 'hotel_cuatro', 'ooooo', '4',false,'Barcelona'),
					  (5, 'hotel_cinco', 'uuuuu', '5',true,'Sevilla');

CREATE TABLE  habitacion (
							 id integer auto_increment PRIMARY KEY,
							 id_hotel integer NOT NULL,
							 tamanho decimal(5,2) NOT NULL ,
							 numero_de_personas integer NOT NULL ,
							 precio_noche decimal(5,2) NOT NULL ,
							 incluye_desayuno boolean NOT NULL,
							 habitacion_ocupada boolean NOT NULL ,
							 FOREIGN KEY (id_hotel) REFERENCES hotel(id)
);

INSERT INTO habitacion VALUES
						   (1, 4,  50.00, 1, 39.9, true, true),
						   (2, 3,  36.50, 2, 100.8, false, true),
						   (3, 5,  20.80, 1, 49.9, true, true),
						   (4, 1,  100.0, 2, 89.9, false, false),
						   (5, 5,  59.50, 1, 76.4, true, false),
						   (6, 5,  59.50, 1, 26.4, true, false),
						   (7, 1,  89.50, 2, 96.4, true, false);

CREATE TABLE User (
					  id_Usuario INT AUTO_INCREMENT PRIMARY KEY,
					  usuario VARCHAR(100) NOT NULL,
					  password VARCHAR (100) not null

);

INSERT INTO User VALUES  (1,'juan', 'juan');

