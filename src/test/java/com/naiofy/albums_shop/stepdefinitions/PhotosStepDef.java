package com.naiofy.albums_shop.stepdefinitions;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.actions.photo_album_list.PhotoAlbumListActions;
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

public class PhotosStepDef {
    @Steps
    CommonActions commonActions;

    @Steps
    CommonQuestions commonQuestions;

    @Steps
    PhotoAlbumListActions photoAlbumListActions;

    Map<String, String> loginInfo;

    @Given("I have an access Token of a regular user to see a photo album list")
    public void iHaveAnAccessTokenOfARegularUserToSeeAPhotoAlbumList() throws IOException {
        Map<String, String> regularUserInfo = FieldValues.in("templates/login/regular-user.properties");
        loginInfo = commonActions.loginAnUser(regularUserInfo);
    }

    @When("I consume the web service to see the photo list of an album on the platform")
    public void iConsumeTheWebServiceToSeeThePhotoListOfAnAlbumOnThePlatform() {
        photoAlbumListActions.searchPhotos(loginInfo.get(Dictionary.TOKEN_TAG), 1);
    }

    @Then("the web service responses with the photo album list of the album")
    public void theWebServiceResponsesWithThePhotoAlbumListOfTheAlbum() {
        commonQuestions.responseCodeIs(200, lastResponse());
    }

    @When("I consume the web service to see the photo list of an album on the platform without access token")
    public void iConsumeTheWebServiceToSeeThePhotoListOfAnAlbumOnThePlatformWithoutAccessToken() {
        photoAlbumListActions.searchPhotosWithoutToken(1);
    }

    @When("I consume the web service to see the photo list of an album on the platform with an invalid access Token")
    public void iConsumeTheWebServiceToSeeThePhotoListOfAnAlbumOnThePlatformWithAnInvalidAccessToken() {
        photoAlbumListActions.searchPhotos("abc", 1);
    }

    @When("I consume the web service to see the photo list of an album on the platform with an invalid album list")
    public void iConsumeTheWebServiceToSeeThePhotoListOfAnAlbumOnThePlatformWithAnInvalidAlbumList() {
        photoAlbumListActions.searchPhotosForInvalidAlbumId(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @When("I consume the web service to see the photo list of an album on the platform without album id")
    public void iConsumeTheWebServiceToSeeThePhotoListOfAnAlbumOnThePlatformWithoutAlbumId() {
        photoAlbumListActions.searchPhotosWithoutAlbumId(loginInfo.get(Dictionary.TOKEN_TAG));
    }

    @Then("the web service will respond with a validation error of invalid album id")
    public void theWebServiceWillRespondWithAValidationErrorOfInvalidAlbumId() {
        commonQuestions.responseCodeNotIs(200, lastResponse());
    }

    @When("I consume the web service to see the photo list for an non existent album on the platform")
    public void iConsumeTheWebServiceToSeeThePhotoListForAnNonExistentAlbumOnThePlatform() {
        photoAlbumListActions.searchPhotos(loginInfo.get(Dictionary.TOKEN_TAG), 1456789123);
    }
}
