package com.grupo4.inventario.movimientos.model;

import java.time.LocalDateTime;

public class Movimiento {

    private final long id;
    private final long productoId;
    private final TipoMovimiento tipo;
    private final int cantidad;
    private final int stockAnterior;
    private final int stockNuevo;
    private final String observacion;
    private final LocalDateTime fecha;

    public Movimiento(long id, long productoId, TipoMovimiento tipo, int cantidad,
                      int stockAnterior, int stockNuevo, String observacion,
                      LocalDateTime fecha) {
        this.id = id;
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public long getId() { return id; }
    public long getProductoId() { return productoId; }
    public TipoMovimiento getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public int getStockAnterior() { return stockAnterior; }
    public int getStockNuevo() { return stockNuevo; }
    public String getObservacion() { return observacion; }
    public LocalDateTime getFecha() { return fecha; }
}
