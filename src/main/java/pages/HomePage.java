package pages;

import models.Reservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class HomePage {
    private final WebDriver driver;
    By firstNameInput = By.id("firstname");
    By lastNameInput = By.id("lastname");
    By totalPriceInput = By.id("totalprice");
    By depositPaidDropDown = By.id("depositpaid");
    By checkInInput = By.id("checkin");
    By checkOutInput = By.id("checkout");
    By saveButton = By.xpath("//input[@type='button'][contains(@value,'Save')]");
    By dataRowDivs = By.xpath("//div[@class='row'][@id]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void submitDetails(Reservation reservation) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int currentRows = driver.findElements(dataRowDivs).size();
        System.out.println("1. current row count:" + currentRows);
        enterFirstName(reservation.firstName);
        enterLastName(reservation.lastName);
        enterPrice(reservation.price);
        selectDeposit(reservation.isDeposit);
        setCheckInDate(reservation.checkIn);
        setCheckOutDate(reservation.checkOut);
        clickAndSave();
        System.out.println("2. current row count:" + driver.findElements(dataRowDivs).size());

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10, 1));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(dataRowDivs, currentRows));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("3. current row count:" + driver.findElements(dataRowDivs).size());
        System.out.println();
    }

    public void enterFirstName(String text) {
        driver.findElement(firstNameInput).sendKeys(text);
    }

    public void enterLastName(String text) {
        driver.findElement(lastNameInput).sendKeys(text);
    }

    public void enterPrice(String prices) {
        driver.findElement(totalPriceInput).sendKeys(prices);
    }

    public void selectDeposit(String isDeposit) {
        Select dropDown = new Select(driver.findElement(depositPaidDropDown));
        dropDown.selectByVisibleText(isDeposit);
    }

    public void setCheckInDate(String date) {
        driver.findElement(checkInInput).sendKeys(date);

    }

    public void setCheckOutDate(String date) {
        driver.findElement(checkOutInput).sendKeys(date);

    }

    public void clickAndSave() {
        driver.findElement(saveButton).click();
    }


    public boolean confirmBooking(Reservation reservation) {
        List<String> submittedData = new ArrayList<>();
        submittedData.add(reservation.firstName);
        submittedData.add(reservation.lastName);
        submittedData.add(reservation.price);
        submittedData.add(reservation.isDeposit);
        submittedData.add(reservation.checkIn);
        submittedData.add(reservation.checkOut);

        List<WebElement> dataRows = driver.findElements(dataRowDivs);
        for (WebElement row : dataRows) {
            List<WebElement> dataColumns = row.findElements(By.tagName("p"));
            List<String> colReadData = new ArrayList<>();
            for (WebElement col : dataColumns) {
                colReadData.add(col.getText());
            }
            if (compareLists(submittedData, colReadData)) {
                return true;
            }
        }
        return false;
    }

    private boolean compareLists(List<String> list1, List<String> list2){
        // if not the same size, lists are not equal
        if (list1.size() != list2.size()) {
            return false;
        }
        // create sorted copies to avoid modifying the original lists
        List<String> copy1 = new ArrayList<>(list1);
        List<String> copy2 = new ArrayList<>(list2);
        Collections.sort(copy1);
        Collections.sort(copy2);
        System.out.println(copy1);
        System.out.println(copy2);

        return copy1.equals(copy2);

    }

    public void deleteBooking(Reservation reservation) {
        List<String> submittedData = new ArrayList<>();
        submittedData.add(reservation.firstName);
        submittedData.add(reservation.lastName);
        submittedData.add(reservation.price);
        submittedData.add(reservation.isDeposit);
        submittedData.add(reservation.checkIn);
        submittedData.add(reservation.checkOut);
        int currentRows = driver.findElements(dataRowDivs).size();
        List<WebElement> dataRows = driver.findElements(dataRowDivs);
        for (WebElement row : dataRows) {
            List<WebElement> dataColumns = row.findElements(By.tagName("p"));
            List<String> colReadData = new ArrayList<>();
            for (WebElement col : dataColumns) {
                colReadData.add(col.getText());
            }
            if (compareLists(submittedData, colReadData)) {
                By deleteButton = By.xpath(".//input[@type='button'][@value='Delete']");
                row.findElement(deleteButton).click();
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(dataRowDivs, currentRows));
    }
}
