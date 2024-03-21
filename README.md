# AREP-Taller6 PATRONES ARQUITECTURALES

El proposito de este proyecto consiste en manejar y distribuir diferentes servicios de manera equitativa, además profundizar en el manejo de docker y por ultimo hacer uso de AWS para el despliegue.   

## Prerequisitos

* Se debe tener minimo: Maven, Java, Git y Docker. Si desea el paso a paso de cada uno de estas instalaciones de manera más visual para mejor comprensión, puede buscar una breve explicacion en Youtube, sin embargo, adjunto los links de las paginas oficiales donde indican paso a paso su respectiva instalación.

    - Git <br>
      <https://git-scm.com/book/es/v2/Inicio---Sobre-el-Control-de-Versiones-Instalaci%C3%B3n-de-Git>
    - Maven <br>
      <https://maven.apache.org/install.html>
    - Java <br>
      <https://www.java.com/es/download/help/windows_manual_download.html>
    - Docker <br>
      <https://docs.docker.com/desktop/install/windows-install/>

## Construido con

* [Maven](https://maven.apache.org/) - Manejo de dependencias y la estructura de las carpetas
* [Git](https://git-scm.com/) - Control de versiones
* [Java](https://www.java.com/en/download/help/whatis_java.html) - Lenguaje de Programación
* [Docker](https://www.docker.com/) - Contenedores

## Arquitectura

Para este proyecto utilizaremos la siguiente arquitectura:

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/fe34b48d-2f4c-46e9-a2b0-96118c1950fa)

### LogService
Es un servicio REST que recibe un texto, además se encarga de iniciar la base datos y enviarle la respectiva informacion que el usuario ingrese, por ultimo devuelve los registros que se encuentren en la base de datos.
### RoudRobin
En este caso RoudRobin se implementara como un metodo en la clase SparkWebServer quien se encargara de recibir el mensaje que el usuario ingrese, éste se redireccionara a una api que se encargara de la conexion con la base de datos y el metodo RoundRobin será un balanceaador de cargas, es decir se encargara de distribuir el proceso en las tres instancias que se tienen.  
### MongoDB
Es una instancia que correra en un contenerdor independiente en docker. MongoDb es una base de datos no relacional que se encargara de guardar el mensaje que el usuario proporcione y asi mismo su fecha y hora.

## Diseño

Las clases que se implementaron son las siguientes:

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/8b7574f8-73bd-46c4-87b1-3b4881db618e)

* HttpConection: Hace la conexión entre apis de java.
* LogService: Inicia la base de datos, además se encarga de enviarle a la base de datos la información que le llego del api que consume el cliente y por ultimo obtiene los registros que se encuentran de la base de datos.
  
![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/4494d7ce-806b-4f26-9541-563cae74fead)

* MongoUtil: Crea la base de datos
* LogController: Administra la base de datos es decir, añade y devuelve logs.
  
![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/a836fd2e-6450-4d46-bc63-605d6ef2e202)

* SparKWebServer: Recibi  los datos del cliente y los pasa al api que se encarga de conectar con la base de datos, ademas contiene el metodo rounRobin para distribuir los  procesos en los tres servicios que se tienen.

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/a5c63700-77d2-47db-99aa-3163799fd4ad)

## Empezando

* Para obtener una copia del proyecto en su maquina local:

    - Se debe ubicar en la carpeta donde desea bajar el proyecto y le da click donde señala la flecha y esribe cmd:

      ![image](https://github.com/XimenaRodriguez20/AREP-Taller2/assets/123812926/52f8f03c-3b3e-48cf-bd2c-f7b029c2d8bb)

    - Después de esto debe escribir el siguiente comando:

        ~~~                  
        git clone https://github.com/XimenaRodriguez20/AREP-Taller6.git
        ~~~                                                                   

* Para poder correr el código abra el IDE de su preferencia y anote los siguientes comandos en la terminal:

    - Para compilar el proyecto utilice:
  
        ~~~                 
            mvn clean install
        ~~~  
      
    - Para crear las imagenes y correr los contendores utilice:

        ~~~
            docker compose up -d
        ~~~
    
* Si desea correr desde el cmd haga los pasos mencionados anteriormente, pero antes de eso recuerde ubicarse en la carpeta del proyecto:

    ~~~
        cd AREP-Taller6/
    ~~~


## Pruebas de Manera Local

* Después de clonado el repositorio escribimos los comandos mencionados anteriormente para arrancar el proyecto:

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/79ea0a46-0045-4e59-b197-b10ba07d6461)

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/4555a961-df2b-4408-a7bd-4b4114fda499)

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/edcc3418-150e-44ff-b1a4-c8ffb51080d0)


* Es necesario tener abierto docker al correr el proyecto porque aca se generaran las imagenes y los contenedores que veran a continuación:

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/16b7c310-b461-46df-81de-dce564cefb78)

![image](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/c43a48d2-5f78-475d-a7f1-b86379ae111b)


* Para evidenciar que está funcionando el proyecto, ingresamos a la siguiente url:

    ~~~
        http://localhost:8080/logs.html
    ~~~

* Ingresamos alguna palabra para comprobar que se esten guardando con su respectiva fecha y que solo se esten mostrando 10 registros, cabe aclarar que apenas se inicia el proyecto se eliminaran los resgistros previos:
  
![WhatsApp Image 2024-03-21 at 01 38 19](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/e3c5bdc9-cff3-445c-97dc-3d60ac9788a2)
![WhatsApp Image 2024-03-21 at 01 37 38](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/0476afaf-7b73-4569-9348-d217b1fc6d55)
![WhatsApp Image 2024-03-21 at 01 34 11](https://github.com/XimenaRodriguez20/AREP-Taller6/assets/123812926/ccba1bf7-1e64-46eb-8a15-d2c85e06197e)


## Prueba y despliegue con AWS

* [Dspligue](https://youtu.be/rsujYsaycY0)

## Autor

* **Ximena Rodriguez** 
