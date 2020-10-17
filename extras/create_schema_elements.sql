CREATE SCHEMA `bookstore` ;

USE `bookstore`;

CREATE TABLE `bookstore`.`tipolista` (
  `id_tipolista` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id_tipolista`));

CREATE TABLE `bookstore`.`genero` (
  `id_genero` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  `descripcion` TEXT NULL,
  PRIMARY KEY (`id_genero`));

CREATE TABLE `bookstore`.`autor` (
  `id_autor` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(250) NOT NULL,
  `alias` VARCHAR(250) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_autor`));

CREATE TABLE `bookstore`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(250) NOT NULL,
  `apellidos` VARCHAR(250) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id_usuario`));

CREATE TABLE `bookstore`.`estado` (
  `id_estado` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id_estado`));

CREATE TABLE `bookstore`.`libro` (
  `id_libro` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(250) NOT NULL,
  `descripcion` TEXT NULL,
  `fecha_publicacion` DATE NULL,
  `precio` DECIMAL(8,2) NOT NULL,
  `id_genero` INT NOT NULL,
  `id_estado` INT NOT NULL,
  `id_autor` INT NOT NULL,
  PRIMARY KEY (`id_libro`),
  INDEX `fk_constraint_libro_genero_idx` (`id_genero` ASC) VISIBLE,
  INDEX `fk_constraint_libro_estado_idx` (`id_estado` ASC) VISIBLE,
  INDEX `fk_constraint_libro_autor_idx` (`id_autor` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_libro_genero`
    FOREIGN KEY (`id_genero`)
    REFERENCES `bookstore`.`genero` (`id_genero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_libro_estado`
    FOREIGN KEY (`id_estado`)
    REFERENCES `bookstore`.`estado` (`id_estado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_libro_autor`
    FOREIGN KEY (`id_autor`)
    REFERENCES `bookstore`.`autor` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bookstore`.`libro_genero` (
  `id_libro` INT NOT NULL,
  `id_genero` INT NOT NULL,
  INDEX `fk_constraint_librogenero_1_idx` (`id_libro` ASC) VISIBLE,
  INDEX `fk_constraint_librogenero_2_idx` (`id_genero` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_librogenero_1`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bookstore`.`libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_librogenero_2`
    FOREIGN KEY (`id_genero`)
    REFERENCES `bookstore`.`genero` (`id_genero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bookstore`.`libro_autor` (
  `id_libro` INT NOT NULL,
  `id_autor` INT NOT NULL,
  INDEX `fk_constraint_libroautor_1_idx` (`id_libro` ASC) VISIBLE,
  INDEX `fk_constraint_libroautor_2_idx` (`id_autor` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_libroautor_1`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bookstore`.`libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_libroautor_2`
    FOREIGN KEY (`id_autor`)
    REFERENCES `bookstore`.`autor` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bookstore`.`lista` (
  `id_lista` INT NOT NULL AUTO_INCREMENT,
  `id_tipolista` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_lista`),
  INDEX `fk_constraint_lista_1_idx` (`id_tipolista` ASC) VISIBLE,
  INDEX `fk_constraint_lista_usuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_lista_tipolista`
    FOREIGN KEY (`id_tipolista`)
    REFERENCES `bookstore`.`tipolista` (`id_tipolista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_lista_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bookstore`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `bookstore`.`comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `comentario` TEXT NOT NULL,
  `fecha` DATE NOT NULL,
  `puntuacion` DECIMAL(8,2) NOT NULL,
  `id_usuario` INT NOT NULL,
  `id_libro` INT NOT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `fk_constraint_comentario_usuario_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_constraint_comentario_libro_idx` (`id_libro` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_comentario_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bookstore`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_comentario_libro`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bookstore`.`libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bookstore`.`lista_libro` (
  `id_lista` INT NOT NULL,
  `id_libro` INT NOT NULL,
  INDEX `fk_constraint_listalibro_1_idx` (`id_lista` ASC) VISIBLE,
  INDEX `fk_constraint_listalibro_2_idx` (`id_libro` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_listalibro_1`
    FOREIGN KEY (`id_lista`)
    REFERENCES `bookstore`.`lista` (`id_lista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_listalibro_2`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bookstore`.`libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `bookstore`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `nroPedido` VARCHAR(50) NOT NULL,
  `fechaPedido` DATETIME NOT NULL,
  `total` DECIMAL(8,2) NOT NULL,
  `direccionEntrega` VARCHAR(250) NULL,
  `referencia` VARCHAR(250) NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_constraint_pedido_usuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_pedido_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bookstore`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bookstore`.`detallepedido` (
  `id_pedido` INT NOT NULL,
  `id_libro` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `precio` DECIMAL(5,2) NOT NULL,
  INDEX `fk_constraint_detallepedido_1_idx` (`id_pedido` ASC) VISIBLE,
  INDEX `fk_constraint_detallepedido_2_idx` (`id_libro` ASC) VISIBLE,
  CONSTRAINT `fk_constraint_detallepedido_1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `bookstore`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_constraint_detallepedido_2`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bookstore`.`libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
