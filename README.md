# NftForMe
## 📝Descripción
NftForMe es una página Web donde se podrán comprar o vender NFTs ( Non-Fungible Token). 
## 🔓Funcionalidades Públicas
Los usuarios podrán ver el precio de los productos (NFTs) y sus características sin necesidad de iniciar sesión.
## 🔐Funcionalidades Privadas
En caso de que el usuario quiera comprar o vender un producto y contactar con el vendedor o cliente deberá iniciar sesión en la página. 
## 📑Entidades 
- #### Producto (NFT)
  Es el elemento en venta. El producto tendrá autor, precio, nombre e identificador.
- #### Carrito
  Conjunto de uno o varios productos que serán comprados por el usuario que lo haya formado. Contendrá el importe total de los productos.
- #### Pedido
  Conjunto de productos que ha sido comprados por el cliente.
- #### Cliente
  Será quien navegue por la página y podrá ver los productos disponibles. Una vez que se haya registrado o iniciado sesión podrá interactuar con ellos. El cliente tendrá un nombre (único), contraseña y saldo.
- #### Vendedor
  Será quien ponga en venta el producto en la página. El vendedor tendrá nombre (único), contraseña, galería y saldo.
## 🔧Funcionalidades del Servicio Interno
- El servidor se encargará de crear una factura con cada pedido que se haga.
- GitHub Servicio Interno : https://github.com/sstsanta/ServicioInterno
## 🌐Diagrama de Navegación
#### 🔓Sin Iniciar Sesión
![SinIniciarSesion](https://user-images.githubusercontent.com/73581636/154928904-e539dae5-97aa-4b12-abb0-ed81b8ed80da.png)
#### 🔐Sesión Iniciada
![SesiónIniciada](https://user-images.githubusercontent.com/73581636/154928850-ecfe911e-1837-4918-b931-3a622eee4894.png)
## 🔷Diagrama Entidad/Relación
![diagrama HTA - Página 1 (2)](https://user-images.githubusercontent.com/73581636/155111702-db859408-7c69-420f-8e9e-207fe61bd908.jpeg)

## 📜Diagramas UML
#### 📁Objetos
![DUMLObjetos](https://user-images.githubusercontent.com/73581636/154920519-15fc8a11-94c9-459f-b385-93224b497df2.png)
#### 📁Controladores
![DiagramaControllers](https://user-images.githubusercontent.com/73581636/154920399-ebd5fbc0-f363-46c3-9683-3cd2f4f13db1.png)

## 📋Instrucciones 
 ### 1ºActualizamos nuestro sistema 

 	sudo apt-get update
 	sudo apt-get upgrade -y
 	sudo apt dist-upgrade -y
 	sudo apt autoremove -y

 ### 2ºInstalamos los paquetes esenciales 

 	sudo apt install docker default-jre mysql-server git

 ### 3ºConfiguramos la base de datos

 	sudo mysql

 #### - Creamos un usuario:
 	CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';

 #### - Dotamos al usuario de permisos
 	GRANT ALL PRIVILEGES ON *.* TO 'user'@'localhost';

 #### - Actualizamos los permisos
 	FLUSH PRIVILEGES;

 #### - Creamos una base de datos
 	CREATE DATABASE db;

 #### - Salimos de SQL:
 	EXIT

 ### 4ºDescargamos los paquetes JAR necesarios para iniciar tanto el servicio interno como la web

 	git clone https://github.com/araso2000/InstalarWebDAD.git

 ### 5º Ejecutamos

 #### - Entramos en la carpeta
 	cd InstalarWebDAD

 #### - Arrancamos RabbitMQ como contenedor de Docker:
 	sudo chmod 777 RabbitMQServer-DockerContainer.sh

 	./RabbitMQServer-DockerContainer.sh

 #### - Arrancamos el Servicio Interno:
 	java -jar ServicioInterno.jar

 #### - Arrancamos la Web
 	java -jar Web.jar

 ### 6º Para acceder a la Web tecleamos:
 	https://localhost:8443/
  ## 🌐Infraestructura de la web
  <img width="1337" alt="Captura de Pantalla 2022-05-04 a las 19 49 12" src="https://user-images.githubusercontent.com/96995329/166754315-7234a575-8319-42b7-9458-40fee0523cba.png">

## 🗄️Instanciar MySQL
 #### - Creamos un contenedor (Docker) de MySQL persistente en el servidor maestro y esclavo usando un volumen:
    docker run -d --rm --name mysql -e MYSQL_ROOT_PASSWORD=root -p 33060:3306 -v mysql_data:/var/lib/mysql mysql/mysql-   server:latest  
#### - Accedemos al nuevo entorno (Docker) MySQL:
    sudo docker exec -it mysql bash
#### Entramos en la Base de Datos introduciendo posteriormente la contraseña:
    mysql -uroot -p
#### Creamos un usuario llamado 'test':
    CREATE USER 'test'@'%' IDENTIFIED BY '******';
#### Damos todos los permisos:
    GRANT ALL ON *.* TO 'test'@'%';
#### Creamos un usuario de réplica:
    CREATE USER 'replicauser'@'51.91.183.157' IDENTIFIED WITH'mysql_native_password' BY '****';
    GRANT REPLICATION SLAVE ON *.* TO'replicauser'@'51.91.183.157';
#### En el servidor maestro añadimos el parámetro server-id en el fichero my.cnf:
    cd /etc
    echo 'server-id=1' >> my.cnf
    echo 'log-bin=mysql-bin' >> my.cnf
#### En el servidor esclavo añadimos el parámetro server-id en el fichero my.cnf
    cd /etc/
    echo 'server-id=2' >> my.cnf
    echo 'log-bin=mysql-bin' >> my.cnf
#### Arrancamos el esclavo
    CHANGE MASTER TO MASTER_HOST='217.71.202.245', MASTER_PORT=33060, MASTER_USER='replicauser', MASTER_PASSWORD='*****', MASTER_LOG_FILE='mysql-bin.000009', MASTER_LOG_POS=2369;
    START SLAVE;
#### Reiniciamos maestro:
    RESET MASTER;
## Haproxy
  #### - Cargamos el contenedor
  	docker pull haproxytech/haproxy-alpine
  #### - Creamos el Docker file
  	FROM haproxytech/haproxy-alpine:2.0
  	COPY haproxy.cfg /usr/local/etc/haproxy/haproxy.cfg
  #### - Contruimos el contenedor, en la ruta que queramos
  	docker build -t {nombre-contenedor} {ruta}
  #### - Copiamos el archivo haproxy.cfg a la ruta de nuestro contenedor y lo editamos
  	cp /usr/local/etc/haproxy/haproxy.cfg {ruta}
  #### - Archivo haproxy.cfg
 	 global

	defaults

	listen stats
   		mode http
			bind *:8404  
			stats enable
			stats refresh 5s
			stats show-legends
			stats uri /stats

	frontend sok-front-end
    		bind *:80
    		bind *:443 ssl crt /usr/local/etc/haproxy/prueba.pem
   		acl https ssl_fc
    		http-request set-header X-Forwarded-Proto http  if !https
   		http-request set-header X-Forwarded-Proto https if https
    		mode http
    		default_backend sok-backend-end

	backend sok-backend-end
   		mode http
    		balance roundrobin 
    		server web1 172.17.0.1:8001 check
    		server web2 172.17.0.1:8002 check

	listen  mysql-cluster
		bind *:33060
		mode tcp
		option mysql-check
		balance roundrobin
		server bbdd1 172.17.0.1:8004 check
		server bbdd2 172.17.0.1:8005 check
  #### - Por ultimo arrancamos el contenedor con los puertos correspondientes para la entrada al haproxy, que tenemos especificados en el archivo de configuración
  	docker run --name haproxy -d -v {ruta-archivo.cfg}:/usr/local/etc/haproxy:ro -p 33060:33060 -p 80:80 -p 8443:8443 -p 8404:8404 -p 443:443 haproxytech/haproxy-alpine:2.4
### Crear las webs y los servicios internos
#### Creamos un archivo Dockerfile para cada jar, en el mismo directorio
	FROM adoptopenjdk/openjdk11:latest
	RUN sh -c 'mkdir /usr/app'
	COPY ./{archivo-jar} /usr/app
	ENTRYPOINT ["java", "-jar", "/usr/app/{archivo-jar}"]
#### Construimos el contenedor
	docker build -t araso2000/nftforme_web1:tag .
#### Iniciamos el contenedor especificando los puertos que queremos parchear
	docker run --name web1 -d -p 8001:80 araso2000/nftforme_web:1.1.9
 
### RabbitMq en contenedor
#### Cogemos la imagen de docker
	docker pull rabbitmq
#### Iniciamos el contenedor
	docker run --rm -d -it --name rabbit --hostname my-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

## 📜Diagramas UML Actualizados
#### 📁Objetos
![DiagramaObjetos](https://user-images.githubusercontent.com/73581636/166884715-b7b759fc-fbde-406f-a991-cac0486582ed.png)
#### 📁Controladores
![Diagrama Controladores](https://user-images.githubusercontent.com/73581636/166884851-acb0bc10-4dcc-4e66-a452-6df82714d684.png)
#### 📁Repositorios
![DiagramaRepositorias](https://user-images.githubusercontent.com/73581636/166884918-f270487e-6e87-42dd-9574-a036a94de665.png)
#### 📁Seguridad
![DiagramaSeguridad](https://user-images.githubusercontent.com/73581636/166884988-8586207c-26b7-45a2-9acb-26a9b0d6bd1a.png)
