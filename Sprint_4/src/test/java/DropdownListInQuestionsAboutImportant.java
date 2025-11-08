import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class DropdownListInQuestionsAboutImportant {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-notifications");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
        @Test
        public void arrowOpensText() {
        for (int i = 0; i <= 7; i++){
            String id = "accordion__heading-" + i;
            System.out.println("Обрабатываем элемент: " + id);
            WebElement arrowOpensText = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", arrowOpensText);

        arrowOpensText.click();

            wait.until(ExpectedConditions.attributeToBe(By.id(id),
                    "aria-expanded",
                    "true"));
            System.out.println("Элемент " + id + " успешно раскрыт");


        }
        driver.quit();
    }
}
