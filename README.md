# Proyecto Gestión de almacén

Este es un proyecto Java Dynamic Web Project, que trata sobre la gestión de productos de un almacén. En este almacén se podrán realizar distintas funcionalidades, como dar de alta un producto, darlo de baja, listar los productos disponibles, modificar un producto y buscar productos específicos.

Para utilizar este proyecto no requiere instalación previa.

## Descripción

**Clases**

La clase Producto representa a los productos del almacén. La información de un producto es su id, nombre, descripción, categoría, precio y stock(cantidad almacenada en el almacén).

La clase Categoria es un enumerado con los distintos valores de categoría de un producto.

En la clase ProductoDAO se encuentran las implementaciones de los métodos indicados en la interfaz IProducto, estos métodos son las funcionalidades que se podrán realizar en la gestión de productos.

La clase FrontControlador se encarga de dirigir al usuario por las diferentes funcionalidades de la página, dependiendo de la acción que desee realizar.

La clase BuscarControlador se encarga de indicar el tipo de búsqueda que está realizando el usuario cuando está buscando productos específicos.

La clase IndexException es una excepción creada, que controlará que el ID de un producto existe. Útil en borrar y modificar.

**Servlets**

El servlet ListarServlet proporciona la lógica necesaria para mostrar los productos disponibles.

El servlet DarAltaServlet proporciona la lógica necesaria para dar de alta a un producto.

El servlet DarBajaServlet proporciona la lógica necesaria para dar de baja a un producto.

El servlet ModificarServlet proporciona la lógica necesaria para modificar un producto.

El servlet BuscarNombreServlet proporciona la lógica necesaria para buscar productos específicos por su nombre.

El servlet BuscarDescripcionServlet proporciona la lógica necesaria para buscar productos específicos por su descripción.

El servlet BuscarCategoriaServlet proporciona la lógica necesaria para buscar productos específicos por su categoría.

**Archivos jsp**

El contenido de alta.jsp se muestra cuando se quiere dar de alta un producto.

El contenido de altacorrecto.jsp se muestra cuando se da de alta un producto correctamente.

El contenido de baja.jsp se muestra cuando se quiere dar de baja un producto.

El contenido de bajacorrecto.jsp se muestra cuando se da de baja un producto correctamente.

El contenido de buscador.jsp se muestra cuando se quiere buscar productos específicos.

El contenido de error.jsp se muestra cuando ocurre algún error durante la ejecución del programa.

El contenido de index.jsp es la página inicial del proyecto.

El contenido de listado.jsp se muestra cuando se quiere listar los productos disponibles.

El contenido de modifica.jsp se muestra cuando se quiere modificar un producto.

El contenido de modificacorrecto.jsp se muestra cuando se modifica un producto correctamente.

El contenido de modificaincorrecto.jsp se muestra cuando se modifica un producto incorrectamente.

El contenido de productosbuscados.jsp se muestra cuando se realiza una búsqueda de productos específicos.

## Funcionalidades
**Clase Producto**

La clase Producto tiene las siguientes funcionalidades:

- Constructores: Un constructor crea una instancia de producto con un nombre, descripción, categoría, precio y stock. Otro constructor crea una instancia de producto añadiendo además el id del producto. Este segundo constructor se usa para mostrar productos en el programa, y el primero se usa para crearlos para la base de datos.
- getId(): Devuelve el id del producto.
- setId(int id): Modifica el id del producto.
- getNombre(): Devuelve el nombre del producto.
- setNombre(String nombre): Modifica el nombre del producto.
- getDescripcion(): Devuelve la descripción del producto.
- setDescripcion(String descripcion): Modifica la descripción del producto.
- getCategoria(): Devuelve la categoría del producto.
- setCategoria(Categoria categoria): Modifica la categoría del producto.
- getPrecio(): Devuelve el precio del producto.
- setPrecio(double precio): Modifica el precio del producto.
- getStock(): Devuelve el stock del producto.
- setStock(int stock): Modifica el stock del producto.
- getAllCategorias(): Método estático que devuelve las categorías disponibles.

**Clase ProductoDAO**

La clase ProductoService() tiene las siguientes funcionalidades:

- obtenerDataSource(): Devuelve el DataSource de la conexión a la base de datos.
- findAll(): Devuelve la lista de productos disponibles.
- save(Producto p): Añade un producto a la base de datos.
- delete(int index): Elimina un producto de la base de datos.
- update(Producto p): Modifica un producto existente de la base de datos.
- findByNombre(String nombre): Devuelve los productos que concuerden con el nombre indicado en el parámetro.
- findByDescripcion(String descripcion): Devuelve los productos que concuerden con la descripción indicada en el parámetro.
- findByCategoria(Categoria categoria): Devuelve los productos que concuerden con la categoría indicada en el parámetro.
- validarDatos(String nombre, String descripcion, Categoria categoria, double precio, int stock): Valida los datos de un producto. Útil para dar de alta o modificar un producto.

**Clases controladores y servlets**

FrontControlador.doGet(HttpServletRequest request, HttpServletResponse response): Redirige a las distintas funcionalidades del programa, en función de la que se eliga.

BuscarControlador.doPost(HttpServletRequest request, HttpServletResponse response): Controla el tipo de búsqueda que realiza el usuario, en función de la que se eliga.

ListarServlet.doGet(HttpServletRequest request, HttpServletResponse response): Devuelve el listado de todos los productos disponibles.

DarAltaServlet.doPost(HttpServletRequest request, HttpServletResponse response): Crea un producto y lo añade a la base de datos.

DarBajaServlet.doGet(HttpServletRequest request, HttpServletResponse response): Devuelve todos los productos disponibles.

DarBajaServlet.doPost(HttpServletRequest request, HttpServletResponse response): Elimina al producto elegido de la base de datos.

ModificarServlet.doGet(HttpServletRequest request, HttpServletResponse response): Devuelve todos los productos disponibles.

ModificarServlet.doPost(HttpServletRequest request, HttpServletResponse response): Modifica el producto seleccionado de la base de datos con los datos indicados.

BuscarNombreServlet.doPost(HttpServletRequest request, HttpServletResponse response): Devuelve los productos de la base de datos en función del nombre indicado.

BuscarDescripcionServlet.doPost(HttpServletRequest request, HttpServletResponse response): Devuelve los productos de la base de datos en función de la descripción indicada.

BuscarCategoriaServlet.doPost(HttpServletRequest request, HttpServletResponse response): Devuelve los productos de la base de datos en función de la categoría indicada.

## Uso
Para utilizar el programa, debe arrancar en un servidor el archivo index.jsp. Una vez hecho, se cargará en su navegador la página inicial, donde podrá acceder a las distintas funcionalidades del programa.

## Historia
Versión 1.0 (2024-12-14) - Lanzamiento inicial