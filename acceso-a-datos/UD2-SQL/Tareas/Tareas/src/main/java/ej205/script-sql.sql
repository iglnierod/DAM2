DROP DATABASE biblioteca;
CREATE DATABASE IF NOT EXISTS biblioteca;

USE biblioteca;

CREATE TABLE IF NOT EXISTS autores (
    id INT PRIMARY KEY,
    nombre VARCHAR(45),
    apellidos VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS libros (
    id INT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    anio_publicacion YEAR,
    autor_id INT,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
);

-- Insertar autores
INSERT INTO autores (id, nombre, apellidos) VALUES
(1, 'Gabriel', 'García Márquez'),
(2, 'J.K.', 'Rowling'),
(3, 'Haruki', 'Murakami');

-- Insertar libros
INSERT INTO libros (id, titulo, anio_publicacion, autor_id) VALUES
(1, 'Cien años de soledad', 1967, 1),
(2, 'Harry Potter y la piedra filosofal', 1997, 2),
(3, 'Norwegian Wood', 1987, 3),
(4, 'El otoño del patriarca', 1975, 1);
