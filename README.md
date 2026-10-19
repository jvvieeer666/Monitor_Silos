# 🚜 Monitor de Silos - Aplicación Android

**Monitor de Silos** es una solución móvil desarrollada en Kotlin y Jetpack Compose para optimizar el control, monitoreo y gestión de alimentos almacenados en silos agrícolas distribuidos en distintas granjas.

---

## 👥 Integrantes del Equipo
* **Nombre Estudiante 1**: [Tu Nombre Completo]
* **Nombre Estudiante 2**: [Nombre de tu Compañero/a]
* **Asignatura / Sección**: Desarrollo de Aplicaciones Mótiles - Duoc UC

---

## 📋 Planificación del Proyecto (Trello & GitHub)
* **Tablero de Trello:** [Enlace a tu tablero de Trello](https://trello.com)
* **Repositorio GitHub:** [Enlace a tu repositorio público de GitHub](https://github.com)

---

## 🚀 Funcionalidades Implementadas

### 1. Interfaz Visual y Navegación por Roles (`IE 2.1.1`)
- **Arquitectura de Navegación con Navigation Compose:** Rutas definidas y seguras.
- **Acceso según Rol:**
  - **👷 Operario:** Registro de cargas y consumos de alimento.
  - **👮 Supervisor:** Coordinación y resolución de alertas de reposición.
  - **📊 Jefatura:** Resumen ejecutivo e historial de movimientos.
  - **🏠 Granjas y Silos:** Vista detallada de capacidades y estado visual.
  - **🚨 Alertas:** Vista unificada de situaciones críticas.

### 2. Formularios Validados e Interactivos (`IE 2.1.2`)
- **Validaciones en Tiempo Real:** Campos vacíos, valores numéricos requeridos y rangos válidos.
- **Retroalimentación Visual Impecable:** Mensajes de error por campo, iconos de advertencia (`Icons.Default.Warning`) y tarjetas informativas.

### 3. Lógica Desacoplada con MVVM (`IE 2.2.1`)
- **ViewModels Dedicados:** `LoginViewModel` y `OperarioViewModel` gestionan el estado sin acoplar la lógica a la UI `@Composable`.

### 4. Animaciones Visuales Funcionales (`IE 2.2.2`)
- **Barras de Nivel Animadas:** `animateFloatAsState` en `TarjetaSilo` para simular la carga progresiva del silo.
- **Transiciones y Visibilidad Suave:** `AnimatedVisibility` para aparición/desaparición de errores y confirmaciones.

### 5. Arquitectura Modular y Persistencia Local (`IE 2.3.1`)
- **Estructura limpia:** `components`, `data`, `model`, `navegation`, `screens`, `ui/theme`, `utils`, `viewmodel`.
- **Persistencia Local con SharedPreferences (`PreferencesManager`):** Guarda la sesión de usuario, nuevos movimientos de operarios y estados de alertas atendidas para que no se pierdan al reiniciar la app.

### 6. Acceso a Recursos Nativos del Dispositivo (`IE 2.4.1`)
1. **🔔 Notificaciones del Sistema (`NotificationHelper`):** Canal de notificaciones nativas de Android (`POST_NOTIFICATIONS`) para alertar sobre silos en estado crítico.
2. **🌐 Estado de Conectividad a Internet (`NetworkObserver` & `EstadoConexionBadge`):** Monitoreo del estado de red con badge visual en tiempo real (`🟢 Online` / `🔴 Offline`) utilizando `ConnectivityManager`.

---

## 💻 Pasos para Ejecutar la Aplicación

1. **Requisitos Previos:**
   - Android Studio Iguana / Ladybug o superior.
   - JDK 11 o 17 configurado.
   - Emulador Android SDK 24 (Android 7.0) o superior (Recomendado API 33+).

2. **Pasos:**
   1. Clonar el repositorio:
      ```bash
      git clone https://github.com/tu-usuario/Monitor_Silos.git
      ```
   2. Abrir el proyecto en **Android Studio**.
   3. Esperar que Gradle sincronice el proyecto (`Gradle Sync`).
   4. Ejecutar en un emulador o dispositivo físico presionado **Run (`Shift + F10`)**.

---

## 🔑 Credenciales de Prueba

Para probar el acceso por roles en la pantalla de inicio de sesión:

| Nombre de Usuario | Rol Autorizado |
| :--- | :--- |
| **Juan Pérez** | 👷 Operario |
| **María González** | 👷 Operario |
| **Carlos Silva** | 👮 Supervisor |
| **Ana Morales** | 📊 Jefatura |
