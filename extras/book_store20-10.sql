-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-10-2020 a las 07:16:11
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `book_store`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `id_autor` int(11) NOT NULL,
  `nombres` varchar(250) NOT NULL,
  `alias` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`id_autor`, `nombres`, `alias`) VALUES
(1, 'Antoine Marie Jean-Baptiste Roger Conde de Saint-Exupéry', 'Antoine de Saint-Exupéry'),
(2, 'Samuel Langhorne Clemens', 'Mark Twain'),
(3, 'Jules Gabriel Verne', 'Julio Verne'),
(4, 'Daniel Foe', 'Daniel Defoe'),
(5, 'Isaak Yúdovich Ozímov', 'Isaac Asimov'),
(6, 'Stanislaw Herman Lem', 'Stanislaw Lem'),
(7, 'Herbert George Wells', 'H. G. Wells'),
(8, 'Richard Burton Matheson', 'Richard Matheson'),
(9, 'Edgar Allan Poe', 'Edgar Allan Poe'),
(10, 'Arthur Ignatius Conan Doyle', 'Arthur Conan Doyle'),
(11, 'Agatha Mary Clarissa Miller', 'Agatha Christie'),
(12, 'Howard Phillips Lovecraft', 'H. P. Lovecraft'),
(13, 'Juan Nepomuceno Carlos Pérez Rulfo Vizcaíno', 'Juan Rulfo'),
(14, 'Stephen Edwin King', 'Stephen King'),
(15, 'Judith McNaught Smith', 'Judith McNaught'),
(16, 'Marian Keyes', 'Marian Keyes'),
(17, 'Jojo Moyes', 'Jojo Moyes'),
(18, 'Roald Dahl', 'Roald Dahl'),
(19, 'Oscar Wilde', 'Oscar Wilde');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id_comentario` int(11) NOT NULL,
  `comentario` text NOT NULL,
  `fecha` datetime NOT NULL,
  `puntuacion` decimal(18,2) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id_estado` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id_estado`, `nombre`) VALUES
(1, 'Disponible'),
(2, 'No disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id_genero` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id_genero`, `nombre`, `descripcion`) VALUES
(1, 'Aventura', 'La novela de aventuras es la esencia misma de la ficción, puesto que se gesta con el sencillo objetivo de entretener. La aventura es aquello que se opone a la rutina, a lo cotidiano, de ahí su valor.'),
(2, 'Ciencia Ficcion', 'La ciencia ficción es un género de la narrativa de ficción en el que están presentes avances científicos y técnicos, ya sea en el futuro o en el presente, que afectan e intervienen en la sociedad y en la vida de los individuos.'),
(3, 'Policíaca', 'Un detective, un crimen, un asesino, un móvil... la novela policíaca está llena de misterio y acción.'),
(4, 'Terror y misterio', 'El horror o terror es un género literario que se define por la sensación que causa: miedo.'),
(5, 'Romanticas', '<3'),
(6, 'Literatura infantil', 'a');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id_libro` int(11) NOT NULL,
  `titulo` varchar(250) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `precio` decimal(18,2) NOT NULL,
  `url_portada` text NOT NULL,
  `url_ubicacion` text NOT NULL,
  `id_estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id_libro`, `titulo`, `descripcion`, `fecha_publicacion`, `precio`, `url_portada`, `url_ubicacion`, `id_estado`) VALUES
