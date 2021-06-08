package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.login.LoginActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.questions.login.LoginQuestions;
import com.naiofy.albums_shop.utils.templates.FieldValues;
import com.naiofy.albums_shop.utils.templates.MergeFrom;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.lastResponse;


public class LoginStepdefs {

    @Steps
    LoginActions loginActions;

    @Steps
    LoginQuestions loginQuestions;

    @Steps
    CommonQuestions commonQuestions;

    String body;

    @Given("I have the credentials of an user type {string}")
    public void iHaveTheCredentialsOfAnUserTypeUser(String userType) throws IOException {
        body = MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(FieldValues.in(String.format("templates/login/%s-user.properties", userType)));
    }

    @When("I consume the web service to login into the platform")
    public void iConsumeTheWebServiceToLoginIntoThePlatform() {
        loginActions.loginUser(body);
    }

    @Then("the web service to login responses successfully")
    public void theWebServiceToLoginResponsesSuccessfully() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }

    @Given("I don't have an existing user in the platform")
    public void iDonTHaveAnExistingUserInThePlatform() {
        body = loginActions.getBodyFromNonExistentUser();
    }

    @Then("the web service will respond with a validation error of unable to authenticate credentials")
    public void theWebServiceWillRespondWithAValidationErrorOfUnableToAuthenticateCredentials() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
        loginQuestions.verifyCredentialsErrorMessage(lastResponse());
    }

    @Given("I have the credentials for a correct user with wrong password in the platform")
    public void iHaveTheCredentialsForACorrectUserWithWrongPasswordInThePlatform() {
        body = loginActions.getUserWithIncorrectPassword();
    }

    @Given("I have an email with an incorrect format to login into the platform")
    public void iHaveAnEmailWithAnIncorrectFormatToLoginIntoThePlatform() {
        body = loginActions.getUserWithIncorrectEmailFormat();
    }

    @Then("the web service will respond with a validation error of invalid email")
    public void theWebServiceWillRespondWithAValidationErrorOfInvalidEmail() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
        loginQuestions.verifyInvalidEmail(lastResponse());
    }

    @Given("I have an user with different email than the accepted for the platform")
    public void iHaveAnUserWithDifferentEmailThanTheAcceptedForThePlatform() {
        body = loginActions.getUserWithNonAcceptedEmail();
    }

    @Then("the web service will respond with a validation error of incorrect domain email")
    public void theWebServiceWillRespondWithAValidationErrorOfIncorrectDomainEmail() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
        loginQuestions.verifyNonAcceptedEmail(lastResponse());
    }

    @Given("I have an user without email to login into the platform")
    public void iHaveAnUserWithoutEmailToLoginIntoThePlatform() {
        body = loginActions.getUserWithoutEmail();
    }

    @Then("the web service will respond with a missing data error of required fields email")
    public void theWebServiceWillRespondWithAMissingDataErrorOfRequiredFieldsEmail() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
        loginQuestions.verifyRequiredEmail(lastResponse());
    }

    @Given("I have an user without password to login into the platform")
    public void iHaveAnUserWithoutPasswordToLoginIntoThePlatform() {
        body = loginActions.getUserWithoutPassword();
        ;
    }

    @Then("the web service will respond with a missing data error of required fields password")
    public void theWebServiceWillRespondWithAMissingDataErrorOfRequiredFieldsPassword() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
        loginQuestions.verifyRequiredPassword(lastResponse());
    }

    @Given("I have an user without email and password to login into the platform")
    public void iHaveAnUserWithoutEmailAndPasswordToLoginIntoThePlatform() {
        body = loginActions.getUserWithoutEmailAndPassword();
    }

    @Then("the web service will respond with a missing data error of required fields email and password")
    public void theWebServiceWillRespondWithAMissingDataErrorOfRequiredFieldsEmailAndPassword() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
        loginQuestions.verifyRequiredEmailAndPassword(lastResponse());
    }
}
