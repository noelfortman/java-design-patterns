package com.iluwater.componentobject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@WebAppConfiguration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class TodoAppTest {

    public static final String BUY_GROCERIES = "Buy groceries";
    public static final String TIDY_UP = "Tidy up";
    public static final String AVOCADOS = "avocados";
    public static final String TOMATOES = "tomatoes";
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe"); // "[folder]\\chromedriver.exe"
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two todos and checks whether they have been clicked.
     */
    @Test
    public void testCreateTodos() {
        new ItemPageObject(driver).get()
            .addTodo(BUY_GROCERIES)
            .addTodo(TIDY_UP)
            .getTodoList()
            .verifyItemStrikethrough(BUY_GROCERIES, false)
            .verifyItemStrikethrough(TIDY_UP, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two todos and click on one.
     */
    @Test
    public void testCompleteTodo() {
        new ItemPageObject(driver).get()
            .addTodo(BUY_GROCERIES)
            .addTodo(TIDY_UP)
            .getTodoList()
            .clickOnItem(BUY_GROCERIES)
            .verifyItemStrikethrough(BUY_GROCERIES, true)
            .verifyItemStrikethrough(TIDY_UP, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two items, clicks on one. Selects the active item and ensures that only the unclicked item is shown.
     */
    @Test
    public void testSelectTodosActive() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addTodo(BUY_GROCERIES)
            .addTodo(TIDY_UP)
            .getTodoList()
            .clickOnItem(BUY_GROCERIES);
        todoPage
            .selectActive()
            .getTodoList()
            .verifyItemNotShown(BUY_GROCERIES)
            .verifyItemStrikethrough(TIDY_UP, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two items, clicks on one. Selects the completed item and ensures that only the clicked item is shown.
     */
    @Test
    public void testSelectTodosCompleted() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addTodo(BUY_GROCERIES)
            .addTodo(TIDY_UP)
            .getTodoList()
            .clickOnItem(BUY_GROCERIES);
        todoPage
            .selectCompleted()
            .getTodoList()
            .verifyItemStrikethrough(BUY_GROCERIES, true)
            .verifyItemNotShown(TIDY_UP);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two items, clicks on one. Selects the all items and ensures that one is clicked, the other is not.
     */
    @Test
    public void testSelectTodosAll() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addTodo(BUY_GROCERIES)
            .addTodo(TIDY_UP)
            .getTodoList()
            .clickOnItem(BUY_GROCERIES);
        todoPage
            .selectCompleted()
            .selectAll()
            .getTodoList()
            .verifyItemStrikethrough(BUY_GROCERIES, true)
            .verifyItemStrikethrough(TIDY_UP, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two grocery items, ensures that both are not clicked.
     */
    @Test
    public void testCreateGroceryItems() {
        new ItemPageObject(driver).get()
            .addGroceryItem(AVOCADOS)
            .addGroceryItem(TOMATOES)
            .getGroceryList()
            .verifyItemStrikethrough(AVOCADOS, false)
            .verifyItemStrikethrough(TOMATOES, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two groceries items, clicks on one and makes sure that one is struck through.
     */
    @Test
    public void testCompleteGroceryItem() {
        new ItemPageObject(driver).get()
            .addGroceryItem(AVOCADOS)
            .addGroceryItem(TOMATOES)
            .getGroceryList()
            .clickOnItem(AVOCADOS)
            .verifyItemStrikethrough(AVOCADOS, true)
            .verifyItemStrikethrough(TOMATOES, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two groceries items, clicks on one. After selecting active items, ensures that only the unclicked item
     * remains.
     */
    @Test
    public void testSelectGroceryItemsActive() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addGroceryItem(AVOCADOS)
            .addGroceryItem(TOMATOES)
            .getGroceryList()
            .clickOnItem(AVOCADOS);
        todoPage
            .selectActive()
            .getGroceryList()
            .verifyItemNotShown(AVOCADOS)
            .verifyItemStrikethrough(TOMATOES, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two grocery items, and clicks on one. When getting completed items, check that only the clicked item
     * is shown.
     */
    @Test
    public void testSelectGroceryItemsCompleted() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addGroceryItem(AVOCADOS)
            .addGroceryItem(TOMATOES)
            .getGroceryList()
            .clickOnItem(AVOCADOS);
        todoPage
            .selectCompleted()
            .getGroceryList()
            .verifyItemStrikethrough(AVOCADOS, true)
            .verifyItemNotShown(TOMATOES);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two grocery items, and clicks on one. When getting all items, check that the clicked item is shown
     * struck through, and the other is shown but not struck through.
     */
    @Test
    public void testSelectGroceryItemsAll() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addGroceryItem(AVOCADOS)
            .addGroceryItem(TOMATOES)
            .getGroceryList()
            .clickOnItem(AVOCADOS);
        todoPage
            .selectCompleted()
            .selectAll()
            .getGroceryList()
            .verifyItemStrikethrough(AVOCADOS, true)
            .verifyItemStrikethrough(TOMATOES, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two groceries and two todos. Click on a grocery and one todoitem. After selecting the active items,
     * check that only the unclicked grocery and todoitem are shown.
     */
    @Test
    public void testSelectCombinedItemsActive() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
            .addTodo(BUY_GROCERIES)
            .addTodo(TIDY_UP)
            .addGroceryItem(AVOCADOS)
            .addGroceryItem(TOMATOES);
        todoPage
            .getGroceryList()
            .clickOnItem(AVOCADOS);
        todoPage
            .getTodoList()
            .clickOnItem(TIDY_UP);
        todoPage
            .selectActive();
        todoPage
            .getTodoList()
            .verifyItemStrikethrough(BUY_GROCERIES, false)
            .verifyItemNotShown(TIDY_UP);
        todoPage
            .getGroceryList()
            .verifyItemNotShown(AVOCADOS)
            .verifyItemStrikethrough(TOMATOES, false);
    }

    /**
     * CS427 Issue Link: https://github.com/iluwatar/java-design-patterns/issues/509
     * Creates two groceries and two todos. Click on a grocery and one todoitem. After selecting the completed items,
     * check that only the clicked grocery and todoitem are shown.
     */
    @Test
    public void testSelectCombinedItemsCompleted() {
        ItemPageObject todoPage = new ItemPageObject(driver).get();
        todoPage
                .addTodo(BUY_GROCERIES)
                .addTodo(TIDY_UP)
                .addGroceryItem(AVOCADOS)
                .addGroceryItem(TOMATOES);
        todoPage
                .getGroceryList()
                .clickOnItem(AVOCADOS);
        todoPage
                .getTodoList()
                .clickOnItem(TIDY_UP);
        todoPage
                .selectCompleted();
        todoPage
                .getTodoList()
                .verifyItemStrikethrough(TIDY_UP, true)
                .verifyItemNotShown(BUY_GROCERIES);
        todoPage
                .getGroceryList()
                .verifyItemNotShown(TOMATOES)
                .verifyItemStrikethrough(AVOCADOS, true);
    }
}
