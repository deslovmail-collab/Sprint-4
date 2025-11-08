package FormPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DataInputPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public DataInputPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
// Ожидание кликабельности (доступно для клика)
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
// Клик по веб‑элементу после ожидания его готовности к взаимодействию
    protected void clickElement(By locator) {
        waitForElementToBeClickable(locator).click();
    }

// Ввод текста в веб‑элемент с предварительным ожиданием его готовности к взаимодействию
    protected void sendKeysToElement(By locator, String text) {
        waitForElementToBeClickable(locator).sendKeys(text);
    }
}