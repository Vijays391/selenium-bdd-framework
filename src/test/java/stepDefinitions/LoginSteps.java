package stepDefinitions;

import io.cucumber.java.en.*;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.concurrent.TimeUnit;

public class LoginSteps {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
    }
    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
    }
    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }
    @Then("user should be redirected to home page")
    public void user_should_be_redirected_to_home_page() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("Current url: " + driver.getCurrentUrl());
        org.testng.Assert.assertTrue(loginPage.getCurrentUrl().contains("dashboard"));
    }
}
