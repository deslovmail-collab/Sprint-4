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

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Клик по верхней кнопке «Заказать» (было)
    public void clickTopOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("Button_Button__ra12g")));
        button.click();
    }

     // Принимает индекс вопроса и ожидаемый текст
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
