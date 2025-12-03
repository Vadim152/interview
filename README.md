# UI automation skeleton for interviews

Лёгкий фреймворк на JUnit 5 + Selenium для демонстрации подхода к UI-тестам и Allure-отчётам.

## Требования
- JDK 17
- Maven
- Google Chrome (для локального запуска UI-тестов)

## Как запустить тесты
```bash
# Используя дефолты: chrome, headless, https://www.saucedemo.com/
./mvnw test

# Выбор окружения и браузера
./mvnw test -Denv=qa -Dbrowser=firefox

# Запуск с явным указанием окружения и headless-режима
mvn clean test \
  -Denv=qa \
  -Dbrowser=chrome \
  -Dheadless=true

# Отключить headless-режим
./mvnw test -Dheadless=false

# Сформировать и открыть отчёт Allure
mvn allure:serve

# Генерация отчёта после тестов через профиль
mvn clean test -Pallure
```

По завершении тестов артефакты Allure лежат в `target/allure-results`, а профиль `allure` соберёт html-отчёт в `target/site/allure-maven-plugin`.

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

## Что должен сделать кандидат
- Найти тесты в `src/test/java/org/example/tests`.
- Найти Page Object-классы в `src/test/java/org/example/pages`.
- Ознакомиться с примером реализованного теста `LoginTest`.

## Задания на собеседовании
- Добавить тест на добавление товара в корзину.
- Реализовать Page Object для корзины.
- Покрыть негативный сценарий логина.

## Заготовка под задание
- Добавьте новые Page Object-классы и тесты по аналогии с `LoginPage`/`ProductsPage`.
- Расширьте конфигурацию новыми свойствами (например, креды) в `config.*.properties` и чтением через `TestConfig`.
- Подключите дополнительный репортинг или логирование по необходимости.
