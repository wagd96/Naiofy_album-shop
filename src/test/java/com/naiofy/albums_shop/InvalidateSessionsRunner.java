package com.naiofy.albums_shop;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/features/invalidate_sessions.feature",
        glue = "com/naiofy/albums_shop/stepdefinitions",
        snippets = SnippetType.CAMELCASE)
public class InvalidateSessionsRunner {
}
