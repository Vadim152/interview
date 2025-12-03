# UI automation skeleton for interviews

Лёгкий фреймворк на JUnit 5 + Selenium для демонстрации подхода к UI-тестам и Allure-отчётам.

## Основные возможности
- Управление конфигурацией через `-D` флаги и property-файлы (`baseUrl`, `browser`, `timeout.seconds`).
- Управление жизненным циклом WebDriver в `BaseTest` с автоматическими скриншотами и `page source` в Allure после каждого теста.
- Page Object слой с базовыми методами ожиданий.
- Пример рабочего теста авторизации на [saucedemo.com](https://www.saucedemo.com/).

## Структура
```
src
└─ test
   ├─ java
   │  └─ org.example
   │     ├─ config
   │     │  └─ TestConfig.java        // чтение конфигов и системных свойств
   │     ├─ driver
   │     │  ├─ DriverFactory.java     // создание WebDriver
   │     │  └─ DriverManager.java     // ThreadLocal-хранилище драйвера
   │     ├─ core
   │     │  ├─ BaseTest.java          // @BeforeEach/@AfterEach, вложения в Allure
   │     │  └─ BasePage.java          // базовые ожидания и действия
   │     ├─ pages
   │     │  ├─ LoginPage.java
   │     │  └─ ProductsPage.java
   │     └─ tests
   │        └─ LoginTest.java         // пример полноценного теста
   └─ resources
      ├─ config.properties            // значения по умолчанию
      ├─ config.qa.properties         // опциональные файлы под окружения
      └─ config.stage.properties
```

## Запуск
```bash
# Используя дефолты: chrome, headless, https://www.saucedemo.com/
./mvnw test

# Выбор окружения и браузера
./mvnw test -Denv=qa -Dbrowser=firefox

# Отключить headless-режим
./mvnw test -Dheadless=false
```

По завершении тестов артефакты Allure лежат в `target/allure-results`.

## Заготовка под задание
- Добавьте новые Page Object-классы и тесты по аналогии с `LoginPage`/`ProductsPage`.
- Расширьте конфигурацию новыми свойствами (например, креды) в `config.*.properties` и чтением через `TestConfig`.
- Подключите дополнительный репортинг или логирование по необходимости.
