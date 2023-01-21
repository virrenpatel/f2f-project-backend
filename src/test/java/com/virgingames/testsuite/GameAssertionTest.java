package com.virgingames.testsuite;

import com.virgingames.constants.EndPoints;
import com.virgingames.constants.Path;
import com.virgingames.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GameAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://www.virgingames.com";
        RestAssured.registerParser("text/plain", Parser.JSON);
        response = given()
                .header("Content-Type","application/json")
                .when()
                .basePath(Path.BINGO)
                .get(EndPoints.GET_ALL_GAMESINFO)
                .then().statusCode(200);
    }
    // Checking that default game frequency is equal to 300000
    @Test
    public void test001() {
        response.body("bingoLobbyInfoResource.streams[3]", hasEntry("defaultGameFrequency",300000));

    }

}
