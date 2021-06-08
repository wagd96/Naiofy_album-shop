package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.login.LoginActions;
import com.naiofy.albums_shop.actions.registration.RegistrationActions;
import com.naiofy.albums_shop.actions.user_list.UserListActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.questions.user_list.UserListQuestions;
import com.naiofy.albums_shop.utils.templates.MergeFrom;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class RegistrationStepdefs {
    @Steps
    RegistrationActions registrationActions;

    @Steps
    CommonQuestions commonQuestions;

    @Steps
    CommonActions commonActions;

    @Steps
    LoginActions loginActions;

    @Steps
    UserListActions userListActions;

    @Steps
    UserListQuestions userListQuestions;

    Map<String, String> dataForNewUser;
    String body;
    int userId;

    @Given("I have the data of an user to register on the platform")
    public void iHaveTheDataOfAnUserToRegisterOnThePlatform() {
        dataForNewUser = commonActions.getNewUserData();
        body = MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Given("I have the data of an user with an invalid email to register on the platform")
    public void iHaveTheDataOfAnUserWithAnInvalidEmailToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithInvalidEmail();
    }

    @Given("I have the data of an user with an incorrectly formatted email to register on the platform")
    public void iHaveTheDataOfAnUserWithAnIncorrectlyFormattedEmailToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithIncorrectFormatEmail();
    }

    @Given("I have the data of an user with numbers in the firstName to register on the platform")
    public void iHaveTheDataOfAnUserWithNumbersInTheFirstNameToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithIncorrectFirstName();
    }

    @Given("I have the data of an user with numbers in the lastName to register on the platform")
    public void iHaveTheDataOfAnUserWithNumbersInTheLastNameToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithIncorrectLastName();
    }

    @Given("I have the data of an user with numbers in the firstName and lastName to register on the platform")
    public void iHaveTheDataOfAnUserWithNumbersInTheFirstNameAndLastNameToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithIncorrectFirstAndLastName();
    }

    @Given("I have the data of an user with incorrect minimum password length to register on the platform")
    public void iHaveTheDataOfAnUserWithIncorrectMinimumPasswordLengthToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithIncorrectMinimumPasswordLength();
    }

    @Given("I have the data of an user with only numbers in the password to register on the platform")
    public void iHaveTheDataOfAnUserWithOnlyNumbersInThePasswordToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithOnlyNumbersInThePassword();
    }

    @Given("I have the data of an user without numbers in the password to register on the platform")
    public void iHaveTheDataOfAnUserWithoutNumbersInThePasswordToRegisterOnThePlatform() {
        body = registrationActions.generateAnUserWithoutNumbersInThePassword();
    }

    @When("I consume the web service to register a regular user on the platform")
    public void iConsumeTheWebServiceToRegisterARegularUserOnThePlatform() {
        registrationActions.registerRegularUser(body);
    }

    @Then("the web service to register a new user responses successfully")
    public void theWebServiceToRegisterANewUserResponsesSuccessfully() {
        commonQuestions.responseCodeIs(201, lastResponse());
        userId = lastResponse().getBody().path("user_id");
    }

    @And("the user will be created on the platform")
    public void theUserWillBeCreatedOnThePlatform() {
        Map<String, String> loginInfo = commonActions.loginAnUser(dataForNewUser);
        userListActions.searchSpecificUser(loginInfo);
        commonQuestions.responseCodeIs(200, lastResponse());
        userListQuestions.validateTheUserSearchResult(dataForNewUser, lastResponse());
    }

    @Then("the web service will respond with a validation error")
    public void theWebServiceWillRespondWithAValidationErrorOfInvalidName() {
        commonQuestions.responseCodeNotIs(201, lastResponse());
    }
}
