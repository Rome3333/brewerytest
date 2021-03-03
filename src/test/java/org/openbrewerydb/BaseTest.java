package org.openbrewerydb;

import org.openbrewerydb.utils.properties.ConfigurationProperties;
import org.testng.annotations.BeforeClass;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public abstract class BaseTest {

    @Step
    @BeforeClass
    public void setup() {
        setRequestSpecification();
        setResponseSpecification();
    }

    protected abstract String getBasePath();

    private void setRequestSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigurationProperties.getBaseUri())
                .setBasePath(getBasePath())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }


    private void setResponseSpecification() {
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

}
