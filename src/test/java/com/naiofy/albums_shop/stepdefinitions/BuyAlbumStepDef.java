package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.buy_album.BuyAlbumActions;
import com.naiofy.albums_shop.actions.purchased_album_list.PurchasedAlbumListActions;
import com.naiofy.albums_shop.actions.registration.RegistrationActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.questions.buy_album.BuyAlbumQuestions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.templates.FieldValues;
import com.naiofy.albums_shop.utils.templates.MergeFrom;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class BuyAlbumStepDef {
    @Steps
    BuyAlbumActions buyAlbumActions;

    @Steps
    CommonActions commonActions;

    @Steps
    CommonQuestions commonQuestions;

    @Steps
    BuyAlbumQuestions buyAlbumQuestions;

    Map<String, String> loginInfo;

    @Given("I have an access Token of a new regular user to buy an album")
    public void iHaveAnAccessTokenOfANewRegularUserToBuyAnAlbum() {
        loginInfo = commonActions.createAndLoginAUser();
    }

    @When("I consume the web service to buy an album on the platform")
    public void iConsumeTheWebServiceToBuyAnAlbumOnThePlatform() {
        buyAlbumActions.buyAnAlbum(loginInfo.get(Dictionary.TOKEN_TAG), 1);
    }

    @And("I bought the same album again")
    public void iBoughtTheSameAlbumAgain() {
        buyAlbumActions.buyAnAlbum(loginInfo.get(Dictionary.TOKEN_TAG), 1);
    }

    @Then("the web service responses with the info of the album")
    public void theWebServiceResponsesWithTheInfoOfTheAlbum() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }

    @And("the user will be see the album in their purchases")
    public void theUserWillBeSeeTheAlbumInTheirPurchases() {
        buyAlbumQuestions.verifyPurchase(loginInfo, 1, 1);
    }

    @When("I consume the web service to buy an album on the platform without access token")
    public void iConsumeTheWebServiceToBuyAnAlbumOnThePlatformWithoutAccessToken() {
        buyAlbumActions.buyAlbumWithoutToken(1);
    }

    @When("I consume the web service to buy an album on the platform with an invalid access Token")
    public void iConsumeTheWebServiceToBuyAnAlbumOnThePlatformWithAnInvalidAccessToken() {
        buyAlbumActions.buyAnAlbum("abc", 1);
    }

    @Given("I have an access Token of a regular user to buy an album")
    public void iHaveAnAccessTokenOfARegularUserToBuyAnAlbum() throws IOException {
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
    }

    @When("I consume the web service to buy an album on the platform with an invalid album id")
    public void iConsumeTheWebServiceToBuyAnAlbumOnThePlatformWithAnInvalidAlbumId() {
        buyAlbumActions.buyInvalidAlbumId(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @Then("the web service will respond with a not found error")
    public void theWebServiceWillRespondWithANotFoundError() {
        commonQuestions.responseCodeNotIs(200,lastResponse());
    }

    @When("I consume the web service to buy a non existent album on the platform")
    public void iConsumeTheWebServiceToBuyANonExistentAlbumOnThePlatform() {
        buyAlbumActions.buyAnAlbum(loginInfo.get(Dictionary.TOKEN_TAG),123456789);
    }

    @Then("the web service will respond with an error of invalid album id")
    public void theWebServiceWillRespondWithAnErrorOfInvalidAlbumId() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
    }
}
