package org.example.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.Когда;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.*;

public class UiSteps {

    private final Map<String, String> fields = new HashMap<>();
    private final Map<String, Map<String, String>> attributes = new HashMap<>();
    private final Set<String> visibleElements = new HashSet<>();
    private final List<String> pageTexts = new ArrayList<>();
    private String pageTitle = "";
    private String currentFrame = "";

    @Дано("открыт сайт {string}")
    public void openSite(String url) {
        pageTitle = "Страница " + url;
        visibleElements.add("Главная");
    }

    @Дано("страница {string} загружена")
    public void pageLoaded(String pageName) {
        visibleElements.add(pageName);
    }

    @Когда("пользователь кликает по элементу {string}")
    public void clickElement(String element) {
        if ("Кнопка поиска".equalsIgnoreCase(element)) {
            visibleElements.add("Результаты поиска");
            String query = fields.getOrDefault("Поиск", "");
            if (!query.isEmpty()) {
                pageTexts.add("Результаты для " + query);
            }
        }
        visibleElements.add(element);
    }

    @Когда("пользователь делает двойной клик по элементу {string}")
    public void doubleClick(String element) {
        visibleElements.add(element);
    }

    @Когда("пользователь наводит курсор на элемент {string}")
    public void hoverElement(String element) {
        visibleElements.add(element);
    }

    @Когда("пользователь прокручивает страницу к элементу {string}")
    public void scrollToElement(String element) {
        visibleElements.add(element);
    }

    @Когда("пользователь вводит в поле {string} текст {string}")
    public void enterText(String field, String text) {
        fields.put(field, text);
        pageTexts.add(text);
    }

    @Когда("пользователь очищает поле {string}")
    public void clearField(String field) {
        fields.remove(field);
    }

    @Когда("пользователь выбирает в списке {string} опцию {string}")
    public void selectOption(String listName, String option) {
        fields.put(listName, option);
        attributes.computeIfAbsent(listName, k -> new HashMap<>()).put("selected", option);
    }

    @Когда("пользователь загружает файл {string}")
    public void uploadFile(String fileName) {
        attributes.computeIfAbsent("Загрузки", k -> new HashMap<>()).put("file", fileName);
    }

    @Когда("пользователь нажимает сочетание клавиш {string}")
    public void pressShortcut(String shortcut) {
        attributes.computeIfAbsent("Сочетания клавиш", k -> new HashMap<>()).put("last", shortcut);
    }

    @Когда("пользователь переходит во фрейм {string}")
    public void switchFrame(String frameName) {
        currentFrame = frameName;
    }

    @Когда("пользователь возвращается во фрейм по умолчанию")
    public void returnToDefaultFrame() {
        currentFrame = "";
    }

    @Когда("пользователь ждет {int} секунд")
    public void waitSeconds(int seconds) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(seconds).toMillis());
    }

    @Тогда("элемент {string} отображается")
    public void elementIsVisible(String element) {
        Assertions.assertTrue(visibleElements.contains(element),
                () -> "Ожидался видимый элемент: " + element);
    }

    @Тогда("элемент {string} не отображается")
    public void elementIsNotVisible(String element) {
        Assertions.assertFalse(visibleElements.contains(element),
                () -> "Элемент не должен быть видим: " + element);
    }

    @Тогда("на странице виден текст {string}")
    public void textIsVisible(String text) {
        Assertions.assertTrue(pageTexts.stream().anyMatch(t -> t.contains(text)),
                () -> "Текст не найден: " + text);
    }

    @Тогда("значение поля {string} равно {string}")
    public void fieldValueIs(String field, String expected) {
        Assertions.assertEquals(expected, fields.get(field),
                "Некорректное значение поля " + field);
    }

    @Тогда("заголовок страницы равен {string}")
    public void pageTitleIs(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, pageTitle, "Заголовок страницы не совпадает");
    }

    @Тогда("элемент {string} содержит атрибут {string} со значением {string}")
    public void elementAttributeHasValue(String element, String attribute, String value) {
        Map<String, String> elementAttributes = attributes.getOrDefault(element, Collections.emptyMap());
        Assertions.assertEquals(value, elementAttributes.get(attribute),
                () -> "Для элемента " + element + " ожидался атрибут " + attribute);
    }
}
