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
