package com.naiofy.albums_shop.actions.login;

import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.WSEndpoints;
import com.naiofy.albums_shop.utils.templates.MergeFrom;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

public class LoginActions {

    @Step("Login user")
    public Response loginUser(String bodyRequest) {

        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(WSEndpoints.LOGIN.getBasePath())
                .body(bodyRequest)
                .post().then().extract().response();
    }

    @Step("Get data for login a non-existent user on the platform")
    public String getBodyFromNonExistentUser() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, "noExiste@wolox.com.ar");
        map.put(Dictionary.PASSWORD_TAG, Dictionary.USER_PASSWORD);

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }

    @Step("Get body request for an user with incorrect password")
    public String getUserWithIncorrectPassword() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, Dictionary.REGULAR_USER_EMAIL);
        map.put(Dictionary.PASSWORD_TAG, "contraIncorrecta");

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }

    @Step("Get body request for an user with incorrect email format")
    public String getUserWithIncorrectEmailFormat() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, "regularwolox.com.ar");
        map.put(Dictionary.PASSWORD_TAG, Dictionary.USER_PASSWORD);

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }

    @Step("Get body request for an user with non accepted email format")
    public String getUserWithNonAcceptedEmail() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, "regular@wolox.com");
        map.put(Dictionary.PASSWORD_TAG, Dictionary.USER_PASSWORD);

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }

    @Step("Get body request for an user without email")
    public String getUserWithoutEmail() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, "");
        map.put(Dictionary.PASSWORD_TAG, Dictionary.USER_PASSWORD);

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }

    @Step("Get body request for an user without password")
    public String getUserWithoutPassword() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, Dictionary.REGULAR_USER_EMAIL);
        map.put(Dictionary.PASSWORD_TAG, "");

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }

    @Step("Get body request for an user without email and password")
    public String getUserWithoutEmailAndPassword() {
        Map<String, String> map = new HashMap<>();
        map.put(Dictionary.EMAIL_TAG, "");
        map.put(Dictionary.PASSWORD_TAG, "");

        return MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(map);
    }
}
