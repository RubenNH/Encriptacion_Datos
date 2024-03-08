Este repositorio alberga dos proyectos donde se implementa el algoritmo de encryptacion de datos en payload y url que se comparten entre el cliente y el servidor de este sistema,
estos datos son encriptados y desencriptados en el cliente y el servidor respectivamente en AES-256-CBC con una llave de 16 bytes aplicando CBC y PKCS7.
Ademas, se implmento en nivel de back el algoritmo de encriptacion de contraseñas de usuarios en la base de datos.


## Despliegue 🚀

Para desplegar el proyecto en tu maquina local, sigue los siguientes pasos:

## Cliente
1. En una terminal, muevete a la carpeta "CruzStay" y ejecuta el siguiente comando para instalar las dependencias del proyecto:

npm install
2. Una vez instaladas las dependencias, ejecuta el siguiente comando para correr el proyecto:

npm run dev

## Servidor

1. Si haces uso de una IDE como IntelliJ IDEA, abre la carpeta "cruzstay-main" como un proyecto de maven y espera a que se instalen las dependencias del proyecto.

2. Una vez instaladas las dependencias, ejecuta el archivo "CruzstayApplication.java" que se encuentra en la carpeta "cruzstay-main/src/main/java/stay/cruz/cruzstay" para correr el proyecto.

3. La base de datos sera creara con las anotaciones de JPA en el archivo "CruzstayApplication.java" y se llenara con datos de prueba al correr el proyecto, sin embargo, recuerda que las credenciales de la base de datos estan en el archivo "application.properties" de la aplicacion deben ser cambiadas por las tuyas propias.

## Construido con 🛠️

Las herramientas que se usaron para crear el proyecto son:

