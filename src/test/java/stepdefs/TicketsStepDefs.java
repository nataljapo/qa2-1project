package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import pageobject.pages.BaseFunc;
import requesters.ReservationsRequester;
import tickets.*;

import java.util.List;
import java.util.Map;

public class TicketsStepDefs {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private List<Reservation> response;
    private Reservation givenReservation = new Reservation();

    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage;
    private PersonalInfoPage infoPage;
    private SeatSelectionPage seatSelectionPage;
    private SuccessfulBookingPage successfulBookingPage;

    @Given("airports {string} and {string}")
    public void set_airports(String from, String to) {
        LOGGER.info("Setting airports");
        givenReservation.setFrom(from);
        givenReservation.setTo(to);
    }

    @Given("seat number is {int}")
    public void set_seat_nr(int seatNr) {
        LOGGER.info("Setting seat Nr.");
        givenReservation.setSeat(seatNr);
    }

    @Given("personal info is:")
    public void set_personal_info(Map<String, String> params) {
        LOGGER.info("Setting personal information");
        givenReservation.setName(params.get("first_name"));
        givenReservation.setSurname(params.get("last_name"));
        givenReservation.setDiscount(params.get("discount"));
        givenReservation.setAdultCount(Integer.parseInt(params.get("adults")));
        givenReservation.setChildren(Integer.parseInt(params.get("kids")));
        givenReservation.setBagCount(Integer.parseInt(params.get("bags")));
        givenReservation.setFullFlightDay(params.get("flight"));
    }

    @Given("home page opened")
    public void open_home_page() {
        LOGGER.info("Opening home page");
        baseFunc.openUrl("http://www.qaguru.lv:8089/tickets/");
        homePage = new HomePage(baseFunc);
    }

    @When("we are selecting airports")
    public void select_airports() {
        LOGGER.info("Selecting airports");
        homePage.selectAirports(givenReservation.getFrom(), givenReservation.getTo());
    }

    @When("pressing GoGoGo button")
    public void press_go_button() {
        LOGGER.info("Pressing GoGoGo button");
        homePage.pressGoBtn();
        infoPage = new PersonalInfoPage(baseFunc);
    }

    @When("we are filling in personal info")
    public void fill_personal_info_form() {
        LOGGER.info("Filling personal information");
        infoPage.fillInPersonalInfoForm(givenReservation);
    }

    @When("we are submitting form")
    public void submit_personal_info_form() {
        LOGGER.info("Submitting registration form");
        infoPage.submitForm();
    }

    @When("we are pressing Book btn")
    public void press_book_btn() {
        LOGGER.info("Pressing Book button");
        infoPage.book();
        seatSelectionPage = new SeatSelectionPage(baseFunc);
    }

    @When("selecting seat")
    public void select_seat() {
        LOGGER.info("Selecting seat");
        seatSelectionPage.selectSeat(givenReservation.getSeat());
    }

    @When("we are making final book")
    public void final_book() {
        LOGGER.info("Making final booking");
        seatSelectionPage.book();
        successfulBookingPage = new SuccessfulBookingPage(baseFunc);
    }

    @When("we are requesting all reservations")
    public  void request_reservations() throws JsonProcessingException {
        LOGGER.info("Requesting all reservations");
        ReservationsRequester requester = new ReservationsRequester();
        response = requester.getReservations();
    }

    @Then("selected airports appears")
    public void check_selected_airports() {
        LOGGER.info("Checking if correct airports appears");
        List<String> selectedAirports = infoPage.getSelectedAirports();
        Assertions.assertEquals(givenReservation.getFrom(), selectedAirports.get(0), "Wrong from!");
        Assertions.assertEquals(givenReservation.getTo(), selectedAirports.get(1), "Wrong to!");
    }

    @Then("passenger name is shown")
    public void check_passenger_name() {
        LOGGER.info("Checking if a correct passenger name appears");
        Assertions.assertEquals(givenReservation.getName(), infoPage.getPassengerName(), "Wrong name!");
    }

    @Then("correct seat selected")
    public  void check_seat_nr() {
        LOGGER.info("Checking if a correct seat selected");
        Assertions.assertEquals(givenReservation.getSeat(), seatSelectionPage.getSelectedSeatNr(), "Wrong seat Nr!");
    }

    @Then("successful message appears")
    public void check_success_msg() {
        LOGGER.info("Checking if a successful message appears");
        Assertions.assertTrue(successfulBookingPage.isSuccessMessagePresents(), "There is no success message!");
    }

    @Then("reservation exists in the list with correct data")
    public  void check_current_reservation() {
        LOGGER.info("Checking if a reservation exists in the list with correct data");
        Reservation actualReservation = null; //=null
        for (Reservation r : response) {
            if (r.getName().equals(givenReservation.getName())) {
                actualReservation = r;
                break;
            }
        }
        Assertions.assertNotNull(actualReservation, "There is no reservation!");
        Assertions.assertEquals(givenReservation.getSurname(), actualReservation.getSurname(), "Incorrect surname!");
        Assertions.assertEquals(givenReservation.getBagCount(), actualReservation.getBagCount(), "Incorrect bag count!");
        Assertions.assertEquals(givenReservation.getDiscount(), actualReservation.getDiscount(), "Incorrect discount!");
        Assertions.assertEquals(givenReservation.getChildren(), actualReservation.getChildren(), "Incorrect children count!");
        Assertions.assertEquals(givenReservation.getFlightDay(), actualReservation.getFlightDay(), "Incorrect flight date!");
        Assertions.assertEquals(givenReservation.getAdultCount(), actualReservation.getAdultCount(), "Incorrect adult count!");
        Assertions.assertEquals(givenReservation.getSeat(), actualReservation.getSeat(), "Incorrect seat!");
    }
}
