package api.restcountries.api;

import api.restcountries.specs.CountrySpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CountryApi {

    public static Response getAllCountries(){
        return given(CountrySpecBuilder.getAllCountriesRequestSpec())
                .when()
                    .get()
                .then()
                    .spec(CountrySpecBuilder.getResponseSpec())
                    .extract().response();
    }

    public static Response getCountryByAlphaCode(String alphaCode){
        return given(CountrySpecBuilder.getCountryByAlphaCodeRequestSpec(alphaCode))
                .when()
                    .get()
                .then()
                    .spec(CountrySpecBuilder.getResponseSpec())
                    .extract().response();
    }

    public static Response postNewCountry(String body) {
        return given(CountrySpecBuilder.postNewCountry(body))
                .when()
                    .post()
                .then()
                    .spec(CountrySpecBuilder.getResponseSpec())
                    .extract().response();
    }
}
