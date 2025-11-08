package FormPage;

import org.openqa.selenium.By;

public interface OrderFormFiller {

     // Методы для взаимодействия с элементами
    void clickElement(By locator);
    void sendKeysToElement(By locator, String text);

    // Заполнение формы
    default void fillPersonalInfo(String name, String lastName, String address, String station, String number) {
        // Заполняем поле «Имя»
        sendKeysToElement(Locators.OrderPageLocators.FIELD_NAME, name);
        System.out.println("Поле 'Имя' заполнено: " + name);

        // Заполняем поле «Фамилия»
        sendKeysToElement(Locators.OrderPageLocators.FIELD_LAST_NAME, lastName);
        System.out.println("Поле 'Фамилия' заполнено: " + lastName);

        // Заполняем поле «Адрес»
        sendKeysToElement(Locators.OrderPageLocators.DELIVERY_ADDRESS_FIELD, address);
        System.out.println("Поле 'Адрес' заполнено: " + address);

        // Заполняем поле «Станция метро»
        sendKeysToElement(Locators.OrderPageLocators.CLICK_METRO_SELECTION_FIELD, station);
        clickElement(Locators.OrderPageLocators.SELECT_METRO_STATION);
        System.out.println("Поле 'Станция' заполнено: " + station);

        // Заполняем поле «Номер телефона»
        sendKeysToElement(Locators.OrderPageLocators.FIELD_PHONE, number);
        System.out.println("Поле 'Телефон' заполнено: " + number);
    }


}
