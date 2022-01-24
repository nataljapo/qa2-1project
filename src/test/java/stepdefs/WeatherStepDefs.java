package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class WeatherStepDefs {
    private String city;
    private String country;


    @Given("city name is {string}")
    public void city_name(String city) {
        this.city = city;

    }
    @Given("country is {string}")
    public void country_name(String country) {
        this.country = country;

    }
    @When("we are requesting weather data")
    public void request_weather() {

    }
    @Then("coordinates are:")
    public void check_coordinates(Map<String, Double> coordinates) {


    }
    @Then("weather is:")
    public void check_weather(DataTable dataTable) {
        List<Map<String, String>> weather = dataTable.asMaps();
    }

    @Then("base is {string}")
    public void check_base(String base) {

    }

    @Then("main info is:")
    public void check_main_info(Map<String, String> main) {

    }

    @Then("visibility is {int}")
    public void check_visibility(int visibility) {

    }
    @Then("wind is:")
    public void check_wind(Map<String, String> wind) {

    }

    @Then("clouds are:")
    public void check_clouds(Map<String, Integer> clouds) {

    }
    @Then("dt is {long}")
    public void check_dt(long dt) {

    }
    @Then("sys is:")
    public void check_sys(Map<String, String> sys) {

    }
    @Then("id is {long}")
    public void check_id(long id) {

    }
    @Then("name is {string}")
    public  void check_name(String name) {

    }
    @Then("cod is {int}")
    public void check_cod(int cod) {

    }


}
