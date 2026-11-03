# Módulo de movimientos de inventario

Implementación inicial en Java 17, Maven, JDBC y MySQL.

## Funciones

- Registrar entradas: incrementan las existencias.
- Registrar salidas: descuentan las existencias.
- Rechazar cantidades menores o iguales a cero.
- Rechazar salidas superiores al stock disponible.
- Consultar el historial general o por producto.
- Guardar el movimiento y actualizar el stock dentro de una misma transacción.

## Contrato con el módulo de productos

Este módulo espera una tabla llamada `productos` con, al menos:

| Campo | Tipo esperado |
|---|---|
| `id_producto` | BIGINT, clave primaria |
| `stock` | INT, no nulo |

El integrante responsable de productos debe confirmar estos nombres y tipos. Si su diseño es distinto, se deben adaptar la consulta SQL del repositorio y la clave foránea de la migración antes de integrar los módulos.

## Preparar MySQL

1. Crear la base de datos `inventario_tienda`.
2. Ejecutar primero el script de creación de la tabla `productos` del módulo correspondiente.
3. Ejecutar `src/database/migrations/001_create_movimientos.sql`.

## Configurar la conexión

Definir estas variables de entorno:

- `DB_URL`: por ejemplo `jdbc:mysql://localhost:3306/inventario_tienda?serverTimezone=UTC`
- `DB_USER`: usuario de MySQL.
- `DB_PASSWORD`: contraseña de MySQL.

No guardar contraseñas reales en GitHub.

## Compilar

Desde esta carpeta:

```bash
mvn clean package
```

## Ejemplo de uso desde Java

```java
MovimientoController controller = new MovimientoController();

controller.registrarEntrada(1L, 20, "Compra a proveedor");
controller.registrarSalida(1L, 5, "Venta");

controller.obtenerHistorial();
controller.obtenerHistorialProducto(1L);
```

Los ejemplos requieren que exista el producto con ID 1 y que MySQL esté configurado.

## Integración

La clase `MovimientoController` es una fachada Java. Puede conectarse después a una interfaz gráfica o a una API REST cuando el equipo decida el framework de presentación.
