# DistribuidorVentasJMSArtemis

Este proyecto implementa un sistema de mensajería basado en **JMS** (Java Message Service) utilizando **ActiveMQ Artemis** como broker de mensajería. La aplicación simula un proceso de ventas donde los productos se distribuyen a diferentes proveedores y se mantiene un historial de los pedidos.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **JMS (Java Message Service)**: API para la comunicación asincrónica entre aplicaciones.
- **ActiveMQ Artemis**: Broker de mensajería utilizado para gestionar las colas y tópicos.
- **Graddle**: Herramienta de gestión de dependencias y construcción.

## Arquitectura del Proyecto

### Modelo Publish/Subscribe

- **AppVentas**: Publica mensajes de ventas con detalles de los productos.
- **AppHistorial**: Suscriptor que registra las ventas recibidas.
- **AppDistribuidor**: Suscriptor que distribuye los productos a diferentes proveedores según el tipo de producto.

### Modelo Peer-to-Peer

- **AppProA**: Procesa los productos destinados al proveedor A.
- **AppProB**: Procesa los productos destinados al proveedor B.

## Formato de Mensajes

Los mensajes se manejan en formato JSON para transmitir los detalles de los productos y pedidos.

## Requisitos

- **Java 8** o superior.
- **Apache ActiveMQ Artemis**.
- **Graddle**.

## Instalación y Ejecución

1. Configura e inicia un broker de **ActiveMQ Artemis**.
2. Clona el repositorio y compila el proyecto con **Maven**.
3. Ejecuta las diferentes aplicaciones de manera independiente.

## Licencia

Ninguna
