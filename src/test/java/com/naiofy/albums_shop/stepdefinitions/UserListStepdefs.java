package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.user_list.UserListActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.questions.user_list.UserListQuestions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.templates.FieldValues;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class UserListStepdefs {
    @Steps
    CommonActions commonActions;
    @Steps
    CommonQuestions commonQuestions;

    @Steps
    UserListActions userListActions;

    @Steps
    UserListQuestions userListQuestions;

    Map<String, String> loginInfo;

    @Given("I have an access Token of a regular user to search users")
    public void iHaveAnAccessTokenOfARegularUser() throws IOException {
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
    }

    @Given("I have an access Token of an admin user to search users")
    public void iHaveAnAccessTokenOfAnAdminUser() throws IOException {
        Map<String, String> adminUserInfo = FieldValues.in("templates/login/admin-user.properties");
        loginInfo = commonActions.loginAnUser(adminUserInfo);
    }

    @When("I consume the web service to see the user list on the platform")
    public void iConsumeTheWebServiceToSeeTheUserListOnThePlatform() {
        userListActions.searchUsers(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @When("I consume the web service to search a specific user on the platform")
    public void iConsumeTheWebServiceToSearchASpecificUserOnThePlatform() {
        userListActions.searchSpecificUser(loginInfo);
    }

    @When("I consume the web service to search an non-existent user on the platform")
    public void iConsumeTheWebServiceToSearchAnNonExistentUserOnThePlatform() {
        Map<String, String> newLoginInfo = new HashMap();
        newLoginInfo.put(Dictionary.TOKEN_TAG, loginInfo.get(Dictionary.TOKEN_TAG));
        newLoginInfo.put(Dictionary.USER_ID_TAG, "123456789456123");
        userListActions.searchSpecificUser(newLoginInfo);
    }

    @When("I consume the web service to see the user list in an non-existent page on the platform")
    public void iConsumeTheWebServiceToSeeTheUserListInAnNonExistentPageOnThePlatform() {
        userListActions.searchUsersInSpecificPage(loginInfo.get(Dictionary.TOKEN_TAG), 123456789);
    }

    @When("I consume the web service to see the user list on the platform without access token")
    public void iConsumeTheWebServiceToSeeTheUserListOnThePlatformWithoutAccessToken() {
        userListActions.searchUsersWithoutToken();
    }

    @When("I consume the web service to see the user list on the platform with an invalid access Token")
    public void iConsumeTheWebServiceToSeeTheUserListOnThePlatformWithAnInvalidAccessToken() {
        userListActions.searchUsers("abcd");
    }

    @Then("the web service responses with the list of users")
    public void theWebServiceResponsesWithTheListOfUsers() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }


    @Then("the web service responses with the information of the regular user")
    public void theWebServiceResponsesWithTheInformationOfTheRegularUser() throws IOException {
        Map<String, String> userInfo = new HashMap<>(FieldValues.in("templates/login/regular-user.properties"));
        userInfo.put(Dictionary.FIRST_NAME_TAG, Dictionary.REGULAR_USER_FIRST_NAME);
        userInfo.put(Dictionary.LAST_NAME_TAG, Dictionary.REGULAR_USER_LAST_NAME);
        userListQuestions.validateTheUserSearchResult(userInfo, lastResponse());
    }

    @Then("the web service will respond with an error of not found user")
    public void theWebServiceWillRespondWithAnErrorOfNotFoundUser() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
    }

    @And("the total number of users on the platform will be greater than the result for a regular user")
    public void theTotalNumberOfUsersOnThePlatformWillBeGreaterThanTheResultForARegularUser() throws IOException {
        int totalUserWithAdmins = lastResponse().getBody().path("total_count");
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
        userListActions.searchUsers(loginInfo.get(Dictionary.TOKEN_TAG));
        int totalUsers = lastResponse().getBody().path("total_count");

        assertThat(totalUserWithAdmins).isGreaterThan(totalUsers);
    }
}
