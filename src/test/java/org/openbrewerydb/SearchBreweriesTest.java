package org.openbrewerydb;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openbrewerydb.dto.Brewery;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SearchBreweriesTest extends BaseTest {

    private static final String UNDERSCORE = "_";

    private static final String basePath = "/breweries/";

    @Test
    public void searchBreweryByFullNameTest() {
        String searchQuery = "Diving Dog Brewhouse";

        Response response = given().
                                    pathParam("query", searchQuery)
                            .when().
                                    get(Endpoints.search)
                            .then().
                                    extract().response();

        List<Brewery> breweries = getBreweries(response);
        assertThat(breweries).as("Not one result is returned by exact name").hasSize(1);
        assertThat(breweries).as("Name doesn't equal to search query").extracting(Brewery::getName).allMatch(name -> name.equalsIgnoreCase(
                searchQuery));
    }

    @Test
    public void searchBreweryByPartiallyMatchedNameTest() {
        String searchQuery = "Sea Dog";

        Response response = given().
                                    pathParam("query", searchQuery)
                            .when().
                                    get(Endpoints.search)
                            .then().
                                    extract().response();

        List<Brewery> breweries = getBreweries(response);
        assertThat(breweries).as("Name doesn't contain search query").extracting(Brewery::getName).allMatch(name -> containsIgnoreCase(name, searchQuery));
    }

    @Test
    public void emptySearchResults() {
        String searchQuery = "search query which has no results";

        Response response = given().
                                    pathParam("query", searchQuery)
                            .when().
                                    get(Endpoints.search)
                            .then().
                                    extract().response();

        List<Brewery> breweries = getBreweries(response);
        assertThat(breweries).as("Response is not empty").isEmpty();
    }

    @Test
    public void searchResultsSchemaValidationTest() {
        String searchQuery = "Dog";

        given().
                pathParam("query", searchQuery)
        .when().
                get(Endpoints.search)
        .then().
                body(matchesJsonSchemaInClasspath("breweryJsonSchema.json"));
    }

    @Ignore("Test is failing when search query contains more than one underscore")
    @Test
    public void searchQueryWithUnderscoresTest() {
        String searchQuery = "Abnormal_Beer_Company";

        RestAssured.urlEncodingEnabled = false;

        Response response = given().
                                    pathParam("query", searchQuery)
                            .when().
                                    get(Endpoints.search)
                            .then().
                                    extract().response();

        List<Brewery> breweries = getBreweries(response);
        assertThat(breweries).as("Not one result is returned by exact name").hasSize(1);
        assertThat(breweries).as("Name doesn't equal to search query").extracting(Brewery::getName).allMatch(name -> name.equalsIgnoreCase(
                searchQuery.replace(UNDERSCORE, SPACE)));
    }

    private List<Brewery> getBreweries(Response response) {
        return response.jsonPath().getList(EMPTY, Brewery.class);
    }

    @Override
    protected String getBasePath() {
        return basePath;
    }
}
