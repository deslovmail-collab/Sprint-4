package tests;

import formPage.MainPage;
import formPage.OrderPage;
import formPage.Urls;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderButtonTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private OrderPage orderPage;

    private final String buttonLocation;

    public OrderButtonTest(String buttonLocation) {
        this.buttonLocation = buttonLocation;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"top"},
                {"bottom"}
        });
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
        driver = new ChromeDriver(options);
        driver.get(Urls.MAIN_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver, wait);
        orderPage = new OrderPage(driver, wait);
    }

    @Test
    public void shouldOpenOrderFormWhenClickOrderButton() {
        if (buttonLocation.equals("bottom")) {
            orderPage.closeCookieBanner(); // ✅ теперь метод с таким именем есть
        }

        if (buttonLocation.equals("top")) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        boolean isNameFieldVisible = orderPage.isNameFieldVisible();
        Assert.assertTrue(
                "Форма заказа не открылась после клика по кнопке '" + buttonLocation + "'",
                isNameFieldVisible
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
