Hice una aplicación web con Spring Boot donde se guardan canciones y artistas en una base de datos MySQL. Cada canción pertenece a un artista y un artista puede tener muchas canciones, así que la relación que implementé es de muchos a uno.

Primero creé las dos entidades, `Artista` y `Cancion`. En `Cancion` guardé el título, el año y el género. En `Artista` guardé el nombre y la nacionalidad. Con ellas, Spring Data JPA creó las tablas en MySQL automáticamente.

Para la relación usé `@ManyToOne` en la entidad `Cancion` y `@OneToMany` en la entidad `Artista`. En la base de datos esto se refleja con la columna `artista_id` en la tabla `cancion`, que funciona como llave foránea, igual que `CountryCode` en el video de referencia.

Después creé un repositorio por cada entidad, extendiendo `JpaRepository`. Gracias a eso pude guardar, buscar, listar y borrar datos sin escribir consultas SQL.

También creé una capa de servicios donde puse la lógica de negocio. Ahí valido que el título no esté vacío, que el año no sea futuro y que el artista seleccionado exista. Así evité poner esas reglas directamente en los controladores.

Hice dos tipos de controladores. Los de vistas, `CancionController` y `ArtistaController`, trabajan con plantillas Thymeleaf y muestran las páginas en `/canciones` y `/artistas`. Los controladores REST, `CancionRestController` y `ArtistaRestController`, devuelven JSON en las rutas que empiezan con `/api`, para probarlos con Postman.

El CRUD de canciones incluye las cuatro operaciones: crear, leer, actualizar y eliminar. En la lista de canciones se muestra el nombre del artista y no solo su ID, lo que comprueba que la relación funciona correctamente.
Probé todo primero en mi computadora, con una base de datos local, y luego subí el código a mi repositorio de GitHub. Para la contraseña de la base de datos usé variables de entorno, así no queda escrita en el código ni en el repositorio.
