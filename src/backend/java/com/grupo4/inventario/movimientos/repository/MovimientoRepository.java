package com.grupo4.inventario.movimientos.repository;

import com.grupo4.inventario.config.DatabaseConnection;
import com.grupo4.inventario.movimientos.model.Movimiento;
import com.grupo4.inventario.movimientos.model.TipoMovimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Acceso a datos del módulo de movimientos.
 *
 * Contrato esperado con el módulo de productos:
 * productos(id_producto BIGINT PRIMARY KEY, stock INT NOT NULL).
 */
public class MovimientoRepository {

    public Movimiento registrar(long productoId, TipoMovimiento tipo,
                                int cantidad, String observacion) throws SQLException {
        String consultarStock = "SELECT stock FROM productos WHERE id_producto = ? FOR UPDATE";
        String actualizarStock = "UPDATE productos SET stock = ? WHERE id_producto = ?";
        String insertarMovimiento = """
                INSERT INTO movimientos
                    (producto_id, tipo, cantidad, stock_anterior, stock_nuevo, observacion)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            try {
                int stockAnterior;
                try (PreparedStatement statement = connection.prepareStatement(consultarStock)) {
                    statement.setLong(1, productoId);
                    try (ResultSet result = statement.executeQuery()) {
                        if (!result.next()) {
                            throw new IllegalArgumentException(
                                    "No existe el producto con ID " + productoId);
                        }
                        stockAnterior = result.getInt("stock");
                    }
                }

                int stockNuevo = tipo == TipoMovimiento.ENTRADA
                        ? Math.addExact(stockAnterior, cantidad)
                        : stockAnterior - cantidad;

                if (stockNuevo < 0) {
                    throw new IllegalArgumentException(
                            "Stock insuficiente. Disponible: " + stockAnterior);
                }

                try (PreparedStatement statement = connection.prepareStatement(actualizarStock)) {
                    statement.setInt(1, stockNuevo);
                    statement.setLong(2, productoId);
                    statement.executeUpdate();
                }

                long movimientoId;
                try (PreparedStatement statement = connection.prepareStatement(
                        insertarMovimiento, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setLong(1, productoId);
                    statement.setString(2, tipo.name());
                    statement.setInt(3, cantidad);
                    statement.setInt(4, stockAnterior);
                    statement.setInt(5, stockNuevo);
                    statement.setString(6, observacion);
                    statement.executeUpdate();

                    try (ResultSet keys = statement.getGeneratedKeys()) {
                        if (!keys.next()) {
                            throw new SQLException("No se pudo obtener el ID del movimiento.");
                        }
                        movimientoId = keys.getLong(1);
                    }
                }

                connection.commit();
                return new Movimiento(movimientoId, productoId, tipo, cantidad,
                        stockAnterior, stockNuevo, observacion, LocalDateTime.now());
            } catch (Exception exception) {
                connection.rollback();
                if (exception instanceof SQLException sqlException) {
                    throw sqlException;
                }
                if (exception instanceof RuntimeException runtimeException) {
                    throw runtimeException;
                }
                throw new SQLException("No se pudo registrar el movimiento.", exception);
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public List<Movimiento> listarPorProducto(long productoId) throws SQLException {
        String sql = """
                SELECT id_movimiento, producto_id, tipo, cantidad, stock_anterior,
                       stock_nuevo, observacion, fecha
                FROM movimientos
                WHERE producto_id = ?
                ORDER BY fecha DESC, id_movimiento DESC
                """;

        List<Movimiento> movimientos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, productoId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    movimientos.add(new Movimiento(
                            result.getLong("id_movimiento"),
                            result.getLong("producto_id"),
                            TipoMovimiento.valueOf(result.getString("tipo")),
                            result.getInt("cantidad"),
                            result.getInt("stock_anterior"),
                            result.getInt("stock_nuevo"),
                            result.getString("observacion"),
                            result.getTimestamp("fecha").toLocalDateTime()
                    ));
                }
            }
        }
        return movimientos;
    }

    public List<Movimiento> listarTodos() throws SQLException {
        String sql = """
                SELECT id_movimiento, producto_id, tipo, cantidad, stock_anterior,
                       stock_nuevo, observacion, fecha
                FROM movimientos
                ORDER BY fecha DESC, id_movimiento DESC
                """;

        List<Movimiento> movimientos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Timestamp fecha = result.getTimestamp("fecha");
                movimientos.add(new Movimiento(
                        result.getLong("id_movimiento"),
                        result.getLong("producto_id"),
                        TipoMovimiento.valueOf(result.getString("tipo")),
                        result.getInt("cantidad"),
                        result.getInt("stock_anterior"),
                        result.getInt("stock_nuevo"),
                        result.getString("observacion"),
                        fecha.toLocalDateTime()
                ));
            }
        }
        return movimientos;
    }
}
