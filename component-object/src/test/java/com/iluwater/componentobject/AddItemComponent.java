package com.iluwater.componentobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddItemComponent {
    private WebDriver driver;
    private String containerCssSelector;

    public AddItemComponent(WebDriver pDriver, String pContainerCssSelector) {
        this.driver = pDriver;
        this.containerCssSelector = pContainerCssSelector;
    }

    /**
     * Adds an item through a text input field on a webpage.
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * @param item the text item to be added
     * @return this AddItemComponent after adding the item
     */
    public AddItemComponent addItem(String item) {
        WebElement input = driver.findElement(By.cssSelector(containerCssSelector + " input"));
        input.sendKeys(item);
        WebElement button = driver.findElement(By.cssSelector(containerCssSelector + " button"));
        button.click();
        return this;
    }
}
