package com.grupo4.inventario.movimientos.service;

import com.grupo4.inventario.movimientos.model.Movimiento;
import com.grupo4.inventario.movimientos.model.TipoMovimiento;
import com.grupo4.inventario.movimientos.repository.MovimientoRepository;

import java.sql.SQLException;
import java.util.List;

public class MovimientoService {

    private final MovimientoRepository repository;

    public MovimientoService() {
        this(new MovimientoRepository());
    }

    public MovimientoService(MovimientoRepository repository) {
        this.repository = repository;
    }

    public Movimiento registrarEntrada(long productoId, int cantidad, String observacion)
            throws SQLException {
        validarDatos(productoId, cantidad);
        return repository.registrar(productoId, TipoMovimiento.ENTRADA,
                cantidad, limpiarObservacion(observacion));
    }

    public Movimiento registrarSalida(long productoId, int cantidad, String observacion)
            throws SQLException {
        validarDatos(productoId, cantidad);
        return repository.registrar(productoId, TipoMovimiento.SALIDA,
                cantidad, limpiarObservacion(observacion));
    }

    public List<Movimiento> consultarHistorial() throws SQLException {
        return repository.listarTodos();
    }

    public List<Movimiento> consultarHistorialProducto(long productoId) throws SQLException {
        if (productoId <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        }
        return repository.listarPorProducto(productoId);
    }

    private void validarDatos(long productoId, int cantidad) {
        if (productoId <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
    }

    private String limpiarObservacion(String observacion) {
        if (observacion == null || observacion.isBlank()) {
            return null;
        }
        return observacion.trim();
    }
}
