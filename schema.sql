----------------------------------------------------------------------------------------------------
-- Tabla: categorias
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE categorias (
    categoria_id NUMBER NOT NULL,
    nombre NVARCHAR2(50) NOT NULL,

    -- Llave primaria
    CONSTRAINT categorias_pk PRIMARY KEY (categoria_id),

    -- Columnas únicas
    CONSTRAINT categorias_uq UNIQUE (nombre)
);

-- Secuencia
CREATE SEQUENCE categorias_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

-- Disparador
CREATE OR REPLACE TRIGGER categorias_trg BEFORE INSERT ON categorias FOR EACH ROW
BEGIN
	:new.categoria_id := categorias_seq.nextval;
END;
/

----------------------------------------------------------------------------------------------------
-- Tabla: fotografias
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE fotografias (
    fotografia_id NUMBER NOT NULL,
    descripcion NVARCHAR2(2000) NOT NULL,
    url NVARCHAR2(100) NOT NULL,
    visitas NUMBER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    categoria_id NUMBER DEFAULT NULL,

    -- Llave primaria
    CONSTRAINT fotografias_pk PRIMARY KEY (fotografia_id),

    -- Columnas únicas
    CONSTRAINT fotografias_uq UNIQUE (url),

    -- Llave foránea
    CONSTRAINT fotografias_fk FOREIGN KEY (categoria_id) REFERENCES categorias (categoria_id)
        ON DELETE SET NULL
);

-- Secuencia
CREATE SEQUENCE fotografias_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

-- Disparador
CREATE OR REPLACE TRIGGER fotografias_trg BEFORE INSERT ON fotografias FOR EACH ROW
BEGIN
	:new.fotografia_id := fotografias_seq.nextval;
END;
/
