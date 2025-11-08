package Locators;

import org.openqa.selenium.By;

public class OrderPageLocators {

    public static final By CLICK_TOP_BUTTON = By.cssSelector("button.Button_Button__ra12g"); // Кнопка «Заказать» вверху страницы

    public static final By FIELD_NAME = By.cssSelector("input[placeholder = '* Имя']"); // Поле «Имя»

    public static final By FIELD_LAST_NAME = By.cssSelector("input[placeholder = '* Фамилия']"); // Поле «Фамилия»

    public static final By DELIVERY_ADDRESS_FIELD = By.cssSelector("input[placeholder = '* Адрес: куда привезти заказ']"); // Поле «Адрес: куда привезти заказ»

    public static final By CLICK_METRO_SELECTION_FIELD = By.cssSelector("input[placeholder = '* Станция метро']"); // Поле выбора станции метро

    public static final By SELECT_METRO_STATION = By.cssSelector("div.select-search__select");  // Выпадающий список станций метро

    public static final By FIELD_PHONE = By.cssSelector("input[placeholder = '* Телефон: на него позвонит курьер']"); // Поле «Телефон»

    public static final By COOKIE_BUTTON = By.cssSelector("button[id = 'rcc-confirm-button']"); // Кнопка принятия cookies

    public static final By CLICK_BOTTOM_BUTTON = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM"); // Кнопка «Далее» на втором шаге оформления заказа

    public static final By DELIVERY_TIME_FOR_SCOOTER = By.cssSelector("input[placeholder = '* Когда привезти самокат']"); // Выпадающие значения даты заказа

    public static final By CLICK_DELIVERY_TIME_FOR_SCOOTER = By.cssSelector("div.react-datepicker__day.react-datepicker__day--009.react-datepicker__day--selected"); // Выбор конкретной даты 09.10.2025

    public static final By SCOOTER_COLOR = By.cssSelector("input[id = grey]"); // Выбор цвета самоката

    public static final By COURIER_COMMENT = By.cssSelector("input[placeholder = 'Комментарий для курьера']"); // Написать комментарий курьеру

    public static final By CLICK_RENTAL_BUTTON = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM:not(.Button_Inverted__3IF-i)"); // Кнопка «Далее»

    public static final By CLICK_YES_BUTTON =
            By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Да']"); // Подтверждение заказа нажатием на кнопку Ок

    public static final By EXPAND_LIST = By.cssSelector("div.Dropdown-control[aria-haspopup='listbox']"); // Локатор элемента, открывающего выпадающий список периодов аренды

    public static final By SELECT_FROM_DROPDOWN_LIST = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']"); // Выбор из списка двое суток
}
