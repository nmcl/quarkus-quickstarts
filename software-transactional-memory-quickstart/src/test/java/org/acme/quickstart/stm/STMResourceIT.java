package org.acme.quickstart.stm;

import io.quarkus.test.junit.NativeImageTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@NativeImageTest
class STMResourceIT {

    @Test
    void testGet() {
        given()
                .when().get("/stm")
                .then()
                .statusCode(200);
    }

    @Test
    void testPost() {
        String responseString;

        assignTask();
        responseString = assignTask();

        assertThat(responseString, containsString("todo -"));
    }

    private String assignTask() {
        return RestAssured.post("/stm").then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();
    }
}
