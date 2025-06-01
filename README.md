# 🏨 Backend Reservas

API RESTful desarrollada en Java con Spring Boot para la gestión de reservas de hoteles. Este backend permite realizar operaciones CRUD sobre productos (hoteles) y gestionar las reservas asociadas.

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **H2 Database** (modo desarrollo)
- **Spring Web**
- **Spring Data JPA**

## 📁 Estructura del Proyecto

```
backend-reservas/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── ejemplo/
│       │           └── reservas/
│       │               ├── controller/
│       │               ├── model/
│       │               ├── repository/
│       │               └── service/
│       └── resources/
│           ├── application.properties
│           └── data.sql
├── pom.xml
└── README.md
```

## ⚙️ Configuración y Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

### Pasos para ejecutar la aplicación

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/alossada/backend-reservas.git
   cd backend-reservas
   ```

2. **Construir el proyecto con Maven:**

   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación:**

   ```bash
   mvn spring-boot:run
   ```

   La aplicación estará disponible en: [http://localhost:8080](http://localhost:8080)

## 📌 Endpoints Principales

- `GET /productos`: Listar todos los productos
- `POST /productos`: Crear un nuevo producto
- `PUT /productos/{id}`: Actualizar un producto existente
- `DELETE /productos/{id}`: Eliminar un producto
- `GET /reservas`: Listar todas las reservas
- `POST /reservas`: Crear una nueva reserva
- `PUT /reservas/{id}`: Actualizar una reserva existente
- `DELETE /reservas/{id}`: Eliminar una reserva

## 🧪 Datos de Prueba

La base de datos H2 se inicializa con datos de prueba definidos en el archivo `data.sql`. Puedes acceder a la consola de H2 en: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Usuario:** `sa`
- **Contraseña:** *(dejar en blanco)*

## 🛠️ Próximas Mejoras

- Implementación de autenticación y autorización con Spring Security
- Documentación de la API con Swagger
- Integración con una base de datos persistente (MySQL o PostgreSQL)
- Implementación de pruebas unitarias y de integración

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Haz un fork del proyecto
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`)
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## 👩‍💻 Autor

- **Angélica Lossada** - [@alossada](https://github.com/alossada)
