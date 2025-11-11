package formPage;

import locators.OrderPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Заполнение личной информации
    public void fillPersonalInfo(String name, String lastName, String address, String station, String phone) {
        sendKeys(OrderPageLocators.FIELD_NAME, name);
        sendKeys(OrderPageLocators.FIELD_LAST_NAME, lastName);
        sendKeys(OrderPageLocators.DELIVERY_ADDRESS_FIELD, address);
// Выбор станции мметро
        click(OrderPageLocators.CLICK_METRO_SELECTION_FIELD);
        sendKeys(OrderPageLocators.CLICK_METRO_SELECTION_FIELD, station);
        click(OrderPageLocators.SELECT_METRO_STATION);
// Набор номера телефона
        sendKeys(OrderPageLocators.FIELD_PHONE, phone);
    }

    // Переход на шаг 2
    public void clickNextButton() {
        closeCookieIfPresent();
        click(OrderPageLocators.NEXT_BUTTON);
    }

    // Календарь
    public void setRentalDate(String date) {
        // Ждём, что поле даты кликабельно
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                OrderPageLocators.DELIVERY_TIME_FOR_SCOOTER
        ));

        // Очищаем поле
        dateField.clear();

        // Вводим дату в формате ДД.ММ.ГГГГ
        dateField.sendKeys(date);

        // Нажимаем Enter, чтобы "закрыть" календарь (если он появился)
        dateField.sendKeys(org.openqa.selenium.Keys.ENTER);
    }


    public void setRentalPeriod(String period) {
        click(OrderPageLocators.EXPAND_LIST);
        By periodOption = By.xpath("//div[text()='" + period + "']");
        wait.until(ExpectedConditions.elementToBeClickable(periodOption)).click();
    }

    public void selectScooterColor(String color) {
        String normalizedColor = color.toLowerCase().trim();

        if (normalizedColor.equals("чёрный жемчуг")) {
            click(OrderPageLocators.SCOOTER_COLOR_BLACK);
        } else if (normalizedColor.equals("серая безысходность")) {
            click(OrderPageLocators.SCOOTER_COLOR_GREY);
        } else if (normalizedColor.equals("оба")) {
            click(OrderPageLocators.SCOOTER_COLOR_BLACK);
            click(OrderPageLocators.SCOOTER_COLOR_GREY);
        }
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            sendKeys(OrderPageLocators.COURIER_COMMENT, comment);
        }
    }

    // Клик по кнопке "Заказать" на втором шаге
    public void clickRentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(OrderPageLocators.CLICK_RENTAL_BUTTON))
                .click();
    }

    // Подтверждение заказа: кнопка "Да"
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(OrderPageLocators.CLICK_YES_BUTTON))
                .click();
    }

    // Проверка: появилось ли окно "Заказ оформлен"
    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                OrderPageLocators.ORDER_CONFIRMED_HEADER,
                "Заказ оформлен"
        )) != null;
    }

    // Проверка: открылся ли шаг "Про аренду"
    public boolean isAboutRentHeaderVisible() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                OrderPageLocators.ABOUT_RENT_HEADER,
                "Про аренду"
        )) != null;
    }

    // Клик по кнопкам "Заказать" на главной
    public void clickTopOrderButton() {
        click(OrderPageLocators.TOP_ORDER_BUTTON);
    }

    public void clickBottomOrderButton() {
        click(OrderPageLocators.BOTTOM_ORDER_BUTTON);
    }

     private void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .sendKeys(text);
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    private void closeCookieIfPresent() {
        if (driver.findElements(OrderPageLocators.COOKIE_BUTTON).size() > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(OrderPageLocators.COOKIE_BUTTON))
                    .click();
        }
    }
    public void closeCookieBanner() {
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("rcc-confirm-button")
        ));
        cookieButton.click();
    }

}
