package com.iluwater.componentobject;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ItemListComponent {
    private final WebDriver driver;
    private final String containerCssSelector;

    public ItemListComponent(WebDriver pDriver, String pContainerCssSelector) {
        this.driver = pDriver;
        this.containerCssSelector = pContainerCssSelector;
    }

    /**
     * Clicks on an item within the list
     *
     * @param item the item to be clicked on
     * @return this ItemListComponent
     */
    public ItemListComponent clickOnItem(String item) {
        findElementWithText(item).click();
        return this;
    }

    /**
     * Asserts whether an item has the strikethrough text decoration.
     *
     * @param item the item to be checked for strikethrough
     * @param expectedStrikethrough whether the item should have the strikethrough text decoration
     * @return this ItemListComponent
     */
    public ItemListComponent verifyItemStrikethrough(String item, boolean expectedStrikethrough) {
        WebElement todoElement = findElementWithText(item);
        assertNotNull(todoElement);
        boolean actualStrikethrough = todoElement.getAttribute("style").contains("text-decoration: line-through;");
        assertEquals(expectedStrikethrough, actualStrikethrough);
        return this;
    }

    /**
     * Asserts that no elements for the given item can be found.
     *
     * @param item to search for existence
     * @return
     */
    public ItemListComponent verifyItemNotShown(String item) {
        assertTrue(findElementsWithText(item).isEmpty());
        return this;
    }

    private WebElement findElementWithText(String text) {
        return driver.findElement(getConditionForText(text));
    }

    private List<WebElement> findElementsWithText(String text) {
        return driver.findElements(getConditionForText(text));
    }

    private By getConditionForText(String text) {
        String containerClassName = StringUtils.substring(containerCssSelector, 1);
        return By.xpath(format("//*[@class='" + containerClassName + "']//*[text()='%s']", text));
    }
}
