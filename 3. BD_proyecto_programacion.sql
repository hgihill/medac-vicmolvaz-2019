-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.12-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para victor_proyecto_prog
DROP DATABASE IF EXISTS `victor_proyecto_prog`;
CREATE DATABASE IF NOT EXISTS `victor_proyecto_prog` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `victor_proyecto_prog`;

-- Volcando estructura para tabla victor_proyecto_prog.aptitud
DROP TABLE IF EXISTS `aptitud`;
CREATE TABLE IF NOT EXISTS `aptitud` (
  `nombre_apt` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_apt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.apt_x_us
DROP TABLE IF EXISTS `apt_x_us`;
CREATE TABLE IF NOT EXISTS `apt_x_us` (
  `nombre_apt` varchar(20) NOT NULL,
  `DNI/CIF` varchar(9) NOT NULL,
  PRIMARY KEY (`nombre_apt`,`DNI/CIF`),
  KEY `DNI/CIF_apt_x_us` (`DNI/CIF`),
  CONSTRAINT `DNI/CIF_apt_x_us` FOREIGN KEY (`DNI/CIF`) REFERENCES `usuario` (`DNI/CIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_apt_x_us` FOREIGN KEY (`nombre_apt`) REFERENCES `aptitud` (`nombre_apt`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.conocimiento
DROP TABLE IF EXISTS `conocimiento`;
CREATE TABLE IF NOT EXISTS `conocimiento` (
  `nombre_con` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_con`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.con_x_us
DROP TABLE IF EXISTS `con_x_us`;
CREATE TABLE IF NOT EXISTS `con_x_us` (
  `nombre_con` varchar(20) NOT NULL,
  `DNI/CIF` varchar(9) NOT NULL,
  PRIMARY KEY (`nombre_con`,`DNI/CIF`),
  KEY `DNI/CIF_con_x_us` (`DNI/CIF`),
  CONSTRAINT `DNI/CIF_con_x_us` FOREIGN KEY (`DNI/CIF`) REFERENCES `usuario` (`DNI/CIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_con_x_us` FOREIGN KEY (`nombre_con`) REFERENCES `conocimiento` (`nombre_con`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.direccion
DROP TABLE IF EXISTS `direccion`;
CREATE TABLE IF NOT EXISTS `direccion` (
  `CP` int(5) NOT NULL,
  `calle` varchar(50) NOT NULL,
  `numero` int(4) NOT NULL,
  `nombre_loc` varchar(50) NOT NULL,
  `portal` int(2) DEFAULT NULL,
  `bloque` int(2) DEFAULT NULL,
  PRIMARY KEY (`CP`,`calle`,`numero`),
  KEY `nombre_loc` (`nombre_loc`),
  CONSTRAINT `nombre_loc` FOREIGN KEY (`nombre_loc`) REFERENCES `localidad` (`nombre_loc`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.financiacion
DROP TABLE IF EXISTS `financiacion`;
CREATE TABLE IF NOT EXISTS `financiacion` (
  `cuenta` int(30) NOT NULL,
  `tipo_financ` int(1) NOT NULL,
  `ID_proyecto` int(6) NOT NULL,
  `entidad` int(20) DEFAULT NULL,
  PRIMARY KEY (`cuenta`),
  KEY `tipo_fin` (`tipo_financ`),
  KEY `ID_proyecto` (`ID_proyecto`),
  CONSTRAINT `ID_proyecto` FOREIGN KEY (`ID_proyecto`) REFERENCES `proyecto` (`ID_proyecto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tipo_fin` FOREIGN KEY (`tipo_financ`) REFERENCES `tipo_financiacion` (`tipo_financ`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.inventario
DROP TABLE IF EXISTS `inventario`;
CREATE TABLE IF NOT EXISTS `inventario` (
  `ID_Inv` int(6) NOT NULL,
  `cantidad` int(4) NOT NULL,
  PRIMARY KEY (`ID_Inv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.localidad
DROP TABLE IF EXISTS `localidad`;
CREATE TABLE IF NOT EXISTS `localidad` (
  `nombre_loc` varchar(50) NOT NULL,
  `nombre_prov` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_loc`),
  KEY `nombre_prov` (`nombre_prov`),
  CONSTRAINT `nombre_prov` FOREIGN KEY (`nombre_prov`) REFERENCES `provincia` (`nombre_prov`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.pais
DROP TABLE IF EXISTS `pais`;
CREATE TABLE IF NOT EXISTS `pais` (
  `nombre_pais` varchar(50) NOT NULL,
  PRIMARY KEY (`nombre_pais`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.provincia
DROP TABLE IF EXISTS `provincia`;
CREATE TABLE IF NOT EXISTS `provincia` (
  `nombre_prov` varchar(50) NOT NULL DEFAULT '',
  `nombre_pais` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nombre_prov`),
  KEY `nombre_pais` (`nombre_pais`),
  CONSTRAINT `nombre_pais` FOREIGN KEY (`nombre_pais`) REFERENCES `pais` (`nombre_pais`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.proyecto
DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE IF NOT EXISTS `proyecto` (
  `ID_proyecto` int(6) NOT NULL,
  `DNI/CIF` varchar(9) NOT NULL DEFAULT '',
  `ID_inv` int(11) NOT NULL DEFAULT 0,
  `nombre` varchar(30) NOT NULL DEFAULT '0',
  `descripcion` varchar(300) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID_proyecto`),
  KEY `DNI/CIF_proyecto` (`DNI/CIF`),
  KEY `ID_inv_proyecto` (`ID_inv`),
  CONSTRAINT `DNI/CIF_proyecto` FOREIGN KEY (`DNI/CIF`) REFERENCES `usuario` (`DNI/CIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ID_inv_proyecto` FOREIGN KEY (`ID_inv`) REFERENCES `inventario` (`ID_Inv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.recurso
DROP TABLE IF EXISTS `recurso`;
CREATE TABLE IF NOT EXISTS `recurso` (
  `nombre_rec` varchar(50) NOT NULL,
  `cantidad_rec` int(4) NOT NULL DEFAULT 0,
  `tipo_rec` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`nombre_rec`),
  KEY `tipo_rec` (`tipo_rec`),
  CONSTRAINT `tipo_rec` FOREIGN KEY (`tipo_rec`) REFERENCES `tipo_recurso` (`tipo_recurso`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `DNI/CIF` varchar(9) NOT NULL DEFAULT '',
  `ID_proyecto` int(6) NOT NULL,
  `creador` int(1) DEFAULT NULL,
  `participante` int(1) DEFAULT NULL,
  `candidato` int(1) DEFAULT NULL,
  PRIMARY KEY (`DNI/CIF`,`ID_proyecto`),
  KEY `ID_proyecto_rol` (`ID_proyecto`),
  CONSTRAINT `DNI/CIF_rol` FOREIGN KEY (`DNI/CIF`) REFERENCES `usuario` (`DNI/CIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ID_proyecto_rol` FOREIGN KEY (`ID_proyecto`) REFERENCES `proyecto` (`ID_proyecto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.tipo_financiacion
DROP TABLE IF EXISTS `tipo_financiacion`;
CREATE TABLE IF NOT EXISTS `tipo_financiacion` (
  `tipo_financ` int(1) NOT NULL,
  PRIMARY KEY (`tipo_financ`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.tipo_recurso
DROP TABLE IF EXISTS `tipo_recurso`;
CREATE TABLE IF NOT EXISTS `tipo_recurso` (
  `tipo_recurso` int(1) NOT NULL,
  PRIMARY KEY (`tipo_recurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.tipo_usuario
DROP TABLE IF EXISTS `tipo_usuario`;
CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `tipo_usuario` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`tipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `DNI/CIF` varchar(9) NOT NULL,
  `tipo_us` int(1) NOT NULL,
  `CP` int(5) NOT NULL,
  `calle` varchar(50) NOT NULL,
  `numero` int(4) NOT NULL,
  `nombre_us` varchar(50) NOT NULL,
  `contraseña` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`DNI/CIF`),
  KEY `tipo_user` (`tipo_us`),
  KEY `direccion_user` (`CP`,`calle`,`numero`),
  CONSTRAINT `direccion_user` FOREIGN KEY (`CP`, `calle`, `numero`) REFERENCES `direccion` (`CP`, `calle`, `numero`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tipo_user` FOREIGN KEY (`tipo_us`) REFERENCES `tipo_usuario` (`tipo_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.us_x_financ
DROP TABLE IF EXISTS `us_x_financ`;
CREATE TABLE IF NOT EXISTS `us_x_financ` (
  `DNI/CIF` varchar(9) NOT NULL DEFAULT '',
  `cuenta` int(20) NOT NULL DEFAULT 0,
  `fecha` date NOT NULL,
  `importe` int(6) NOT NULL,
  PRIMARY KEY (`DNI/CIF`,`cuenta`),
  KEY `cuenta` (`cuenta`),
  CONSTRAINT `DNI/CIF_us_x_financ` FOREIGN KEY (`DNI/CIF`) REFERENCES `usuario` (`DNI/CIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cuenta` FOREIGN KEY (`cuenta`) REFERENCES `financiacion` (`cuenta`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla victor_proyecto_prog.us_x_inv (descuento)
DROP TABLE IF EXISTS `us_x_inv (descuento)`;
CREATE TABLE IF NOT EXISTS `us_x_inv (descuento)` (
  `ID_inv` int(6) NOT NULL,
  `DNI/CIF` varchar(9) NOT NULL DEFAULT '',
  `procentaje` float(4,2) NOT NULL,
  PRIMARY KEY (`ID_inv`,`DNI/CIF`),
  KEY `DNI/CIF` (`DNI/CIF`),
  CONSTRAINT `DNI/CIF` FOREIGN KEY (`DNI/CIF`) REFERENCES `usuario` (`DNI/CIF`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ID_inv` FOREIGN KEY (`ID_inv`) REFERENCES `inventario` (`ID_Inv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
