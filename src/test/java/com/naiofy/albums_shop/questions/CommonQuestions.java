package com.naiofy.albums_shop.questions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class CommonQuestions {

    @Step("Verify that API response is {0}")
    public void responseCodeIs(int responseCode, Response lastResponse) {
        assertThat(responseCode).isEqualTo(lastResponse.statusCode());
    }

    @Step("Verify that API response not is {0}")
    public void responseCodeNotIs(int responseCode, Response lastResponse) {
        assertThat(responseCode).isNotEqualTo(lastResponse.statusCode());
    }

    @Step("Verify response schema with {1}")
    public void verifyResponseSchema(Response lastResponse, String schemaJson) {
        lastResponse.then().body(matchesJsonSchemaInClasspath("schema/" + schemaJson));
    }
}
