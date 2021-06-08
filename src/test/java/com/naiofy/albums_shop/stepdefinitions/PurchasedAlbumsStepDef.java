package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.purchased_album_list.PurchasedAlbumListActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.templates.FieldValues;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class PurchasedAlbumsStepDef {
    @Steps
    PurchasedAlbumListActions purchasedAlbumListActions;


    @Steps
    CommonActions commonActions;

    @Steps
    CommonQuestions commonQuestions;

    Map<String, String> loginInfo;

    @Given("I have an access Token of regular user to see their purchased albums")
    public void iHaveAnAccessTokenOfRegularUserToSeeTheirPurchasedAlbums() throws IOException {
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
    }

    @When("I consume the web service to see their albums on the platform")
    public void iConsumeTheWebServiceToSeeTheirAlbumsOnThePlatform() {
        purchasedAlbumListActions.seePurchasedAlbums(loginInfo);
    }

    @Then("the web service responses with their album list")
    public void theWebServiceResponsesWithTheirAlbumList() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }

    @Given("I have an access Token of regular user to see purchased albums of other user")
    public void iHaveAnAccessTokenOfRegularUserToSeePurchasedAlbumsOfOtherUser() throws IOException {
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
        loginInfo.remove(Dictionary.USER_ID_TAG);
        loginInfo.put(Dictionary.USER_ID_TAG, "1");
    }

    @Then("the web service will respond with a forbidden error")
    public void theWebServiceWillRespondWithAForbiddenError() {
        commonQuestions.responseCodeIs(403, lastResponse());
    }

    @When("I consume the web service to see the purchased albums on the platform with an invalid access Token")
    public void iConsumeTheWebServiceToSeeThePurchasedAlbumsOnThePlatformWithAnInvalidAccessToken() {
        loginInfo = new HashMap<>();
        loginInfo.put(Dictionary.TOKEN_TAG, "abc");
        loginInfo.put(Dictionary.USER_ID_TAG, "147");
        purchasedAlbumListActions.seePurchasedAlbums(loginInfo);
    }

    @When("I consume the web service to see the purchased albums on the platform without access Token")
    public void iConsumeTheWebServiceToSeeThePurchasedAlbumsOnThePlatformWithoutAccessToken() {
        purchasedAlbumListActions.seePurchasedAlbumsWithoutToken(147);
    }

    @When("I consume the web service to see the purchased albums on the platform without user id")
    public void iConsumeTheWebServiceToSeeThePurchasedAlbumsOnThePlatformWithoutUserId() {
        purchasedAlbumListActions.seePurchasedAlbumsWithoutUserId(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @Then("the web service will respond with a validation error of invalid user id")
    public void theWebServiceWillRespondWithAValidationErrorOfInvalidUserId() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
    }
}
