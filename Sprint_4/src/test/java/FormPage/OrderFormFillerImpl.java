import FormPage.OrderFormFiller;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class OrderFormFillerImpl implements OrderFormFiller {
    private WebDriver driver;

    public OrderFormFillerImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    @Override
    public void sendKeysToElement(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
}
