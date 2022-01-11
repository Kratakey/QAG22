package tests;

import com.codeborne.selenide.Condition;
import config.TestBase;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class WikipediaTests extends TestBase {

    @Test
    @Tag("browserstack_android")
    @DisplayName("Wikipedia language should be English by default")
    void wikipediaEnglishTest() {
        $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_settings")).click();
        $(MobileBy.id("android:id/summary")).shouldHave(Condition.text("English"));
    }

    @Test
    @Tag("selenide_android")
    @DisplayName("Wikipedia 4 welcome steps")
    void wikipediaWelcomeTest() {
        $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(Condition.text("The Free Encyclopedia …in over 300 languages"));
        $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(Condition.text("New ways to explore"));
        $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(Condition.text("Reading lists with sync"));
        $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/switchView")).shouldBe(Condition.visible);
        $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
        $(MobileBy.AccessibilityId("Search Wikipedia")).shouldBe(Condition.visible);
    }
}
