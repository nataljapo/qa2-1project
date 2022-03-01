package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Weather;
import model.WeatherResponse;
import requesters.WeatherRequester;

import java.util.Map;

import static java.lang.Double.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city id is {long}")
    public void set_city_id(long cityId) {
        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.requestWeather(cityId);
    }

    @Then("coordinates are:")
    public void check_coordinates(Map<String, Double> params) {
        assertEquals(params.get("lon"), response.getCoord().getLon(), "Wrong Lon!");
        assertEquals(params.get("lat"), response.getCoord().getLat(), "Wrong Lat!");
    }

    @Then("weather is:")
    public void check_weather(Map<String, String> params) {
       Weather weather = response.getWeathers().get(0);
        assertEquals(parseLong(params.get("id")), weather.getId(), "Wrong Weather ID!");
        assertEquals(params.get("main"), weather.getMain(), "Wrong Weather Main!");
        assertEquals(params.get("description"), weather.getDescription(), "Wrong Weather Description!");
        assertEquals(params.get("icon"), weather.getIcon(), "Wrong Weather Icon!");
    }

    @Then("base is {string}")
    public void check_base(String base) {
        assertEquals(base, response.getBase(), "Wrong Base!");
    }

    @Then("main info is:")
    public void check_main_info(Map<String, String> params) {
        assertEquals(parseDouble(params.get("temp")), response.getMain().getTemp(), "Wrong Temp!");
        assertEquals(parseInt(params.get("pressure")), response.getMain().getPressure(), "Wrong Pressure!");
        assertEquals(parseInt(params.get("humidity")), response.getMain().getHumidity(), "Wrong Humidity!");
        assertEquals(parseDouble(params.get("temp_min")), response.getMain().getTemp_min(), "Wrong Temp_min!");
        assertEquals(parseDouble(params.get("temp_max")), response.getMain().getTemp_max(), "Wrong Temp_max!");
    }

    @Then("visibility is {int}")
    public void check_visibility(int visibility) {
        assertEquals(visibility, response.getVisibility(), "Wrong Visibility!");
    }

    @Then("wind is:")
    public void check_wind(Map<String, String> params) {
        assertEquals(parseDouble(params.get("speed")), response.getWind().getSpeed(), "Wrong Wind Speed!");
        assertEquals(parseInt(params.get("deg")), response.getWind().getDeg(), "Wrong Wind Deg!");
    }

    @Then("all is {int}")
    public void check_clouds(int all) {
        assertEquals(all, response.getClouds().getAll(), "Wrong All!");
    }

    @Then("dt is {long}")
    public void check_dt(long dt) {
        assertEquals(dt, response.getDt(), "Wrong Dt!");
    }

    @Then("sys is:")
    public void check_sys(Map<String, String> params) {
        assertEquals(parseInt(params.get("type")), response.getSys().getType(), "Wrong Type!");
        assertEquals(parseLong(params.get("id")), response.getSys().getId(), "Wrong Sys ID!");
        assertEquals(parseDouble(params.get("message")), response.getSys().getMessage(), "Wrong Message!");
        assertEquals(params.get("country"), response.getSys().getCountry(), "Wrong Country!");
        assertEquals(parseLong(params.get("sunrise")), response.getSys().getSunrise(), "Wrong Sunrise!");
        assertEquals(parseLong(params.get("sunset")), response.getSys().getSunset(), "Wrong Sunset!");
    }

    @Then("id is {long}")
    public void check_id(long id) {
        assertEquals(id, response.getId(), "Wrong ID!");
    }

    @Then("name is {string}")
    public void check_name(String name) {
        assertEquals(name, response.getName(), "Wrong Name!");
    }

    @Then("cod is {int}")
    public void check_cod(int cod) {
        assertEquals(cod, response.getCod(), "Wrong Cod!");
    }
}
