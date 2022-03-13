# Notes App (project for Simbirsoft)


## 1. Стек технологий

---
    Java 8 (Build tool: Apache Maven 3.8.1)
    Spring Boot 2.6.3
    
    PostgreSQL 14
    Docker 20.10.12

Также были использованы следующие библиотеки

``lombok`` -  библиотека для генерации бойлерплейт-кода

``flywaydb`` - система управления миграциями

## 2. Запуск приложения

---

1. Склонируйте репозиторий на свой ПК
2. Откройте папку с приложением через консоль
3. Введите команды: ``mvn package`` и ``docker-compose up``
4. Перейдите по адресу ``localhost:8080`` в браузере