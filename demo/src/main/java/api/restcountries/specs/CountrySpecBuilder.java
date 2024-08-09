package api.restcountries.specs;

import api.restcountries.constants.Route;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CountrySpecBuilder {
    public static RequestSpecification getAllCountriesRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(Route.BASE_URI)
                .setBasePath(Route.API_VERSION + Route.ALL_PATH)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getCountryByAlphaCodeRequestSpec(String alphaCode){
        return new RequestSpecBuilder()
                .setBaseUri(Route.BASE_URI)
                .setBasePath(Route.API_VERSION + Route.ALPHA_PATH + "/"+alphaCode)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification postNewCountry(String json){
        return new RequestSpecBuilder()
                .setBaseUri(Route.BASE_URI)
                .setBasePath(Route.API_VERSION+Route.NEW_COUNTRY_PATH+"/")
                .setBody(json)
                .log(LogDetail.ALL)
                .build();
    }
}
