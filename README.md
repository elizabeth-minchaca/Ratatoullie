# Ratatoullie
Recomendaciones de Restaurantes y Menús.
Administracion de Restaurantes, Historial de Menús y Usuarios.

Este proyecto fue realizado por Elizabeth Minchaca, Natanael Nufrio y Adnan Ferrer durante la capacitación inicial del LIFIA.

Desarrollado en lenguaje Java.

## 1 Implementacion del Sistema
### 1.1 Requerimientos de hardware
Contar con:

* Computadora personal.
* Conexion a Internet.
### 1.2 Requerimientos de software
Contar con:

* Navegador (Chrome, Mozilla Firefox, Opera u otro)
* Servidor mysql
* version 15.1 Distrib 10.1.9-MariaDB o ultima version de
* xamp(https://www.apachefriends.org/es/download.html)
* Eclipse para Java EE, el cual debe contar con los plugins para maven y GIT,también se le debe agregar un servidor tomcat 8 o superior.
* JAVA 8 o superior.

## 2 Instalacion del servidor

* 2.1 Abrir el Eclipse
* 2.2 Importar el proyecto GIT Ratatoullie al workspace como un proyecto java.
* 2.3 Convertir el poryecto a un proyecto maven.
* 2.4 Acceder ar archivo /resources/properties y configurarlo:
database.user=grupo6 /* Nombre de usuario de la base de datos*/
database.pass=grupo6 /* Clave de usuario de la base de datos*/
database.name=grupo6/* Nombre de la base de datos*/
database.host=localhost /*Direccion IP de la base de datos*/
database.port=3306 /*Puerto en que corre la base de datos*/
workspace=C:/workspace/Ratatoullie/ /*carpeta en donde se encuentra el src/ del
proyecto*/
* 2.5 Acceder a las propiedades del proyecto y habilitar la propiedad mostrada en la siguiente imagen

![Ratatoullie](https://github.com/elizabeth-minchaca/Ratatoullie/blob/master/Entrega/imagen.png)

* 2.6 En el archivo /resources/log4j.xml configurar la siguiente propiedad para indicar la
ubicación del archivo de log:<param name="file" value="Ratatoullie.log"
="Ratatoullie.log" />
* 2.7 Establecer el timeout start del servidor tomcat en 600 segudos
* 2.8 Iniciar el servidor mysql
* 2.9 Cargar el proyecto en el servidor tomcat.
* 2.10 Iniciar el servidor tomcat.
