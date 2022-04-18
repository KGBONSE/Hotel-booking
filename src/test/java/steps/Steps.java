package steps;

import base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import models.Reservation;

import java.time.Duration;
import java.util.List;

public class Steps extends BaseUtil {
    private final BaseUtil baseUtil;

    public Steps(BaseUtil baseUtil) {
        this.baseUtil = baseUtil;
    }

    @Given("I am on the hotel booking page")
    public void i_am_on_the_hotel_booking_page() {
        baseUtil.driver.navigate().to("http://hotel-test.equalexperts.io/");
        baseUtil.homePage = new HomePage(baseUtil.driver);
    }

    @When("I submit my details")
    public void i_submit_my_details(DataTable bookingDetails) {
        List<List<String>> data = bookingDetails.asLists();
        baseUtil.reservation = new Reservation(data.get(1).get(0),
                data.get(1).get(1),
                data.get(1).get(2),
                data.get(1).get(3),
                data.get(1).get(4),
                data.get(1).get(5));
        baseUtil.homePage.submitDetails(baseUtil.reservation);
    }

    @Then("my booking is registered")
    public void my_booking_is_registered() {
        boolean expected = baseUtil.homePage.confirmBooking(baseUtil.reservation);
        Assert.assertTrue("Booking not found", expected);
    }

    @Given("I have submitted my details")
    public void i_have_submitted_my_details(DataTable bookingDetails) {
        i_submit_my_details(bookingDetails);
        my_booking_is_registered();
    }

    @When("I delete my booking")
    public void i_delete_my_booking() {
        baseUtil.homePage.deleteBooking(baseUtil.reservation);
    }

    @Then("my booking is not saved")
    @Then("my booking is removed from the list")
    public void my_booking_is_removed_from_the_list() {
        boolean expected = baseUtil.homePage.confirmBooking(baseUtil.reservation);
        Assert.assertFalse("Booking not deleted", expected);
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        baseUtil.driver = new ChromeDriver();
        baseUtil.driver.manage().window().maximize();
        baseUtil.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        baseUtil.driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        baseUtil.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @After
    public void tearDown() {
        baseUtil.driver.quit();
    }
}
