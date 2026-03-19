-- Seed data — runs on every startup (spring.sql.init.mode=always)
-- ON CONFLICT clauses make it safe to re-run without duplicating rows.
-- Passwords hashed with BCrypt strength 10:
--   Jaime  → Yago2004
--   Miguel → Yago2003

INSERT INTO autores (id_autor, nombre)
VALUES
    (1, 'J. K. Rowling'),
    (2, 'George R. R. Martin'),
    (3, 'Stephen King')
ON CONFLICT (id_autor) DO NOTHING;

INSERT INTO libros (id_libro, titulo, url_portada, anio_publicacion)
VALUES
    (1, 'Harry Potter y la Piedra Filosofal',     'url1', '1997'),
    (2, 'Harry Potter y la Cámara Secreta',       'url2', '1998'),
    (3, 'Harry Potter y el Prisionero de Azkaban','url3', '1999'),
    (4, 'El Resplandor',                          'url4', '1977'),
    (5, 'El Resplandor',                          'url5', '2015'),
    (6, 'Juego de Tronos',                        'url6', '1996'),
    (7, 'Choque de Reyes',                        'url7', '1998')
ON CONFLICT (id_libro) DO NOTHING;

INSERT INTO autor_libro (id_autor, id_libro)
VALUES
    (1, 1), (1, 2), (1, 3),
    (3, 4), (3, 5),
    (2, 6), (2, 7)
ON CONFLICT DO NOTHING;

INSERT INTO usuarios (id_usuario, nombre, "contraseña", fecha_registro, email, url_imagen_perfil)
VALUES
    (1, 'Jaime',  '$2b$10$s/tjpl4ycg6GRt.QvatvSu1usXam/r3JiWTh.XUInk6.1WFOZYBCy', '2025-03-12 14:45:30', 'jaimelaika12@gmail.com',  'imagen.png'),
    (2, 'Miguel', '$2b$10$lGhcbxvsuKBXY8Llkr29FuGXMhiHBueoK6FnYEklorm13Y7EN5Kce', '2025-03-12 14:45:30', 'miguelneira18@gmail.com', 'imagen2.png')
ON CONFLICT (id_usuario) DO NOTHING;
