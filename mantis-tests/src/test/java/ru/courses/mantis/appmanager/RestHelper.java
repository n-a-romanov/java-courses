package ru.courses.mantis.appmanager;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }


    public String getIssueStatus (int issueId) {
        Response response = RestAssured.given()
            .auth().basic("288f44776e7bec4bf44fdfeb1e646490","")
            .pathParams("issueId", issueId)
            .then().statusCode(200)
            .get("https://bugify.stqa.ru/api/issues/{issueId}.json");
        return JsonPath.from(response.asString()).get("issues[0].state_name");
    }
}
