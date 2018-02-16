package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.config.DecoderConfig;
import io.restassured.http.Header;

import java.io.IOException;
import static  io.restassured.RestAssured.*;


public class RestYahoo {
    private String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22vinnitsia%2C%20ua%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";


    private String getJson() throws IOException {
         String json =
                given()
                    .urlEncodingEnabled(false)
                    .header(new Header("Content-Type", "application/json"))
                    .contentType(DecoderConfig.decoderConfig().defaultCharsetForContentType("UTF-8"))
                    .contentType("application/x-www-form-urlencoded")
                .when()
                    .get(url)
                .then()
                    .extract().asString();

         return json;
    }

    public String getTemp() {
        try {
            String json = getJson();
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            return jsonObject
                    .get("query").getAsJsonObject()
                    .get("results").getAsJsonObject()
                    .get("channel").getAsJsonObject()
                    .get("item").getAsJsonObject()
                    .get("condition").getAsJsonObject()
                    .get("temp").getAsString();
        } catch (IOException e) {
            return "";
        }
    }
}
