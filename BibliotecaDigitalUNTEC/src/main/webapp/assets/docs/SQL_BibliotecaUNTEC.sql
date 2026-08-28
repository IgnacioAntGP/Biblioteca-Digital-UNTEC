-- DDL - Schema
CREATE DATABASE IF NOT EXISTS db_untec_biblioteca
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
USE db_untec_biblioteca;

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    passwrdusuario VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL DEFAULT 'ALUMNO' -- 'ADMIN', 'ALUMNO', 'DOCENTE', 'PERSONAL'
);

CREATE TABLE IF NOT EXISTS libro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    editorial VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    anio_publicacion INT NOT NULL,
    descripcion VARCHAR(200) DEFAULT '',
    img_url VARCHAR(255) DEFAULT ''
);

CREATE TABLE IF NOT EXISTS prestamo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_libro INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_tope DATE NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PRESTADO',
    CONSTRAINT fk_prestamo_libro FOREIGN KEY (id_libro)
        REFERENCES libro (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_prestamo_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE
);

-- DML - Datos de prueba
INSERT INTO usuario (username, passwrd, email, tipo) VALUES
('admin', 'admin123', 'admin@untec.cl', 'ADMIN'),
('usuario', 'user123', 'usuario@untec.cl', 'ALUMNO');

INSERT INTO libro (titulo, autor, editorial, categoria, isbn, anio_publicacion, descripcion, img_url) VALUES
('Clean Code', 'Robert C. Martin', 'Prentice Hall', 'Informática', '978-0132350884', 2008, 'Libro de ingeniería de software para buenas prácticas', ''),
('Desarrollo Humano', 'Diane E. Papalia, Gabriela Martorell', 'McGraw-Hill', 'Psicología', '978-6071509338', 2017, 
'Obra de referencia académica que aborda de manera integral el desarrollo físico, cognoscitivo y psicosocial del ser humano a lo largo de todas las etapas del ciclo vital.', '/assets/img/libros/psychology-book.jpg'),
( 'Atlas de Anatomía Humana', 'Frank H. Netter', 'Elsevier', 'Medicina', '978-8491134688', 2019, 
'Guía visual y clínica de referencia médica mundial con ilustraciones magistrales del cuerpo humano organizadas por regiones anatómicas y sistemas.', '/assets/img/libros/medicine-book.jpg'),
('Automate the boring stuff with Python third edition', 'Al Sweigart', 'No Starch Press', 'Informática', '978-8362132680', 2025,'Libro de primer año | Introducción a Python', '/assets/img/libros/python-book.jpg');

INSERT INTO prestamo (id_libro, id_usuario, fecha_prestamo, fecha_tope, estado) VALUES
(1, 2, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'PRESTADO');

select * from usuario;
select * from libro;
select * from prestamo;

