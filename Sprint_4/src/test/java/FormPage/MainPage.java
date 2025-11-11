package formPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Клик по верхней кнопке «Заказать»
    public void clickTopOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("Button_Button__ra12g")));
        button.click();
    }

}

