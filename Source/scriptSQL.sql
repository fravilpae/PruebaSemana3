CREATE DATABASE bdalmacencurso;
USE bdalmacencurso;

CREATE TABLE `bdalmacencurso`.`productos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `precio` DECIMAL(6,2) NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO productos VALUES (1, 'Producto 1','Limpieza', 'Producto de limpieza caro', 47.99, 10);
INSERT INTO productos VALUES (2, 'Producto 2','Limpieza', 'Producto de limpieza barato', 12.99, 100);
INSERT INTO productos VALUES (3, 'Producto 3','Alimentacion', 'Comida', 2.99, 400);
INSERT INTO productos VALUES (4, 'Producto 4','Mobiliario', 'Armario grande', 117.99, 50);