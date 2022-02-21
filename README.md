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
- #### Categoría
  Será la clasificación de los productos que estará agrupados en grupos dependiendo de sus características.
- #### Carrito
  Conjunto de uno o varios productos que serán comprados por el usuario que lo haya formado. Contendrá el importe total de los productos.
- #### Pedido
  Conjunto de productos que ha sido comprados por el cliente.
- #### Cliente
  Será quien navegue por la página y podrá ver los productos disponibles. Una vez que se haya registrado o iniciado sesión podrá interactuar con ellos. El cliente tendrá un nombre (único), contraseña y saldo.
- #### Vendedor
  Será quien ponga en venta el producto en la página. El vendedor tendrá nombre (único), contraseña, galería y saldo.
## 🔧Funcionalidades del Servicio Interno
- El cliente recibir correos electrónicos ya sean de carácter publicitario, informativo o por alguna actividad realizada con su cuenta. 
- El vendedor podrá recibir correos eléctronicos de carácter publicitario, informativo o por alguna actividad realizada con su cuenta y mandar correos a clientes sobre los productos o pedidos.
## 🌐Diagrama de Navegación
#### 🔓Sin Iniciar Sesión
![SinIniciarSesion](https://user-images.githubusercontent.com/73581636/154928904-e539dae5-97aa-4b12-abb0-ed81b8ed80da.png)
#### 🔐Sesión Iniciada
![SesiónIniciada](https://user-images.githubusercontent.com/73581636/154928850-ecfe911e-1837-4918-b931-3a622eee4894.png)
## 🔷Diagrama Entidad/Relación
![diagrama HTA - Página 1 (2)](https://user-images.githubusercontent.com/73581636/154920694-8410a963-9cc9-42b9-94c4-f19562532d98.jpeg)
## 📜Diagramas UML
#### 📁Objetos
![DUMLObjetos](https://user-images.githubusercontent.com/73581636/154920519-15fc8a11-94c9-459f-b385-93224b497df2.png)
#### 📁Controladores
![DiagramaControllers](https://user-images.githubusercontent.com/73581636/154920399-ebd5fbc0-f363-46c3-9683-3cd2f4f13db1.png)
