package FormPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import Locators.OrderPageLocators;
import org.openqa.selenium.By;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RentalPage {

    private final String browser;
    private final String name;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;
    private WebDriver driver;
    private WebDriverWait wait;
    private DataInputPage dataInputPage;

    public RentalPage(String browser, String name, String lastName, String address, String station,
                      String phone) {
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

    private void fillPersonalInfo() {
        dataInputPage.sendKeysToElement(OrderPageLocators.FIELD_NAME, name);
        dataInputPage.sendKeysToElement(OrderPageLocators.FIELD_LAST_NAME, lastName);
        dataInputPage.sendKeysToElement(OrderPageLocators.DELIVERY_ADDRESS_FIELD, address);
        dataInputPage.clickElement(Locators.OrderPageLocators.CLICK_METRO_SELECTION_FIELD);
        dataInputPage.sendKeysToElement(OrderPageLocators.CLICK_METRO_SELECTION_FIELD, station);
        dataInputPage.clickElement(Locators.OrderPageLocators.SELECT_METRO_STATION);
        dataInputPage.sendKeysToElement(OrderPageLocators.FIELD_PHONE, phone);
    }

    @Test
    public void testRentalFlow() {
        System.out.println("→ Тестируем: | Браузер: " + browser +
                " | Имя: " + name + " " + lastName +
                " | Адрес: " + address + " | Станция: " + station +
                " | Телефон: " + phone);


        // Шаг 1. Клик по кнопке «Заказать» вверху страницы
        dataInputPage.clickElement(OrderPageLocators.CLICK_TOP_BUTTON);
        System.out.println("1. Нажать кнопку 'Заказать' успешно");
        // Шаг 2. Заполняем личные данные (те же, что в OrderButtonAtTheTop)
        fillPersonalInfo();
        // Шаг 3. Закрываем cookies, если есть
        if (!driver.findElements(OrderPageLocators.COOKIE_BUTTON).isEmpty()) {
            dataInputPage.clickElement(OrderPageLocators.COOKIE_BUTTON);
            System.out.println("2. Баннер cookies закрыт");
        }
        // Шаг 4. Нажимаем «Далее» (после заполнения личных данных)
        dataInputPage.clickElement(OrderPageLocators.CLICK_BOTTOM_BUTTON);
        System.out.println("3. Клик по кнопке Далее успешно");
        // Вводим дату "09.10.2025" в поле «Время доставки».
        dataInputPage.sendKeysToElement(OrderPageLocators.DELIVERY_TIME_FOR_SCOOTER, "09.10.2025");
        dataInputPage.clickElement(OrderPageLocators.CLICK_DELIVERY_TIME_FOR_SCOOTER);
        System.out.println("1. Время доставки выбрано успешно");
        // Кликаем по элементу, открывающему выпадающий список периодов аренды и по конкретному пункту в списке, выбирая нужный период.
        driver.findElement(OrderPageLocators.EXPAND_LIST).click();
        driver.findElement(OrderPageLocators.SELECT_FROM_DROPDOWN_LIST).click();
        System.out.println("2. Период аренды выбран успешно");
        // Кликаем по элементу, отвечающему за выбор цвета скутера
        dataInputPage.clickElement(OrderPageLocators.SCOOTER_COLOR);
        System.out.println("3. Цвет скутера серый выбран успешно");
        // Заполняем поле комментарий
        dataInputPage.sendKeysToElement(OrderPageLocators.COURIER_COMMENT, "Привет, было сложно, но я старался");
        System.out.println("4. Поле комментарий заполнен успешно");
        // Кликаем на кнопку Далее
        dataInputPage.clickElement(OrderPageLocators.CLICK_RENTAL_BUTTON);
        System.out.println("5. Клик по кнопке Далее успешно");
        //Кликаем на кнопку ОК
        dataInputPage.clickElement(OrderPageLocators.CLICK_YES_BUTTON);
        System.out.println("6. Клик по кнопке ОК успешно");
        // Проверка результата
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[class*='Order_ModalHeader']")
        ));

        assertTrue("Модальное окно не открылось", modal.getText().contains("Заказ оформлен"));
        System.out.println("Форма заказа открылась успешно!");

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

