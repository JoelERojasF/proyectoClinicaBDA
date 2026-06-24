CREATE DATABASE IF NOT EXISTS laboratorio_salud_total;
USE laboratorio_salud_total;

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    sexo ENUM('Masculino', 'Femenino') NOT NULL,
    tipo_sangre VARCHAR(10) NOT NULL
);

ALTER TABLE doctores 
ADD COLUMN especialidad VARCHAR(100) NOT NULL,
ADD COLUMN cedula VARCHAR(50) NOT NULL;

CREATE TABLE doctores (
    id_doctor INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    especialidades VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    cedula VARCHAR(50) NOT NULL,
    sexo ENUM('Masculino', 'Femenino') NOT NULL
);

CREATE TABLE tipos_muestra (
    id_muestra INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);


CREATE TABLE analisis (
    id_analisis INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    id_muestra INT NOT NULL,
    FOREIGN KEY (id_muestra) REFERENCES tipos_muestra(id_muestra) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE pruebas_laboratorio (
    id_prueba INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora_generacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_cliente INT NOT NULL,
    id_doctor INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_doctor) REFERENCES doctores(id_doctor) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TABLE parametros (
    id_parametro INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    orden_aparicion INT NOT NULL,
    descripcion TEXT,
    unidad_medida VARCHAR(50) NOT NULL,
    id_analisis INT NOT NULL,
    FOREIGN KEY (id_analisis) REFERENCES analisis(id_analisis) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rangos_evaluacion (
    id_rango INT AUTO_INCREMENT PRIMARY KEY,
    sexo_aplica ENUM('Masculino', 'Femenino', 'Ambos') NOT NULL,
    edad_inicial INT NOT NULL,
    edad_final INT NOT NULL,
    rango_inicial DECIMAL(10,2) NOT NULL,
    rango_final DECIMAL(10,2) NOT NULL,
    id_parametro INT NOT NULL,
    FOREIGN KEY (id_parametro) REFERENCES parametros(id_parametro) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE resultados (
    id_resultado INT AUTO_INCREMENT PRIMARY KEY,
    valor_resultado DECIMAL(10,2) NOT NULL,
    observacion TEXT,
    id_prueba INT NOT NULL,
    id_parametro INT NOT NULL,
    FOREIGN KEY (id_prueba) REFERENCES pruebas_laboratorio(id_prueba) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_parametro) REFERENCES parametros(id_parametro) ON DELETE RESTRICT ON UPDATE CASCADE
);

SELECT * FROM clientes;

SELECT * FROM tipos_muestra;
SELECT * FROM analisis;
SELECT * FROM doctores;
SELECT * FROM  parametros;
SELECT * FROM rangos_evaluacion;