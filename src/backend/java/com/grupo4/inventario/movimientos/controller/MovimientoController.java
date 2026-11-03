package com.grupo4.inventario.movimientos.controller;

import com.grupo4.inventario.movimientos.model.Movimiento;
import com.grupo4.inventario.movimientos.service.MovimientoService;

import java.sql.SQLException;
import java.util.List;

/**
 * Fachada del módulo. Puede conectarse posteriormente a una interfaz gráfica
 * o a endpoints REST cuando el equipo defina el framework de presentación.
 */
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController() {
        this.service = new MovimientoService();
    }

    public Movimiento registrarEntrada(long productoId, int cantidad, String observacion)
            throws SQLException {
        return service.registrarEntrada(productoId, cantidad, observacion);
    }

    public Movimiento registrarSalida(long productoId, int cantidad, String observacion)
            throws SQLException {
        return service.registrarSalida(productoId, cantidad, observacion);
    }

    public List<Movimiento> obtenerHistorial() throws SQLException {
        return service.consultarHistorial();
    }

    public List<Movimiento> obtenerHistorialProducto(long productoId) throws SQLException {
        return service.consultarHistorialProducto(productoId);
    }
}
