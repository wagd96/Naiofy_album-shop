package com.naiofy.albums_shop.utils;

public class Dictionary {
    public static final String EMAIL_TAG = "email";
    public static final String PASSWORD_TAG = "password";
    public static final String FIRST_NAME_TAG = "firstName";
    public static final String LAST_NAME_TAG = "lastName";
    public static final String ROLE_TAG = "role";
    public static final String TOKEN_TAG = "token";
    public static final String USER_ID_TAG = "user_id";
    public static final String PAGE_TAG = "page=%s";

    public static final String EMAIL_FORMAT = "%s.%s@wolox.com.ar";
    public static final String INVALID_EMAIL_FORMAT = "%s.%s@wolox.com";
    public static final String INCORRECT_EMAIL_FORMAT = "%s.%swolox.com.ar";

    public static final String REGULAR_USER_EMAIL = "regular@wolox.com.ar";
    public static final String USER_PASSWORD = "candidatoWolox2020";

    public static final String REGULAR_USER_FIRST_NAME = "regular";
    public static final String REGULAR_USER_LAST_NAME = "regular";

    public static final String REGULAR_ROLE = "regular";

    public static final String CREDENTIALS_ERROR_CODE = "authentication_error";
    public static final String VALIDATION_ERROR_CODE = "validation_error";
    public static final String MISSING_DATA_ERROR_CODE = "missing_data_error";

    public static final String CREDENTIALS_ERROR_MESSAGE = "Unable to authenticate credentials";
    public static final String INVALID_EMAIL_ERROR_MESSAGE = "Invalid email";
    public static final String NON_ACCEPTED_EMAIL_ERROR_MESSAGE = "The email must be @wolox.com.ar";
    public static final String MISSING_DATA_EMAIL_ERROR_MESSAGE = "missing required fields: email";
    public static final String MISSING_DATA_PASSWORD_ERROR_MESSAGE = "missing required fields: password";
    public static final String MISSING_DATA_EMAIL_PASSWORD_ERROR_MESSAGE = "missing required fields: email, password";
}