(1, 'Las aventuras de Tom Sawyer', 'Los recuerdos de la niñez y juventud le sirven de inspiración a Mark Twain para escribir Las aventuras de Tom Sawyer, ambientado en una pequeña localidad a orillas del río Misisipi, como la que vio crecer al escritor en los primeros años de su vida (Hannibal).', '2005-01-01', '46.70', 'C:\\web\\resources\\images\\cover_page\\las_aventuras_tom_sawyer_2005.jpg', 'C:\\web\\resources\\ebook\\as_aventuras_tom_sawyer_2005.pdf', 1),
(2, 'La vuelta al mundo en ochenta dias', 'Cuenta las aventuras de Phileas Fogg y su criado para cumplir con una apuesta dada a sus compañeros de club y los problemas que se encuentra en su intento.', '2020-01-01', '31.70', 'C:\\web\\resources\\images\\cover_page\\la_vuelta_al_mundo_en_80_dias_2020.jpg', 'C:\\web\\resources\\ebook\\la_vuelta_al_mundo_en_80_dias_2020.pdf', 1),
(3, 'Robinson Crusoe', 'Daniel Defoe te deleitará con la historia de Robinson Crusoe, un marinero de York que naufraga y pasa 28 años viviendo en una isla alejada de la civilización. El libro está escrito en forma de autobiografía, como si el propio Robinson estuviera relatando los hechos de su puño y letra.', '2016-01-01', '45.90', 'C:\\web\\resources\\images\\cover_page\\robinson_crusoe_2016.jpg', 'C:\\web\\resources\\ebook\\robinson_crusoe_2016.pdf', 1),
(4, 'Viaje al centro de la tierra', 'El profesor Lidenbrock y su sobrino Axel descubrirán un mundo subterráneo, con mares, bosques y animales antediluvianos. ¿Sobrevivirán a su peligroso encuentro con dinosaurios gigantes, poderosas tormentas y seres misteriosos?', '2018-01-01', '48.00', 'C:\\web\\resources\\images\\cover_page\\viaje_al_centro_de_la_tierra_2018.jpg', 'C:\\web\\resources\\ebook\\viaje_al_centro_de_la_tierra_2018.pdf', 1),
(5, 'Robbie y otros relatos', 'Sinopsis', '2015-01-01', '39.90', 'C:\\web\\resources\\images\\cover_page\\robbie_y_otros_relatos_2015.jpg', 'C:\\web\\resources\\ebook\\robbie_y_otros_relatos_2015.pdf', 1),
(6, 'La maquina del tiempo', 'Obra que se halla en los inicios de la novela de ciencia-ficción, sigue conservando el mismo poder de fascinación y vigor narrativo que le valieron el éxito inmediato en el momento de su publicación.', '2019-01-01', '47.90', 'C:\\web\\resources\\images\\cover_page\\la_maquina_del_tiempo_2019.jpg', 'C:\\web\\resources\\ebook\\la_maquina_del_tiempo_2019.pdf', 1),
(7, 'Soy leyenda', 'Robert Neville es el único superviviente de una guerra bacteriológica que ha asolado el planeta y convertido al resto de la humanidad en vampiros. Su vida se ha reducido a asesinar el máximo número posible de estos seres sanguinarios durante el día, y a soportar su asedio cada noche.', '2020-01-01', '67.70', 'C:\\web\\resources\\images\\cover_page\\soy_leyenda_2020.jpg', 'C:\\web\\resources\\ebook\\soy_leyenda_2020.pdf', 1),
(8, 'Estudio en escarlata', 'Gira en torno a un crimen cometido en Londres y cuya trama se relaciona con la secta mormona y el estado de Utah.', '2012-01-01', '39.90', 'C:\\web\\resources\\images\\cover_page\\estudio_en_escarlata_2012.jpg', 'C:\\web\\resources\\ebook\\estudio_en_escarlata_2012.pdf', 1),
(9, 'El misterioso caso de styles', 'Essex, Inglaterra. En la mansión Styles, la millonaria Emily Inglethorp es encontrada muerta en su cama, aparentemente víctima de un ataque cardíaco.', '2018-01-01', '59.40', 'C:\\web\\resources\\images\\cover_page\\el_misterioso_caso_de_styles_2018.jpg', 'C:\\web\\resources\\ebook\\el_misterioso_caso_de_styles_2018.pdf', 1),
(10, 'Relatos espectrales', 'sinopsis', '2015-01-01', '43.50', 'C:\\web\\resources\\images\\cover_page\\relatos_espectrales_2015.jpg', 'C:\\web\\resources\\ebook\\relatos_espectrales_2015.pdf', 1),
(11, 'El resplandor', 'Danny tenía cinco años, y a esa edad poco niños saben que los espejos invierten las imágenes y menos aún saben diferenciar entre realidad y fantasía.', '2013-01-01', '43.70', 'C:\\web\\resources\\images\\cover_page\\el_resplandor_2013.jpg', 'C:\\web\\resources\\ebook\\el_resplandor_2013.pdf', 1),
(12, 'Para siempre', 'La estadounidense Victoria Seaton atraviesa el vasto océano ansiosa por recuperar su patrimonio perdido hace tiempo. Al llegar a destino, conocerá el amor y la traición, cuando había soñado que ...', '2018-01-01', '39.70', 'C:\\web\\resources\\images\\cover_page\\para_siempre_2018.jpg', 'C:\\web\\resources\\ebook\\para_siempre_2018.pdf', 1),
(13, 'Una pareja casi perfecta', 'Amy tiene el marido perfecto. Hugh es guapo, es un buen tipo y un padre maravilloso. Pero un día, de pronto, le dice que quiere que se tomen un descanso.', '2019-01-01', '39.70', 'C:\\web\\resources\\images\\cover_page\\una_pareja_casi_perfecta_2019.jpg', 'C:\\web\\resources\\ebook\\una_pareja_casi_perfecta_2019.pdf', 1),
(14, 'Yo antes de ti', 'Louisa Clark sabe muchas cosas. Sabe cuántos pasos hay entre la parada del autobús y su casa. Sabe que le gusta trabajar en el café Buttered Bun y sabe que quizá no quiera a su novio Patrick.', '2018-01-01', '39.70', 'C:\\web\\resources\\images\\cover_page\\yo_antes_de_ti_2018.jpg', 'C:\\web\\resources\\ebook\\yo_antes_de_ti_2018.pdf', 1),
(15, 'Matilda', 'Matilda es una lectora empedernida con tan solo cinco años. Sensible e inteligente, todos la admiran menos sus mediocres padres que la consideran una inútil. Además, tiene poderes extraños y maravillosos...', '2016-01-01', '41.50', 'C:\\web\\resources\\images\\cover_page\\matilda_2016.jpg', 'C:\\web\\resources\\ebook\\matilda_2016.pdf', 1),
(16, 'Charlie y la fabrica de chocolate', 'El señor Wonka ha escondido cinco billetes de oro en sus chocolatinas. Quienes los encuentren serán los afortunados que visiten su magnífica fábrica de chocolate...', '2016-01-01', '41.50', 'C:\\web\\resources\\images\\cover_page\\charlie_y_la_fabrica_de_chocolote_2016.jpg', 'C:\\web\\resources\\ebook\\charlie_y_la_fabrica_de_chocolote_2016.pdf', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro_autor`
--

CREATE TABLE `libro_autor` (
  `id_libro` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libro_autor`
--

INSERT INTO `libro_autor` (`id_libro`, `id_autor`) VALUES
(1, 2),
(2, 3),
(4, 3),
(3, 4),
(5, 5),
(6, 7),
(7, 8),
(8, 10),
(9, 11),
(10, 12),
(11, 14),
(12, 15),
(13, 16),
(14, 17),
(15, 18),
(16, 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro_genero`
--

CREATE TABLE `libro_genero` (
  `id_libro` int(11) NOT NULL,
  `id_genero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libro_genero`
--

INSERT INTO `libro_genero` (`id_libro`, `id_genero`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 2),
(6, 2),
(7, 2),
(8, 3),
(9, 3),
(10, 4),
(11, 4),
(12, 5),
(13, 5),
(14, 5),
(15, 6),
(16, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista`
--

CREATE TABLE `lista` (
  `id_lista` int(11) NOT NULL,
  `id_tipolista` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista_libro`
--

CREATE TABLE `lista_libro` (
  `id_lista` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipolista`
--

CREATE TABLE `tipolista` (
  `id_tipolista` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombres` varchar(250) NOT NULL,
  `apellidos` varchar(250) NOT NULL,
  `nickname` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombres`, `apellidos`, `nickname`, `password`) VALUES
(1, 'Diego', 'Salas', 'dsalas', 'dsalas'),
(2, 'Flor', 'Cortez', 'fcortez', 'fcortez'),
(3, 'Wilder', 'Lizama', 'wlizama', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id_autor`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id_comentario`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_libro` (`id_libro`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id_genero`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id_libro`),
  ADD KEY `id_estado` (`id_estado`);

--
-- Indices de la tabla `libro_autor`
--
ALTER TABLE `libro_autor`
  ADD KEY `id_libro` (`id_libro`),
  ADD KEY `id_autor` (`id_autor`);

--
-- Indices de la tabla `libro_genero`
--
ALTER TABLE `libro_genero`
  ADD KEY `id_libro` (`id_libro`),
  ADD KEY `id_genero` (`id_genero`);

--
-- Indices de la tabla `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`id_lista`),
  ADD KEY `id_tipolista` (`id_tipolista`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `lista_libro`
--
ALTER TABLE `lista_libro`
  ADD KEY `id_lista` (`id_lista`),
  ADD KEY `id_libro` (`id_libro`);

--
-- Indices de la tabla `tipolista`
--
ALTER TABLE `tipolista`
  ADD PRIMARY KEY (`id_tipolista`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autor`
--
ALTER TABLE `autor`
  MODIFY `id_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id_comentario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id_genero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `lista`
--
ALTER TABLE `lista`
  MODIFY `id_lista` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipolista`
--
ALTER TABLE `tipolista`
  MODIFY `id_tipolista` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `libro_autor`
--
ALTER TABLE `libro_autor`
  ADD CONSTRAINT `libro_autor_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `libro_autor_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `libro_genero`
--
ALTER TABLE `libro_genero`
  ADD CONSTRAINT `libro_genero_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `libro_genero_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id_genero`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `lista`
--
ALTER TABLE `lista`
  ADD CONSTRAINT `lista_ibfk_1` FOREIGN KEY (`id_tipolista`) REFERENCES `tipolista` (`id_tipolista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `lista_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `lista_libro`
--
ALTER TABLE `lista_libro`
  ADD CONSTRAINT `lista_libro_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `lista_libro_ibfk_2` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id_lista`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
