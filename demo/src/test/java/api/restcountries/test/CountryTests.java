package api.restcountries.test;

import api.restcountries.api.CountryApi;
import api.restcountries.constants.enums.StatusCode;
import api.restcountries.data.CountryDTO;
import io.restassured.response.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CountryTests {
    @Test
    public void testGetAllCountries(){
        Response response = CountryApi.getAllCountries();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));
        assertThat(response.jsonPath().getList("$").size(), greaterThan(100));

    }

    @DataProvider(name = "countryAlphaCode")
    public Object[][] alphaCodes() {
        return new Object[] [] { {"US"},{"DE"}, {"GB"}};
    }

    @Test(dataProvider = "countryAlphaCode")
    public void testGetCountryByAlphaCode(String alphaCode) {
        Response response = CountryApi.getCountryByAlphaCode(alphaCode);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));
        assertThat(response.jsonPath().getList("$").size(), equalTo(1));
        assertThat(response.getBody().jsonPath().get("[0].cca2"), equalTo(alphaCode));
    }

    @Test
    public void testGetInvalidAlphaCode() {
        Response response = CountryApi.getCountryByAlphaCode("XX");
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_404.code));
        assertThat(response.getBody().jsonPath().get("message"), equalToIgnoringCase(StatusCode.CODE_404.msg));
    }

    @Test
    public void testPostNewCountry() {
        String body = CountryDTO.getNewCountryJson().toString();
        Response response = CountryApi.postNewCountry(body);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_404.code));
        assertThat(response.getBody().jsonPath().get("message"), equalToIgnoringCase("Page Not Found"));
    }
}
