package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.questions.CommonQuestions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class CommonSteps {

    @Steps
    CommonQuestions commonQuestions;

    @And("the schema should match with the specification defined in \"(.*)\"")
    public void the_schema_should_match_with_the_specification(String spec) {
        commonQuestions.verifyResponseSchema(lastResponse(), spec);
    }

    @Then("the web service will respond with a validation error of invalid token")
    public void theWebServiceWillRespondWithAValidationErrorOfInvalidToken() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
    }
}
