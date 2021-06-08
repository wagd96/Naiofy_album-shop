package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.invalidate_sessions.InvalidateSessionsActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.templates.FieldValues;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.it.Ma;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class InvalidateSessionsStepDef {
    @Steps
    CommonActions commonActions;

    @Steps
    InvalidateSessionsActions invalidateSessionsActions;

    @Steps
    CommonQuestions commonQuestions;

    Map<String, String> loginInfo;

    @Given("I have the access token of an user type {string}")
    public void iHaveTheAccessTokenOfAnUserTypeUser(String userType) throws IOException {
        loginInfo = commonActions.loginAnUser(FieldValues.in(String.format("templates/login/%s-user.properties", userType)));
    }

    @When("I consume the web service to invalidate sessions")
    public void iConsumeTheWebServiceToInvalidateSessions() {
        invalidateSessionsActions.invalidateSessions(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @Then("the web service to invalidate sessions responses successfully")
    public void theWebServiceToInvalidateSessionsResponsesSuccessfully() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }

}