- [JAVA](https://www.java.com/es/) - El lenguaje de programacion usado en la version 21
- [JavaScript](https://www.javascript.com/) - El lenguaje de programacion usado en la version ES6
- [HTML](https://developer.mozilla.org/es/docs/Web/HTML) - El lenguaje de marcado usado
- [CSS](https://developer.mozilla.org/es/docs/Web/CSS) - El lenguaje de estilos usado

- [Bootstrap](https://getbootstrap.com/) - El framework de estilos usado
- [Vue.js](https://vuejs.org/) - El framework web usado
- [Spring Boot](https://spring.io/projects/spring-boot) - El framework de java usado
- [Maven](https://maven.apache.org/) - Manejador de dependencias
- [Node.js](https://nodejs.org/) - Entorno de ejecucion de javascript
- [Axios](https://axios-http.com/) - Cliente http para el navegador y node.js
- [CryptoJS](https://cryptojs.gitbook.io/docs/) - Biblioteca de encriptacion de datos
- [Pinia](https://pinia.esm.dev/) - Manejador de estado global de la aplicacion
- [Spring Security](https://spring.io/projects/spring-security) - Framework de seguridad de spring
- [JWT](https://jwt.io/) - Biblioteca de tokens de autenticacion
- [Hibernate](https://hibernate.org/) - Framework de mapeo objeto-relacional
- [H2](https://www.h2database.com/html/main.html) - Base de datos en memoria
- [Lombok](https://projectlombok.org/) - Biblioteca de java para reducir el codigo boilerplate
- [JPA](https://spring.io/projects/spring-data-jpa) - Framework de persistencia de spring
- [H2](https://www.h2database.com/html/main.html) - Base de datos en memoria

## Autores ✒️

- **Jorge Angel Ruiz Juarez** - *Lider de trabajo* - [JorgeAngeCodeBreaker]
- **Cruz Ruben Najera Hernandez** - *Desarrollador* - [RubenNH]
- **Cesar Uriel Gonzalez Saldaña** - *Desarrollador* - [Cesar_Gonzalez_S]
- **Elena Abigail Alanis Reyes ** - *Desarrollador* - [ElenaReyes19]
- **Emiliano Vargas Ortega** - *Desarrollador* - [emilianovargas13]


## Proyecto 1: Encryptacion de datos en payload y url
Este proyecto se compone de dos carpetas, una para el cliente desarrollado en Vue.js y otra para el servidor desarrollado en Spring Boot.

### Cliente
El cliente es una aplicacion web desarrollada en Vue.js en la version 2.7.16, dicha aplicacion esta alojada en la carpeta de nombre "CruzStay" y se compone de las siguientes dependencias para el uso de la encriptacion de datos:

cryptojs: ^4.2.0, para la encriptacion de datos en el cliente en AES-256-CBC.
axios: ^1.6.7, para el manejo de peticiones http.
pinia: ^2.0.16, para el manejo de estado global de la aplicacion.


En la carpeta "CruzStay" se encuentra el archivo "README.md" con las instrucciones para la instalacion y ejecucion de la aplicacion.

# Explicacion de la encriptacion de datos en el cliente

Para implementar la encriptacion de datos, se explica lo siguiente:

1. Haciendo uso de una store de pinia se almacena la llave de encriptacion y desencriptacion de datos, esta llave es generada en el cliente y se puede almacenar en el localstorage del navegador. 

En este caso esta llave es generada en el archivo "key.js" en "CruzStay/src/store" donde podra ser llamda en cualquier archivo vue de la aplicacion para hacer uso de ella en la encriptacion de datos.

Cabe aclarar que para su uso debe ser activado pinia en el archivo "main.js" de la aplicacion. ademas de tener que importar { useSecret } from './store/key' en el archivo donde se quiera hacer uso de la llave.

2. Los metodos de encriptacion y desencriptacion de datos se encuentran en el archivo "EncryptionService.js" en "CruzStay/src/utils", aqui es donde se hace uso de la llave generada para encriptar y desencriptar los datos ademas del uso de la dependencia cryptojs para la encriptacion de datos..

3. Se implemento el servicio de encryptacion en un plugin de vue llamado "EncryptionPlugin.js" en "CruzStay/src/utils", este plugin se encarga de hacer uso de los metodos de encriptacion y desencriptacion de datos en cualquier archivo vue de la aplicacion al ser declarado y usado en el archivo "main.js" de la aplicacion.

para hacer uso de este plugin en cualquier archivo vue unicamente basta con llamar el metodo de encriptacion o desencriptacion de datos de la siguiente manera:

$encryptionService, de esta manera se podra hacer uso de los metodos de encriptacion y desencriptacion de datos en cualquier archivo vue de la aplicacion.

Como parentesis, axios tambien fue implementado en otro plugin para poder acceder a sus funiones desde cualquier archivo vue de la aplicacion, este plugin se encuentra en el archivo "AxiosPlugin.js" en "CruzStay/src/utils" y se hace uso de el en el archivo "main.js" de la aplicacion de la misma manera que el plugin de encriptacion.

4. Por ultimo, se implemento un ejemlo de uso en el archivo "CategoriesTemporal.vue" en "CruzStay/src/views", donde se hace uso de los metodos de encriptacion y desencriptacion de datos para encriptar y desencriptar datos que se envian al servidor o que se pretenden mostrar en la aplicacion.

Puedes usar este mismo como un ejemplo para tus implementaciones pues los metodos permiten encriptar y desencriptar cualquier tipo de dato, incluidos los objetos, y los datos que se desean enviar tanto desde el body de una peticion http como desde la url.



### Servidor

El servidor es una aplicacion desarrollada en Spring Boot mas actual a la fecha, spring boot 3.2.2, dicha aplicacion esta alojada en la carpeta de nombre "cruzstay-main" y se compone de las siguientes dependencias para el uso de la encriptacion de datos:

spring-boot-starter-data-jpa: ^2.6.3, para el manejo de la base de datos.
spring-boot-starter-web: ^2.6.3, para el manejo de peticiones http.
spring-boot-starter-security: ^2.6.3, para el manejo de la seguridad de la aplicacion.
jjwt-impl: ^0.11.5, para el manejo de tokens de autenticacion.
jjwt-api: ^0.11.5, para el manejo de tokens de autenticacion.
jjwt-jackson: ^0.11.5, para el manejo de tokens de autenticacion.
javax.crypto.Cipher: ^1.0.0, para el manejo de la encriptacion de datos en AES-256-CBC.
javax.crypto.spec.SecretKeySpec: ^1.0.0, para el manejo de la encriptacion de datos en AES-256-CBC.
java.nio.charset.StandardCharsets: ^1.0.0, para el manejo de la encriptacion de datos en AES-256-CBC.
java.util.Base64: ^1.8.0, para el manejo de la encriptacion de datos en AES-2


En la carpeta "cruzstay-main" se encuentra el archivo "README.md" con las instrucciones para la instalacion y ejecucion de la aplicacion.

# Explicacion de la encriptacion de datos en el servidor

Para implementar la encriptacion de datos, se explica lo siguiente:

1. Haciendo uso de la dependencia javax.crypto se implementa el algoritmo de encriptacion y desencriptacion de datos en AES-256-CBC con una llave de 16 bytes aplicando CBC y PKCS7.

2. En el archivo "properties" de la aplicacion se encuentra la llave de encriptacion y desencriptacion de datos, esta llave es generada en el servidor y se puede almacenar en el archivo "application.properties" de la aplicacion.

3. Se implementa el servicio de encryptacion en un servicio de java llamado "EncryptionService.java" en "cruzstay-main/src/main/java/stay/cruz/cruzstay/utils/", este servicio se encarga de hacer uso de los metodos de encriptacion y desencriptacion de datos en cualquier archivo java de la aplicacion unicamente usando las dependencias de javax.crypto. y la llave generada en el archivo "application.properties".

4. Por ultimo, para la implementacion de estos metodos se pueden usar en los servicios de la aplicacion para encriptar y desencriptar datos antes de que se guarden en la base de datos o antes de que se muestren en la aplicacion. asi, estos siempre seran encriptados y desencriptados en el servidor antes de ser guardados o mostrados en la aplicacion.

Puedes usar este mismo como un ejemplo para tus implementaciones pues los metodos permiten encriptar y desencriptar cualquier tipo de dato, incluidos los objetos, y los datos que se desean guardar en la base de datos o mostrar en la aplicacion.

## Proyecto 2: Encryptacion de contraseñas de usuarios en la base de datos

Este proyecto se compone de una carpeta para el servidor desarrollado en Spring Boot.

### Servidor

El servidor es una aplicacion desarrollada en Spring Boot mas actual a la fecha, spring boot 3.2.2, dicha aplicacion esta alojada en la carpeta de nombre "cruzstay-main" y se compone de las siguientes dependencias para el uso de la encriptacion de contraseñas de usuarios:

spring-boot-starter-data-jpa: ^2.6.3, para el manejo de la base de datos.
spring-boot-starter-web: ^2.6.3, para el manejo de peticiones http.
spring-boot-starter-security: ^2.6.3, para el manejo de la seguridad de la aplicacion.
jjwt-impl: ^0.11.5, para el manejo de tokens de autenticacion.
jjwt-api: ^0.11.5, para el manejo de tokens de autenticacion.
jjwt-jackson: ^0.11.5, para el manejo de tokens de autenticacion.


En la carpeta "cruzstay-main" se encuentra el archivo "README.md" con las instrucciones para la instalacion y ejecucion de la aplicacion.

# Explicacion de la encriptacion de contraseñas de usuarios en la base de datos

Para implementar la encriptacion de contraseñas de usuarios, se explica lo siguiente:

1. Haciendo uso de la dependencia spring-boot-starter-security se implementa el algoritmo de encriptacion de contraseñas de usuarios en la base de datos.

2. En el archivo "properties" de la aplicacion se encuentra la llave de encriptacion de contraseñas de usuarios, esta llave es generada en el servidor y se puede almacenar en el archivo "application.properties" de la aplicacion.

3. Se implementa el servicio de encryptacion en un servicio de java llamado "UserService.java" en "cruzstay-main/src/main/java/stay/cruz/cruzstay/services/", este servicio se encarga de hacer uso de los metodos de encriptacion de contraseñas de usuarios en cualquier archivo java de la aplicacion unicamente usando las dependencias de spring-boot-starter-security. y la llave generada en el archivo "application.properties".

4. Por ultimo, para la implementacion de estos metodos se pueden usar en los servicios de la aplicacion para encriptar las contraseñas de los usuarios antes de que se guarden en la base de datos. asi, estas siempre seran encriptadas en el servidor antes de ser guardadas en la base de datos.

Puedes usar este mismo como un ejemplo para tus implementaciones pues los metodos permiten encriptar las contraseñas de los usuarios antes de que se guarden en la base de datos.

