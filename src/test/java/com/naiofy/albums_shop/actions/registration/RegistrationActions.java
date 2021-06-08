package com.naiofy.albums_shop.actions.registration;

import com.github.javafaker.Faker;
import com.naiofy.albums_shop.actions.CommonActions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.WSEndpoints;
import com.naiofy.albums_shop.utils.templates.MergeFrom;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegistrationActions {

    @Step("Register a regular user into the platform")
    public Response registerRegularUser(String bodyRequest) {
        return SerenityRest.given().spec(CommonActions.albumShopRequestSpec())
                .basePath(WSEndpoints.REGISTRATION.getBasePath())
                .body(bodyRequest)
                .post().then().extract().response();
    }

    @Step("Generate data for new user with incorrect format email")
    public String generateAnUserWithIncorrectFormatEmail() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.INCORRECT_EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 10, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user with invalid format email")
    public String generateAnUserWithInvalidEmail() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.INVALID_EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 10, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user with incorrect firstName")
    public String generateAnUserWithIncorrectFirstName() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 10, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName.concat("1"));
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user with incorrect lastName")
    public String generateAnUserWithIncorrectLastName() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 10, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName.concat("1"));

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user with incorrect firstName and lastName")
    public String generateAnUserWithIncorrectFirstAndLastName() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 10, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName.concat("1"));
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName.concat("1"));

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user with incorrect minimum password length")
    public String generateAnUserWithIncorrectMinimumPasswordLength() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(5, 7, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user with only numbers in the password")
    public String generateAnUserWithOnlyNumbersInThePassword() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, "12345678");
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

    @Step("Generate data for new user without numbers in the password")
    public String generateAnUserWithoutNumbersInThePassword() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 10, true, true, false));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
    }

}
