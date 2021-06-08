# 🚀 Prueba técnica WOLOX. 🚀

Proyecto de pruebas para la tienda de álbumes desarrollada por Naiofy en el que se automatizan los servicios web que permiten realizar las siguientes acciones dentro de la tienda:
* Registro.
* Login.
* Listado de usuarios.
* Listado de álbumes.
* Listado de fotos de un álbum.
* Listado de álbumes comprados.
* Comprar álbum.
* Invalidar sesiones.   


## Ejecutando el proyecto pruebas ⚙️

El proyecto cuenta con un runner que permite ejecutar las pruebas específicas para cada una de las funcionalidades. En caso de querer ejecutar los escenarios de prueba de una única funcionalidad, se debe ejecutar alguno de los siguientes comandos:
* **Login:** gradlew clean test -Dregresion="@LOGIN" aggregate
* **Listado de usuarios:** gradlew clean test -Dregresion="@USER_LIST" aggregate
* **Listado de álbumes:** gradlew clean test -Dregresion="@ALBUM_LIST" aggregate
* **Listado de fotos de un álbum:** gradlew clean test -Dregresion="@PHOTO_ALBUM_LIST" aggregate
* **Listado de álbumes comprados:** gradlew clean test -Dregresion="@PURCHASED_ALBUM_LIST" aggregate
* **Comprar álbum:** gradlew clean test -Dregresion="@BUY_ALBUM" aggregate
* **Invalidar sesiones:** gradlew clean test -Dregresion="@INVALIDATE_SESSIONS" aggregate


En caso de necesitar ejecutar los escenarios de pruebas de todas las funcionalidades, se debe ejecutar el comando: 

* **Todos los test:** gradlew clean test aggregate

## Construido con 🛠️

* **Serenity BDD**
* **Rest Assured**
* **Cucumber4**
* **JUnit**
* **AssertJ**
* **JavaFaker**
* **Json-Schema-Validator**
* **Java 1.8**
* **Gradle 5.4.1**


## Autores ✒️

* **Alejandro Gallego Durango**
