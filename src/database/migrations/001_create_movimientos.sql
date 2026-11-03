-- Requiere que el módulo de productos cree previamente:
-- productos(id_producto BIGINT PRIMARY KEY, stock INT NOT NULL).
-- Si el equipo utiliza otros nombres o tipos, ajustar producto_id y la FK
-- antes de ejecutar esta migración.

CREATE TABLE IF NOT EXISTS movimientos (
    id_movimiento BIGINT NOT NULL AUTO_INCREMENT,
    producto_id BIGINT NOT NULL,
    tipo ENUM('ENTRADA', 'SALIDA') NOT NULL,
    cantidad INT NOT NULL,
    stock_anterior INT NOT NULL,
    stock_nuevo INT NOT NULL,
    observacion VARCHAR(255) NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_movimiento),
    INDEX idx_movimientos_producto_fecha (producto_id, fecha),
    CONSTRAINT chk_movimientos_cantidad CHECK (cantidad > 0),
    CONSTRAINT fk_movimientos_producto
        FOREIGN KEY (producto_id) REFERENCES productos(id_producto)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
