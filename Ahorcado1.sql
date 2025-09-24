Drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

CREATE TABLE Palabras (
    codigoPalabra INT AUTO_INCREMENT,
    Palabra VARCHAR(50) NOT NULL,
    Pista1 VARCHAR(60) NOT NULL,
    Pista2 VARCHAR(60) NOT NULL,
    Pista3 VARCHAR(60) NOT NULL,
    PRIMARY KEY PK_codigoPalabra (codigoPalabra)
);

CREATE TABLE Usuarios (
    codigoUsuario INT AUTO_INCREMENT,
    correoUsuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(60) NOT NULL,
    PRIMARY KEY PK_codigoUsuario (codigoUsuario)
);

-- ---------------------------------
-- Procedimientos Almacenados
-- ---------------------------------
DELIMITER $$

-- PROCEDIMIENTOS PARA LA TABLA 'Palabras'
-- AGREGAR PALABRA
CREATE PROCEDURE sp_AgregarPalabra(
    IN pal VARCHAR(50),
    IN p1 VARCHAR(60),
    IN p2 VARCHAR(60),
    IN p3 VARCHAR(60)
)
BEGIN
    INSERT INTO Palabras (Palabra, Pista1, Pista2, Pista3)
    VALUES (pal, p1, p2, p3);
END$$

-- LISTAR PALABRAS
CREATE PROCEDURE sp_ListarPalabras ()
BEGIN
    SELECT codigoPalabra, Palabra, Pista1, Pista2, Pista3
    FROM Palabras;
END$$

-- ELIMINAR PALABRA
CREATE PROCEDURE sp_EliminarPalabra (
    IN codPalabra INT
)
BEGIN
    DELETE FROM Palabras WHERE codigoPalabra = codPalabra;
END$$

-- BUSCAR PALABRA
CREATE PROCEDURE sp_BuscarPalabra (
    IN codPalabra INT
)
BEGIN
    SELECT codigoPalabra, Palabra, Pista1, Pista2, Pista3
    FROM Palabras
    WHERE codigoPalabra = codPalabra;
END$$

-- EDITAR PALABRA
CREATE PROCEDURE sp_EditarPalabra (
    IN codPalabra INT,
    IN pal VARCHAR(50),
    IN p1 VARCHAR(60),
    IN p2 VARCHAR(60),
    IN p3 VARCHAR(60)
)
BEGIN
    UPDATE Palabras p
    SET p.Palabra = pal,
        p.Pista1 = p1,
        p.Pista2 = p2,
        p.Pista3 = p3
    WHERE p.codigoPalabra = codPalabra;
END$$

-- PROCEDIMIENTOS PARA LA TABLA 'Usuarios'
-- VALIDAR USUARIO (PROCEDIMIENTO CORREGIDO Y AÑADIDO)
CREATE PROCEDURE sp_ValidarUsuario (
    IN p_correo VARCHAR(50),
    IN p_contrasena VARCHAR(60)
)
BEGIN
    SELECT codigoUsuario, correoUsuario, contrasena
    FROM Usuarios
    WHERE correoUsuario = p_correo AND contrasena = p_contrasena;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_ObtenerPalabraAleatoria()
BEGIN
    SELECT codigoPalabra, Palabra, Pista1, Pista2, Pista3
    FROM Palabras
    ORDER BY RAND()
    LIMIT 1;
END$$

DELIMITER ;

call sp_ObtenerPalabraAleatoria();
-- ---------------------------------
-- Inserción de Datos de Ejemplo
-- ---------------------------------
CALL sp_AgregarPalabra('Cangrejo', 'Es un crustaceo con caparazon.', 'Camina de lado.', 'Tiene un par de pinzas grandes.');
CALL sp_AgregarPalabra('Delfines', 'Es un mamífero cetaceo.', 'Usa la ecolocalizacion para comunicarse.', 'Suele vivir en grupos sociales muy organizados.');
CALL sp_AgregarPalabra('Langosta', 'Es un crustaceo de diez patas.', 'Tiene un caparazon duro y antenas largas.', 'Es valorada como un platillo de alta cocina.');
CALL sp_AgregarPalabra('Caracola', 'Es la concha de un molusco.', 'Tiene forma de espiral.', 'Se dice que, si la acercas a tu oido, puedes oir el mar.');
CALL sp_AgregarPalabra('Estrella', 'Es un equinodermo, no un pez.', 'Generalmente tiene cinco brazos.', 'Puede regenerar sus brazos perdidos.');


-- Inserción de un usuario de prueba para el login
INSERT INTO Usuarios (correoUsuario, contrasena) VALUES ('1', '1');

select * from Palabras;
select * from Usuarios;

