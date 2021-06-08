package com.naiofy.albums_shop.actions;

import com.github.javafaker.Faker;
import com.naiofy.albums_shop.actions.login.LoginActions;
import com.naiofy.albums_shop.actions.registration.RegistrationActions;
import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.templates.MergeFrom;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class CommonActions {
    @Steps
    LoginActions loginActions;

    @Steps
    RegistrationActions registrationActions;

    /**
     * Get Request Specification for album shop ws
     *
     * @return RequestSpecification
     */
    public static RequestSpecification albumShopRequestSpec() {
        EnvironmentVariables environmentVariables = Injectors.getInjector()
                .getInstance(EnvironmentVariables.class);

        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("baseurl");

        return new RequestSpecBuilder().setBaseUri(baseUrl)
                .setContentType("application/json")
                .build();
    }

    @Step("Login an user into the platform")
    public Map<String, String> loginAnUser(Map<String, String> userData) {
        String body = MergeFrom.template("templates/login/loginBodyRequest.json")
                .withFieldsFrom(userData);
        loginActions.loginUser(body);
        String token = lastResponse().getHeader("Authorization");
        String userId = lastResponse().getBody().path("user_id").toString();

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put(Dictionary.TOKEN_TAG, token);
        loginInfo.put(Dictionary.USER_ID_TAG, userId);

        return loginInfo;
    }

    @Step("Generate data for new user")
    public Map<String, String> getNewUserData() {
        Map<String, String> dataForNewUser = new HashMap<>();
        Locale locale = new Locale("en", "us");
        Faker faker = new Faker(locale);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        dataForNewUser.put(Dictionary.EMAIL_TAG, String.format(Dictionary.EMAIL_FORMAT, firstName, lastName));
        dataForNewUser.put(Dictionary.PASSWORD_TAG, faker.internet().password(8, 20, true, true, true));
        dataForNewUser.put(Dictionary.FIRST_NAME_TAG, firstName);
        dataForNewUser.put(Dictionary.LAST_NAME_TAG, lastName);

        return dataForNewUser;
    }

    public Map<String, String> createAndLoginAUser() {
        Map<String, String> dataForNewUser = getNewUserData();
        String body = MergeFrom.template("templates/registration/registrationBodyRequest.json")
                .withFieldsFrom(dataForNewUser);
        registrationActions.registerRegularUser(body);
        return loginAnUser(dataForNewUser);
    }
}
