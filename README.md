# Sistema de Control de Inventario

Proyecto académico del **Grupo N.º 4** para organizar y controlar el inventario de una tienda.

## Objetivo

Proponer y desarrollar un sistema que permita administrar productos y existencias, registrar movimientos de inventario, realizar consultas y generar alertas relacionadas con el stock.

## Estructura del repositorio

```text
Sistema-Control-Inventario/
├── docs/
│   ├── requisitos/
│   ├── diagramas/
│   └── manual_usuario/
├── src/
│   ├── frontend/
│   │   ├── css/
│   │   ├── js/
│   │   └── pages/
│   ├── backend/
│   │   ├── controllers/
│   │   ├── models/
│   │   ├── routes/
│   │   └── services/
│   └── database/
│       ├── migrations/
│       └── scripts/
├── tests/
│   ├── unitarias/
│   └── integracion/
├── assets/
│   ├── images/
│   └── icons/
├── .gitignore
└── README.md
```

> Las carpetas se mantienen en Git mediante archivos `.gitkeep`. La tecnología y el framework se definirán según los requisitos del proyecto.

## Estrategia de ramas

- `main`: versión estable del proyecto.
- `develop`: rama de integración del equipo.
- `feature/productos`: gestión y mantenimiento de productos.
- `feature/movimientos`: entradas, salidas y ajustes de inventario.
- `feature/consultas`: búsquedas, listados e informes.
- `feature/alertas`: avisos de stock mínimo y existencias.

## Flujo de trabajo colaborativo

1. Cada integrante trabaja en la rama de la funcionalidad asignada.
2. Realiza cambios pequeños y commits con mensajes descriptivos.
3. Publica su rama en GitHub y abre un Pull Request hacia `develop`.
4. El equipo revisa los cambios antes de integrarlos.
5. Cuando `develop` esté probado y estable, se integra en `main`.

## Convención de commits

Se recomienda usar prefijos como:

- `feat:` nueva funcionalidad.
- `fix:` corrección de errores.
- `docs:` documentación.
- `test:` pruebas.
- `chore:` mantenimiento y configuración.

## Estado del proyecto

Estructura inicial y ramas colaborativas creadas. La implementación del sistema se incorporará progresivamente.
