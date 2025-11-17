package tests;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import formPage.OrderPage;
import formPage.MainPage;
import static formPage.TestData.ORDER_DATA;

@RunWith(Parameterized.class)
public class OrderButtonAtTheBottomTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private OrderPage orderPage;

    private final String name;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;

    // Глобальный выбор браузера (один на весь прогон)
    private static final String BROWSER = System.getProperty("browser", "chrome").toLowerCase();

    public OrderButtonAtTheBottomTest(String name, String lastName, String address,
                                      String station, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(ORDER_DATA);
    }

    @Before
    public void setUp() {
        if (BROWSER.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + BROWSER);
        }

        driver.get(Urls.MAIN_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        mainPage = new MainPage(driver, wait);
        orderPage = new OrderPage(driver, wait);
    }

    @Test
    public void shouldOpenSecondPageWhenClickNextAfterFillingFormFromBottomButton() {
        // Закрываем баннер с куки
        orderPage.closeCookieBanner();

        // Клик по нижней кнопке «Заказать»
        mainPage.clickBottomOrderButton();

        // Заполняем персональные данные
        orderPage.fillPersonalInfo(name, lastName, address, station, phone);

        // Кликаем «Далее»
        orderPage.clickNextButton();

        // Проверяем, что открылась страница "Про аренду"
        Assert.assertTrue(
                "Страница 'Про аренду' не отображается",
                orderPage.isAboutRentHeaderVisible()
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
