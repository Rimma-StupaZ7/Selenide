package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.;

class CardDeliveryTest {
    private String generateDate(int addDays, String pattern) {
      return LocalDate.now(). plusDays(addDays). format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldCardDeliveryWorks(){
        
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        String currentData = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='data'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE));
        $("[data-test-id='data'] input").sendKeys(currentData);
        $("[data-test-id='name'] input").setValue("Куроткин Виктор");
        $("[data-test-id='phone'] input").setValue("+79216556666");
        $("[data-test-id='agreement']").click();
        $("baton.baton").click();
        $("notification__").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentData));

    }
}
