# 🛒 TimpShop API

API REST desarrollada para la gestión de una tienda en línea.  
Permite administrar productos, usuarios y pedidos de forma eficiente.

---

## 🚀 Descripción

**TimpShop API** es el backend de una tienda online que proporciona endpoints para:

- Gestión de productos 🛍️  
- Administración de usuarios 👤  
- Control de pedidos 📦  
- Integración con frontend (web o móvil) 🌐  

---

## 🛠️ Tecnologías utilizadas

<p align="center">
  <img src="https://img.shields.io/badge/PHP-Backend-777BB4?style=for-the-badge&logo=php&logoColor=white"/>
  <img src="https://img.shields.io/badge/Laravel-Framework-F55247?style=for-the-badge&logo=laravel&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-Database-00000F?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/API-REST-02569B?style=for-the-badge"/>
</p>

---

## ⚙️ Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/isairey/TimpShopApi.git
```
2. Entrar al proyecto
```
cd TimpShopApi
```
3. Instalar dependencias
```
composer install
```
4. Configurar entorno
```
cp .env.example .env
```
Editar:
```
DB_DATABASE=timpshop
DB_USERNAME=root
DB_PASSWORD=
```
5. Generar clave
```
php artisan key:generate
```
6. Migrar base de datos
```
php artisan migrate
```
7. Ejecutar servidor
```
php artisan serve
```
## 🔌 Endpoints principales
## 👤 Autenticación
```
POST /api/register
POST /api/login
POST /api/logout
```
## 🛍️ Productos
```
GET /api/products
POST /api/products
PUT /api/products/{id}
DELETE /api/products/{id}
```
## 📦 Pedidos
```
GET /api/orders
POST /api/orders
```
## 📊 Ejemplo JSON
```
{
  "name": "Producto ejemplo",
  "price": 150,
  "stock": 20
}
```
## 🌐 Integración

Este backend puede conectarse con:

Frontend en Netlify
Aplicaciones móviles
Sistemas externos
## 📁 Estructura
```
/app
/routes/api.php
/database
/public
```
## 📌 Características

✔ API RESTful
✔ Arquitectura MVC
✔ Escalable
✔ Fácil integración

## 📜 Licencia

Este proyecto está bajo la licencia MIT.
