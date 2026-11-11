Hice una aplicación web con Spring Boot donde se guardan canciones y artistas en una base de datos MySQL. Cada canción pertenece a un artista y un artista puede tener muchas canciones, así que la relación que implementé es de muchos a uno.

Primero creé las dos entidades, `Artista` y `Cancion`. En `Cancion` guardé el título, el año y el género. En `Artista` guardé el nombre y la nacionalidad. Con ellas, Spring Data JPA creó las tablas en MySQL automáticamente.

Para la relación usé `@ManyToOne` en la entidad `Cancion` y `@OneToMany` en la entidad `Artista`. En la base de datos esto se refleja con la columna `artista_id` en la tabla `cancion`, que funciona como llave foránea, igual que `CountryCode` en el video de referencia.

Después creé un repositorio por cada entidad, extendiendo `JpaRepository`. Gracias a eso pude guardar, buscar, listar y borrar datos sin escribir consultas SQL.

También creé una capa de servicios donde puse la lógica de negocio. Ahí valido que el título no esté vacío, que el año no sea futuro y que el artista seleccionado exista. Así evité poner esas reglas directamente en los controladores.

Hice dos tipos de controladores. Los de vistas, `CancionController` y `ArtistaController`, trabajan con plantillas Thymeleaf y muestran las páginas en `/canciones` y `/artistas`. Los controladores REST, `CancionRestController` y `ArtistaRestController`, devuelven JSON en las rutas que empiezan con `/api`, para probarlos con Postman.

El CRUD de canciones incluye las cuatro operaciones: crear, leer, actualizar y eliminar. En la lista de canciones se muestra el nombre del artista y no solo su ID, lo que comprueba que la relación funciona correctamente.
Probé todo primero en mi computadora, con una base de datos local, y luego subí el código a mi repositorio de GitHub. Para la contraseña de la base de datos usé variables de entorno, así no queda escrita en el código ni en el repositorio.


PREGUNTAS CLASSROOM

a) ¿Qué dos entidades elegiste y qué tipo de relación implementaste entre ellas (uno a muchos, muchos a uno)?
Elegí las entidades canción y artista. la relación es de muchos a uno, porque muchas canciones pertenecen a un solo artista. use @manyToOne en canción y en artista use @OneToMany y en la base de datos se guarda con la llave foránea artista_id en la tabla canción.

b) ¿Qué es un Repository en Spring Data JPA y qué ventaja tiene sobre escribir tú mismo las consultas SQL?
Un Repository es una interfaz que conecta con la base de datos y que se crea extendiendo JpaRepository. con un solo Spring puedo tener para guardar, buscar, listar, actualizar y eliminar los registros. La ventaja de escribir las consultas es que no tengo que escribirlas a manos.

c) ¿Por qué es una buena práctica separar la lógica en una capa de Service en vez de ponerla directamente en el Controller?
Porque cada capa se encarga de una sola cosa, el controller solo recibe las peticiones y devuelve respuestas, mientras que service guarda reglas, como validar que el titulo no este vacío o que el artista exista

d) Pega el enlace de tu repositorio de GitHub y el link de tu proyecto ya corriendo en el VPS (de Postman/Bruno, incluye también tu colección).

Repositorio: https://github.com/JAZMIN-FERNANDEZ/FLJact3_t4.git
VPS:http://167.99.15.140:8086/canciones
http://167.99.15.140:8086/artistas
API EN EL VPS:http://167.99.15.140:8086/api/canciones