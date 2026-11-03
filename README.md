# Sistema de Control de Inventario

Proyecto académico del **Grupo N.º 4** para administrar el inventario de una tienda.

## Tecnologías

- Backend: Java 17.
- Base de datos: MySQL.
- Acceso a datos: JDBC.
- Gestión de dependencias: Maven.

La interfaz gráfica o web y el framework de presentación se definirán en coordinación con el equipo.

## Módulos del equipo

- `feature/productos`: registro y mantenimiento de productos.
- `feature/movimientos`: entradas, salidas y consulta del historial de inventario.
- `feature/consultas`: búsquedas e informes.
- `feature/alertas`: avisos de stock mínimo.

## Estructura principal

```text
Sistema-Control-Inventario/
├── docs/
│   ├── requisitos/
│   ├── diagramas/
│   └── manual_usuario/
├── src/
│   ├── frontend/
│   ├── backend/
│   │   ├── controllers/
│   │   ├── models/
│   │   ├── routes/
│   │   ├── services/
│   │   └── java/
│   └── database/
│       ├── migrations/
│       └── scripts/
├── tests/
├── assets/
├── .gitignore
└── README.md
```

## Flujo colaborativo

1. Cada integrante desarrolla en su rama `feature/*`.
2. Los cambios se guardan mediante commits descriptivos.
3. Se crea un Pull Request desde la rama de trabajo hacia `develop`.
4. El equipo revisa e integra los cambios.
5. Cuando el proyecto esté probado, se integra `develop` en `main`.

## Convención de commits

- `feat:` nueva funcionalidad.
- `fix:` corrección de errores.
- `docs:` documentación.
- `test:` pruebas.
- `chore:` mantenimiento y configuración.

## Módulo de movimientos

La implementación Java se encuentra en `src/backend/`. Consultar `src/backend/README.md` para configurar MySQL, ejecutar la migración y utilizar el módulo.

**Importante:** el módulo de movimientos espera que el módulo de productos proporcione una tabla `productos` con `id_producto BIGINT` y `stock INT`. El equipo debe confirmar este contrato antes de integrar los módulos.
