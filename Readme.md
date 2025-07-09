

## 📦 Sistema de Gestión de Productos y Pedidos con Spring

Trabajo final para Talento Tech

## 🛡️ Características implementadas

📦 Gestión de Productos

CRUD completo: Crear, leer, actualizar y eliminar productos
Búsqueda: Por ID y por nombre
Actualizaciones parciales: Solo precio o solo stock
Validación: Nombres, precios y stock con mensajes de error claros

🛒 Gestión de Pedidos

Creación de pedidos: Con múltiples productos y cantidades
Verificación de stock: Antes de confirmar el pedido
Cálculo automático: Subtotales y total del pedido
Descuento de stock: Automático al confirmar el pedido

🔧 Características técnicas

Arquitectura en capas: Controller → Service → Model
Manejo de excepciones: Centralizado con respuestas HTTP apropiadas
Validaciones: Con Bean Validation (Jakarta)
DTOs: Para separar la lógica de presentación
CORS: Habilitado para desarrollo frontend

🚀 Para empezar rápidamente:

Abrir la terminal en el directorio PI Final - Spring
Ejecutar con mvn spring-boot:run
Probar en http://localhost:8080

🧪 Pruebas sugeridas en Postman:

Crear productos:
jsonPOST /api/productos
{
    "nombre": "Laptop Gaming",
    "precio": 1599.99,
    "stock": 5
}

Crear pedido:
jsonPOST /api/pedidos
{
    "lineas": [
        {"productoId": 1, "cantidad": 2}
    ]
}

Listar todo:
GET /api/productos
GET /api/pedidos


### ✅ Funcionalidades principales
- ✅ Gestión completa de productos (CRUD)
- ✅ Creación y listado de pedidos
- ✅ Validación de stock antes de crear pedidos
- ✅ Cálculo automático de totales
- ✅ Manejo de excepciones personalizado
- ✅ Validación de datos de entrada
- ✅ Respuestas HTTP apropiadas
- ✅ Logging para debugging

### ✅ Principios de diseño
- ✅ Arquitectura en capas (Controller, Service, Model)
- ✅ Separación de responsabilidades
- ✅ DTOs para transferencia de datos
- ✅ Manejo centralizado de excepciones
- ✅ Validaciones con anotaciones
- ✅ Código limpio y documentado

### ✅ Características técnicas
- ✅ Spring Boot 3.5.3
- ✅ Java 17
- ✅ Maven como gestor de dependencias
- ✅ API REST con JSON
- ✅ CORS habilitado
- ✅ Almacenamiento Sqlite (configurado) o hibernate

## 🔄 A implementar

2. **Seguridad**: Implementar Spring Security
3. **Documentación**: Agregar Swagger/OpenAPI
4. **Testing**: Crear tests unitarios e integración
5. **Paginación**: Implementar paginación para listados
6. **Auditoría**: Agregar campos de auditoría (createdAt, updatedAt)

## 📋 Notas importantes

- La aplicación usa sqlite en su configuración actual.
- Los IDs se generan automáticamente de forma secuencial
- Se incluye manejo de CORS para desarrollo frontend
- Todas las validaciones siguen las mejores prácticas de Spring
- Los errores se manejan de forma consistente con códigos HTTP apropiados


## 📝 Documentación de la API REST

### 🛒 Endpoints de Productos

#### 1. Listar todos los productos
```http
GET /api/productos
```

#### 2. Buscar producto por ID
```http
GET /api/productos/{id}
```

#### 3. Buscar productos por nombre
```http
GET /api/productos/buscar?nombre=laptop
```

#### 4. Agregar nuevo producto
```http
POST /api/productos
Content-Type: application/json

{
    "nombre": "Laptop Dell",
    "precio": 1299.99,
    "stock": 10
}
```

#### 5. Actualizar producto completo
```http
PUT /api/productos/{id}
Content-Type: application/json

{
    "nombre": "Laptop Dell XPS",
    "precio": 1399.99,
    "stock": 8
}
```

#### 6. Actualizar solo el precio
```http
PATCH /api/productos/{id}/precio?precio=1199.99
```

#### 7. Actualizar solo el stock
```http
PATCH /api/productos/{id}/stock?stock=15
```

#### 8. Eliminar producto
```http
DELETE /api/productos/{id}
```

#### 9. Verificar disponibilidad
```http
GET /api/productos/{id}/disponibilidad?cantidad=5
```

### 📦 Endpoints de Pedidos

#### 1. Listar todos los pedidos
```http
GET /api/pedidos
```

#### 2. Buscar pedido por ID
```http
GET /api/pedidos/{id}
```

#### 3. Crear nuevo pedido
```http
POST /api/pedidos
Content-Type: application/json

{
    "lineas": [
        {
            "productoId": 1,
            "cantidad": 2
        },
        {
            "productoId": 2,
            "cantidad": 1
        }
    ]
}
```

## 🧪 Ejemplos de pruebas con Postman

### 1. Crear productos de prueba
```json
// POST /api/productos
{
    "nombre": "Laptop Gaming",
    "precio": 1599.99,
    "stock": 5
}

// POST /api/productos
{
    "nombre": "Mouse Inalámbrico",
    "precio": 29.99,
    "stock": 20
}

// POST /api/productos
{
    "nombre": "Teclado Mecánico",
    "precio": 89.99,
    "stock": 15
}
```

### 2. Crear pedido de prueba
```json
// POST /api/pedidos
{
    "lineas": [
        {
            "productoId": 1,
            "cantidad": 1
        },
        {
            "productoId": 2,
            "cantidad": 2
        }
    ]
}
```

### 3. Casos de prueba para manejo de errores

#### Producto no encontrado
```http
GET /api/productos/999
```

#### Stock insuficiente
```json
// POST /api/pedidos
{
    "lineas": [
        {
            "productoId": 1,
            "cantidad": 100
        }
    ]
}
```

#### Validación de datos
```json
// POST /api/productos
{
    "nombre": "",
    "precio": -10,
    "stock": -5
}
```
