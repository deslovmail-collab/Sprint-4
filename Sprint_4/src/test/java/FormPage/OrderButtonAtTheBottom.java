package FormPage;

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
import Locators.OrderPageLocators;
import org.openqa.selenium.By;

@RunWith(Parameterized.class)
public class OrderButtonAtTheBottom implements OrderFormFiller {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected DataInputPage dataInputPage;

    private final String browser;
    private final String name;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;

    public OrderButtonAtTheBottom(String browser, String name, String lastName, String address,
                                  String station, String phone) {
        this.browser = browser;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(TestData.ORDER_DATA);
    }

    @Before
    public void setUp() {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }

        driver.get("https://qa-scooter.praktikum-services.ru");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        dataInputPage = new DataInputPage(driver);
    }

    @Override
    public void clickElement(By locator) {
        dataInputPage.clickElement(locator);
    }

    @Override
    public void sendKeysToElement(By locator, String text) {
        dataInputPage.sendKeysToElement(locator, text);
    }

    private void fillPersonalInfo() {
        sendKeysToElement(OrderPageLocators.FIELD_NAME, name);
        sendKeysToElement(OrderPageLocators.FIELD_LAST_NAME, lastName);
        sendKeysToElement(OrderPageLocators.DELIVERY_ADDRESS_FIELD, address);
        clickElement(OrderPageLocators.CLICK_METRO_SELECTION_FIELD);
        sendKeysToElement(OrderPageLocators.CLICK_METRO_SELECTION_FIELD, station);
        clickElement(OrderPageLocators.SELECT_METRO_STATION);
        sendKeysToElement(OrderPageLocators.FIELD_PHONE, phone);
    }

    @Test
    public void testOrderButtonBottom() {
        System.out.println("→ Тестируем данные: | Имя: " + name + " " + lastName +
                " | Адрес: " + address + " | Станция: " + station + " | Телефон: " + phone);

        // Закрытие куки, если они есть
        if (!driver.findElements(OrderPageLocators.COOKIE_BUTTON).isEmpty()) {
            clickElement(OrderPageLocators.COOKIE_BUTTON);
            System.out.println("Баннер cookies закрыт");
        }

        // Клик по кнопке «Заказать» внизу страницы
        clickElement(OrderPageLocators.CLICK_BOTTOM_BUTTON);
        System.out.println("1. Клик по кнопке Заказать внизу успешно");

        // Заполнение персональных данных
        fillPersonalInfo();

        // Повторная проверка куки (если появились после заполнения)
        if (!driver.findElements(OrderPageLocators.COOKIE_BUTTON).isEmpty()) {
            clickElement(OrderPageLocators.COOKIE_BUTTON);
            System.out.println("Баннер cookies закрыт");
        }

        // Клик по кнопке «Далее»
        clickElement(OrderPageLocators.CLICK_BOTTOM_BUTTON);
        System.out.println("Клик по кнопке Далее успешно");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}