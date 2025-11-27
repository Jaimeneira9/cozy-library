INSERT INTO autor (id_autor, nombre, fecha_nacimiento, nacionalidad)
VALUES
(1, 'J. K. Rowling', '1965-07-31', 'Británica'),
(2, 'George R. R. Martin', '1948-09-20', 'Estadounidense'),
(3, 'Stephen King', '1947-09-21', 'Estadounidense');

INSERT INTO libro (id_libro, titulo, url_portada, anio_publicacion)
VALUES
(1, 'Harry Potter y la Piedra Filosofal', 'url1', 1997),
(2, 'Harry Potter y la Cámara Secreta', 'url2', 1998),
(3, 'Harry Potter y el Prisionero de Azkaban', 'url3', 1999),
INSERT INTO usuario (id,nombre,contraseña,fechaRegistro,email,pathImagenPerfil)
VALUES
(1,"Jaime","Yago2004",2025-03-12 14:45:30,"jaimelaika12@gmail.com","imagen.png")
(2,"Miguel","Yago2003",2025-03-12 14:45:30,"miguelneira18@gmail.com","imagen2.png")

-- 2 libros con el MISMO título (ejemplo buscador)
(4, 'El Resplandor', 'url4', 1977),
(5, 'El Resplandor', 'url5', 2015),

(6, 'Juego de Tronos', 'url6', 1996),
(7, 'Choque de Reyes', 'url7', 1998);

INSERT INTO autor_libro (id_autor, id_libro) VALUES
(1, 1),
(1, 2),
(1, 3);
INSERT INTO autor_libro (id_autor, id_libro) VALUES
(3, 4),
(3, 5);

INSERT INTO autor_libro (id_autor, id_libro) VALUES
(2, 6),
(2, 7);

