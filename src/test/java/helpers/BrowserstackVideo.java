package helpers;

import static config.TestBase.credentials;
import static io.restassured.RestAssured.given;

public class BrowserstackVideo {
    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(credentials.login(), credentials.password())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .path("automation_session.video_url");
    }
}