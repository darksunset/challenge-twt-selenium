package api.restcountries.data;

import org.json.JSONObject;

public class CountryDTO {
    public static JSONObject getNewCountryJson(){
        JSONObject body = new JSONObject();
        body.put("name", "Test Country");
        body.put("alpha2_code", "TC");
        body.put("alpha3_code", "TCY");
        return body;
    }
}
