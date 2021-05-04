package ru.netology.delivery.data;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import java.time.Duration;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL + "A" + Keys.DELETE));
        String date1 = DataGenerator.generateDate(5);
        $("[data-test-id=date] input").setValue(date1);
        $("[data-test-id=name] input").setValue(DataGenerator.generateName());
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно запланирована на"), Duration.ofSeconds(15));
        $(withText("Встреча успешно запланирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText(date1)).shouldBe(visible);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL + "A" + Keys.DELETE));
        String date2 = DataGenerator.generateDate(7);
        $("[data-test-id=date] input").setValue(date2);
        $$("button").find(exactText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована на")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText(date2)).shouldBe(visible);
    }
}
