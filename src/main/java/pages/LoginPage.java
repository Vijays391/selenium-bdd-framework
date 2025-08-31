package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginBtn = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }
    public void enterUsername(String username) {
        //WebDriverWait wait=new WebDriverWait(driver, SECONDS(30));
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        //wait.until(ExpectedConditions.visibilityOf((WebElement) usernameField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
