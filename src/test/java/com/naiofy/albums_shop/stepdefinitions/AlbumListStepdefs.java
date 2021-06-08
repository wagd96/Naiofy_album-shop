package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.album_list.AlbumListActions;
import com.naiofy.albums_shop.questions.CommonQuestions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.templates.FieldValues;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class AlbumListStepdefs {
    @Steps
    AlbumListActions albumListActions;

    @Steps
    CommonActions commonActions;

    @Steps
    CommonQuestions commonQuestions;

    Map<String, String> loginInfo;

    @Given("I have an access Token of a regular user to search albums")
    public void iHaveAnAccessTokenOfARegularUserToSearchAlbums() throws IOException {
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
    }

    @When("I consume the web service to see the album list on the platform")
    public void iConsumeTheWebServiceToSeeTheAlbumListOnThePlatform() {
        albumListActions.searchAlbums(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @Then("the web service responses with the list of albums")
    public void theWebServiceResponsesWithTheListOfAlbums() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }

    @When("I consume the web service to see the album list on the platform without access token")
    public void iConsumeTheWebServiceToSeeTheAlbumListOnThePlatformWithoutAccessToken() {
        albumListActions.searchAlbumsWithoutToken();
    }

    @When("I consume the web service to see the album list on the platform with an invalid access Token")
    public void iConsumeTheWebServiceToSeeTheAlbumListOnThePlatformWithAnInvalidAccessToken() {
        albumListActions.searchAlbums("abcd");
    }
}
