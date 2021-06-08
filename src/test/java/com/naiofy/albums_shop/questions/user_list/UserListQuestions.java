package com.naiofy.albums_shop.questions.user_list;

import com.naiofy.albums_shop.utils.Dictionary;
import com.naiofy.albums_shop.utils.LastResponseTransformation;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

public class UserListQuestions {
    @Step("Validate the result of search an user")
    public void validateTheUserSearchResult(Map<String, String> userExpected, Response lastResponse) {
        Map<String, String> dataUser = new HashMap<>();
        dataUser.put("first_name", userExpected.get(Dictionary.FIRST_NAME_TAG));
        dataUser.put("last_name", userExpected.get(Dictionary.LAST_NAME_TAG));
        dataUser.put("email", userExpected.get(Dictionary.EMAIL_TAG));
        dataUser.put(Dictionary.ROLE_TAG, Dictionary.REGULAR_ROLE);

        assertThat(LastResponseTransformation.returned()).containsAllEntriesOf(dataUser);
    }
}
