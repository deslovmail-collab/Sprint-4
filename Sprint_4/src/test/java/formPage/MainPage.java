package formPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ЛОКАТОРЫ
    private static final By TOP_ORDER_BUTTON = By.className("Button_Button__ra12g");
    private static final By BOTTOM_ORDER_BUTTON = By.xpath("//button[contains(text(), 'Заказать') and parent::div[contains(@class, 'Home_FinishButton')]]");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Клик по верхней кнопке «Заказать»
    public void clickTopOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(TOP_ORDER_BUTTON));
        button.click();
    }

    // Клик по нижней кнопке «Заказать»
    public void clickBottomOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BOTTOM_ORDER_BUTTON));
        button.click();
    }

    // Метод для проверки аккордеона
    public void verifyAccordionQuestionAndAnswer(int questionIndex, String expectedAnswer) {
        String questionId = "accordion__heading-" + questionIndex;
        String answerId = "accordion__panel-" + questionIndex;

        // Прокрутка и клик по вопросу
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(By.id(questionId)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();

        // Ожидание видимости ответа
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(answerId)));

        // Получение текста
        String actualAnswer = answer.getText().trim();

        // Сравнение
        if (!actualAnswer.equals(expectedAnswer)) {
            throw new AssertionError("Ожидался текст: " + expectedAnswer + ", но был: " + actualAnswer);
        }
    }
}
