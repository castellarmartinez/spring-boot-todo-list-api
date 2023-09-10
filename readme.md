# ToDo API

## ¿Qué es ToDo API?
Es una aplicación que permite la manipulación de una lista de tareas, concretamente, la creación de tareas, la obtención de tareas, la actualización del estado de una tarea y la eliminación de tareas (conocidas como CRUD operation).

Esas tareas se crean en una base de datos para luego ser manipuladas. Se usó el framework Spring Boot, usando una clase para la entidad que correlaciona las tareas en la base de datos, CrudRepository (org.springframework.data.repository.CrudRepository), para la manipulaciones CRUD (Create, Read, Update, Delete), una clase controller para exponer las rutas de la API, permitiendo recibir peticiones y mostrar respuestas con sus respectivos códigos HTTP. Y una clase para ayudar en la validación del input ingresado por el cliente y mostrar errores comunes. La aplicación guarda la información en base de datos usando PostgresSQL.

---
## Requerimientos

- Java SE 17
- Spring Boot 3.1.3
- Apache Maven 3.9.3

---
## Instalación

#### 1. Clonar proyecto: 

```bash
https://github.com/castellarmartinez/spring-boot-todo-list-api.git
```

#### 2. Instalar maven dependencies

#### 3. Configurar Spring Boot propierties:

Agregar valores para las siguientes propiedades en src/main/resources
/application.properties
:

- {postgresqlConnectionString}: URL para conectaser a la base de datos de postgresql.
- {username}: username para conectaser a la base de datos de postgresql.
- {password}: password para conectaser a la base de datos de postgresql.

```bash
spring.datasource.url=jdbc:postgresql://{postgresqlConnectionString}
spring.datasource.username={username}
spring.datasource.password={password}
```


---
## ¿Cómo ejecutar la aplicación?

- Abrir el proyecto en el IDE de preferencia. 
- Abrir el archivo ***TodoapiApplication.java***, que se encuentra en la ruta ***src/main/java/com/castellarmartinez/todoapi/TodoapiApplication.java*** del proyecto y ejecutarlo. 

---
## ¿Cómo ejecutar los tests?

- Abrir los archivos que se encuentra en la ruta ***src/test/***, ejecutarlos y visualizar los resultados. 

---
## ¿Cómo usar la API?
Las rutas disponibles para usar la api se pueden observar en la url: [http://localhost:3000](http://localhost:3000) (el puerto 3000 está harcodeado en las propiedades de spring boot, este valor se puede modificar).

#### 1. Crear una nueva tarea:

Para crear una nueva tarea se puede hacer una petición POST la ruta: [http://localhost:3000/api/tasks](http://localhost:3000/api/tasks):

```
curl -i -X POST http://localhost:3000/api/tasks -H "Content-Type: application/json" -d "{\"title\": \"Peluquearme\", \"description\": \"Necesito peluquearme\", \"state\": \"PENDING\"}"
```

Los únicos valores válidos para el estado de la tarea son "PENDING" y "COMPLETED".

Si no se envía el estado, la tarea se marca como pendiente "PENDING".

```
curl -i -X POST http://localhost:3000/api/tasks -H "Content-Type: application/json" -d "{\"title\": \"Peluquearme\", \"description\": \"Necesito peluquearme\"}"
```

#### 2. Obtener todas las tareas

Para obtener todas las tareas se puede hacer un petición GET a la ruta: [http://localhost:3000/api/tasks](http://localhost:3000/api/tasks):

```
curl -i -X GET http://localhost:3000/api/tasks
```

#### 3. Obtener tarea usando id

Para obtener una tarea usando el id se puede hacer un petición GET a la ruta: [http://localhost:3000/api/tasks/:id](http://localhost:3000/api/tasks/:id):

```
curl -i -X GET http://localhost:3000/api/tasks/1
```

#### 4. Actualizar estado de una tarea

Para actualizar el estado de una tarea se puede hacer una petición PUT a la ruta: [http://localhost:3000/api/tasks/:id](http://localhost:3000/api/tasks/:id):

```
curl -i -X PUT http://localhost:3000/api/tasks/1 -H "Content-Type: application/json" -d "{\"state\": \"COMPLETED\"}"
```

NOTA: mirar punto 1 para los valores válidos de los estados.

#### 5. Eliminar tarea usando id

Para eliminar una tarea se puede hacer una petición DELETE a la ruta: [http://localhost:3000/api/tasks/:id](http://localhost:3000/api/tasks/:id):

```
curl -i -X DELETE http://localhost:3000/api/tasks/1
```

## Construido con: 

- [Java](https://www.java.com) - Lenguaje de programación orientado a objetos
- [Spring Boot](https://spring.io/projects/spring-boot/) - Framework para el desarrollo de aplicaciones y contenedor de inversión de control, de código abierto para la plataforma Java
- [JUnit](https://junit.org/) - Bibliotecas utilizadas en programación para hacer pruebas unitarias de aplicaciones Java.
- [Mockito](https://site.mockito.org) -  Framework que permite la creación de objetos dobles de prueba (objetos simulados) en pruebas de unidad automatizada.
- [PostgresSQL](https://www.postgresql.org) - Sistema de gestión de bases de datos relacional orientado a objetos

---
## Autor 
**David Castellar Martínez** [[GitHub](https://github.com/castellarmartinez/)]
[[LinkedIn](https://www.linkedin.com/in/castellarmartinez/)]
