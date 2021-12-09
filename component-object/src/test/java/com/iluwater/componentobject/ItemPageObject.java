package com.iluwater.componentobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

public final class ItemPageObject {
    public static final int TIME_OUT_IN_SECONDS = 10;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final ItemListComponent todoItemsList;
    private final AddItemComponent addTodoItemComponent;
    private final ItemListComponent groceryItemsList;
    private final AddItemComponent addGroceryItemComponent;

    public ItemPageObject(WebDriver pDriver) {
        this.driver = pDriver;
        this.wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        todoItemsList = new ItemListComponent(driver, ".todo-list");
        addTodoItemComponent = new AddItemComponent(driver, ".add-todo");
        groceryItemsList = new ItemListComponent(driver, ".grocery-list");
        addGroceryItemComponent = new AddItemComponent(driver, ".add-grocery-item");
    }

    /**
     * Navigates to the page under test, waiting until it is ready
     * @return this ItemPageObject
     */
    public ItemPageObject get() {
        driver.get("http://localhost:8080");
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
        return this;
    }

    /**
     * Clicks the "All" button on the page
     * @return this ItemPageObject
     */
    public ItemPageObject selectAll() {
        findElementWithText("All").click();
        return this;
    }


    /**
     * Clicks the "Active" button on the page
     * @return this ItemPageObject
     */
    public ItemPageObject selectActive() {
        findElementWithText("Active").click();
        return this;
    }


    /**
     * Clicks the "Completed" button on the page
     * @return this ItemPageObject
     */
    public ItemPageObject selectCompleted() {
        findElementWithText("Completed").click();
        return this;
    }

    /**
     * Adds an item to the todolist
     * @return this ItemPageObject
     */
    public ItemPageObject addTodo(String todoName) {
        addTodoItemComponent.addItem(todoName);
        return this;
    }

    /**
     * Adds an item to the grocery list
     * @return this ItemPageObject
     */
    public ItemPageObject addGroceryItem(String groceryName) {
        addGroceryItemComponent.addItem(groceryName);
        return this;
    }

    public ItemListComponent getTodoList() {
        return todoItemsList;
    }

    public ItemListComponent getGroceryList() {
        return groceryItemsList;
    }

    private WebElement findElementWithText(String text) {
        return driver.findElement(getConditionForText(text));
    }

    private By getConditionForText(String text) {
        return By.xpath(format("//*[text()='%s']", text));
    }


}
