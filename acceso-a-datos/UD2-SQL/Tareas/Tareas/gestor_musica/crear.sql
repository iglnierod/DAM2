DROP TABLE IF EXISTS canciones;
CREATE TABLE canciones(
	id INT PRIMARY KEY,
	titulo VARCHAR(100),
	artista VARCHAR(100),
	duracion INT,
	anio INT
);

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
	id INT PRIMARY KEY,
	nombre_usuario VARCHAR(100) UNIQUE,
	nombre VARCHAR(100),
	correo VARCHAR(100)
);

DROP TABLE IF EXISTS listas_reproduccion;
CREATE TABLE listas_reproduccion(
	id INT PRIMARY KEY,
	nombre_lista VARCHAR(100),
	id_usuario INT,

	FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);