CREATE TABLE IF NOT EXISTS vacaciones (
    id_vacacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empleado VARCHAR(120) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    motivo VARCHAR(255),
    estado CHAR(1) NOT NULL DEFAULT 'A',
    usr_creacion VARCHAR(63) NOT NULL,
    fe_creacion DATETIME NOT NULL,
    usr_ult_modificacion VARCHAR(63),
    fe_ult_modificacion DATETIME
);
