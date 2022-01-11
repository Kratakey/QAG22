package config;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackMobileDriver;
import drivers.EmulationMobileDriver;
import drivers.LocalMobileDriver;
import drivers.SelenoidMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.getSessionId;
import static org.junit.jupiter.api.Assertions.fail;

public class TestBase {
    public static String deviceFarm = System.getProperty("deviceFarm", "browserstack");
    public static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
    private static final ProjectConfig config =
            ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        switch (deviceFarm) {
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "emulation":
                Configuration.browser = EmulationMobileDriver.class.getName();
                break;
            case "local":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "selenoid":
                Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            default:
                fail();
                break;
        }
        Configuration.startMaximized = config.getStartMaximized();
        Configuration.timeout = config.getTimeout();
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();
        if (!deviceFarm.equals("local")) {
            Attach.attachVideo(sessionId);
        }
    }
}
