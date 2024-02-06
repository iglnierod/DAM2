DROP TABLE IF EXISTS canciones;
CREATE TABLE canciones(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	titulo VARCHAR(100),
	artista VARCHAR(100),
	duracion INT,
	anio INT
);

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	nombre_usuario VARCHAR(100) UNIQUE,
	nombre VARCHAR(100),
	correo VARCHAR(100)
);

DROP TABLE IF EXISTS listas_reproduccion;
CREATE TABLE listas_reproduccion(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	nombre_lista VARCHAR(100),
	id_usuario INT,

	FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);

DROP TABLE IF EXISTS listas_canciones;
CREATE TABLE listas_canciones(
	id_lista INTEGER,
	id_cancion INTEGER,
	PRIMARY KEY(id_lista, id_cancion),

	FOREIGN KEY (id_lista) REFERENCES listas_reproduccion (id),
	FOREIGN KEY (id_cancion) REFERENCES canciones (id)
);