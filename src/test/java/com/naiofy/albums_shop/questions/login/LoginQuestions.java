package com.naiofy.albums_shop.questions.login;

import com.naiofy.albums_shop.utils.Dictionary;
import net.thucydides.core.annotations.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginQuestions {

    @Step("Verify that response contains a credentials error message")
    public void verifyCredentialsErrorMessage(Response lastResponse) {
        String errorCode = lastResponse.getBody().path("errors[0].code").toString();
        assertThat(errorCode).isEqualTo(Dictionary.CREDENTIALS_ERROR_CODE);
        String errorMessage = lastResponse.getBody().path("errors[0].message").toString();
        assertThat(errorMessage).isEqualTo(Dictionary.CREDENTIALS_ERROR_MESSAGE);
    }

    @Step("Verify that response contains a invalid email error message")
    public void verifyInvalidEmail(Response lastResponse) {
        String errorCode = lastResponse.getBody().path("errors[0].code").toString();
        assertThat(errorCode).isEqualTo(Dictionary.VALIDATION_ERROR_CODE);
        String errorMessage = lastResponse.getBody().path("errors[0].message").toString();
        assertThat(errorMessage).isEqualTo(Dictionary.INVALID_EMAIL_ERROR_MESSAGE);
    }

    public void verifyNonAcceptedEmail(Response lastResponse) {
        String errorCode = lastResponse.getBody().path("errors[0].code").toString();
        assertThat(errorCode).isEqualTo(Dictionary.VALIDATION_ERROR_CODE);
        String errorMessage = lastResponse.getBody().path("errors[0].message").toString();
        assertThat(errorMessage).isEqualTo(Dictionary.NON_ACCEPTED_EMAIL_ERROR_MESSAGE);
    }

    public void verifyRequiredEmail(Response lastResponse) {
        String errorCode = lastResponse.getBody().path("errors[0].code").toString();
        assertThat(errorCode).isEqualTo(Dictionary.MISSING_DATA_ERROR_CODE);
        String errorMessage = lastResponse.getBody().path("errors[0].message").toString();
        assertThat(errorMessage).isEqualTo(Dictionary.MISSING_DATA_EMAIL_ERROR_MESSAGE);
    }

    public void verifyRequiredPassword(Response lastResponse) {
        String errorCode = lastResponse.getBody().path("errors[0].code").toString();
        assertThat(errorCode).isEqualTo(Dictionary.MISSING_DATA_ERROR_CODE);
        String errorMessage = lastResponse.getBody().path("errors[0].message").toString();
        assertThat(errorMessage).isEqualTo(Dictionary.MISSING_DATA_PASSWORD_ERROR_MESSAGE);
    }

    public void verifyRequiredEmailAndPassword(Response lastResponse) {
        String errorCode = lastResponse.getBody().path("errors[0].code").toString();
        assertThat(errorCode).isEqualTo(Dictionary.MISSING_DATA_ERROR_CODE);
        String errorMessage = lastResponse.getBody().path("errors[0].message").toString();
        assertThat(errorMessage).isEqualTo(Dictionary.MISSING_DATA_EMAIL_PASSWORD_ERROR_MESSAGE);
    }
}
