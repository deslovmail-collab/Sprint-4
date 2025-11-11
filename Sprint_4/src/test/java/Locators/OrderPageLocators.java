package locators;

import org.openqa.selenium.By;

public class OrderPageLocators {

    public static final By FIELD_NAME = By.cssSelector("input[placeholder = '* Имя']"); // Поле «Имя»

    public static final By FIELD_LAST_NAME = By.cssSelector("input[placeholder = '* Фамилия']"); // Поле «Фамилия»

    public static final By DELIVERY_ADDRESS_FIELD = By.cssSelector("input[placeholder = '* Адрес: куда привезти заказ']"); // Поле «Адрес: куда привезти заказ»

    public static final By CLICK_METRO_SELECTION_FIELD = By.cssSelector("input[placeholder = '* Станция метро']"); // Поле выбора станции метро

    public static final By SELECT_METRO_STATION = By.cssSelector("div.select-search__select");  // Выпадающий список станций метро

    public static final By FIELD_PHONE = By.cssSelector("input[placeholder = '* Телефон: на него позвонит курьер']"); // Поле «Телефон»

    public static final By COOKIE_BUTTON = By.cssSelector("button[id = 'rcc-confirm-button']"); // Кнопка принятия cookies

    public static final By DELIVERY_TIME_FOR_SCOOTER = By.cssSelector("input[placeholder = '* Когда привезти самокат']"); // Выпадающие значения даты заказа

    public static final By COURIER_COMMENT = By.cssSelector("input[placeholder = 'Комментарий для курьера']"); // Написать комментарий курьеру

    public static final By EXPAND_LIST = By.cssSelector("div.Dropdown-control[aria-haspopup='listbox']"); // Локатор элемента, открывающего выпадающий список периодов аренды

    public static final By NEXT_BUTTON = By.xpath("//button[text()='Далее']");

    public static final By SCOOTER_COLOR_BLACK = By.id("black"); // Чекбокс "чёрный жемчуг"

    public static final By SCOOTER_COLOR_GREY = By.id("grey");   // Чекбокс "серая безысходность"

    public static final By BOTTOM_ORDER_BUTTON = By.xpath("//button[contains(text(), 'Заказать') and parent::div[contains(@class, 'Home_FinishButton')]]"); // Кнопка "Заказать" внизу страницы

    public static final By ABOUT_RENT_HEADER = By.xpath("//div[text()='Про аренду']");// Заголовок "Про аренду" на втором шаге

    public static final By TOP_ORDER_BUTTON = By.className("Button_Button__ra12g");

    public static final By CLICK_RENTAL_BUTTON = By.xpath("//div[contains(@class, 'Buttons')]//button[text()='Заказать']");

    public static final By CLICK_YES_BUTTON = By.xpath("//div[contains(@class, 'Modal')]//button[text()='Да']");

    public static final By ORDER_CONFIRMED_HEADER = By.xpath("//div[contains(@class, 'ModalHeader') and text()='Заказ оформлен']");


}
