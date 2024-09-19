 ScrumApp Management System

 Contexto del Proyecto

Una agencia de desarrollo de software que gestiona múltiples proyectos de desarrollo web y aplicaciones móviles ha experimentado un crecimiento significativo en los últimos meses. Debido a la expansión de su equipo y la creciente cantidad de proyectos y tareas en curso, han identificado la necesidad de implementar un sistema de gestión de tareas que permita:
- Controlar el avance de las tareas de cada proyecto.
- Asignar responsabilidades claras entre desarrolladores, diseñadores, y testers.
- Supervisar el progreso de los proyectos por parte de los gestores y administradores.
- Asegurar la seguridad y control de accesos, para que cada empleado pueda ver y gestionar solo la información relevante a su trabajo.

 Marcos de Competencias

- Gestión de proyecto con metodologías ágiles.
- Desarrollar el backend de una aplicación web (niv. 3).
- Administrar bases de datos (niv. 2).
- Desarrollar y Ejecutar pruebas automatizadas (niv. 3).

 Descripción del Proyecto

El proyecto es un sistema de gestión de tareas basado en una API REST, desarrollado con Java 17 y Spring Boot. La aplicación permite la autenticación de usuarios a través de JWT (JSON Web Tokens) y roles con distintos niveles de acceso utilizando Spring Security. Utiliza una base de datos MySQL para almacenar la información y emplea Docker para contenedores, integrando GitHub Actions para el CI/CD.

 Objetivos del Proyecto

1. Reforzar los conceptos de creación de APIs.
2. Aplicar relaciones de BBDD.
3. Desarrollar un login con Spring Security y JWT.
4. Tener primeros contactos con GitHub Actions.

 Requerimientos Funcionales

 Roles de Usuario

- Administrador: Puede gestionar usuarios y tareas.
- Gestor: Puede crear, asignar y gestionar tareas, pero no gestionar usuarios.
- Usuario: Solo puede visualizar y actualizar el estado de sus tareas asignadas.

 Autenticación y Autorización

- Implementación de autenticación con JWT para permitir un inicio de sesión seguro.
- Autorización basada en roles con Spring Security, que otorga diferentes permisos según el rol asignado al usuario.

 CRUD para Tareas

- Crear: Los gestores pueden crear nuevas tareas y asignarlas a un usuario.
- Leer: Los usuarios pueden visualizar sus tareas, mientras que los gestores pueden ver las tareas asignadas a cualquier usuario.
- Actualizar: Los usuarios pueden actualizar el estado de sus tareas.
- Eliminar: Solo los gestores pueden eliminar tareas.

 Gestión de Usuarios

- El administrador puede gestionar el alta, baja y actualización de los usuarios del sistema, así como asignar roles.
- Filtro por Usuario: El gestor puede ver la lista de tareas por usuario y el estado de las mismas.

 Relaciones entre Entidades (Base de Datos)

- Usuario: Tabla que contiene información de los usuarios y su relación con las tareas.
- Tarea: Tabla que almacena las tareas asignadas a los usuarios.
- Proyecto: Relación con la entidad Usuario (Un usuario puede pertenecer a varios proyectos, y un proyecto puede tener varios usuarios). Relación con Tarea (Un proyecto puede tener varias tareas).

 Diagrama de Entidades (ER)

- Usuario:
  - id (PK)
  - username
  - password
  - email
  - role (ADMIN, GESTOR, USUARIO)

- Proyecto:
  - id (PK)
  - nombre

- Tarea:
  - id (PK)
  - nombre
  - descripcion
  - estado (PENDIENTE, EN_PROGRESO, COMPLETADO)
  - usuario_id (FK) (Relación con Usuario)
  - proyecto_id (FK) (Relación con Proyecto)

 Endpoints de la API

 Autenticación

- Iniciar Sesión:
  - `POST /api/auth/login`: Autenticación de usuario (Devuelve JWT)
  
- Registro de Usuario:
  - `POST /api/auth/register`: Registro de un nuevo usuario (Solo accesible por ADMIN)

  Usuarios

- Listar Todos los Usuarios:
  - `GET /api/users`: Solo ADMIN
  
- Obtener un Usuario por ID:
  - `GET /api/users/{id}`: ADMIN o usuario mismo
  
- Crear un Nuevo Usuario:
  - `POST /api/users`: Solo ADMIN
  
- Actualizar un Usuario:
  - `PUT /api/users/{id}`: Solo ADMIN
  
- Eliminar un Usuario:
  - `DELETE /api/users/{id}`: Solo ADMIN

  Proyectos

- Listar Todos los Proyectos:
  - `GET /api/projects`
  
- Crear un Nuevo Proyecto:
  - `POST /api/projects`: Solo GESTOR y ADMIN
  
- Actualizar un Proyecto:
  - `PUT /api/projects/{id}`: Solo GESTOR y ADMIN
  
- Eliminar un Proyecto:
  - `DELETE /api/projects/{id}`: Solo GESTOR y ADMIN

  Tareas

- Listar Todas las Tareas:
  - `GET /api/tasks`: Solo GESTOR y ADMIN
  
- Obtener una Tarea Específica:
  - `GET /api/tasks/{id}`: Usuarios pueden obtener solo las suyas
  
- Crear una Nueva Tarea:
  - `POST /api/tasks`: Solo GESTOR
  
- Actualizar una Tarea:
  - `PUT /api/tasks/{id}`: Usuarios solo sus propias tareas
  
- Eliminar una Tarea:
  - `DELETE /api/tasks/{id}`: Solo GESTOR

  Tecnologías Utilizadas

- Java 17: Lenguaje de programación utilizado para el desarrollo backend.
- Spring Boot: Framework para construir aplicaciones Java.
- Spring Security y JWT: Para autenticación y autorización.
- MySQL: Base de datos utilizada para el almacenamiento de datos.
- Docker: Para contenerización y despliegue.
- GitHub Actions: Para CI/CD y automatización de despliegues.
- JUnit y Mockito: Para pruebas automatizadas.

  Requisitos Técnicos del Proyecto

1. Conocimientos de programación en Java.
2. Conocimientos en Programación Orientada a Objetos (POO).
3. Spring Boot.
4. Spring Security y JWT.
5. Testing.
6. Docker.
7. GitHub Actions.
8. No se puede usar Lombok ni la annotation `@Autowired`.

 Cómo Ejecutar el Proyecto

  Configuración

 Clona el Repositorio:

   ```bash
   git clone https://github.com/anamartin99/ScrumApp-1.git
