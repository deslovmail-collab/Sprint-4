package tests;

import formPage.MainPage;
import formPage.OrderPage;
import formPage.TestData;
import formPage.Urls;
import org.junit.After;
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

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RentalPageTest {

    private final String browser;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private OrderPage orderPage;

    // Конструктор
    public RentalPageTest(String browser, String name, String lastName, String address,
                          String metroStation, String phone) {
        this.browser = browser;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
    }

    // Параметры теста: Chrome и Firefox + данные
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(TestData.ORDER_DATA);
    }

    @Before
    public void setUp() {
        // Настройка драйвера в зависимости от браузера
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Поддерживаемые браузеры: chrome, firefox. Получено: " + browser);
        }

        // Открываем главную страницу
        driver.get(Urls.MAIN_URL);
        driver.manage().window().maximize();

        // Устанавливаем явное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        mainPage = new MainPage(driver, wait);
        orderPage = new OrderPage(driver, wait);
    }

    @Test
    public void shouldSuccessfullyCompleteOrder() {
        // 1. Нажимаем верхнюю кнопку «Заказать»
        mainPage.clickTopOrderButton();

        // 2. Заполняем личные данные
        orderPage.fillPersonalInfo(name, lastName, address, metroStation, phone);

        // 3. Переходим на второй шаг — «Про аренду»
        orderPage.clickNextButton();

        // Проверка: отображается заголовок «Про аренду»
        assertTrue(orderPage.isAboutRentHeaderVisible());

        // 4. Заполняем данные аренды
        orderPage.setRentalDate("09.10.2025");
        orderPage.setRentalPeriod("двое суток");
        orderPage.selectScooterColor("чёрный жемчуг");
        orderPage.addComment("Привет, было сложно, но я старался");

        // 5. Нажимаем кнопку «Заказать» на форме
        orderPage.clickRentButton();

        // 6. Подтверждаем заказ
        orderPage.confirmOrder();

        // 7. Проверка: появилось окно «Заказ оформлен»
        assertTrue(orderPage.isOrderConfirmed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
