package formPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ЛОКАТОРЫ
    private static final By FIELD_NAME = By.cssSelector("input[placeholder = '* Имя']");
    private static final By FIELD_LAST_NAME = By.cssSelector("input[placeholder = '* Фамилия']");
    private static final By DELIVERY_ADDRESS_FIELD = By.cssSelector("input[placeholder = '* Адрес: куда привезти заказ']");
    private static final By CLICK_METRO_SELECTION_FIELD = By.cssSelector("input[placeholder = '* Станция метро']");
    private static final By SELECT_METRO_STATION = By.cssSelector("div.select-search__select");
    private static final By FIELD_PHONE = By.cssSelector("input[placeholder = '* Телефон: на него позвонит курьер']");
    private static final By COOKIE_BUTTON = By.cssSelector("button[id = 'rcc-confirm-button']");
    private static final By DELIVERY_TIME_FOR_SCOOTER = By.cssSelector("input[placeholder = '* Когда привезти самокат']");
    private static final By COURIER_COMMENT = By.cssSelector("input[placeholder = 'Комментарий для курьера']");
    private static final By EXPAND_LIST = By.cssSelector("div.Dropdown-control[aria-haspopup='listbox']");
    private static final By NEXT_BUTTON = By.xpath("//button[text()='Далее']");
    private static final By SCOOTER_COLOR_BLACK = By.id("black");
    private static final By SCOOTER_COLOR_GREY = By.id("grey");
    private static final By CLICK_RENTAL_BUTTON = By.xpath("//div[contains(@class, 'Buttons')]//button[text()='Заказать']");
    private static final By CLICK_YES_BUTTON = By.xpath("//div[contains(@class, 'Modal')]//button[text()='Да']");
    private static final By ORDER_CONFIRMED_HEADER = By.xpath("//div[contains(@class, 'ModalHeader') and text()='Заказ оформлен']");
    private static final By ABOUT_RENT_HEADER = By.xpath("//div[text()='Про аренду']");

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillPersonalInfo(String name, String lastName, String address, String station, String phone) {
        sendKeys(FIELD_NAME, name);
        sendKeys(FIELD_LAST_NAME, lastName);
        sendKeys(DELIVERY_ADDRESS_FIELD, address);
        click(CLICK_METRO_SELECTION_FIELD);
        sendKeys(CLICK_METRO_SELECTION_FIELD, station);
        click(SELECT_METRO_STATION);
        sendKeys(FIELD_PHONE, phone);
    }

    public void clickNextButton() {
        closeCookieBanner();
        click(NEXT_BUTTON);
    }

    public void setRentalDate(String date) {
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(DELIVERY_TIME_FOR_SCOOTER));
        dateField.clear();
        dateField.sendKeys(date);
        dateField.sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void setRentalPeriod(String period) {
        click(EXPAND_LIST);
        By periodOption = By.xpath("//div[text()='" + period + "']");
        wait.until(ExpectedConditions.elementToBeClickable(periodOption)).click();
    }

    public void selectScooterColor(String color) {
        String normalizedColor = color.toLowerCase().trim();

        if (normalizedColor.equals("чёрный жемчуг")) {
            click(SCOOTER_COLOR_BLACK);
        } else if (normalizedColor.equals("серая безысходность")) {
            click(SCOOTER_COLOR_GREY);
        } else if (normalizedColor.equals("оба")) {
            click(SCOOTER_COLOR_BLACK);
            click(SCOOTER_COLOR_GREY);
        }
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            sendKeys(COURIER_COMMENT, comment);
        }
    }

    public void clickRentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(CLICK_RENTAL_BUTTON)).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(CLICK_YES_BUTTON)).click();
    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                ORDER_CONFIRMED_HEADER, "Заказ оформлен")) != null;
    }

    public boolean isAboutRentHeaderVisible() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                ABOUT_RENT_HEADER, "Про аренду")) != null;
    }

    private void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    //
    public void closeCookieBanner() {
        if (driver.findElements(COOKIE_BUTTON).size() > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON)).click();
        }
    }

    public boolean isNameFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FIELD_NAME)) != null;
    }
}
